package com.bigbasket.readData;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class Fresho {

    private static final String uri = "https://www.bigbasket.com/pd/";
    private static final List<Product> notValidProducts = new ArrayList<>();
    private static final List<Product> validProducts = new ArrayList<>();
    private static final long startProductId = 10000000;
    private static final long endProductId = 10001030;

    public String readData() throws IOException {
        long init = System.currentTimeMillis();
        String rawData = "";
        long prodId = startProductId;
        while(prodId<endProductId){
            long start = System.currentTimeMillis();
            readData(String.valueOf(prodId));
            prodId++;
//            System.out.println(rawData);
//            System.out.println("    Time taken: "+getTimeTaken ((System.currentTimeMillis())-start));
        }
        System.out.println(validProducts.toString());
        System.out.println("Total Time taken: "+getTimeTaken ((System.currentTimeMillis())-init));
        System.out.println(notValidProducts.toString());
        return rawData;
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
    private void readData(String productId) {
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
            notValidProducts.add(new Product(String.valueOf(productId)));
//            System.out.println("Failed to retrieve data");
//            return "### NO PRODUCT FOR PRODUCT ID"+productId+ "###";
        }
        extractProductDetails(sb,productId);
    }

    private void extractProductDetails(StringBuffer sb, String productId){
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
            notValidProducts.add(new Product(String.valueOf(productId)));
//            System.out.println("Failed to retrieve data");
//            return "### NO PRODUCT FOR PRODUCT ID"+productId+ "###";
        }
        Product p = new Product(productId);
        if(!StringUtils.isEmpty(productName)){
            p.setName(productName);
            p.setQuantity(quantity);
            p.setPrice(price);
            validProducts.add(p);
        } else {
            notValidProducts.add(p);
        }

    }
}
