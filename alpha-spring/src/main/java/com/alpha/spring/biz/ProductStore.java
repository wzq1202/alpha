package com.alpha.spring.biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssports on 2018/4/9.
 */
public class ProductStore {
    
    private static Map<String,Product> products = new HashMap<>();
    static {
        Product p1 = new Product("1", "5401", "18/19英超全季通球票", new BigDecimal(388));
        Product p2 = new Product("2", "6186", "月包", new BigDecimal(24));
        Product p3 = new Product("2", "6619", "季包", new BigDecimal(88));
        Product p4 = new Product("3", "5403", "阿森纳死忠通球票", new BigDecimal(158));
        Product p5 = new Product("3", "5404", "莱斯特城死忠通球票", new BigDecimal(158));
        Product p6 = new Product("3", "5405", "热刺死忠通球票", new BigDecimal(158));
        products.put("5401",p1);
        products.put("6186",p2);
        products.put("6619",p3);
        products.put("5403",p4);
        products.put("5404",p5);
        products.put("5405",p6);
    }

    public static Product getProduct(String productId) {
       return products.get(productId);
    }


    public static class Product {
        private String packageId;
        private String productId;
        private String productName;
        private BigDecimal price;
        public Product(String packageId, String productId, String productName, BigDecimal price) {
            this.packageId = packageId;
            this.productId = productId;
            this.productName = productName;
            this.price = price;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

}
