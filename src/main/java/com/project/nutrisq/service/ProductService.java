package com.project.nutrisq.service;

import com.project.nutrisq.webclient.product.ProductClient;
import com.project.nutrisq.webclient.product.dto.ProductDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public ProductDetailsDto getProducts(String name) {
        return productClient.getProductsByName(name);
    }

}