package homeWorks.models;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "success",
        "status"
})
@Generated("jsonschema2pojo")

public class ResponseBase<AnyData> {

    public ResponseBase(){

    }

    public ResponseBase(AnyData data, Boolean success, Integer status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    @JsonProperty("data")
    private AnyData data;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("data")
    public AnyData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(AnyData data) {
        this.data = data;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        //Немного допоплнил стандартный toString переносами строк, что бы глаз хоть как то целялся за структуру
        return "ResponseBase{" +
                "\ndata=" + data +
                "\n, success=" + success +
                "\n, status=" + status +
                "\n}";
    }
}
