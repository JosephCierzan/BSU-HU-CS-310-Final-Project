
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
/*
 * Author: Joseph Cierzan
 */
public class Project 
{
	public static Items createItem(String item_code, String description, double price, int inventory_amount) throws SQLException 
	{
		
		Connection connection = null;
		Items item = new Items(item_code, description, price, inventory_amount);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		

        String sql = String.format("INSERT INTO items (item_code, description, price, inventory_amount) VALUES ('%s', '%s', %s, %s);",
                item.getItem_code(),
                item.getDescription(),
                item.getPrice(),
        		item.getInventory_amount());
        sqlStatement.executeUpdate(sql);
        connection.close();

        return item;
	}
	
	
	public static void updateInventory(String item_code, int inventory_amount) throws SQLException 
	{
        Connection connection = null;

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("UPDATE items SET inventory_amount = %s WHERE item_code = '%s';",
        		inventory_amount, item_code);

        sqlStatement.executeUpdate(sql);

        connection.close();
    }
	
	public static void deleteItem(String item_code) throws SQLException {
        Connection connection = null;

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("DELETE FROM items WHERE item_code = '%s';", item_code);
        sqlStatement.executeUpdate(sql);
        connection.close();
    }
	
	public static List<Items> getAllItems() throws SQLException {
        Connection connection = null;


        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = "SELECT * FROM items;";
        ResultSet resultSet = sqlStatement.executeQuery(sql);

        List<Items> items = new ArrayList<Items>();

        while (resultSet.next()) {
            int item_id = resultSet.getInt(1);
            String item_code = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = resultSet.getDouble(4);
            int inventory_amount = resultSet.getInt(5);

            Items item = new Items(item_id, item_code, description, price, inventory_amount);
            items.add(item);
        }
        resultSet.close();
        connection.close();
        
        
        return items;

    }
	
	public static Orders createOrder(String item_code, int quantity) throws SQLException 
	{
		
		Connection connection = null;
		Orders order = new Orders(item_code, quantity);
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		

        String sql = String.format("INSERT INTO orders (item_code, quantity) VALUES ('%s', %s);",
                order.getItem_code(),
                order.getQuantity());
        
    	
        sqlStatement.executeUpdate(sql);
        connection.close();

        return order;
	}
	
	 public static void deleteOrder(String item_code) throws SQLException {
	        Connection connection = null;

	        connection = MySqlDatabase.getDatabaseConnection();
	        Statement sqlStatement = connection.createStatement();

	        String sql = String.format("DELETE FROM orders WHERE item_code = '%s';", item_code);
	        sqlStatement.executeUpdate(sql);
	        connection.close();
	    }
	
	 
	 public static List<Orders> getAllOrders() throws SQLException {
	        Connection connection = null;


	        connection = MySqlDatabase.getDatabaseConnection();
	        Statement sqlStatement = connection.createStatement();

	        String sql = "SELECT * FROM orders;";
	        ResultSet resultSet = sqlStatement.executeQuery(sql);

	        List<Orders> orders = new ArrayList<Orders>();

	        while (resultSet.next()) {
	            int order_id = resultSet.getInt(1);
	            String item_code = resultSet.getString(2);
	            int quantity = resultSet.getInt(3);
	            Date order_timestamp = resultSet.getDate(4); //?

	            Orders order = new Orders(order_id, item_code, quantity, order_timestamp);
	            orders.add(order);
	        }
	        resultSet.close();
	        connection.close();
	        return orders;
	    }

	
	public static void attemptToCreateItem(String item_code, String ItemDescription, double price, int inventory_amount)  
	{
		try
		{
			Items item = createItem(item_code, ItemDescription, price, inventory_amount); 
			System.out.println("item_id | item_code | item_description | price | inventory_amount");
			System.out.println("-----------------------------------------------------------------");
			System.out.println(item.toString());
		}
		catch (SQLException sqlException) 
		{
			System.out.println("Failed to create item");
            System.out.println(sqlException.getMessage());
		}
	}

