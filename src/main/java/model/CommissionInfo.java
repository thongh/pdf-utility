package model;

import java.util.HashMap;
import java.util.Map;

public class CommissionInfo {

    private String transactionType;
    private Integer receiptAmount;
    private Integer commissionAmount;
    private Integer quantity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTransactionType() {
    return transactionType;
    }

    public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
    }

    public Integer getReceiptAmount() {
    return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount) {
    this.receiptAmount = receiptAmount;
    }

    public Integer getCommissionAmount() {
    return commissionAmount;
    }

    public void setCommissionAmount(Integer commissionAmount) {
    this.commissionAmount = commissionAmount;
    }

    public Integer getQuantity() {
    return quantity;
    }

    public void setQuantity(Integer quantity) {
    this.quantity = quantity;
    }

    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }

}
