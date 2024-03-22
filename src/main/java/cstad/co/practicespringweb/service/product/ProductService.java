package cstad.co.practicespringweb.service.product;

import cstad.co.practicespringweb.dto.productDto.ProductResponse;

import java.util.List;
import java.util.Objects;

public interface ProductService {
    public List<ProductResponse> getAllProduct();
}
