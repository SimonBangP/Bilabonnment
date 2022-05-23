package com.example.bilabonnmenteksamensprojekt.repositories.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CarBrandModelRepository {

    @Autowired
    JdbcTemplate template;

    public String getModelById(int id) {
        String sql = "SELECT Model FROM car_models WHERE ModelId = ?";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);

        List<String> models = template.query(sql, rowMapper, id);

        if (models.size() <= 0) {
            return null;
        }
        else {
            return models.get(0);
        }
    }

    public boolean modelExists(String brand, String model) {
        String sql = "SELECT COUNT(*) FROM car_models WHERE Model = ? AND BrandID = ?";

        int brandId = getBrandId(brand);
        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, model, brandId).get(0) >= 1;
    }

    public int getModelId(String brand, String model) {
        String sql = "SELECT ModelId FROM car_models WHERE Model = ? AND BrandID = ?";

        int brandId = getBrandId(brand);
        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, model, brandId);

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public int insertNewModel(String brand, String model) {
        String sql = "INSERT INTO car_models VALUES (DEFAULT, ?, ?)";

        int brandId = insertNewBrand(brand);
        template.update(sql, brandId, model);

        return getModelId(brand, model);
    }

    public String getBrandById(int id) {
        String sql = "SELECT Brand FROM car_brands WHERE BrandId = ?";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);

        List<String> brands = template.query(sql, rowMapper, id);

        if (brands.size() <= 0) {
            return null;
        }
        else {
            return brands.get(0);
        }
    }

    public int getBrandId(String brand) {
        String sql = "SELECT BrandId FROM car_brands WHERE Brand = ?";

        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, brand);

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public boolean brandExists(String brand) {
        String sql = "SELECT COUNT(*) FROM car_brands WHERE Brand = ?";

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, brand).get(0) >= 1;
    }

    public int insertNewBrand(String brand) {
        if (brandExists(brand)) {
            return getBrandId(brand);
        }
        else {
            String sql = "INSERT INTO car_brands VALUES (DEFAULT, ?)";

            template.update(sql, brand);
            return getBrandId(brand);
        }
    }
}
