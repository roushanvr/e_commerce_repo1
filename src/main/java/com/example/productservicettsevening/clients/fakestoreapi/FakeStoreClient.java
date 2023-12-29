package com.example.productservicettsevening.clients.fakestoreapi;

import com.example.productservicettsevening.dtos.ProductDto;
import com.example.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l=restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        return Arrays.asList(l.getBody());
    }
    Optional<FakeStoreProductDto> getSingleProduct(Long productId){
        return null;
    }
    //Product addNewProduct(ProductDto productDto);//methods of service don't take Dto object
    FakeStoreProductDto addNewProduct(ProductDto productDto){
        return null;
    }
    FakeStoreProductDto updateProduct(Long productId, Product product){
        return null;
    }
    FakeStoreProductDto replaceProduct(Long productId, Product product){
        return null;
    }
    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
