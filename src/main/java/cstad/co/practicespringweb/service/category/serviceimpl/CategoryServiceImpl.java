package cstad.co.practicespringweb.service.category.serviceimpl;

import cstad.co.practicespringweb.dto.categoryDto.CategoryRequest;
import cstad.co.practicespringweb.dto.categoryDto.CategoryResponse;
import cstad.co.practicespringweb.model.Category;
import cstad.co.practicespringweb.model.Product;
import cstad.co.practicespringweb.repository.CategoryRepository;
import cstad.co.practicespringweb.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    private Category mapRequestToCategory(CategoryRequest request) {
        return Category.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }


    private CategoryResponse mapCategoryToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.allCategories()
                .stream()
                .map(category -> {
                            return mapCategoryToResponse(category);
                        }
                ).toList();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category newCategory = mapRequestToCategory(categoryRequest);
        var maxID = categoryRepository.allCategories().stream().max(Comparator.comparing(Category::getId)).map(Category::getId);
        int newID = maxID.get() + 1;
        newCategory.setId(newID);

        categoryRepository.addCategory(newCategory);
        return mapCategoryToResponse(newCategory);
    }

    @Override
    public CategoryResponse searchById(int id) {
        Category categorySearchID = categoryRepository.allCategories().stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category Not Found!"));
        return mapCategoryToResponse(categorySearchID);

    }

    @Override
    public CategoryResponse deleteCategory(int id) {
        Category categoryDelete = categoryRepository.allCategories().stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category Not Found!"));
        categoryRepository.deleteCategory(id);
        return mapCategoryToResponse(categoryDelete);
    }

    @Override
    public CategoryResponse putCategory(CategoryRequest categoryRequest, int id) {
        Category categoryToUpdate = categoryRepository.allCategories()
                .stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category Not Found!"));

        if (categoryToUpdate != null) {

            categoryToUpdate.setTitle(categoryRequest.title());
            categoryToUpdate.setDescription(categoryRequest.description());

            categoryRepository.updateCategory(categoryToUpdate, id);
            return mapCategoryToResponse(categoryToUpdate);
        } else {
            return null;
        }
    }


    @Override
    public CategoryResponse patchCategory(CategoryRequest categoryRequest, int id) {
        Category categoryToUpdate = categoryRepository.allCategories().stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category Not Found!"));

        if (categoryToUpdate != null) {
            if (categoryRequest.title() != null) {
                System.out.println("title" + categoryRequest.title());
                categoryToUpdate.setTitle(categoryRequest.title());
                if (categoryToUpdate.getTitle() == null) {
                    categoryToUpdate.setTitle(categoryToUpdate.getTitle());
                }
            }

            if (categoryRequest.description() != null) {
                categoryToUpdate.setDescription(categoryRequest.description());
                if (categoryToUpdate.getDescription() == null) {
                    categoryToUpdate.setDescription(categoryToUpdate.getDescription());
                }
            }


            categoryRepository.updateCategory(categoryToUpdate, id);
            return mapCategoryToResponse(categoryToUpdate);
        } else {
            return null;
        }
    }
}
