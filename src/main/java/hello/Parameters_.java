
package hello;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "empid",
    "empid.original"
})
public class Parameters_ {

    @JsonProperty("empid")
    private Integer empid;
    @JsonProperty("empid.original")
    private String empidOriginal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("empid")
    public Integer getEmpid() {
        return empid;
    }

    @JsonProperty("empid")
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    @JsonProperty("empid.original")
    public String getEmpidOriginal() {
        return empidOriginal;
    }

    @JsonProperty("empid.original")
    public void setEmpidOriginal(String empidOriginal) {
        this.empidOriginal = empidOriginal;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