	public static void attemptToUpdateInventory(String item_code, int inventory_amount)
	{
		try
		{
			updateInventory(item_code, inventory_amount);
			System.out.println("item_id | item_code | item_description | price | inventory_amount");
			System.out.println("-----------------------------------------------------------------");
			
			
			List<Items> items = getAllItems();
			
			
            for (Items item : items) 
            {
            	if(item.getItem_code().equals(item_code))
            	{
        			System.out.println(item.toString());
            	}
            }
            
//			System.out.println("(" +  ", "  + item_code + ", " + item_id + ", " + item_id + ", " + inventory_amount + ")");
            
			

		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to update inventory");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToDeleteItem(String item_code)
	{
		try
		{
			deleteItem(item_code);
			System.out.println("Item Deleted!");
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to delete item");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToGetItems(String item_code)
	{
		try
		{
			System.out.println("item_id | item_code | item_description | price | inventory_amount");
			System.out.println("-----------------------------------------------------------------");
			
			List<Items> items = getAllItems();
			
			if(item_code.equalsIgnoreCase("%"))
			{		
				for (Items item : items) 
		         {
		            	
					System.out.println(item.toString());
	      
		         }
			}
			else
			{
	            for (Items item : items) 
	            {
	            	
	            	if(item.getItem_code().equals(item_code))
	            	{
	        			System.out.println(item.toString());
	            	}        
	            }
			}
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to get item");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToCreateOrder(String item_code, int quantity)
	{
		try
		{
			Orders order = createOrder(item_code, quantity);
			System.out.println("order_id | item_code | quantity | order_timestamp | inventory_amount | order_total");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println(order.toString());

		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to create order");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToDeleteOrder(String item_code)
	{
		try
		{
			deleteOrder(item_code);
			System.out.println("Order Deleted!");
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to delete orderr");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToGetOrders(String item_code)
	{
		try
		{
			System.out.println("order_id | item_code | quantity | order_timestamp | inventory_amount | order_total");
			System.out.println("----------------------------------------------------------------------------------");
			
			List<Orders> orders = getAllOrders();
			
			if(item_code.equalsIgnoreCase("%"))
			{		
				for (Orders order : orders) 
		         {
		            	
					System.out.println(order.toString());
	      
		         }
			}
			else
			{
	            for (Orders order : orders) 
	            {
	            	
	            	if(order.getItem_code().equals(item_code))
	            	{
	        			System.out.println(order.toString());
	            	}        
	            }
			}
                
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to get orders");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToGetOrderDetails(int order_id)
	{
		try
		{
			System.out.println("order_id | item_code | description | quantity | price | order_total");
			System.out.println("----------------------------------------------------------------------------------");
			
			List<Orders> orders = getAllOrders();
			
			List<Items> items = getAllItems();
			
			double order_total_amount = 0;
			
            for (Orders order : orders)  
            {
            	if(order.getOrder_id() == order_id)
            	{
            		for (Items item : items)  
                    {
            			order_total_amount+=item.getPrice();
            			System.out.println("(" + order.getOrder_id() + ", "+ order.getItem_code() + ", "+ item.getDescription() + ", "+ order.getQuantity() + ", "+ item.getPrice() + ", " + order_total_amount  + ")");
            			
                    }
            	} 
                	
            }
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to get order details");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void attemptToGetOrderDetailsString(String order_id)
	{
		try
		{
			System.out.println("order_id | item_code | description | quantity | price | order_total");
			System.out.println("----------------------------------------------------------------------------------");
			
			List<Orders> orders = getAllOrders();
			
			List<Items> items = getAllItems();
			
			double order_total_amount = 0;
			
            for (Orders order : orders)  
            {
            	for (Items item : items)  
                {
            		order_total_amount+=item.getPrice();
                	System.out.println("(" + order.getOrder_id() + ", "+ order.getItem_code() + ", "+ item.getDescription() + ", "+ order.getQuantity() + ", "+ item.getPrice() + ", " + order_total_amount  + ")");
                	
                }
                	
            }
		}
		catch (SQLException sqlException)
		{
			System.out.println("Failed to get order details");
            System.out.println(sqlException.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Command: " + args[0]);
		
		if(args[0].equals("CreateItem"))
		{
			String item_code = args[1];
			String ItemDescription = args[2];
			double price = Double.parseDouble(args[3]);
			int inventory_amount = Integer.parseInt(args[4]);
			attemptToCreateItem(item_code, ItemDescription, price, inventory_amount);
		}
		else if(args[0].equals("UpdateInventory"))
		{
			String item_code = args[1];
			int inventory_amount = Integer.parseInt(args[2]);
			attemptToUpdateInventory(item_code, inventory_amount);
		}
		else if(args[0].equals("DeleteItem"))
		{
			String item_code = args[1];
			attemptToDeleteItem(item_code);
		}
		else if(args[0].equals("GetItems"))
		{
			String item_code = args[1];
			attemptToGetItems(item_code);
		}
		else if(args[0].equals("CreateOrder"))
		{
			String item_code = args[1];
			int quantity = Integer.parseInt(args[2]);
			attemptToCreateOrder(item_code, quantity);
		}
		else if(args[0].equals("DeleteOrder"))
		{
			String item_code = args[1];
			attemptToDeleteOrder(item_code);
		}
		else if(args[0].equals("GetOrders"))
		{
			String item_code = args[1];
			attemptToGetOrders(item_code);

		}
		else if(args[0].equals("GetOrderDetails"))
		{
			try
			{
				int order_id = Integer.parseInt(args[1]);
				attemptToGetOrderDetails(order_id);

			}
			catch(NumberFormatException e)
			{
				String order_id = args[1];
				attemptToGetOrderDetailsString(order_id);
			}
		}
	}
		
}
