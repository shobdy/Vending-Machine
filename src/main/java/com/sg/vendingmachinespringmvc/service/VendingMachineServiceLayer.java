package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.FormDisplayContent;
import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.math.BigDecimal;
import java.util.List;


public interface VendingMachineServiceLayer {
    
    void createItem( InventoryItem item) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;
    
    void editItem(InventoryItem item) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;

    void removeItem(String itemId) throws
            VendingMachinePersistenceException;
    
    List<InventoryItem> getAllItems(FormDisplayContent formDisplayContent) throws
            VendingMachinePersistenceException;

    InventoryItem getItem(String itemId) throws
            VendingMachinePersistenceException,
            NoItemInventoryException;
    
    BigDecimal addMoney(BigDecimal balance) throws
            VendingMachinePersistenceException;
    
    void removeMoney(BigDecimal amtRemoved) throws
            VendingMachinePersistenceException;
    
    BigDecimal getBalance() throws
            VendingMachinePersistenceException;
    
    InventoryItem makePurchase(InventoryItem itemChosen) throws
            VendingMachinePersistenceException,
            InsufficientFundsException,
            NoItemInventoryException,
            VendingMachineDuplicateIdException, 
            VendingMachineDataValidationException;
            
    Change makeChange() throws
            VendingMachinePersistenceException;
    
    void updateMessageBox(String message) throws
            VendingMachinePersistenceException; 
}

