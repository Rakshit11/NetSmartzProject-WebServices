package com.webservices.restapi.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webservices.restapi.dto.APIResponse;
import com.webservices.restapi.entity.FoodProduct;
import com.webservices.restapi.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.nio.file.Paths;
@RestController

@RequestMapping("/api")
@Api(tags = {"Food Products Services Developed By Rakshit Kashyap"})
@SwaggerDefinition(tags = {
    @Tag(name = "Food Products Services", description = "Catalog of food items")})

public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/addproduct")
	@ApiOperation(value = "Add a product")
	public FoodProduct addProduct(@RequestBody FoodProduct product) {
		return service.saveProduct(product);
		
		}
	@PostMapping("/addproducts")
	@ApiOperation(value = "Add multiple products")

	public List<FoodProduct> addProducts(@RequestBody List<FoodProduct> product){
		return service.saveProducts(product);
	}
	@ApiOperation(value = "show the products")
	@GetMapping("/showproducts")
	
	public List<FoodProduct> findAllProducts(){
		return service.getProducts();
	}
	@ApiOperation(value = "Find product by id")
	@GetMapping("/productById/{id}")
	public FoodProduct findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	@ApiOperation(value = "Find product by name")
	@GetMapping("/product/{name}")
	public FoodProduct findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}

	@ApiOperation(value = "Update an item")
	@PutMapping("/update")
	public FoodProduct updateProduct(@RequestBody FoodProduct product) {
		return service.updateProduct(product);
		
		}
	@ApiOperation(value = "Delete item")
	@DeleteMapping("/delete")
	public void deleteAll() {
		service.deleteAll();
	}
	@ApiOperation(value = "Delete all products")
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
		}
	@ApiOperation(value = "Use Pagination")
	@GetMapping("/pagination/{offset}/{pagesize}")
	public APIResponse<Page<FoodProduct>> getProductsWithPagination(@PathVariable int offset,@PathVariable int pagesize){
		Page<FoodProduct> productsWithPagination = service.findProductsWithPagination(offset, pagesize);
		return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
	}
	@ApiOperation(value = "Download Availaible Data")
	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> downloadFile() throws IOException {
		File file = service.getFile();
		byte[] data = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		ByteArrayResource byteData = new ByteArrayResource(data);
		MediaType type = MediaType.parseMediaType("appication/txt");
		return (ResponseEntity<ByteArrayResource>) ResponseEntity.ok()
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
						"attachment;filename=" + file.getName())
				.contentType(type).contentLength((int) file.length()).body(byteData);
	}

}
