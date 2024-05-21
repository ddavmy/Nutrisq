package com.project.nutrisq.webclient.product.dto;

import com.project.nutrisq.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {
    private Integer count;
    private Integer page;
    private Integer page_count;
    private Integer page_size;
    private Integer skip;
    private List<Product> products;

    @Data
    public static class SingleProductDetailsDto {
        private List<Product> products;
    }
}