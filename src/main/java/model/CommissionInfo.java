package model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"transactionType",
"receiptAmount",
"commissionAmount",
"quantity"
})
public class CommissionInfo {
    @JsonProperty("transactionType")
    private String transactionType;
    
    @JsonProperty("receiptAmount")
    private double receiptAmount;
    
    @JsonProperty("commissionAmount")
    private double commissionAmount;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("transactionType")
    public String getTransactionType() {
    return transactionType;
    }
    
    @JsonProperty("transactionType")
    public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
    }
    
    @JsonProperty("receiptAmount")
    public double getReceiptAmount() {
    return receiptAmount;
    }

    @JsonProperty("receiptAmount")
    public void setReceiptAmount(double receiptAmount) {
    this.receiptAmount = receiptAmount;
    }
    
    @JsonProperty("commissionAmount")
    public double getCommissionAmount() {
    return commissionAmount;
    }

    @JsonProperty("commissionAmount")
    public void setCommissionAmount(double commissionAmount) {
    this.commissionAmount = commissionAmount;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
    return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
    this.quantity = quantity;
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
