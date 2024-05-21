package com.project.nutrisq.webclient.product;

import com.project.nutrisq.service.UserService;
import com.project.nutrisq.webclient.product.dto.ProductDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductClient {

    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PRODUCT_URL = "https://pl.openfoodfacts.org/cgi/search.pl?search_terms=";

    public ProductDetailsDto getProductsByKeywords(String keywords) {
        try {
            String countryCode = userService.getUserCountry();
            String url = buildUrlByKeywords(keywords, countryCode);
            ProductDetailsDto productDetails = callGetMethod(url, ProductDetailsDto.class);
            Optional.ofNullable(productDetails).ifPresent(details -> details.getProducts().forEach(product -> {
                if (product.getImageUrl() != null) {
                    product.setImageUrl(product.getImageUrl().replaceAll("\\.net", ".org"));
                }
            }));
            return productDetails;
        } catch (HttpServerErrorException.InternalServerError e) {
            log.error("500 Internal Server Error occurred: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return null;
        }
    }

    public ProductDetailsDto.SingleProductDetailsDto getProductByBarcode(String barcode) {
        try {
            String countryCode = userService.getUserCountry();
            String url = buildUrlByBarcode(barcode, countryCode);
            ProductDetailsDto.SingleProductDetailsDto productDetails = callGetMethod(url, ProductDetailsDto.SingleProductDetailsDto.class);
            Optional.ofNullable(productDetails).ifPresent(details -> details.getProducts().forEach(product -> {
                if (product.getImageUrl() != null) {
                    product.setImageUrl(product.getImageUrl().replaceAll("\\.net", ".org"));
                }
            }));
            return productDetails;
        } catch (HttpServerErrorException.InternalServerError e) {
            log.error("500 Internal Server Error occurred: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return null;
        }
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

    private String buildUrlByKeywords(String keywords, String countryCode) {
        String encodedKeywords = URLEncoder.encode(keywords, StandardCharsets.UTF_8);
        return String.format("%s%s&cc=%s&lc=%s&fields=code,product_name,image_url,nutriments,nutriments_estimated,brands,quantity&json=1&sort_by=popularity&search_simple=1&action=process",
                PRODUCT_URL, encodedKeywords, countryCode, countryCode);
    }

    private String buildUrlByBarcode(String barcode, String countryCode) {
        return String.format("%s&code=%s&cc=%s&lc=%s&fields=code,product_name,image_url,nutriments,nutriments_estimated,brands,quantity&json=1&sort_by=popularity&search_simple=1&action=process",
                PRODUCT_URL, barcode, countryCode, countryCode);
    }
}
