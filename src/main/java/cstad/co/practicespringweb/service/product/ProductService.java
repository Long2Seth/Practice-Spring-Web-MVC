package cstad.co.practicespringweb.service.product;

import cstad.co.practicespringweb.dto.productDto.ProductRequest;
import cstad.co.practicespringweb.dto.productDto.ProductResponse;

import java.util.List;
import java.util.Objects;

public interface ProductService {
    public List<ProductResponse> getAllProduct();
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse searchByID(int id);
    ProductResponse deleteByID(int id);
    ProductResponse putProduct(ProductRequest productResponse , int id);
    ProductResponse patchProduct(ProductRequest productResponse , int id);
}
