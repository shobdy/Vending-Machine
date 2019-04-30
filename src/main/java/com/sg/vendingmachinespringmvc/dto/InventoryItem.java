package com.sg.vendingmachinespringmvc.dto;

import java.util.Objects;

public class InventoryItem {

    private String itemId;
    private String itemName;
    private String itemCost;
    private String itemQty;
    
    public InventoryItem(){
        
    }

    public InventoryItem(String itemId) {
        this.itemId = itemId;
    }
    
    public void setItemId(String itemId){
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.itemId);
        hash = 71 * hash + Objects.hashCode(this.itemName);
        hash = 71 * hash + Objects.hashCode(this.itemCost);
        hash = 71 * hash + Objects.hashCode(this.itemQty);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InventoryItem other = (InventoryItem) obj;
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemCost, other.itemCost)) {
            return false;
        }
        if (!Objects.equals(this.itemQty, other.itemQty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " Item Requested: " + itemName;
    }
}
