package com.example.productservicettsevening.repositories;

import com.example.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //Jpa Repository is going to manage Product objects and datatype
    //of key is long
    Product save(Product product);
}
