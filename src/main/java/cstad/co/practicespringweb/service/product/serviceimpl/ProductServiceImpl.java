package cstad.co.practicespringweb.service.product.serviceimpl;


import cstad.co.practicespringweb.dto.productDto.ProductRequest;
import cstad.co.practicespringweb.dto.productDto.ProductResponse;
import cstad.co.practicespringweb.model.Product;
import cstad.co.practicespringweb.repository.ProductRepository;
import cstad.co.practicespringweb.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    private Product mapRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .title(productRequest.title())
                .description(productRequest.description())
                .price(productRequest.price())
                .imageUrl(productRequest.imageUrl())
                .categoryId(productRequest.categoryId())
                .build();
    }

    private ProductResponse mapProductToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategoryId())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository
                .allProducts()
                .stream()
                .map(pro -> {
                    return mapProductToResponse(pro);
                }).toList();

    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapRequestToProduct(productRequest);
        var id = productRepository.allProducts()
                .stream()
                .max(Comparator.comparing(Product::getId))
                .map(Product::getId);
        int newID = id.get() + 111;
        product.setId(newID);
        productRepository.addProduct(product);
        return mapProductToResponse(product);
    }

    @Override
    public ProductResponse searchByID(int id) {
        Product productSearch = productRepository.allProducts()
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Not Found!"));
        return mapProductToResponse(productSearch);
    }

    @Override
    public ProductResponse deleteByID(int id) {
        Product productDelete = productRepository.allProducts()
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Not Found!"));
        productRepository.deletedProduct(id);
        return mapProductToResponse(productDelete);
    }

    @Override
    public ProductResponse putProduct(ProductRequest productRequest, int id) {
        Product productPut = productRepository.allProducts()
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Not Found!"));
        if (productPut != null) {
            productPut.setTitle(productRequest.title());
            productPut.setDescription(productRequest.description());
            productPut.setPrice(productRequest.price());
            productPut.setImageUrl(productRequest.imageUrl());
            productPut.setCategoryId(productRequest.categoryId());


            productRepository.updateProduct(productPut, id);
            return mapProductToResponse(productPut);
        }
        return null;
    }



    @Override
    public ProductResponse patchProduct(ProductRequest productRequest, int id) {
        Product productPatch = productRepository.allProducts()
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Not Found!"));

        if (productPatch != null) {
            if (productRequest.title() != null) {
                productPatch.setTitle(productRequest.title());
            }

            if (productRequest.description() != null) {
                productPatch.setDescription(productRequest.description());
            }

            if (productRequest.price() != 0.0f) {
                productPatch.setPrice(productRequest.price());
            }

            if (productRequest.imageUrl() != null) {
                productPatch.setImageUrl(productRequest.imageUrl());
            }

            if (productRequest.categoryId() != 0) {
                productPatch.setCategoryId(productRequest.categoryId());
            }

            productRepository.updateProduct(productPatch, id);
            return mapProductToResponse(productPatch);
        } else {
            return null;
        }
    }


}
