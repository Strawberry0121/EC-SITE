package com.example.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shopping.model.Product;
import com.example.shopping.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository repo;

	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public List<Product> listAll() {
		return repo.findAll();
	}

	public Optional<Product> findById(Long id) {
		return repo.findById(id);
	}

	public Product save(Product p) {
		return repo.save(p);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

}
