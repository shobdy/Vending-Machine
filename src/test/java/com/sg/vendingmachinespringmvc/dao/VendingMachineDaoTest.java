package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteItem() throws VendingMachinePersistenceException {
        // Create new Item
        InventoryItem ni = new InventoryItem("1");
        ni.setItemName("Snickers");
        ni.setItemCost("0.85");
        ni.setItemQty("5");
        dao.addItem(ni.getItemId(), ni);
        InventoryItem fromDb = dao.getItemById(ni.getItemId());
        assertEquals(fromDb, ni);
        dao.removeItem(ni.getItemId());
        assertNull(dao.getItemById(ni.getItemId()));
    }

    @Test
    public void testGetAllItems() throws VendingMachinePersistenceException {
        // Create new item
        InventoryItem ni = new InventoryItem("1");
        ni.setItemName("Snickers");
        ni.setItemCost("0.85");
        ni.setItemQty("4");
        dao.addItem(ni.getItemId(), ni);
        // Create 2nd new item
        InventoryItem ni2 = new InventoryItem("2");
        ni2.setItemName("Butterfinger");
        ni2.setItemCost("0.90");
        ni2.setItemQty("4");
        dao.addItem(ni2.getItemId(), ni2);
        List<InventoryItem> iList = dao.getAllItems();
        assertEquals(iList.size(), 2);
    }

}