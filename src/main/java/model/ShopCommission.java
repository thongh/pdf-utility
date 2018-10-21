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
"shopName",
"shopCode",
"commissionInfos"
})
public class ShopCommission {

    @JsonProperty("shopName")
    private String shopName;
    
    @JsonProperty("shopCode")
    private String shopCode;
    
    @JsonProperty("commissionInfos")
    private List<CommissionInfo> commissionInfos = null;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shopName")
    public String getShopName() {
    return shopName;
    }

    @JsonProperty("shopName")
    public void setShopName(String shopName) {
    this.shopName = shopName;
    }

    @JsonProperty("shopCode")
    public String getShopCode() {
    return shopCode;
    }

    @JsonProperty("shopCode")
    public void setShopCode(String shopCode) {
    this.shopCode = shopCode;
    }

    @JsonProperty("commissionInfos")
    public List<CommissionInfo> getCommissionInfos() {
    return commissionInfos;
    }

    @JsonProperty("commissionInfos")
    public void setCommissionInfos(List<CommissionInfo> commissionInfos) {
    this.commissionInfos = commissionInfos;
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
