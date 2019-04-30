package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineDataValidationException;
import com.sg.vendingmachinespringmvc.service.VendingMachineDuplicateIdException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;

    
    public VendingMachineServiceLayerImplTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        service = new VendingMachineServiceLayerImpl(dao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    @Test // Test 1
//    public void testCreateItem() throws Exception {
//        InventoryItem item = new InventoryItem("01");
//        item.setItemName("Test01");
//        item.setItemCost("0.55");
//        item.setItemQty("2");
//        
//        service.createItem(item);
//    }
//    
//    @Test // Test 2
//    public void testCreateItemDuplicateId() throws Exception{
//        InventoryItem item = new InventoryItem("A1");
//        item.setItemName("Test01");
//        item.setItemCost("0.55");
//        item.setItemQty("2");
//        
//        try{
//            service.createItem(item);
//            fail("Expected VendingMachineDuplicateIdException was not thrown.");
//        } catch(VendingMachineDuplicateIdException e){
//            return;
//        }
//    }
//
//    @Test // Test3
//    public void testEditItem() throws Exception {
//        InventoryItem item = new InventoryItem("A1");
//        item.setItemName("Test01");
//        item.setItemCost("0.55");
//        item.setItemQty("2");
//        
//        service.editItem(item);
//    }
//
//    @Test // Test 4
//    public void testEditItemIdNotFound() throws Exception {
//        InventoryItem item = new InventoryItem("A15");
//        item.setItemName("Test01");
//        item.setItemCost("0.55");
//        item.setItemQty("2");
//        
//        try{
//            service.editItem(item);
//            fail("Expected VendingMachineDataValidationException was not thrown.");
//        } catch (VendingMachineDataValidationException e){
//            return;
//        }
//    }
//
//    @Test // Test 5
//    public void testGetAllItems() throws Exception {
//       // assertEquals(1, service.getAllItems().size());
//    }
//
//    @Test // Test 6
//    public void testGetItem() throws Exception {
//        InventoryItem item = service.getItem("A1");
//        assertNotNull(item);
//    }
//    
//    @Test // Test 7
//    public void testGetItemNotExist() throws Exception {
//        try{
//            InventoryItem item = service.getItem("A2");
//            fail("Expected NoItemInventoryException was not thrown.");
//        } catch (NoItemInventoryException e){
//            return;
//        }
//    }
//
////    @Test // Test 8
////    public void testRemoveItem() throws Exception {
////        service.removeItem("A1");
////        InventoryItem item = service.getItem("A1");
////        assertNotNull(item);
////    }
////    
////    @Test // Test 9
////    public void testRemoveItemNotExist() throws Exception {
////        service.removeItem("A2");
////        InventoryItem item = service.getItem("A2");
////        assertNull(item);
////    }
//
//    @Test // Test 10
//    public void testAddMoney() throws Exception {
//        service.addMoney(new BigDecimal("1.00"));
//        assertEquals(new BigDecimal("1.00"), service.getBalance());
//    }
//
//    @Test // Test 11
//    public void testMakeChange() throws Exception {
//        Change change = new Change();
//        change.setQuarters(3);
//        change.setDimes(1);
//        change.setNickels(1);
//        change.setPennies(3);
//        
//       // Change makeChange = service.makeChange(new BigDecimal("0.93"));
//        
//        // assertEquals(change, makeChange);
//    }
//
//    @Test // Test 12
//    public void testGetBalance() throws Exception {
//        BigDecimal compareBalance = new BigDecimal("1.23");
//        service.addMoney(compareBalance);
//        assertEquals(compareBalance, service.getBalance());
//    }
//
//    @Test // Test 13
//    public void testMakePurchase() throws Exception {
//        service.addMoney(new BigDecimal("1.00"));
//        InventoryItem item = service.getItem("A1");
//        
//        assertEquals(1, Integer.parseInt(item.getItemQty()));
//        InventoryItem itemPurchased = service.makePurchase(service.getItem("A1"));
//        
//        assertTrue(item.equals(itemPurchased)); 
//    }
//    
//    @Test // Test 14
//    public void testMakePurchaseItemNotCorrect() throws Exception {
//        service.addMoney(new BigDecimal("1.00"));
//        InventoryItem item = new InventoryItem("A2");
//        
//        InventoryItem itemPurchased = service.makePurchase(service.getItem("A1"));
//        
//        assertFalse(item.equals(itemPurchased));
//    }
//    
//    @Test // Test 15
//    public void testMakePurchaseInsufficientFunds() throws Exception {
//        try{
//            service.makePurchase(service.getItem("A1"));
//            fail("Expected InsufficientFundsException to be thrown.");
//        } catch(InsufficientFundsException e){
//            return;
//        }
//    }
//    
//    @Test // Test 16
//    public void testPurchaseDecrementsQty() throws Exception {
//        service.addMoney(new BigDecimal("1.00"));
//        InventoryItem item = service.getItem("A1");
//        
//        assertEquals(1, Integer.parseInt(item.getItemQty()));
//        InventoryItem itemPurchased = service.makePurchase(service.getItem("A1"));
//        
//        assertEquals(0, Integer.parseInt(itemPurchased.getItemQty()));
//    }
//    
//    @Test // Test 17
//    public void testMakePurchaseInventoryOut() throws Exception {
//        service.addMoney(new BigDecimal("1.00"));
//        InventoryItem item = service.getItem("A1");
//        
//        assertEquals(1, Integer.parseInt(item.getItemQty()));
//        InventoryItem itemPurchased = service.makePurchase(service.getItem("A1"));
//        try{
//            service.makePurchase(service.getItem("A1"));
//            fail("Expected NoItemInventoryException to be thrown");
//        } catch(NoItemInventoryException e){
//            return;
//        }
//    }
    
}
