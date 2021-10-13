package modelsProductController;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@With
public class ErrorInfo {

        @JsonProperty("status")
        public Integer status;

        @JsonProperty("message")
        public String message;

        @JsonProperty("timestamp")
        public String timestamp;

}
