package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarSpecificationService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CarSpecificationService carSpecificationService;

    public List<Car> getCars(){
        String sql = "SELECT * FROM view_cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

       return template.query(sql, (ResultSet rs, int rowNum) -> {
            Car foundCar = rowMapper.mapRow(rs, rowNum);

            foundCar.setCarSpecification(carSpecificationService.getSpecificationById(rs.getInt(2)));

            return foundCar;
        });
    }

    public Car getCarFromId(int id) {
        String sql = "SELECT * FROM cars WHERE CarId = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Car foundCar = rowMapper.mapRow(rs, rowNum);

            foundCar.setCarSpecification(carSpecificationService .getSpecificationById(rs.getInt(2)));

            return foundCar;
        }, id).get(0);
    }

    public int insertNewCar (Car car) {
        String sql = "INSERT INTO cars VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        int specificationId = carSpecificationService.insertCarSpecification(car.getCarSpecification());

        template.update(sql, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription());
        return getCarId(car);
    }

    public int getCarId(Car car) {
        String sql = "SELECT CarId FROM cars WHERE SpecificationId = ? AND Price = ? AND InsuranceIncluded = ? AND OwnersFeeIncluded = ? AND ShortDescription = ?";
        int specificationId = carSpecificationService.getCarSpecificationId(car.getCarSpecification());

        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription());

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public boolean carExists(Car car) {
        String sql = "SELECT COUNT(*) FROM cars WHERE SpecificationId = ? AND Price = ? AND InsuranceIncluded = ? AND OwnersFeeIncluded = ? AND ShortDescription = ?";
        int specificationId = carSpecificationService.getCarSpecificationId(car.getCarSpecification());

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription()).get(0) >= 1;
    }

    public void updateCarById (int id, Car car) {
        String sql = "Update cars SET SpecificationId = ?, Price = ?, InsuranceIncluded = ?, OwnersFeeIncluded = ?, ShortDescription = ?";

        int specificationId = carSpecificationService.insertCarSpecification(car.getCarSpecification());
        template.update(sql, specificationId, car.getPrice(), car.isInsuranceIncluded(), car.isOwnersFeeIncluded(), car.getShortDescription());
    }

    public void removeCarById(int id) {
        String sql = "UPDATE cars SET Removed = TRUE WHERE CarId = ?";

        template.update(sql, id);
    }

}
