package com.example.productservicettsevening.controllers;

import com.example.productservicettsevening.dtos.ErrorResponseDto;
import com.example.productservicettsevening.dtos.ProductDto;
import com.example.productservicettsevening.exceptions.NotFoundException;
import com.example.productservicettsevening.models.Category;
import com.example.productservicettsevening.models.Product;
import com.example.productservicettsevening.repositories.ProductRepository;
import com.example.productservicettsevening.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;
    private ProductRepository productRepository;
    public ProductController(ProductService productService, ProductRepository productRepository){
        this.productService= productService;
        this.productRepository=productRepository;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
//        GetSingleProductResponseDto responseDto=new GetSingleProductResponseDto();
//        responseDto.setProduct(productService.getSingleProduct(productId));

        Optional<Product> productOptional=productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("No product found with productId"+ productId);
        }

        ResponseEntity<Product> response =new ResponseEntity(
                productService.getSingleProduct(productId), HttpStatus.NOT_FOUND
        );
        return response;
    }
    @PostMapping("/products")
    //controller should not take models.
    // It should take a specific dto that only have data that are needed
    //public ResponseEntity<Product> addNewProduct(@RequestBody Product product){

    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
       // Product newProduct=productService.addNewProduct(productDto);
        //here we are calling repository directly from controller which
        //is not a good practice
        Product newProduct= new Product();
        newProduct.setDescription(productDto.getDescription());
        newProduct.setImageUrl(productDto.getImage());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setTitle(productDto.getTitle());

        newProduct=productRepository.save(newProduct);
        ResponseEntity<Product> response=new ResponseEntity(newProduct,HttpStatus.OK);

        return response;
    }
    @PatchMapping("/products/{productId}")
    public Product updateProduct(@PathVariable ("productId") Long productId, @RequestBody ProductDto productDto ){
        //since updateProduct method requires product in params
        //so converting productDto to product
        Product product=new Product();
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());

        Category category=new Category();
        product.setCategory(category);
        product.getCategory().setName(productDto.getCategory());

        return productService.updateProduct(productId,product);
    }
    public String deleteProduct(){
        return "";
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception){
//        ErrorResponseDto errorResponseDto=new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
//    }

}
