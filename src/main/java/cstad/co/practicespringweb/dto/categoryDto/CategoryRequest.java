package cstad.co.practicespringweb.dto.categoryDto;


import lombok.Builder;

@Builder
public record CategoryRequest (String title, String description) {

}
