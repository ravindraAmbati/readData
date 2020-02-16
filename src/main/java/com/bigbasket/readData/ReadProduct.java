package com.bigbasket.readData;

import com.bigbasket.readData.component.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@Service
public class ReadProduct {

    private static final String uri = "https://www.bigbasket.com/pd/";

    public Product readProduct(long productId) throws IOException {
        long start = System.currentTimeMillis();
        Product product = readData(String.valueOf(productId));
        System.out.println(getTimeTaken(System.currentTimeMillis()-start)+"Time Taken to read Product"+product.toString());
        return product;
    }

    private String getTimeTaken(long time){
        String suffix = " ms";
        if(time>1000){
            time = time/1000;
            suffix = " s";
            if(time>60){
                time = time/60;
                suffix = " m";
                if(time>60){
                    time = time/60;
                    suffix = " h";
                    if(time>24){
                        time = time/24;
                        suffix = " d";
                    }
                }
            }
        }
        return time+suffix;
    }
    private Product readData(String productId) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(uri+productId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String in;
                while((in=br.readLine())!=null){
                    sb.append(in);
                }
            }
        } catch (IOException e) {
            return new Product(String.valueOf(productId));
        }
        return extractProductDetails(sb,productId);
    }

    private Product extractProductDetails(StringBuffer sb, String productId){
        String product = "";
        String productName = "";
        String quantity = "";
        String price = "";
        try{
            //product
            StringBuffer actual = new StringBuffer();
            int begin = sb.indexOf("[{\"@context\":\"http://schema.org/\",\"@type\":\"Product\",\"name\":\"");
            actual.append(sb.substring(begin+60,begin+100));
            int end = actual.indexOf("\"");

            //product Name
            product = actual.substring(0,end);
            end = product.indexOf(",");
            productName = product.substring(0,end);

            //quantity
            begin = product.indexOf(",")+1;
            quantity = product.substring(begin,begin+7);
            end = quantity.indexOf("g")+1;
            quantity = quantity.substring(0,end);

            //price
            begin = sb.indexOf("\"sku\":"+productId);
            actual = new StringBuffer();
            actual.append(sb.substring(begin+72,begin+100));
            end = actual.indexOf("\"");
            price = actual.substring(0,end);
        } catch (Exception e){
            return new Product(String.valueOf(productId));
        }
        Product p = new Product(productId);
        if(!StringUtils.isEmpty(productName)){
            p.setName(productName);
            p.setQuantity(quantity);
            p.setPrice(Double.parseDouble(price));
        }
        return p;
    }
}
