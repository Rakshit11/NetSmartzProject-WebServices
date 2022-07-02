package com.webservices.restapi.service;


import java.io.File;


import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webservices.restapi.entity.FoodProduct;
import com.webservices.restapi.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceFileAccess {

	@Autowired
	private ProductRepository repository;
	public FoodProduct saveProduct(FoodProduct product) {
		return repository.save(product);
		
	}
	public List<FoodProduct> saveProducts(List<FoodProduct> product) {
		return repository.saveAll(product);
		
	}
	public List<FoodProduct> getProducts(){
		return repository.findAll();
	}
	public FoodProduct getProductById(int id) {
		return repository.findById(id).orElse(null);
	}
	public FoodProduct getProductByName(String name) {
		return repository.findByName(name);
	}
	public void deleteAll() {
		repository.deleteAll();
	}
	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "FoodProduct removed !" +id;
	}

	public Page<FoodProduct> findProductsWithPagination(int offset, int pageSize) {
	
		Page<FoodProduct> products = repository.findAll(PageRequest.of(offset,pageSize));
		return products;
	}

	
	public FoodProduct updateProduct(FoodProduct product) {
		FoodProduct existingProduct =repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		return repository.save(existingProduct);
	}
	@Override
	public File getFile() {
		File file = new File("file.txt");
		try {
			List<FoodProduct> foodItemList = (List<FoodProduct>) repository.findAll();

			try (FileOutputStream fileStream = new FileOutputStream(file);
				PrintWriter writer = new PrintWriter(fileStream)) {
				foodItemList.forEach(items -> writer.println(items));
				writer.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	
}
