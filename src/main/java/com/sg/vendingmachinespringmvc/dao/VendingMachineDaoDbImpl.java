package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class VendingMachineDaoDbImpl implements VendingMachineDao{
    
    private static final String SQL_INSERT_ITEM
            = "INSERT INTO InventoryItems "
            + "(item_name, item_cost, item_qty) "
            + "VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ITEM
            = "SELECT * FROM InventoryItems WHERE item_id = ?";
    private static final String SQL_UPDATE_ITEM
            = "UPDATE InventoryItems SET "
            + "item_name = ?, item_cost = ?, item_qty = ? "
            + "WHERE item_id = ?";
    private static final String SQL_DELETE_ITEM
            = "DELETE FROM InventoryItems WHERE item_id = ?";
    private static final String SQL_SELECT_ALL_ITEMS
            = "SELECT * FROM InventoryItems";
    
    // Strt of JdbcTemplate injection
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    // End of JdbcTemplate Injection
    
    @Override
    public InventoryItem addItem(String itemId, InventoryItem item) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                item.getItemName(),
                item.getItemCost(),
                item.getItemQty());
        
        String newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class).toString();
        item.setItemId(newId);
        return item;
    }
    
    @Override
    public void editItem(InventoryItem item){
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getItemName(), 
                item.getItemCost(),
                item.getItemQty(),
                item.getItemId());
    }

    @Override
    public void removeItem(String itemId) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_DELETE_ITEM, itemId);
    }

    @Override
    public List<InventoryItem> getAllItems() throws VendingMachinePersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new InventoryItemMapper());
    }

    @Override
    public List<InventoryItem> getAllStockedItems() throws VendingMachinePersistenceException {
        List<InventoryItem> allStockedItems = jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new InventoryItemMapper());
        Predicate<InventoryItem> itemNotStocked = i -> Integer.parseInt(i.getItemQty()) == 0;
        allStockedItems.removeIf(itemNotStocked);
        return allStockedItems;
    }

    @Override
    public InventoryItem getItemById(String itemId) throws VendingMachinePersistenceException {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new InventoryItemMapper(), itemId);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    // Create a new mapper that turns a DB row into a DTO.
    private static final class InventoryItemMapper implements RowMapper<InventoryItem>{
        public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException{
            InventoryItem item = new InventoryItem();
            item.setItemId(rs.getString("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemCost(rs.getString("item_cost"));
            item.setItemQty(rs.getString("item_qty"));
            return item;
        }
    }

}
