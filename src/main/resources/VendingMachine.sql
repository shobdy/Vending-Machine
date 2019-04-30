DROP DATABASE IF EXISTS VendingMachine;
CREATE DATABASE VendingMachine;

USE VendingMachine;

CREATE TABLE InventoryItems(
	item_id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_name NVARCHAR(20) NOT NULL,
    item_cost DECIMAL(5, 2) NOT NULL,
    item_qty int(3) NOT NULL
);


INSERT INTO InventoryItems (item_id, item_name, item_cost, item_qty) 
	VALUES (1, "M & Ms", 0.85, 12),
			(2, "Snickers", 0.90, 8),
            (3, "Mounds", 1.00, 3),
            (4, "100 Grand Bar", 1.00, 14),
            (5, "Honey Bun", 0.90, 1),
            (6, "Ding Dongs", 1.25, 7),
            (7, "Coke", 1.50, 10),
            (8, "Chocolate Milk", 1.50, 10),
            (9, "Sprite", 1.50, 8);

            
            