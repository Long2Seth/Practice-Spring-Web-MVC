package cstad.co.practicespringweb.dto.productDto;


import lombok.Builder;

@Builder
public record ProductResponse
        (int id, String title, String description, float price, String imageUrl, int categoryId) {
}
