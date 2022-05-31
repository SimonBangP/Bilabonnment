package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.services.CustomerService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarSpecificationService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CarSpecificationService carSpecificationService;

    @Autowired
    CustomerService customerService;

    private Car mapRow(RowMapper<Car> rowMapper, ResultSet rs, int rowNum) throws SQLException {
        Car foundCar = rowMapper.mapRow(rs, rowNum);

        foundCar.setCarSpecification(carSpecificationService.getSpecificationById(rs.getInt(2)));
        foundCar.setCustomer(customerService.getCustomerById(rs.getInt(7)));

        return foundCar;
    }

    public List<Car> getCars(){
        String sql = "SELECT * FROM view_cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

       return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        });
    }

    public Car getCarFromId(int id) {
        String sql = "SELECT * FROM cars WHERE CarId = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

        List<Car> cars = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        }, id);

        if (cars.size() <= 0) {
            return null;
        }
        else {
            return cars.get(0);
        }
    }

    public int insertNewCar (Car car) {
        String sql = "INSERT INTO cars VALUES (DEFAULT, ?, ?, ?, ?, ?, false)";
        int specificationId = carSpecificationService.insertCarSpecification(car.getCarSpecification());

        template.update(sql, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription());
        return getCarId(car);
    }

    public int getCarId(Car car) {
        String sql = "SELECT CarId FROM cars WHERE SpecificationId = ? AND Price = ? AND InsuranceIncluded = ? AND OwnersFeeIncluded = ? AND ShortDescription = ?";
        int specificationId = carSpecificationService.getCarSpecificationId(car.getCarSpecification());

        return template.queryForObject(sql, Integer.class, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription());
    }

    public boolean carExists(Car car) {
        String sql = "SELECT COUNT(*) FROM cars WHERE SpecificationId = ? AND Price = ? AND InsuranceIncluded = ? AND OwnersFeeIncluded = ? AND ShortDescription = ?";
        int specificationId = carSpecificationService.getCarSpecificationId(car.getCarSpecification());

        return template.queryForObject(sql, Integer.class, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription()) >= 1;
    }

    public void updateCarById (int id, Car car) {
        String sql = "Update cars SET SpecificationId = ?, Price = ?, InsuranceIncluded = ?, OwnersFeeIncluded = ?, ShortDescription = ? WHERE CarId = ?";

        int specificationId = carSpecificationService.insertCarSpecification(car.getCarSpecification());
        template.update(sql, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription(), id);
    }

    public void removeCarById(int id) {
        String sql = "UPDATE cars SET Removed = TRUE WHERE CarId = ?";

        template.update(sql, id);
    }

    public int carsInUse (){
        String sql = "SELECT COUNT(Status) FROM view_cars WHERE Status = 'Bilen er i brug'";
        return template.queryForObject(sql, Integer.class);
    }

    public int getTotalPrice(){
        String sql = "SELECT SUM(Price) FROM view_cars WHERE Status = 'Bilen er i brug'";

        return template.queryForObject(sql, Integer.class);
    }

    public int getCount() {
        String sql = "SELECT COUNT(CarId) FROM view_cars";

        return template.queryForObject(sql, Integer.class);
    }

    public int getReturnCar (){
        String sql = "SELECT COUNT(Status) FROM view_cars WHERE Status = 'Bilen er blevet planlagt til returnering idag'";
        return template.queryForObject(sql, Integer.class);
    }

    public int getCountWithWhereClause(String whereClause) {
        String sql = "SELECT COUNT(CarId) FROM view_cars WHERE " + whereClause;

        return template.queryForObject(sql, Integer.class);
    }

    public List<Car> getCarsInStorage (){
        String sql = "SELECT * FROM view_cars WHERE Status = 'Bilen er på lager'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        });
    }
}
