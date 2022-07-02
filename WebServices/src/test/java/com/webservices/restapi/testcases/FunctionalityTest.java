package com.webservices.restapi.testcases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webservices.restapi.entity.FoodProduct;
import com.webservices.restapi.repository.ProductRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FunctionalityTest {
	
	@Autowired
	ProductRepository testing;
	
	@Test
	@Order(1)
	public void testMethod() {
		FoodProduct f = new FoodProduct();
		f.setId(16);
		f.setName("WaterMelon");
		f.setQuantity(150);
		f.setPrice(8000);
		testing.save(f);
		assertNotNull(testing.findById(11).get());
	}

	//	For Read 
	@Test
	@Order(2)
	public void readAllMethodTest() {
		List<FoodProduct> products = (List<FoodProduct>) testing.findAll();
		assertThat(products).size().isGreaterThan(0);
	}
	
	// to READ using ID
	
	@Test
	@Order(3)
	public void getOneProduct() {
		FoodProduct oneproduct = testing.findById(2).get();
		assertEquals("Grapes", oneproduct.getName());
	}
	// For UPDATE
	@Test	
	@Order(4)
	public void toUpdate() {
		FoodProduct update = testing.findById(3).get();
		update.setName("PineApple");
		testing.save(update);
		assertNotEquals("Oranges", testing.findById(3).get().getName());	
	}	
	
	// to DELETE
	@Test	
	@Order(5)
	public void testDelete() {		
		testing.deleteById(3);
		assertThat(testing.existsById(3)).isFalse();
	}

}
