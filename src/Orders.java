import java.util.Date;

public class Orders 
{
	private int order_id;
	private String item_code;
	private int quantity;
	private Date order_timestamp;
	
	public Orders(int order_id, String item_code, int quantity, Date order_timestamp)
	{
		this.order_id = order_id;
		this.item_code = item_code;
		this.quantity = quantity;
		this.order_timestamp = order_timestamp;
	}	

	public Orders(String item_code, int quantity)
	{
		this.item_code = item_code;
		this.quantity = quantity;
		this.order_timestamp = new Date();
	}
	
	public int getOrder_id() 
	{
		return order_id;
	}
	
	public String getItem_code() 
	{
		return item_code;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public Date getOrder_timestamp() 
	{
		return order_timestamp;
	}
	
	public String toString()
	{
		return String.format("(%s, %s, %s, %s, None)", this.order_id, this.item_code, this.quantity, this.order_timestamp);
	}
}
