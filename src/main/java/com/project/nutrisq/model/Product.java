package com.project.nutrisq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {
    private String code;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("image_url")
    private String imageUrl;
    private Nutriments nutriments;
    private Nutriments_estimated nutriments_estimated;

    @Data
    public static class Nutriments {
        @JsonProperty("energy-kcal")
        private Double energyKcal;
        private Double fat;
        @JsonProperty("saturated-fat")
        private Double saturatedFat;
        private Double carbohydrates;
        private Double sugars;
        private Double fiber;
        private Double salt;
    }

    @Data
    public static class Nutriments_estimated {
        @JsonProperty("cholesterol_100g")
        private Double cholesterol;
        // Vitamins
        @JsonProperty("vitamin-a_100g")
        private Double vitaminA;
        @JsonProperty("vitamin-b1_100g")
        private Double vitaminB1;
        @JsonProperty("vitamin-pp_100g")
        private Double vitaminB3;
        @JsonProperty("pantothenic-acid_100g")
        private Double vitaminB5;
        @JsonProperty("vitamin-b6_100g")
        private Double vitaminB6;
        @JsonProperty("vitamin-b9_100g")
        private Double vitaminB9;
        @JsonProperty("vitamin-b12_100g")
        private Double vitaminB12;
        @JsonProperty("vitamin-c_100g")
        private Double vitaminC;
        @JsonProperty("vitamin-d_100g")
        private Double vitaminD;
        @JsonProperty("vitamin-e_100g")
        private Double vitaminE;
        @JsonProperty("phylloquinone_100g")
        private Double vitaminK;

        // Minerals
        @JsonProperty("zinc_100g")
        private Double zinc;
        @JsonProperty("phosphorus_100g")
        private Double phosphorus;
        @JsonProperty("iodine_100g")
        private Double iodine;
        @JsonProperty("magnesium_100g")
        private Double magnesium;
        @JsonProperty("copper_100g")
        private Double copper;
        @JsonProperty("potassium_100g")
        private Double potassium;
        @JsonProperty("selenium_100g")
        private Double selenium;
        @JsonProperty("sodium_100g")
        private Double sodium;
        @JsonProperty("calcium_100g")
        private Double calcium;
        @JsonProperty("iron_100g")
        private Double iron;
    }
}