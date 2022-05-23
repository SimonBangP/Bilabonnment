package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarBrandModelService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CarSpecificationRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    CarEngineService carEngineService;

    @Autowired
    CarBrandModelService carBrandModelService;

    public CarSpecification getSpecificationById(int id) {
        String sql = "SELECT * FROM car_specifications WHERE SpecificationId = ?";
        RowMapper<CarSpecification> rowMapper = new BeanPropertyRowMapper<>(CarSpecification.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            CarSpecification specification = rowMapper.mapRow(rs, rowNum);

            String query = "SELECT Model, BrandId FROM car_models WHERE ModelId = ?";
            int modelId = rs.getInt(2);

            int brandId = template.query(query, (ResultSet models, int modelsRowNum) -> {
                specification.setModel(models.getString(1));
                return models.getInt(2);
            }, modelId).get(0);

            query = "SELECT Brand FROM car_brands WHERE BrandId = ?";

            template.query(query, (ResultSet brands, int brandsRowNum) -> {
                specification.setBrand(brands.getString(1));
                return specification;
            }, brandId);

            specification.setCarEngine(carEngineService.getCarEngineById(rs.getInt(3)));

            return specification;
        }, id).get(0);
    }

    public boolean carSpecificationExists(CarSpecification carSpecification) {
        String sql = "SELECT COUNT(*) FROM car_specifications WHERE ModelId = ? AND EngineId = ? AND Variant = ? AND Color = ?";
        int modelId = carBrandModelService.getModelId(carSpecification.getBrand(), carSpecification.getModel());
        int engineId = carEngineService.getEngineId(carSpecification.getCarEngine());

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, modelId, engineId, carSpecification.getVariant(), carSpecification.getColor()).get(0) >= 1;
    }

    public int getCarSpecificationId(CarSpecification carSpecification) {
        String sql = "SELECT SpecificationId FROM car_specifications WHERE ModelId = ? AND EngineId = ? AND Variant = ? AND Color = ?";
        int modelId = carBrandModelService.getModelId(carSpecification.getBrand(), carSpecification.getModel());
        int engineId = carEngineService.getEngineId(carSpecification.getCarEngine());

        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, modelId, engineId, carSpecification.getVariant(), carSpecification.getColor());

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public int insertNewCarSpecification(CarSpecification carSpecification) {
        String sql = "INSERT INTO car_specifications VALUES (DEFAULT, ?, ?, ?, ?)";

        int modelId = carBrandModelService.insertModel(carSpecification.getBrand(), carSpecification.getModel());
        int engineId = carEngineService.insertCarEngine(carSpecification.getCarEngine());

        template.update(sql, modelId, engineId, carSpecification.getVariant(), carSpecification.getColor());
        return getCarSpecificationId(carSpecification);
    }
}
