package com.example.asm1.seeder;

import com.example.asm1.entity.Product;
import com.example.asm1.repository.ProductRepository;
import com.example.asm1.util.NumberHelper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class ProductSeeder implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    private static final int NUMBER_OF_PRODUCT = 100;

    public static ArrayList<Product> products;

    public void generate(){
        ArrayList<String> color = new ArrayList<>();
        color.add("red");
        color.add("white");
        color.add("yellow");
        color.add("blue");
        color.add("green");
        ArrayList<String> size = new ArrayList<>();
        size.add("M");
        size.add("S");
        size.add("XL");
        size.add("L");
        size.add("XXL");
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Fmale");
        Random random = new Random();
        Faker faker = new Faker();
        products = new ArrayList<>();
        for (int i = 0 ; i<NUMBER_OF_PRODUCT ; i++){
            Product product = Product.builder()
                    .name(faker.name().name())
                    .description(faker.name().title())
                    .gender(gender.get(NumberHelper.numberRandom(0 , gender.size() - 1)))
                    .color(color.get(NumberHelper.numberRandom(0 , color.size() -1)))
                    .size(size.get(NumberHelper.numberRandom(0 , size.size() -1)))
                    .price(NumberHelper.numberRandom(10000 , 100000))
                    .status(NumberHelper.numberRandom(0,1))
                    .createAt(LocalDateTime.now().minusDays(NumberHelper.numberRandom(1, 30)))
                    .build();
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    @Override
    public void run(String... args) throws Exception {
        generate();
    }
}

