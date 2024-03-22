package cstad.co.practicespringweb.dto.productDto;


import lombok.Builder;
import lombok.Getter;

@Builder
public record ProductResponse
        (int id, String title, String description, float price, String imageUrl, int categoryId) {
}
