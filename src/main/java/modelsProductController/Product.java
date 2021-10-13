package modelsProductController;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@With
public class Product {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("title")
    String title;

    @JsonProperty("price")
    Integer price;

    @JsonProperty("categoryTitle")
    String categoryTitle;
}