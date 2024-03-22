package cstad.co.practicespringweb.dto.productDto;


import lombok.Builder;
import lombok.Getter;

@Builder
public record ProductRequest(String title, String description, float price, String imageUrl, int categoryId) {

}
