package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommissionREST {
    private String name;
    private Boolean success;
    private String narrative;
    private Object documents;
    private List<ShopCommission> shopCommissions = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getSuccess() {
    return success;
    }

    public void setSuccess(Boolean success) {
    this.success = success;
    }

    public String getNarrative() {
    return narrative;
    }

    public void setNarrative(String narrative) {
    this.narrative = narrative;
    }

    public Object getDocuments() {
    return documents;
    }

    public void setDocuments(Object documents) {
    this.documents = documents;
    }

    public List<ShopCommission> getShopCommissions() {
    return shopCommissions;
    }

    public void setShopCommissions(List<ShopCommission> shopCommissions) {
    this.shopCommissions = shopCommissions;
    }

    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
