package cstad.co.practicespringweb.dto.categoryDto;


import lombok.Builder;
import lombok.Getter;

@Builder
public record CategoryRequest (String title, String description) {

}
