package com.example.productservicettsevening.dtos;

import com.example.productservicettsevening.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
