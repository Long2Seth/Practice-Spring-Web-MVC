package cstad.co.practicespringweb.resController;


import cstad.co.practicespringweb.dto.categoryDto.CategoryRequest;
import cstad.co.practicespringweb.dto.productDto.ProductRequest;
import cstad.co.practicespringweb.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryResController {

    private final CategoryService categoryService;



    public Map<String , Object> response(Object object , String message , int status){
        HashMap<String , Object> responseCategory = new HashMap<>();
        responseCategory.put("payload" , object);
        responseCategory.put("message" , message);
        responseCategory.put("status" , status);
        return responseCategory;
    }

    @GetMapping("/all-category")
    public Map<String , Object > getAllCategory (){
        return response(categoryService.getAllCategory() , "Retrieved Data Successfully!" , HttpStatus.OK.value());
    }


    @PostMapping("/create-category")
    public Map<String , Object> createCategory(@RequestBody CategoryRequest request){
        return response(categoryService.createCategory(request) , "Category Created Successfully!" , HttpStatus.CREATED.value());
    }


    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Map<String , Object> searchById(@PathVariable int id){
        return response(categoryService.searchById(id) , "Category Found Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/delete-category/{id}" , method = RequestMethod.DELETE)
    public Map<String , Object> deleteCategory( @PathVariable int id){
        return response(categoryService.deleteCategory(id) , "Category Deleted Successfully!" , HttpStatus.OK.value());
    }

    @RequestMapping(value = "/update-category/{id}" , method = RequestMethod.PUT)
    public Map<String , Object> putCategory(@RequestBody CategoryRequest request , @PathVariable int id){
        return response(categoryService.putCategory(request , id) , "Category Updated Successfully!" , HttpStatus.OK.value());
    }
    @RequestMapping(value = "/update-category/{id}" , method = RequestMethod.PATCH)
    public Map<String , Object> patchCategory(@RequestBody CategoryRequest request , @PathVariable int id){
        return response(categoryService.patchCategory(request , id) , "Category Updated Successfully!" , HttpStatus.OK.value());
    }







}
