package com.project.nutrisq.webclient.product;

import com.project.nutrisq.webclient.product.dto.ProductDetailsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private static final String PRODUCT_URL = "https://world.openfoodfacts.net/cgi/";
    private RestTemplate restTemplate = new RestTemplate();

    public ProductDetailsDto getProductsByName(String name) {
        try {
            ProductDetailsDto productDetails = callGetMethod("search.pl?search_terms=" + name + "&json=1&fields=code,product_name,nutriments,nutriments_estimated,image_url",
                    ProductDetailsDto.class);
            if (productDetails != null) {
                productDetails.getProducts().forEach(product -> {
                    if (product.getImageUrl() != null) {
                        product.setImageUrl(product.getImageUrl().replaceAll("\\.net", ".org"));
                    }
                });
            }
            return productDetails;
        } catch (HttpServerErrorException.InternalServerError e) {
            System.err.println("500 Internal Server Error occurred: " + e.getMessage());
            return null;
        }
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects) {
        return restTemplate.getForObject(PRODUCT_URL + url,
                responseType, objects);
    }
}