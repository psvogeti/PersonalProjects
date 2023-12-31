/**
 * A class dedicated to hosting basic functionalities of 
 * a shopping list including adding items, removing items, 
 * clearing the shopping list, printing the shopping list, and
 * sorting the shopping list 
 * @author Pranav Vogeti
 *
 */
public class ShoppingList implements ListADT<Item>{
	
	private int size; 
	private ItemNode headItemNode;
	private ItemNode tailItemNode;
	
	/**
	 * Returns the size of this Shopping List
	 * @return the size of this Shopping List
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks to see if this Shopping List is free of any items
	 * @return true if this Shopping List is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Retrieves the item from the head node of this Shopping List
	 * @return the head item from this Shopping List
	 */
	public Item getHeadItem() {
		if (headItemNode == null) {
			return null;
		}
		return headItemNode.getItem();
	}
	
	/**
	 * Retrieves the item from the tail node of this Shopping List
	 * @return the tail item from this Shopping List
	 */
	public Item getTailItem() {
		if (tailItemNode == null) {
			return null;
		}
		return tailItemNode.getItem();
	}

	/**
	 * Checks to see if this ShoppingList contains the specified item
	 * @param item the item to look for in this Shopping List
	 * @return true if this Shopping List has this item, false otherwise
	 */
	@Override
	public boolean contains(Item item) {
		if (isEmpty()) {
			throw new IllegalStateException("ERROR: List is empty!");
		}
		//create a reference to the head to avoid assignment issues
		ItemNode shoppingListItemNode = headItemNode;
		
		//while we have a node to inspect
		while (shoppingListItemNode != null) {
			//check each node's item to see if it matches with the item we are finding
			if (shoppingListItemNode.getItem().compareTo(item) == 0) {
				return true;
			} else { //not a match in this iteration, so advance the pointer
				shoppingListItemNode = shoppingListItemNode.getNextItemNode();
			}
			
		}
		
		//if we reach here, we went through our list and did not find the item
		return false;
	}

	/**
	 * Gets the index of the specified item in this Shopping List
	 * @param item the item to look for in this Shopping List
	 * @return the index of this item from this Shopping List if it is found,
	 * -1 otherwise
	 */
	@Override
	public int indexOf(Item item) {
		if (isEmpty()) {
			throw new IllegalStateException("ERROR: List is empty!");
		}
		//default return if this item is not found
		int itemNotFoundIndex = -1; 
		
		//create reference to the head of this shoppingList
		ItemNode shoppingListItemNode = headItemNode;
		
		//create an index counter for this run
		int itemIndex = 0; 
		//while we have an ItemNode to examine
		while (shoppingListItemNode != null) {
			//if we have a match, return the index it was found at
			if (shoppingListItemNode.getItem().compareTo(item) == 0) {
				return itemIndex;
			}
			
			//if we do not have a match, advance to the next item node
			shoppingListItemNode = shoppingListItemNode.getNextItemNode();
			//increment the item index to reflect that we are in the next position
			itemIndex++;
		}
		
		//if we reach here, this item was not found
		return itemNotFoundIndex; 
	}

	/**
	 * Gets the Item at the specified index from this Shopping List
	 * @param index the specified location of the item to get
	 * @return the Item at this index from this Shopping List if it is there, 
	 * null otherwise
	 */
	@Override
	public Item get(int index) {
		if (isEmpty()) {
			throw new IllegalStateException("ERROR: List is empty!");
		} else if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("ERROR: Invalid Index Supplied");
		}
		
		//keep track of the index we are on
		int currentIndex = 0; 
		
		//create a reference to the head of this ShoppingList
		ItemNode shoppingListItemNode = headItemNode;
		
		
		//while we have an ItemNode to examine
		while (shoppingListItemNode != null) {
			//if the requested index matches our current index, 
			//return the item at this position
			if (currentIndex == index) {
				return shoppingListItemNode.getItem();
			}
			
			//otherwise, move onto the next item and update the currentIndex
			//to reflect that we are on the next item
			shoppingListItemNode = shoppingListItemNode.getNextItemNode();
			currentIndex++;
		}
		
