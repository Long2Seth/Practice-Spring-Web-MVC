package cstad.co.practicespringweb.resController;


import cstad.co.practicespringweb.dto.productDto.ProductRequest;
import cstad.co.practicespringweb.dto.productDto.ProductResponse;
import cstad.co.practicespringweb.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductResController {

    private final ProductService productService;


    private Map<String , Object> response(Object object , String message , int status){
        HashMap<String , Object> responseCategory = new HashMap<>();
        responseCategory.put("payload" , object);
        responseCategory.put("message" , message);
        responseCategory.put("status" , status);
        return responseCategory;
    }

    @GetMapping("/all-products")
    public Map<String , Object> allProducts(){
        return response(productService.getAllProduct() , "Retrieved Data Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/create-product" , method = RequestMethod.POST)
    public Map<String , Object> createProduct(@RequestBody ProductRequest productRequest){
        return response(productService.createProduct(productRequest) , "Product Created Successfully!" , HttpStatus.CREATED.value());
    }


    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Map<String , Object> searchById(@PathVariable int id){
        return response(productService.searchByID(id) , "Product Found Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/delete-product/{id}" , method = RequestMethod.DELETE)
    public Map<String , Object> deleteProduct(@PathVariable int id){
        return response(productService.deleteByID(id) , "Product Deleted Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/update-product/{id}" , method = RequestMethod.PUT)
    public Map<String , Object> putProduct(@RequestBody ProductRequest productResponse , @PathVariable int id){
        return response(productService.putProduct(productResponse , id) , "Product Updated Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/update-product/{id}" , method = RequestMethod.PATCH)
    public Map<String , Object> patchProduct(@RequestBody ProductRequest productResponse , @PathVariable int id){
        return response(productService.patchProduct(productResponse , id) , "Product Updated Successfully!" , HttpStatus.OK.value());
    }



}
