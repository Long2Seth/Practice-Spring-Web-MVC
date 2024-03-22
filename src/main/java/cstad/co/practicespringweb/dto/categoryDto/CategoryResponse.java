package cstad.co.practicespringweb.dto.categoryDto;


import lombok.Builder;

import java.util.ServiceLoader;

@Builder
public record CategoryResponse (int id , String title , String description) {

}
