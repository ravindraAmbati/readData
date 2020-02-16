package com.bigbasket.readData.repo.mapper;

import com.bigbasket.readData.component.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setProdId(rs.getString("prodId"));
        product.setName(rs.getString("name"));
        product.setQuantity(rs.getString("quantity"));
        product.setPrice(rs.getDouble("price"));
        product.setTimestamp(rs.getTimestamp("timeStamp"));

        return product;
    }
}
