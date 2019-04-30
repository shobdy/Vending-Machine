package com.sg.vendingmachinespringmvc.dto;

import java.util.Objects;

public class FormDisplayContent {

    String totalAmtContent, messagesContent, itemIdContent, changeReturnContent;

    public String getTotalAmtContent() {
        return totalAmtContent;
    }

    public void setTotalAmtContent(String totalAmtContent) {
        this.totalAmtContent = totalAmtContent;
    }

    public String getMessagesContent() {
        return messagesContent;
    }

    public void setMessagesContent(String messagesContent) {
        this.messagesContent = messagesContent;
    }

    public String getItemIdContent() {
        return itemIdContent;
    }

    public void setItemIdContent(String itemIdContent) {
        this.itemIdContent = itemIdContent;
    }

    public String getChangeReturnContent() {
        return changeReturnContent;
    }

    public void setChangeReturnContent(String changeReturnContent) {
        this.changeReturnContent = changeReturnContent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.totalAmtContent);
        hash = 89 * hash + Objects.hashCode(this.messagesContent);
        hash = 89 * hash + Objects.hashCode(this.itemIdContent);
        hash = 89 * hash + Objects.hashCode(this.changeReturnContent);
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
        final FormDisplayContent other = (FormDisplayContent) obj;
        if (!Objects.equals(this.totalAmtContent, other.totalAmtContent)) {
            return false;
        }
        if (!Objects.equals(this.messagesContent, other.messagesContent)) {
            return false;
        }
        if (!Objects.equals(this.itemIdContent, other.itemIdContent)) {
            return false;
        }
        if (!Objects.equals(this.changeReturnContent, other.changeReturnContent)) {
            return false;
        }
        return true;
    }
    
    
    
}
