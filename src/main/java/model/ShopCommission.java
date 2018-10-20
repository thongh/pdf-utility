package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCommission {

    private String shopName;
    private String shopCode;
    private List<CommissionInfo> commissionInfos = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getShopName() {
    return shopName;
    }

    public void setShopName(String shopName) {
    this.shopName = shopName;
    }

    public String getShopCode() {
    return shopCode;
    }

    public void setShopCode(String shopCode) {
    this.shopCode = shopCode;
    }

    public List<CommissionInfo> getCommissionInfos() {
    return commissionInfos;
    }

    public void setCommissionInfos(List<CommissionInfo> commissionInfos) {
    this.commissionInfos = commissionInfos;
    }

    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }
}
