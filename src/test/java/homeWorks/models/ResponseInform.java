package homeWorks.models;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error",
        "request",
        "method"
})

@Generated("jsonschema2pojo")

public class ResponseInform extends ResponseBase <ResponseInform.DataInform> {

    public ResponseInform() {

    }

    public ResponseInform(DataInform dataInform, Boolean success, Integer status) {
        super(dataInform, success, status);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataInform {

        public DataInform() {

        }

        public DataInform(String error, String request, String method) {
            this.error = error;
            this.request = request;
            this.method = method;
        }

        @JsonProperty("error")
        private String error;

        @JsonProperty("request")
        private String request;

        @JsonProperty("method")
        private String method;

        @JsonProperty("error")
        public String getError() {
            return error;
        }

        @JsonProperty("error")
        public void setError(String error) {
            this.error = error;
        }

        @JsonProperty("request")
        public String getRequest() {
            return request;
        }

        @JsonProperty("request")
        public void setRequest(String request) {
            this.request = request;
        }

        @JsonProperty("method")
        public String getMethod() {
            return method;
        }

        @JsonProperty("method")
        public void setMethod(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return "DataInform{" +
                    "error='" + error + '\'' +
                    ", request='" + request + '\'' +
                    ", method='" + method + '\'' +
                    '}';
        }
    }

}
