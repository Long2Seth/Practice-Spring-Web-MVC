package cstad.co.practicespringweb.service.category;

import cstad.co.practicespringweb.dto.categoryDto.CategoryRequest;
import cstad.co.practicespringweb.dto.categoryDto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse searchById(int id);

    CategoryResponse deleteCategory(int id);

    CategoryResponse putCategory(CategoryRequest categoryRequest, int id);

    CategoryResponse patchCategory(CategoryRequest categoryRequest, int id);


}
