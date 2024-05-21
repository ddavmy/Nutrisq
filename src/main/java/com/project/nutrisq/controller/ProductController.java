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

    // TODO: Implement caching here
    @GetMapping("/products/name/{keywords}")
    public ProductDetailsDto getProductsByKeywords(@PathVariable String keywords) {
        return productService.getProductsByKeywords(keywords);
    }

    // TODO: Implement caching here
    @GetMapping("/products/code/{barcode}")
    public ProductDetailsDto.SingleProductDetailsDto getProductByBarcode(@PathVariable String barcode) { return productService.getProductByBarcode(barcode); }

}
