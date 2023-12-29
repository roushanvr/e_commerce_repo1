package com.example.productservicettsevening.services;

import com.example.productservicettsevening.clients.fakestoreapi.FakeStoreClient;
import com.example.productservicettsevening.clients.fakestoreapi.FakeStoreProductDto;
import com.example.productservicettsevening.dtos.ProductDto;
import com.example.productservicettsevening.models.Category;
import com.example.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder=restTemplateBuilder;
        this.fakeStoreClient=fakeStoreClient;
    }
    private Product converFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Category category=new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(productDto.getImage());
        return product;
    }
    //copied from postForEntity code
    private<T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object...uriVariables) throws RestClientException {
        RestTemplate restTemplate= restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod , requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public List<Product> getAllProducts() {
//        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto[]> l=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        //(at what url we have to send request, List of productDto in response)
        //List<ProductDto>.class will face TYPE ERASER issue
        //What's the datatype of list is not visible at runtime.

        List<FakeStoreProductDto> fakeStoreProductDtos=fakeStoreClient.getAllProducts();
        List<Product> answer=new ArrayList<>();

        for(FakeStoreProductDto productDto:fakeStoreProductDtos){
            answer.add(converFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }
   // It will return a product object with all the details of the fetched product
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDto.class,
          productId);

        FakeStoreProductDto productDto=response.getBody();

        if(productDto==null){
            return Optional.empty();
        }
        return Optional.of(converFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto,
                FakeStoreProductDto.class);
        //(url, which product u want to add(body), response that we will get)

        FakeStoreProductDto productDto1 =response.getBody();
        return converFakeStoreProductDtoToProduct(productDto1);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        // RestTemplate restTemplate=restTemplateBuilder.build();
        //The above line changed to a new line for PATCH method

        RestTemplate restTemplate=restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class).build();
        //converting product to productDto
        FakeStoreProductDto productDto=new FakeStoreProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());
        productDto.setPrice(product.getPrice());


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                FakeStoreProductDto.class,
                productId
                );

        return converFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
