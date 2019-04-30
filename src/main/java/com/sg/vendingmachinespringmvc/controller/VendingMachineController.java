package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.FormDisplayContent;
import com.sg.vendingmachinespringmvc.dto.InventoryItem;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineDataValidationException;
import com.sg.vendingmachinespringmvc.service.VendingMachineDuplicateIdException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingMachineController {

    private VendingMachineServiceLayer service;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping(value="/displayMenuPage", method=RequestMethod.GET)
    public String displayMenuPage(Model model){
        FormDisplayContent formDisplayContent = new FormDisplayContent();
        List<InventoryItem> menuList = new ArrayList<>();
        
        try {
            menuList = service.getAllItems(formDisplayContent);
        } catch (VendingMachinePersistenceException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        
        model.addAttribute("formDisplayContent", formDisplayContent);
        model.addAttribute("menuList", menuList);
        
        return "menu";
    }
    
    @RequestMapping(value = "/addDollar", method = RequestMethod.GET)
    public String addDollar(HttpServletRequest request, Model model){
        try{
            service.addMoney(new BigDecimal("1.00"));
        } catch (VendingMachinePersistenceException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect: displayMenuPage";
    }
    
    @RequestMapping(value = "/addQuarter", method = RequestMethod.GET)
    public String addQuarter(HttpServletRequest request, Model model){
        try{
            service.addMoney(new BigDecimal("0.25"));
        } catch (VendingMachinePersistenceException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect: displayMenuPage";
    }
    
    @RequestMapping(value = "/addDime", method = RequestMethod.GET)
    public String addDime(HttpServletRequest request, Model model){
        try{
            service.addMoney(new BigDecimal("0.10"));
        } catch (VendingMachinePersistenceException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect: displayMenuPage";
    }
    
    @RequestMapping(value = "/addNickel", method = RequestMethod.GET)
    public String addNickel(HttpServletRequest request, Model model){
        try{
            service.addMoney(new BigDecimal("0.05"));
        } catch (VendingMachinePersistenceException e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect: displayMenuPage";
    }
    
    @RequestMapping(value="/changeReturn", method=RequestMethod.GET)
    public  String changeReturn(Model model) {
        try{
            service.makeChange();
        } catch(VendingMachinePersistenceException e){
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect:displayMenuPage";
    }
    
    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request, Model model) throws VendingMachinePersistenceException, NoItemInventoryException {
        String itemId = request.getParameter("itemId");
        service.getItem(itemId);
        return "redirect: displayMenuPage";
    }
    
    @RequestMapping(value = "/purchaseItem", method = RequestMethod.GET)
    public String purchaseItem(HttpServletRequest request, Model model) throws VendingMachinePersistenceException, VendingMachineDuplicateIdException, VendingMachineDataValidationException {
        String itemID = request.getParameter("itemID");
        if(!itemID.equals("")){
            itemID = itemID.substring(0,1);
        }
        try {
            InventoryItem itemChosen = service.getItem(itemID);
            service.makePurchase(itemChosen);
            makeChange();
        } catch(InsufficientFundsException | NoItemInventoryException e){
            String errorMessage = e.getMessage();
            service.updateMessageBox(errorMessage);
        }
        return "redirect: displayMenuPage";
    }
    
    private void makeChange() throws VendingMachinePersistenceException {
        Change change = service.makeChange();
        //view.makeChange(change);
    }
}
