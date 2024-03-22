package cstad.co.practicespringweb.repository;

import cstad.co.practicespringweb.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



@Repository
public class ProductRepository {

    private List<Product> productList = new ArrayList<>(){
        {
            add(Product.builder()
                    .id(111)
                    .title("product one")
                    .price(100.0f)
                    .description("product one description here")
                    .imageUrl("https://via.placeholder.com/200")
                    .categoryId(1).build());

            add(Product.builder()
                    .id(222)
                    .title("product two")
                    .description("product two description here")
                    .price(200.0f)
                    .imageUrl("https://via.placeholder.com/200")
                    .categoryId(2).build());

            add(Product.builder()
                    .id(333)
                    .title("product three")
                    .description("product three description here")
                    .price(300.0f)
                    .imageUrl("https://via.placeholder.com/200")
                    .categoryId(3).build());
        }
    };


    public List<Product>  allProducts(){
        return productList;
    }

    public List<Product> addProduct ( Product product ){
        productList.add(product);
        return productList;
    }

    public List<Product> deletedProduct ( int id ){
        productList.removeIf(product -> product.getId() == id);
        return productList;
    }

    public List<Product> updateProduct ( Product product , int id ){
        productList.forEach(product1 -> {
            if(product1.getId() == id){
                product1.setTitle(product.getTitle());
                product1.setDescription(product.getDescription());
                product1.setPrice(product.getPrice());
                product1.setImageUrl(product.getImageUrl());
                product1.setCategoryId(product.getCategoryId());
            }
        });
        return productList;
    }



}
