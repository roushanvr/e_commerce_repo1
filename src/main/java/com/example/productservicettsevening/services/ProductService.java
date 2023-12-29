package com.example.productservicettsevening.services;

import com.example.productservicettsevening.dtos.GetSingleProductResponseDto;
import com.example.productservicettsevening.dtos.ProductDto;
import com.example.productservicettsevening.exceptions.NotFoundException;
import com.example.productservicettsevening.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;
    //Product addNewProduct(ProductDto productDto);//methods of service don't take Dto object
    Product addNewProduct(ProductDto productDto);
    Product updateProduct(Long productId, Product product);
    Product replaceProduct(Long productId, Product product);
    boolean deleteProduct(Long productId);
}
