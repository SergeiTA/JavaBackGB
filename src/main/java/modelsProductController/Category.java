package modelsProductController;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Category {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("title")
    String title;
    @JsonProperty("products")
    ArrayList<Product> product;

}
