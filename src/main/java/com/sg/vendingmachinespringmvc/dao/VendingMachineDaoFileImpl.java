package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, InventoryItem> items = new HashMap<>();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public InventoryItem addItem(String itemId, InventoryItem item) throws VendingMachinePersistenceException {
        InventoryItem newItem = items.put(itemId, item);
        writeInventoryItems();
        return newItem;
    }

    @Override
    public void removeItem(String itemId) {
        InventoryItem removedItem = items.remove(itemId);
    }

    @Override
    public List<InventoryItem> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<InventoryItem>(items.values());
    }
    
    @Override
    public List<InventoryItem> getAllStockedItems() throws VendingMachinePersistenceException {
        loadInventoryItems();
        List<InventoryItem> allItemsNotZeroQty = new ArrayList<InventoryItem>(items.values());
        Predicate<InventoryItem> itemNotStocked = i -> Integer.parseInt(i.getItemQty()) == 0;
        allItemsNotZeroQty.removeIf(itemNotStocked);
        return allItemsNotZeroQty;
    }
    
    @Override
    public InventoryItem getItemById(String itemId) throws VendingMachinePersistenceException {
        return items.get(itemId);
    }
    
    private void loadInventoryItems() throws VendingMachinePersistenceException {
        Scanner scan;

        try {
            scan = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("\n-_- Could not load Inventory into memory.\n", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            InventoryItem currentItem = new InventoryItem(currentTokens[0]);

            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemCost(currentTokens[2]);
            currentItem.setItemQty(currentTokens[3]);

            items.put(currentItem.getItemId(), currentItem);
        }
        scan.close();
    }

    private void writeInventoryItems() throws VendingMachinePersistenceException {

        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save data.", e);
        }

        List<InventoryItem> itemList = this.getAllItems();
        for (InventoryItem currentItem : itemList) {
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemCost() + DELIMITER
                    + currentItem.getItemQty());
            out.flush();
        }
        out.close();
    }

    @Override
    public void editItem(InventoryItem item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
