package com.restaurant.modern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestaurantModernApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantModernApplication.class, args);
	}
}