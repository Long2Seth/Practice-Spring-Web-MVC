package cstad.co.practicespringweb.repository;

import cstad.co.practicespringweb.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    public List<Category> categoryList = new ArrayList<>(){
        {
            add( Category.builder()
                    .id(1)
                    .title("category one")
                    .description("category one description here").build());

            add( Category.builder()
                    .id(2)
                    .title("category two")
                    .description("category two description here").build());

            add( Category.builder()
                    .id(3)
                    .title("category three")
                    .description("category three description here").build());

            add( Category.builder()
                    .id(4)
                    .title("category four")
                    .description("category four description here").build());
        }
    };


    public List<Category> allCategories(){
        return categoryList;
    }

    public List<Category> addCategory( Category category){
        categoryList.add(category);
        return categoryList;
    }

    public List<Category> deleteCategory(int id){
        categoryList.removeIf(category -> category.getId() == id);
        return categoryList;
    }

    public List<Category> updateCategory( Category category , int id){
        categoryList.forEach(category1 -> {
            if(category1.getId() == id){
                category1.setTitle(category.getTitle());
                category1.setDescription(category.getDescription());
            }
        });
        return categoryList;
    }


}
