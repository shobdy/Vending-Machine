package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineAuditDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.FormDisplayContent;
import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    private VendingMachineDao dao;
    
    private BigDecimal totalAmount = new BigDecimal("0.00");
    
    String totalAmtContent = "0.00", messagesContent, itemIdContent, changeReturnContent="";
    
    public  VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }

    @Override
    public void createItem(InventoryItem item) throws VendingMachineDuplicateIdException, VendingMachineDataValidationException, VendingMachinePersistenceException {
        if(dao.getItemById(item.getItemId()) != null){
            throw new VendingMachineDuplicateIdException("ERROR: Could not create item. Item Id " + item.getItemId() + " already exists.");
        }
        
        validateItemData(item);
        dao.addItem(item.getItemId(), item);
    }

    @Override
    public void editItem(InventoryItem item) throws VendingMachineDuplicateIdException, VendingMachineDataValidationException, VendingMachinePersistenceException {
        if(dao.getItemById(item.getItemId()) == null){
            throw new VendingMachineDataValidationException("ERROR: Could not edit item. Item Id " + item.getItemId() + " does not exist.");
        } 
        
        dao.editItem(item);
    }
    
    @Override
    public List<InventoryItem> getAllItems(FormDisplayContent formDisplayContent) throws VendingMachinePersistenceException {
        formDisplayContent.setTotalAmtContent(totalAmtContent);
        formDisplayContent.setMessagesContent(messagesContent);
        formDisplayContent.setChangeReturnContent(changeReturnContent);
        formDisplayContent.setItemIdContent(itemIdContent);
        
        return dao.getAllStockedItems();
    }
    
    @Override
    public InventoryItem getItem(String itemId)throws VendingMachinePersistenceException, NoItemInventoryException {
        if(dao.getItemById(itemId) == null) throw new NoItemInventoryException("The item does not exist");
        InventoryItem item = dao.getItemById(itemId);
        itemIdContent = item.getItemId() + " (" + item.getItemName() + ")";
        return dao.getItemById(itemId);
    }

    @Override
    public void removeItem(String itemId) throws VendingMachinePersistenceException {
        dao.removeItem(itemId);
    }
    
    
    
    private void validateItemData(InventoryItem item) throws VendingMachineDataValidationException{
        if(item.getItemName() == null
                || item.getItemName().trim().length() == 0
                || item.getItemCost() == null
                || item.getItemCost().trim().length() == 0
                || item.getItemQty() == null
                || item.getItemQty().trim().length() == 0){
            throw new VendingMachineDataValidationException(
                "ERROR: All fields [Item Name, Item Cost, Item Qty] are required.");
        }
    }

    
    @Override
    public BigDecimal addMoney(BigDecimal balance) throws VendingMachinePersistenceException {
        totalAmount = totalAmount.add(balance);
        totalAmtContent = totalAmount.toString();
        return totalAmount;
    }
    
    @Override
    public void removeMoney(BigDecimal amtRemoved) throws VendingMachinePersistenceException {
        totalAmount = totalAmount.subtract(amtRemoved);
    }

    @Override
    public Change makeChange() throws VendingMachinePersistenceException {
        Change change = new Change();
        changeReturnContent = "";
        change.setPennies(totalAmount.multiply(new BigDecimal("100")).intValue());
        change.setQuarters(change.getPennies() / 25);
        change.setPennies(change.getPennies() % 25);
        change.setDimes(change.getPennies() / 10);
        change.setPennies(change.getPennies() % 10);
        change.setNickels(change.getPennies() / 5);
        change.setPennies(change.getPennies() % 5);
        
        
        if(change.getQuarters() > 0){
            if(change.getQuarters() > 1){
                changeReturnContent = "Quarters: " + change.getQuarters();
            }else{
                changeReturnContent = "Quarter: " + change.getQuarters();
            }
        }
        if(change.getDimes() > 0){
            if(change.getDimes() > 1){
                changeReturnContent += " Dimes: " + change.getDimes();
            }else{
                changeReturnContent += " Dime: " + change.getDimes();
            }
        }
        if(change.getNickels() > 0){
            if(change.getNickels() > 1){
                changeReturnContent += " Nickels: " + change.getNickels();
            }else{
                changeReturnContent += " Nickel: " + change.getNickels();
            }
        }
        removeMoney(totalAmount);
        totalAmtContent = totalAmount.toString();
        itemIdContent = "";
        return change;
    }

    @Override
    public BigDecimal getBalance() throws VendingMachinePersistenceException {
        return totalAmount;
    }

    @Override
    public InventoryItem makePurchase(InventoryItem itemChosen) throws VendingMachinePersistenceException, InsufficientFundsException, 
            NoItemInventoryException, VendingMachineDuplicateIdException, VendingMachineDataValidationException {
        changeReturnContent="";
        BigDecimal itemCost = new BigDecimal(itemChosen.getItemCost());
        if(Integer.parseInt(itemChosen.getItemQty()) == 0){
            throw new NoItemInventoryException("SOLD OUT!!!");
        }
        if(getBalance().compareTo(itemCost) < 0){
            throw new InsufficientFundsException("Please deposit: $" + itemCost.subtract(getBalance()));
        }
        removeMoney(new BigDecimal(itemChosen.getItemCost()));
        
        int reduceQty = Integer.parseInt(itemChosen.getItemQty()) - 1;
        itemChosen.setItemQty(Integer.toString(reduceQty));
        
        editItem(itemChosen);
        
        totalAmtContent = "0.00";
        changeReturnContent="";
        messagesContent = "Thank You!!!";
        
        return itemChosen;
    }

    @Override
    public void updateMessageBox(String message) throws VendingMachinePersistenceException {
        messagesContent = message;
    }

}
