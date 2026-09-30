package com.example.productcatalog.Service;

import com.example.productcatalog.Model.Product;
import com.example.productcatalog.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id:"+id));
        productRepository.delete(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product1=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id:"+id));

        product1.setName(productDetails.getName());
        product1.setDescription(productDetails.getDescription());
        product1.setPrice(productDetails.getPrice());
        product1.setCategory(productDetails.getCategory());
        product1.setStock(productDetails.getStock());

        return productRepository.save(product1);

    }
}
