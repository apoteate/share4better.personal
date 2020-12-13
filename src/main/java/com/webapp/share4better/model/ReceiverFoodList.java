package com.webapp.share4better.model;

public class ReceiverFoodList {

    private Integer id;
    private Integer contributorID;
    private Integer receiverID;
    private String name;
    private String type;
    private String quantity;
    private String quality;
    private String receiveOrContributorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContributorID() {
        return contributorID;
    }

    public void setContributorID(Integer contributorID) {
        this.contributorID = contributorID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getReceiveOrContributorName() {
        return receiveOrContributorName;
    }

    public void setReceiveOrContributorName(String receiveOrContributorName) {
        this.receiveOrContributorName = receiveOrContributorName;
    }


}
