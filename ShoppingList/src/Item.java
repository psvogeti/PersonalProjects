/**
 * A class dedicated to storing properties of a shopping list item
 * 
 * @author 715st
 *
 */
public class Item implements Comparable<Item>{

	private String name; // the name of this item
	private int quantity; // the quantity of this item
	private double price; // the price of this item without the dollar sign
	private ItemPriority itemPriority; //the priority of this item

	/**
	 * Creates a new shopping list item
	 * 
	 * @param name     the name of this shopping list item
	 * @param quantity the desired quantity of this shopping list item, capped at
	 *                 999
	 * @param price    the unit price of this one shopping list item
	 */
	public Item(String name, int quantity, double price, ItemPriority itemPriority) {
		// cap protection for quantity so that only 999 units of this item can be set
		if (quantity <= 0 || quantity >= 1000) {
			throw new IllegalArgumentException("ERROR: Invalid Quantity");
		}
		
		// price protection to avoid negative prices
		if (price < 0) {
			throw new IllegalArgumentException("ERROR: Price can't be negative");
		}

		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.itemPriority = itemPriority;
	}

	/**
	 * Gets the passed in name of this shopping list item
	 * 
	 * @return the name of this shopping list item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Edits the name of this shopping list item
	 * 
	 * @param name the new name of this shopping list item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the passed in quantity of this shopping list item
	 * 
	 * @return the quantity of this shopping list item
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Edits the quantity of this shopping list item, at most 999
	 * 
	 * @param quantity the new quantity of this shopping list item
	 */
	public void setQuantity(int quantity) throws IllegalArgumentException {
		if (quantity >= 1000) {
			throw new IllegalArgumentException("ERROR: Quantity Overload!");
		}

		this.quantity = quantity;
	}

	/**
	 * Gets the unit price of this shopping list item
	 * 
	 * @return the unit price of this shopping list item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Edits the unit price of this shopping list item
	 * 
	 * @param price the new unit price of this shopping list item
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	/**
	 * Gets the numeric priority level of this shopping list item
	 * 
	 * @return the numeric priority level of this shopping list item
	 *  <p>
	 *  WANT has numeral 1
	 *  <p>
	 *  NEED has numeral 2
	 *  <p>
	 *  URGENT has numeral 3
	 * 
	 */
	public int getNumericItemPriority() {
		return itemPriority.numericPriority;
	}
	
	public ItemPriority getItemPriority() {
		return itemPriority;
	}

	
	/**
	 * Edits the priority level of this shopping list item
	 * @param itemPriority the new priority level of this shopping list item
	 */
	public void setItemPriority(ItemPriority itemPriority) {
		this.itemPriority = itemPriority;
	}

	/**
	 * Returns this item as a string in this format: 
	 * <p>
	 * [ITEMNAME | quantity | $price | Priority]
	 * </p>
	 * 
	 * <p>
	 * NOTE: the inputed price is rounded to the nearest hundredth 
	 * 
	 * @return this item as a custom formatted string
	 */
	@Override
	public String toString() {
		String nameAsString = "[" + getName().toUpperCase() + " | ";
		String quantityAsString = getQuantity() + " | ";
		String priceAsString = "$" + String.format("%.2f", getPrice()) + " | ";
		String itemPriorityAsString = getItemPriority() + "]";
		
		return nameAsString + quantityAsString + priceAsString + itemPriorityAsString;
	}

	/**
	 * Compares this item with otherItem by priority first, then by price. 
	 * @param otherItem the other item to compare this item with
	 * 
	 * <p>
	 * @return a positive integer if this item is more urgent and
	 * has the higher price value for the same urgency as otherItem
	 * <p>
	 * a negative integer if this item is less urgent and 
	 * has the lower price value for the same urgency as otherItem
	 * <p>
	 * zero if this item has the same urgency and same price or
	 * is the same item by the item name
	 */
	@Override
	public int compareTo(Item otherItem) {
		// CASE: HIGHER PRIORITY
		if (this.getNumericItemPriority() > otherItem.getNumericItemPriority()) {
			return 1;
		// CASE: LOWER PRIORITY	
		} else if (this.getNumericItemPriority() < otherItem.getNumericItemPriority()) {
			return -1;
		} else { //CASE: EQUAL PRIORITY, CHECK PRICE
			//CASE: HIGHER PRICE
			if (this.getPrice() >  otherItem.getPrice()) {
				return 1;
			//CASE: LOWER PRICE
			} else if (this.getPrice() < otherItem.getPrice()) {
				return -1;
			} else { //CASE: SAME PRICE
				return 0;
			}
		}
	}
	
	
	

}
