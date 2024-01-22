/**
 * A class dedicated to storing the data associated to each item
 * in a singular node
 * @author Pranav Vogeti
 *
 */
public class ItemNode {
	private Item item; // the item to be stored in this ItemNode
	private ItemNode nextItemNode; //the next item to be stored after this ItemNode

	
	/**
	 * Creates a new ItemNode with this item as its data
	 * @param item the item to be stored in this ItemNode
	 */
	public ItemNode(Item item) {
		this.item = item;
	}
	
	/**
	 * Creates a new ItemNode that links nextItem after item
	 * @param item the item to be stored in this ItemNode
	 * @param nextItem the next item to be stored after this ItemNode
	 */
	public ItemNode(Item item, Item nextItem) {
		this.item = item;
		this.nextItemNode = new ItemNode(nextItem);
	}
	
	/**
	 * Gets the item stored in this ItemNode
	 * @return the item stored in this ItemNode
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Gets the next item node linked to this ItemNode
	 * @return the next item node linked to this ItemNode 
	 */
	public ItemNode getNextItemNode() {
		return nextItemNode;
	}
	
	/**
	 * Gets the numeric item priority of the item stored in
	 * this ItemNode
	 * @return the numeric item priority of the item stored in this ItemNode
	 */
	public int getItemPriority() {
		return item.getNumericItemPriority();
	}
	
	/**
	 * Gets the numeric item priority of the next item linked to
	 * this ItemNode
	 * @return the numeric item priority of the next item linked to this ItemNode
	 */
	public int getNextItemPriority() {
		return nextItemNode.getItem().getNumericItemPriority();
	}
	
	/**
	 * Gets the numeric item priority of the item stored in
	 * this ItemNode
	 * @return the numeric item priority of the item stored in this ItemNode
	 */
	public double getItemPrice() {
		return item.getPrice();
	}
	/**
	 * Gets the price of the next item linked to
	 * this ItemNode
	 * @return the price of the next item linked to this ItemNode
	 */
	public double getNextItemPrice() {
		return nextItemNode.getItem().getPrice();
	}
	
	/**
	 * Links a new item node to this ItemNode
	 * @param newNextItem the new item node to be linked after this ItemNode
	 */
	public void setNextItemNode(ItemNode newNextItemNode) {
		this.nextItemNode = newNextItemNode;
	}
	
	
	}
