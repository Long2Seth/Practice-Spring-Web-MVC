package cstad.co.practicespringweb.resController;


import cstad.co.practicespringweb.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductResController {

    private final ProductService productService;


    @GetMapping("/all-products")
    public Map<String , Object> allProducts(){
        HashMap<String , Object> allProducts = new HashMap<>();
        allProducts.put("payload" , productService.getAllProduct());
        allProducts.put("message" , "Retrieved Data Successfully!");
        allProducts.put("status" , HttpStatus.OK.value());
        return allProducts;
    }


}