		//if we reach here, no item was found
		return null;
	}

	/**
	 * Adds a new item to the end of this Shopping List
	 * @param newItem the new item to add to this Shopping List
	 */
	@Override
	public void addLast(Item newItem) {
		//encapsulate the new item to add in its own node
		ItemNode itemNodeToAdd = new ItemNode(newItem);
		
		//CASE: EMPTY LIST
		if (isEmpty()) {
			headItemNode = itemNodeToAdd;
			tailItemNode = itemNodeToAdd;
		} else { //CASE: NON-EMPTY LIST
			//since we are adding last, just add after the tail
			tailItemNode.setNextItemNode(itemNodeToAdd);
			//then update the tail to the next node which should be the new item node added
			tailItemNode = tailItemNode.getNextItemNode();
		}
		//finally, we added an item so increment the size
		size++;
	}

	/**
	 * Adds a new item to the beginning of this Shopping List
	 * @param newItem the new item to add to this Shopping List
	 */
	@Override
	public void addFirst(Item newItem) {
		//encapsulate the new item to add in its own node
		ItemNode itemNodeToAdd = new ItemNode(newItem);
		
		//CASE: EMPTY LIST
		if (isEmpty()) {
			headItemNode = itemNodeToAdd;
			tailItemNode = itemNodeToAdd;
		} else { //CASE: NON-EMPTY LIST
			/* 
			 * Since we are adding to the beginning of this shopping list,
			 * this new item node will become the new head.
			 * 
			 * To retain the current shopping list structure, make it point
			 * to the current head item first, and then make this new item
			 * become the head item node
			 */
			itemNodeToAdd.setNextItemNode(headItemNode);
			headItemNode = itemNodeToAdd;
		}
		//finally, we added an item so increment the size
		size++;
		
	}
	/**
	 * Adds a new item at the specified index of this Shopping List
	 * @param newItem the new item to add to this Shopping List
	 * @param index the position at where newItem should be placed
	 */
	@Override
	public void add(Item newItem, int index) {
		//TODO: Index out of bounds if index < 0 and index > size
		//CASE: INDEX = 0 --> addFirst() //also handles empty list cases
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("ERROR: Invalid Index Supplied");
		} else if (index == 0 || isEmpty()) {
			addFirst(newItem);
		} else if (index == size) { //CASE: INDEX = SIZE --> addLast()
			addLast(newItem); //also handles empty list cases
		} else { //CASE: [0 < INDEX < SIZE]
			ItemNode itemNodeToAdd = new ItemNode(newItem);
			ItemNode shoppingListItemNode = headItemNode;
			int indexCounter = 0;
			
			while (shoppingListItemNode.getNextItemNode() != null) {	
				//by this point, we are on the node right before our desired position
				if (indexCounter == index - 1) { 
					//so save the next linked node to this node
					ItemNode itemAfterAddedNode = shoppingListItemNode.getNextItemNode();
					
					//set this previous node's next pointer to our newly added node
					shoppingListItemNode.setNextItemNode(itemNodeToAdd);
					
					//now, link our newly added node to the previously saved node
					itemNodeToAdd.setNextItemNode(itemAfterAddedNode);
					
					//finally, we added a node, so increment size
					size++;
					
					//since the process is done, break out of this loop
					break;
				}
				//update our counter if the condition is not met yet
				indexCounter++;	
				//update our loop
				shoppingListItemNode = shoppingListItemNode.getNextItemNode();
			}
		}
	}

	/**
	 * Removes the item at the specified index from this Shopping List
	 * @param index the location of the item that should be removed
	 * @return the item that was removed
	 */
	@Override
	public Item delete(int index) {
		if (isEmpty()) { //CASE: Empty List
			throw new IllegalStateException("List is already empty!");
		} else if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("ERROR: Invalid Index Supplied");
		} else if (index == 0) { //CASE: Remove Head
			Item removedItem = headItemNode.getItem(); //store and return current head
			
			if (headItemNode.getNextItemNode() == null) { //check to see if there is another node
				headItemNode = null; //if not, no list, so make head be null to reflect deletion
				tailItemNode = null; //if not, no list, so tail is null
			} else { //if there is, advance the head to be the next node
				headItemNode = headItemNode.getNextItemNode();
			}
			
			//since we removed an item, decrement the size
			size--;
			
			//in any case, return the removed item
			return removedItem;
		}
		
		//index tracker
		int indexCounter = 0;
		
		//reference node creation
		ItemNode shoppingListItemNode = headItemNode;
		
		//List traversal begins
		while (shoppingListItemNode != null) {
			//go to the position right before our deletion position
			if (indexCounter == index - 1) { 
				if (index == size - 1) { //CASE: TAIL REMOVAL
					//the next node is the tail node so save the tail item
					Item removedItem = shoppingListItemNode.getNextItemNode().getItem();
					//make the node before the tail be the tail node
					tailItemNode = shoppingListItemNode;
					
					//make the new tail item point to nothing
					shoppingListItemNode.setNextItemNode(null);
					
					//decrease size to reflect our deletion
					size--;
					
					//return the deleted item
					return removedItem;
					
				} else { //CASE: NON-TAIL REMOVAL
					//therefore, the next item is the item we want to delete so store and return
					Item removedItem = shoppingListItemNode.getNextItemNode().getItem();
					
					//to delete, simply redirect our next pointer to be the node after our deleted node
					shoppingListItemNode.setNextItemNode(shoppingListItemNode.getNextItemNode().getNextItemNode());
					
					//since we deleted an item, decrement size
					size--;
					return removedItem;
				}
			} else { //CASE: NOT AT DESIRED DELETION POSITION
				//otherwise, update to the next node and update our index counter
				shoppingListItemNode = shoppingListItemNode.getNextItemNode();
				indexCounter++;	
			}
				
		}
		//if we reach here, no items were removed so return null
		return null;
	}

	/**
	 * Clears this Shopping List of all of its stored items
	 */
	@Override
	public void clear() {
		if (isEmpty()) {
			throw new IllegalStateException("ERROR: List is already empty!");
		}
		
		headItemNode = null;
		tailItemNode = null;
		size = 0;
	}
	
	/**
	 * Sorts this shopping list in order of item priority first, then by price
	 * such that the item with the highest priority number and highest price is the first item
	 * and the item with the lowest priority number and lowest price is the last item 
	 * in this sorted list 
	 * @param headOfShoppingList the first item node of this shopping list
	 * @return the item node of this sorted shopping list
	 */
	public ItemNode sortList(ItemNode headOfShoppingList) {
		//empty list or list of size one
		if (headOfShoppingList == null || headOfShoppingList.getNextItemNode() == null) {
			return headOfShoppingList;
		}
		
		//Divide the list into two halves: left and right
		//EX: [1] -> [0] -> [3] -> [2] -> [5] -> null
		//    [LEFT ------- MID]   [RIGHT ---------]
		
		//LEFT: [1] -> [0] -> [3] -> null		
		//RIGHT: [2] -> [5] -> null
		ItemNode leftHeadNode = headOfShoppingList;
		ItemNode middleNode = getMiddleNode(leftHeadNode); //Get Middle
		ItemNode rightHeadNode = middleNode.getNextItemNode(); //Save the next of the middle
		middleNode.setNextItemNode(null); //Split into two halves here
		
		//Recursively divide till one-node lists emerge 
		leftHeadNode = sortList(leftHeadNode);
		rightHeadNode = sortList(rightHeadNode);
		
		//recursively merge these lists together in sorted order
		return mergeList(leftHeadNode, rightHeadNode);
		
	}
	
	/**
	 * A helper method to merge two sorted shopping lists together in sorted order
	 * @param leftHeadNode the sorted first item node of the left shopping list
	 * @param rightHeadNode the sorted first item node of the right shopping list
	 * @return the new sorted first node of the joined and sorted shopping list
	 */
	private ItemNode mergeList(ItemNode leftHeadNode, ItemNode rightHeadNode) {
		//Two pointers needed to traverse the halves
		ItemNode leftPointerNode = leftHeadNode;
		ItemNode rightPointerNode = rightHeadNode;
		ItemNode nodeToPreserve = null;
		
		//CASE: No items to look through in left, so just get the right element
		if (leftHeadNode == null) {
			return rightHeadNode;
		} 
		
		//CASE: No items to look through in right, so just get the left element
		if (rightHeadNode == null) {
			return leftHeadNode;
		}
		
		//CASE: Left element is larger or the same as the corresponding right element, add the left
		if (leftPointerNode.getItem().compareTo(rightPointerNode.getItem()) >= 0) {
			nodeToPreserve = leftPointerNode;
			//this builds the list in sorted order through recursive return
			leftPointerNode.setNextItemNode(mergeList(leftPointerNode.getNextItemNode(), rightPointerNode));
			
		//CASE: Left element is smaller than corresponding right element, add the right
		} else {
			nodeToPreserve = rightPointerNode;
			//this builds the list in sorted order through recursive return
			rightPointerNode.setNextItemNode(mergeList(leftPointerNode, rightPointerNode.getNextItemNode()));
			
		}
		
		//recursively return the kept node from the comparison
		return nodeToPreserve;
	}
	
	/**
	 * A helper method to find the middle item node of this Shopping List
	 * @param headItemNode the first item node of this Shopping List
	 * @return the middle item node of this Shopping List
	 */
	private ItemNode getMiddleNode(ItemNode headItemNode) {
		ItemNode fastHare = headItemNode.getNextItemNode();
		ItemNode slowTortoise = headItemNode;
		
		while (fastHare != null && fastHare.getNextItemNode() != null) {
			fastHare = fastHare.getNextItemNode().getNextItemNode();
			slowTortoise = slowTortoise.getNextItemNode();
		}
		
		return slowTortoise;
	}
	
	/**
	 * Reverses the order in which the items appear from this Shopping List
	 * @param headOfShoppingList the first item node of this Shopping List
	 * @return the first item node from this reversed Shopping List
	 */
	public ItemNode reverseShoppingList(ItemNode headOfShoppingList) {
		//empty list or list of size one
		if (headOfShoppingList == null || headOfShoppingList.getNextItemNode() == null) {
			return headOfShoppingList;
		}
		//Save the current head of this shopping list
		ItemNode currentHead = headOfShoppingList;
		//Break the list into smaller lists and therefore the last call is the new head of the reversed list
		ItemNode newHead = reverseShoppingList(headOfShoppingList.getNextItemNode());
		//To reverse the head's pointer, set the current head to succeed the reversed list
		headOfShoppingList.getNextItemNode().setNextItemNode(currentHead);
		//Since we brought the head to the end of the reversed list, it should point to null
		currentHead.setNextItemNode(null);
		
		//Keep the reference of the reversed head
		return newHead;
	}

}
