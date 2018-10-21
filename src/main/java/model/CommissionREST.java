package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"success",
"narrative",
"documents",
"shopCommissions"
})
public class CommissionREST {
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("narrative")
    private String narrative;
    
    @JsonProperty("documents")
    private Object documents;
    
    @JsonProperty("shopCommissions")
    private List<ShopCommission> shopCommissions = null;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("success")
    public Boolean getSuccess() {
    return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
    this.success = success;
    }

    @JsonProperty("narrative")
    public String getNarrative() {
    return narrative;
    }

    @JsonProperty("narrative")
    public void setNarrative(String narrative) {
    this.narrative = narrative;
    }

    @JsonProperty("documents")
    public Object getDocuments() {
    return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(Object documents) {
    this.documents = documents;
    }

    @JsonProperty("shopCommissions")
    public List<ShopCommission> getShopCommissions() {
    return shopCommissions;
    }

    @JsonProperty("shopCommissions")
    public void setShopCommissions(List<ShopCommission> shopCommissions) {
    this.shopCommissions = shopCommissions;
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
