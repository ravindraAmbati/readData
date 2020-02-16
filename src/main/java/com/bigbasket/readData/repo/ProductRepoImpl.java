package com.bigbasket.readData.repo;

import com.bigbasket.readData.component.Product;
import com.bigbasket.readData.repo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> readAll() {
        return jdbcTemplate.query(readAllSQL, new ProductMapper());
    }

    @Override
    public boolean save(Product product) {
        return 1 == jdbcTemplate.update(saveSQL,product.getProdId(),product.getName(),product.getQuantity(),product.getPrice());
    }

    @Override
    public String saveAndReturnId(Product product) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(saveAndReturnIdSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProdId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            return statement;
        }, holder);

        return holder.getKey().toString();
    }

    @Override
    public List<Product> create() {
        jdbcTemplate.execute(createSQL);
        jdbcTemplate.execute(insertTestDataSQL);
        return jdbcTemplate.query(selectTestDataSQL,new ProductMapper());
    }

    @Override
    public String drop() {
        jdbcTemplate.execute(dropSQL);
        return drop;
    }
}
