package com.project.nutrisq.controller;

import com.project.nutrisq.service.ProductService;
import com.project.nutrisq.webclient.product.dto.ProductDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{name}")
    public ProductDetailsDto getProductsByName(@PathVariable String name) {
        return productService.getProducts(name);
    }

}
