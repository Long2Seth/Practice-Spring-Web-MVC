package cstad.co.practicespringweb.model;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String title;
    private String description;
    private float price;
    private String imageUrl;
    private int categoryId;
}

