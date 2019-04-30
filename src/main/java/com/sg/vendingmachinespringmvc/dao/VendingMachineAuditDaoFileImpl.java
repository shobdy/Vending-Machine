package com.sg.vendingmachinespringmvc.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }catch(IOException e){
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        out.println("(" + timestamp.format(formatter).toString() +  ") - " + entry);
        
        out.flush();
        out.close();
    }

}
