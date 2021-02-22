Create Table orders
(order_id INT auto_increment, 
item_code VARCHAR(10) NOT NULL,
quantity INT NOT NULL,
order_timestamp TIMESTAMP DEFAULT now(),
Primary key (order_id));