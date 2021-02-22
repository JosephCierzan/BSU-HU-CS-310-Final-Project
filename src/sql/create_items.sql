Create Table items
(item_id INT auto_increment,
item_code VARCHAR(10) UNIQUE,
description VARCHAR(50),
price DECIMAL(13,2) DEFAULT 0,
inventory_amount INT DEFAULT 0,
PRIMARY KEY (item_id));