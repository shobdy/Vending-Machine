package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineDao {
    
    InventoryItem addItem(String itemId, InventoryItem item) throws VendingMachinePersistenceException;
    
    void editItem(InventoryItem item) throws VendingMachinePersistenceException;
    
    void removeItem(String itemId) throws VendingMachinePersistenceException;
        
    List<InventoryItem> getAllItems() throws VendingMachinePersistenceException;
    
    List<InventoryItem> getAllStockedItems() throws VendingMachinePersistenceException;
    
    InventoryItem getItemById(String itemId) throws VendingMachinePersistenceException;
    
}
