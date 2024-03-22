package cstad.co.practicespringweb.service.product.serviceimpl;


import cstad.co.practicespringweb.dto.productDto.ProductResponse;
import cstad.co.practicespringweb.repository.ProductRepository;
import cstad.co.practicespringweb.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository ;

    @Override
    public List<ProductResponse> getAllProduct() {
        return  productRepository
                .allProducts()
                .stream()
                .map(pro->{
                    return ProductResponse.builder()
                            .id(pro.getId())
                            .imageUrl(pro.getImageUrl())
                            .price(pro.getPrice())
                            .title(pro.getTitle())
                            .description(pro.getDescription())
                            .categoryId(pro.getCategoryId())
                            .build();
                }).toList();

    }



}
