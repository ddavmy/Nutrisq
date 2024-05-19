package com.project.nutrisq.webclient.product.dto;

import com.project.nutrisq.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailsDto {
    private Integer count;
    private Integer page;
    private Integer page_count;
    private Integer page_size;
    private Integer skip;
    private List<Product> products;
}