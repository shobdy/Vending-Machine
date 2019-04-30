package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private InventoryItem item01;
    private Map<String, InventoryItem> items = new HashMap<>();
    
    public VendingMachineDaoStubImpl(){
        item01 = new InventoryItem("A1");
        item01.setItemName("Snickers");
        item01.setItemCost(".85");
        item01.setItemQty("1");
        
        items.put(item01.getItemId(), item01);
    }

    @Override
    public InventoryItem addItem(String itemId, InventoryItem item) throws VendingMachinePersistenceException {
        InventoryItem newItem = items.put(itemId, item);
        return newItem;
    }

    @Override
    public void removeItem(String itemId) throws VendingMachinePersistenceException {
        InventoryItem removedItem = items.remove(itemId);
    }

    @Override
    public List<InventoryItem> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<InventoryItem>(items.values());
    }

    @Override
    public List<InventoryItem> getAllStockedItems() throws VendingMachinePersistenceException {
        List<InventoryItem> allItemsNotZeroQty = new ArrayList<InventoryItem>(items.values());
        Predicate<InventoryItem> itemNotStocked = i -> Integer.parseInt(i.getItemQty()) == 0;
        allItemsNotZeroQty.removeIf(itemNotStocked);
        return allItemsNotZeroQty;    }

    @Override
    public InventoryItem getItemById(String itemId) throws VendingMachinePersistenceException {
        return items.get(itemId);
    }

    @Override
    public void editItem(InventoryItem item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
