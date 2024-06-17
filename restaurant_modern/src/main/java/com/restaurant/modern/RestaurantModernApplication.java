package com.restaurant.modern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication(scanBasePackages = "com.restaurant.modern") @ComponentScan(basePackageClasses = RestaurantModernApplication.class) 
public class RestaurantModernApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantModernApplication.class, args);
	}
}