package com.bigbasket.readData.repo;

import com.bigbasket.readData.component.Product;
import com.bigbasket.readData.repo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> readAll() {
        return jdbcTemplate.query(readAllSQL, new ProductMapper());
    }

    @Override
    public boolean save(Product product) {
        int i = jdbcTemplate.update(saveSQL, product.getProdId(), product.getName(), product.getQuantity(), product.getPrice());
        if (1 == i) {
            try {
                insertStats(product);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String saveAndReturnId(Product product) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        int i = jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(saveAndReturnIdSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProdId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            return statement;
        }, holder);
        if (1 == i && null != holder.getKey().toString() && !holder.getKey().toString().isEmpty()) {
            try {
                insertStats(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return holder.getKey().toString();
    }

    private void insertStats(Product product) throws Exception {

        int i = jdbcTemplate.update(
                saveProdPrices,
                product.getProdId(), product.getPrice(), getMaxPrice(product.getProdId()),
                getMinPrice(product.getProdId()), getAvgPrice(product.getProdId()),
                getStdDevPrice(product.getProdId()), getVarPrice(product.getProdId()));

        if (1 != i) {
            throw new Exception("Record failed to updated" + product.toString());
        }
    }

    @Override
    public List<Product> create() {
        jdbcTemplate.execute(createSQL);
        jdbcTemplate.execute(insertTestDataSQL);
        return jdbcTemplate.query(selectTestDataSQL, new ProductMapper());
    }

    @Override
    public String drop() {
        jdbcTemplate.execute(dropSQL);
        return drop;
    }

    public List<Product> getProd(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForList(readProd, paramMap, Product.class);
    }

    public Double getMaxPrice(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForObject(maxPrice, paramMap, Double.class);
    }

    public Double getMinPrice(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForObject(minPrice, paramMap, Double.class);
    }

    public Double getAvgPrice(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForObject(avgPrice, paramMap, Double.class);
    }

    public Double getStdDevPrice(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForObject(stdDevPrice, paramMap, Double.class);
    }

    public Double getVarPrice(String prodId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("prodId", prodId);
        return namedParameterJdbcTemplate.queryForObject(varPrice, paramMap, Double.class);
    }

}
