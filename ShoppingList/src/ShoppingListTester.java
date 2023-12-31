/**
 * A class designed to test the functionalities of 
 * the ShoppingList program
 * @author Pranav Vogeti
 *
 */
public class ShoppingListTester {
	
	/**
	 * Tests the cases and edge cases of an invalid Item constructor call
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testItemConstructorInvalid() {
		//Test 1: Create a new item with an invalid quantity, extreme bound >=1000
		{
			try {
				Item invalidQuantityItem = new Item("Lots of Stuff", 10000, 15, ItemPriority.NEED);
				
				//if we reach here, the proper exception was never caught
				invalidQuantityItem.getName(); 
				return false;
			} catch (IllegalArgumentException illArgE) { 
				//should catch this exception so we continue to Test 2
			} catch (Exception e) { //fail if alternate exceptions are present
				e.printStackTrace();
				return false;
			}
		}
		
		//Test 2: Create a new item with an invalid quantity, extreme bound <=0
		{
			try {
				Item invalidQuantityItem = new Item("No Stuff", 0, 15, ItemPriority.NEED);
				
				//if we reach here, the proper exception was never caught
				invalidQuantityItem.getName(); 
				return false;
			} catch (IllegalArgumentException illArgE) { //should catch this exception
				//should catch this exception so we continue to Test 3
			} catch (Exception e) { //fail if alternate exceptions are present
				e.printStackTrace();
				return false;
			}
		}
		
		
		//Test 3: Create a new item with an invalid price (negative price)
		{
			try {
				Item invalidPriceItem = new Item("Negative Price", 10, -6.5, ItemPriority.NEED);
				
				//if we reach here, the proper exception was never caught
				invalidPriceItem.getName(); 
				return false;
			} catch (IllegalArgumentException illArgE) { //should catch this exception
				//should catch this exception so we continue
			} catch (Exception e) { //fail if alternate exceptions are present
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		//if we reach here, all of our test cases have passed!
		return true;
		
	}
	
	/**
	 * Tests the cases of valid Item constructor calls and the toString() method
	 * to further validate the constructor arguments passed in
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testItemConstructorValid() {
		
		//Test 1: Happy constructor calls with only difference being ItemPriority
		{
			try {
				Item chocolateItemWant = new Item("Chocolate", 2 , 1.50, ItemPriority.WANT);
				Item chocolateItemNeed = new Item("Chocolate", 2 , 1.50, ItemPriority.NEED);
				Item chocolateItemUrgent = new Item("Chocolate", 2 , 1.50, ItemPriority.URGENT);
				
				//if we reach here, move on to Test 2
				
			} catch (Exception e) { //should not catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		
		//Test 2: Happy constructor calls with only difference being Item Name
		{
			try {
				Item chocolate = new Item("Chocolate", 2 , 1.50, ItemPriority.WANT);
				Item chocolate2 = new Item("Chocolate2", 2 , 1.50, ItemPriority.WANT);
				Item chocolate3 = new Item("Chocolate3", 2 , 1.50, ItemPriority.WANT);
				
				//if we reach here, move on to test 3
				
			} catch (Exception e) { //should not catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		
		//Test 3: toString(), tests functionalities of Item construction inherently
		{
			try {
				Item chocolate = new Item("Chocolate", 2 , 1.5950, ItemPriority.WANT);
				
				String expectedString = "[CHOCOLATE | 2 | $1.60 | WANT]";
				String actualString = chocolate.toString();
				
				if (!expectedString.equals(actualString)) {
					System.out.println(chocolate);
					return false;
				}
			} catch (Exception e) { //should not catch any exceptions
				e.printStackTrace();
				return false;
			}
			
		}
		
		//if we reach here, all of our test cases have passed!
		return true;
}

	/**
	 * Tests several comparisons between created items and checks for proper
	 * output based on the comparison
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testItemCompareTo() {
		Item urgentItem = new Item("urgent", 5, 2, ItemPriority.URGENT);
		Item needItem = new Item("need", 5, 2, ItemPriority.NEED);
		Item wantItem = new Item("want", 5, 2, ItemPriority.WANT);
		
		//Test 1: Urgent over Need
		{
			boolean expectedComparison = true;
			boolean actualComparison = urgentItem.compareTo(needItem) > 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//Test 2: Urgent over Want
		{
			boolean expectedComparison = true;
			boolean actualComparison = urgentItem.compareTo(wantItem) > 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//Test 3: Need over Want
		{
			boolean expectedComparison = true;
			boolean actualComparison = needItem.compareTo(wantItem) > 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		
		//Test 4: Want less than Urgent
		{
			boolean expectedComparison = true;
			boolean actualComparison = wantItem.compareTo(urgentItem) < 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//Test 5: Need less than Urgent
		{
			boolean expectedComparison = true;
			boolean actualComparison = needItem.compareTo(urgentItem) < 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//Test 6: Want less than Need
		{
			boolean expectedComparison = true;
			boolean actualComparison = wantItem.compareTo(needItem) < 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		
		Item urgentSamePriceItem = new Item("urgeNT", 5, 2, ItemPriority.URGENT);
		Item urgentLowPriceItem = new Item("urgeNT", 5, 1, ItemPriority.URGENT);
		Item urgentHighPriceItem = new Item("urgeNT", 5, 10, ItemPriority.URGENT);
		
		//Test 7: Urgent equals Urgent
		{
			boolean expectedComparison = true;
			boolean actualComparison = urgentSamePriceItem.compareTo(urgentItem) == 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		
		//Test 8: Urgent Low less than Urgent High
		{
			boolean expectedComparison = true;
			boolean actualComparison = urgentLowPriceItem.compareTo(urgentHighPriceItem) < 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//Test 9: Urgent High more than Urgent Same
		{
			boolean expectedComparison = true;
			boolean actualComparison = urgentHighPriceItem.compareTo(urgentSamePriceItem) > 0;
			
			if (expectedComparison != actualComparison) {
				return false;
			}
		}
		
		//if we reach here, all of our test cases have passed!
		return true;
	}
	
	/**
	 * Tests the cases and edge cases of ItemNode and its functionalities
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testItemNode() {
		//create the items we will use
		Item firstItem = new Item("first", 1, 1, ItemPriority.NEED);
		Item secondItem = new Item("second", 2, 2, ItemPriority.NEED);
		Item thirdItem = new Item("third", 3, 3, ItemPriority.NEED);
		Item fourthItem = new Item("fourth", 4, 4, ItemPriority.NEED);
		
		
		try {
			
			//Test 1: Test the ItemNode one item constructor
			{
				ItemNode firstItemNode = new ItemNode(firstItem);
				
				//Test 1a: Validate fields of this ItemNode
				{
					int expectedItemPriority = 2;
					int actualItemPriority = firstItemNode.getItemPriority();
					
					if (expectedItemPriority != actualItemPriority) {
						return false;
					}
					
					double expectedItemPrice = 1.00;
					double actualItemPrice = firstItemNode.getItemPrice();
					
					if (expectedItemPrice != actualItemPrice) {
						return false;
					}
				}
				
				//Test 1b: Validate that there is no next node
				{
					ItemNode expectedNextItemNode = null;
					ItemNode actualNextItemNode = firstItemNode.getNextItemNode();
					
					if (expectedNextItemNode != actualNextItemNode) {
						return false;
					}
				}
			}
			
			//Test 2: Test the ItemNode two item constructor
			{
				ItemNode firstItemNode = new ItemNode(firstItem, secondItem);
				
				//Test 2a: Validate fields of the next item linked to firstItemNode
				{
					int expectedNextItemPriority = 2;
					int actualNextItemPriority = firstItemNode.getNextItemPriority();
					
					if (expectedNextItemPriority != actualNextItemPriority) {
						return false;
					}
					
					double expectedNextItemPrice = 2.00;
					double actualNextItemPrice = firstItemNode.getNextItemPrice();
					
					if (expectedNextItemPrice != actualNextItemPrice) {
						return false;
					}
				}
				
				//Test 2b: Validate that the next item is the second item
				{
					Item expectedNextItem = secondItem;
					Item actualNextItem = firstItemNode.getNextItemNode().getItem();
					
					if (!expectedNextItem.equals(actualNextItem)) {
						return false;
					}
				}
			}
	
			//Test 3: Validate linking additional items to the list
			{
				ItemNode firstItemNode = new ItemNode(firstItem);
				ItemNode secondItemNode = new ItemNode(secondItem);
				ItemNode thirdItemNode = new ItemNode(thirdItem);
				ItemNode fourthItemNode = new ItemNode(fourthItem);
				
				firstItemNode.setNextItemNode(secondItemNode);
				secondItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(fourthItemNode);
				
				
				//Test 3a: Validate that second item is next to first item
				{
					ItemNode expectedNextItemNode = secondItemNode;
					ItemNode actualNextItemNode = firstItemNode.getNextItemNode();
					
					if (!expectedNextItemNode.equals(actualNextItemNode)) {
						return false;
					}
				}
				
				//Test 3b: Validate that third item is next to second item
				{
					ItemNode expectedNextItemNode = thirdItemNode;
					ItemNode actualNextItemNode = secondItemNode.getNextItemNode();
					
					if (!expectedNextItemNode.equals(actualNextItemNode)) {
						return false;
					}
				}
				//Test 3c: Validate that the fourth item is next to third item
				{
					ItemNode expectedNextItemNode = fourthItemNode;
					ItemNode actualNextItemNode = thirdItemNode.getNextItemNode();
					
					if (!expectedNextItemNode.equals(actualNextItemNode)) {
						return false;
					}
				}
				
				//Test 4: Finally, go from the first node to the fourth node
				{
					ItemNode expectedLastItemNode = fourthItemNode;
					ItemNode actualLastItemNode = 
							firstItemNode.getNextItemNode().getNextItemNode().getNextItemNode();
					
					if (!expectedLastItemNode.equals(actualLastItemNode)) {
						return false;
					}
				}
				
			}
			
		} catch (Exception e) { //should not catch any exceptions
			e.printStackTrace();
			return false;
		}
		
		
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the invalid calls of ShoppingList and its functionalities
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testInvalidShoppingListCalls() {
		ShoppingList emptyShoppingList = new ShoppingList();
		Item firstItem = new Item("First", 1, 1.025, ItemPriority.WANT);
		
		
		//Test 1: Verify that indeed we have an empty shopping list 
		{
			int expectedSize = 0;
			int actualSize = emptyShoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
			
			boolean expectedEmpty = true;
			boolean actualEmpty = emptyShoppingList.isEmpty();
			
			if (expectedEmpty != actualEmpty) {
				return false;
			}
		}
		
		//Test 2: Test empty calls on contains(), indexOf(), get(), delete(), clear()
		{	//Test 2a: contains()
			{
				try {
					emptyShoppingList.contains(firstItem);
				} catch (IllegalStateException illStE) {
					//do nothing, should move on
				} catch (Exception e) {
					//should not catch any exceptions
					e.printStackTrace();
					return false;
				}
			}
			
			//Test 2b: indexOf()
			{
				try {
					emptyShoppingList.indexOf(firstItem);
				} catch (IllegalStateException illStE) {
					//do nothing, should move on
				} catch (Exception e) {
					//should not catch any exceptions
					e.printStackTrace();
					return false;
				}
			}
			
			
			//Test 2c: get()
			{
				try {
					emptyShoppingList.get(0);
				} catch (IllegalStateException illStE) {
					//do nothing, should move on
				} catch (Exception e) {
					//should not catch any exceptions
					e.printStackTrace();
					return false;
				}
			}
			
			
			//Test 2d: delete()
			{
				try {
					emptyShoppingList.delete(0);
				} catch (IllegalStateException illStE) {
					//do nothing, should move on
				} catch (Exception e) {
					//should not catch any exceptions
					e.printStackTrace();
					return false;
				}
			}
			
			
			//Test 2e: clear()
			{
				try {
					emptyShoppingList.clear();
				} catch (IllegalStateException illStE) {
					//do nothing, should move on
				} catch (Exception e) {
					//should not catch any exceptions
					e.printStackTrace();
					return false;
				}
			}
			
		}
		
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the calls to the addFirst() method of ShoppingList
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testAddFirst() {
		ShoppingList shoppingList = new ShoppingList();
		
		
		Item firstItem = new Item("First", 1, 1.00, ItemPriority.WANT);
		Item secondItem = new Item("Second", 2, 2.00, ItemPriority.NEED);
		Item thirdItem = new Item("Third", 3, 3.00, ItemPriority.URGENT);
		Item fourthItem = new Item("Fourth", 4, 4.00, ItemPriority.WANT);
		
		
		//Test 1: Add fourth item first and check that it's the head and tail
		shoppingList.addFirst(fourthItem);
		{ 
			
			Item expectedHeadItem = fourthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fourthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 1;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 2: Add third item first and check that it's the head
		shoppingList.addFirst(thirdItem);
		{ 
			
			Item expectedHeadItem = thirdItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fourthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 2;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 3: Add third item first and check that it's the head
		shoppingList.addFirst(secondItem);
		{ 
			
			Item expectedHeadItem = secondItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fourthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 3;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 4: Add first item first and check that it's the head
		shoppingList.addFirst(firstItem);
		{ 
			
			Item expectedHeadItem = firstItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fourthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 4;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		
		//Test 5: Verify correct positions of this shopping list: get() test
		{
			//Test 5a: First verify out of bounds calls
			{
				try {
					shoppingList.get(4);
				} catch (IndexOutOfBoundsException indexExc) {
					//do nothing, move on
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
				try {
					shoppingList.get(-1);
				} catch (IndexOutOfBoundsException indexExc) {
					//do nothing, move on
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
			//Test 5b: verify each item's position
			{
				Item expectedFirstItem = firstItem;
				Item actualFirstItem = shoppingList.get(0);
				
				if (!expectedFirstItem.equals(actualFirstItem)) {
					return false;
				}
				
				
				Item expectedSecondItem = secondItem;
				Item actualSecondItem = shoppingList.get(1);
				
				if (!expectedSecondItem.equals(actualSecondItem)) {
					return false;
				}
				
				
				Item expectedThirdItem = thirdItem;
				Item actualThirdItem = shoppingList.get(2);
				
				if (!expectedThirdItem.equals(actualThirdItem)) {
					return false;
				}
				
				
				Item expectedFourthItem = fourthItem;
				Item actualFourthItem = shoppingList.get(3);
				
				if (!expectedFourthItem.equals(actualFourthItem)) {
					return false;
				}
				
			}
		}
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the calls to addLast() method of ShoppingList
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testAddLast() {
		ShoppingList shoppingList = new ShoppingList();
		
		
		Item firstItem = new Item("First", 1, 1.00, ItemPriority.WANT);
		Item secondItem = new Item("Second", 2, 2.00, ItemPriority.NEED);
		Item thirdItem = new Item("Third", 3, 3.00, ItemPriority.URGENT);
		Item fourthItem = new Item("Fourth", 4, 4.00, ItemPriority.WANT);
		
		
		//Test 1: Add fourth item last and check that it's the head and tail
		shoppingList.addLast(fourthItem);
		{ 
			
			Item expectedHeadItem = fourthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fourthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 1;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 2: Add third item last and check that it's the tail
		shoppingList.addLast(thirdItem);
		{ 
			
			Item expectedHeadItem = fourthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = thirdItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 2;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 3: Add second item last and check that it's the tail
		shoppingList.addLast(secondItem);
		{ 
			
			Item expectedHeadItem = fourthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = secondItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 3;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 4: Add first item last and check that it's the tail
		shoppingList.addLast(firstItem);
		{ 
			
			Item expectedHeadItem = fourthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = firstItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			int expectedSize = 4;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		
		//Test 5: Verify correct positions of this shopping list: get() test
		{
			//Test 5a: First verify out of bounds calls
			{
				try {
					shoppingList.get(4);
				} catch (IndexOutOfBoundsException indexExc) {
					//do nothing, move on
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
				try {
					shoppingList.get(-1);
				} catch (IndexOutOfBoundsException indexExc) {
					//do nothing, move on
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
			//Test 5b: verify each item's position
			{
				Item[] itemsList = new Item[] {fourthItem, thirdItem, secondItem, firstItem};
				
				for (int i = 0; i < itemsList.length; i++) {
					if (!itemsList[i].equals(shoppingList.get(i))) {
						return false;
					}
				}
				
			}
		}
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the calls to add() method of ShoppingList, both invalid and valid calls
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testAdd() {
		ShoppingList shoppingList = new ShoppingList();
		
		Item zerothItem = new Item("Zeroth", 1, 0.01, ItemPriority.URGENT);
		Item firstItem = new Item("First", 1, 1.02333, ItemPriority.WANT);
		Item secondItem = new Item("Second", 2, 2.94053, ItemPriority.NEED);
		Item thirdItem = new Item("Third", 3, 3.34, ItemPriority.URGENT);
		Item fourthItem = new Item("Fourth", 4, 4.503, ItemPriority.WANT);
		Item fifthItem = new Item("Fifth", 5, 5.01, ItemPriority.NEED);
		Item sixthItem = new Item("Sixth", 6, 6.067, ItemPriority.URGENT);
		
		
		//Test 1: Invalid calls to add() method
		{
			try {
				shoppingList.add(firstItem, 1);
			} catch (IndexOutOfBoundsException idxE) {
				//do nothing move on
			} catch (Exception e) { //not supposed to catch any other exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		//Test 2: add items to the list and verify structure
		shoppingList.add(firstItem, 0);
		shoppingList.add(thirdItem, 1);
		shoppingList.add(fifthItem, 2);
		{
			Item[] expectedItemOrder = new Item[] {firstItem, thirdItem, fifthItem};
			
			for (int i = 0; i < expectedItemOrder.length; i++) {
				if (!expectedItemOrder[i].equals(shoppingList.get(i))) {
					return false;
				}
			}
			
			Item expectedHeadItem = firstItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fifthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			//Verify indices of a middle item since we already verified
			// head and tail and we only have a size of three items
			int expectedThirdItemIndex = 1;
			int actualThirdItemIndex = shoppingList.indexOf(thirdItem);
			
			if (expectedThirdItemIndex != actualThirdItemIndex) {
				return false;
			}
			
			int expectedSize = 3;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}
		
		//Test 3: Add items in between other items in the current list
		//and verify structure
		shoppingList.add(zerothItem, 0); //changes head
		shoppingList.add(secondItem, 2); //changes position
		shoppingList.add(fourthItem, 4); //changes position
		shoppingList.add(sixthItem, 6); //changes tail
		{
			Item[] expectedItemOrder = new Item[] 
					{zerothItem, firstItem, secondItem, thirdItem,
							fourthItem, fifthItem, sixthItem};
			
			for (int i = 0; i < expectedItemOrder.length; i++) {
				if (!expectedItemOrder[i].equals(shoppingList.get(i))) {
					return false;
				}
			}
			
			Item expectedHeadItem = zerothItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = sixthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			//Verify changes indices of items that changed positions
			int expectedFirstItemIndex = 1;
			int actualFirstItemIndex = shoppingList.indexOf(firstItem);
			
			if (expectedFirstItemIndex != actualFirstItemIndex) {
				return false;
			}
			
			
			int expectedThirdItemIndex = 3;
			int actualThirdItemIndex = shoppingList.indexOf(thirdItem);
			
			if (expectedThirdItemIndex != actualThirdItemIndex) {
				return false;
			}
			
			
			int expectedFifthItemIndex = 5;
			int actualFifthItemIndex = shoppingList.indexOf(fifthItem);
			
			if (expectedFifthItemIndex != actualFifthItemIndex) {
				return false;
			}
			
			int expectedSize = 7;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
		}

		//if we reach here, all of our test cases pass!
		return true;
	}
	
	
	/**
	 * Tests the valid calls of delete() method of ShoppingList
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testDelete() {
		ShoppingList shoppingList = new ShoppingList();
		
		Item firstItem = new Item("First", 1, 1.02333, ItemPriority.WANT);
		Item secondItem = new Item("Second", 2, 2.94053, ItemPriority.NEED);
		Item thirdItem = new Item("Third", 3, 3.34, ItemPriority.URGENT);
		Item fourthItem = new Item("Fourth", 4, 4.503, ItemPriority.WANT);
		Item fifthItem = new Item("Fifth", 5, 5.01, ItemPriority.NEED);
		Item sixthItem = new Item("Sixth", 6, 6.067, ItemPriority.URGENT);
		
		shoppingList.add(secondItem, 0);
		shoppingList.addFirst(firstItem);
		shoppingList.addLast(sixthItem);
		shoppingList.add(thirdItem, 2);
		shoppingList.add(fourthItem, 3);
		shoppingList.add(fifthItem, 4);
		
		//Pre-test list index range: [0, 5];
		
		//Test 1: Delete the head item (first item) and tail item (sixth item)
		// and verify non existence --> contains() test
		// and verify new structure 
		shoppingList.delete(0); // post-call index range: [0, 4]
		shoppingList.delete(4); // post-call index range: [0, 3];
		{
			
			Item expectedHeadItem = secondItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fifthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			
			boolean expectedContainsHeadItem = false;
			boolean actualContainsHeadItem= shoppingList.contains(firstItem);
			
			if (expectedContainsHeadItem!= actualContainsHeadItem) {
				return false;
			}
			
			boolean expectedContainsTailItem = false;
			boolean actualContainsTailItem = shoppingList.contains(sixthItem);
			
			if (expectedContainsTailItem != actualContainsTailItem) {
				return false;
			}
			
			Item[] expectedItemOrder = new Item[] {secondItem, thirdItem, fourthItem, fifthItem};
			
			for (int i = 0; i < expectedItemOrder.length; i++) {
				if (!expectedItemOrder[i].equals(shoppingList.get(i))) {
					return false;
				}
			}
			
			
			int expectedSize = 4;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
			
		}
		
		//Test 2: Delete middle items (third and fourth item)
		// and verify structure
		shoppingList.delete(1); //post-call index range: [0, 2]
		shoppingList.delete(1); //post-call index range: [0, 1]
		{
			Item expectedHeadItem = secondItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fifthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			Item[] expectedItemOrder = new Item[] {secondItem, fifthItem};
			
			for (int i = 0; i < expectedItemOrder.length; i++) {
				if (!expectedItemOrder[i].equals(shoppingList.get(i))) {
					return false;
				}
			}
			
			boolean expectedContainsThirdItem = false;
			boolean actualContainsThirdItem = shoppingList.contains(thirdItem);
			
			if (expectedContainsThirdItem != actualContainsThirdItem) {
				return false;
			}
			
			int expectedSize = 2;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}		
		}
		
		
		//Test 3: Delete one item (secondItem) and verify structure
		shoppingList.delete(0); //post-call index range: [0]
		{
			Item expectedHeadItem = fifthItem;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (!expectedHeadItem.equals(actualHeadItem)) {
				return false;
			}
			
			Item expectedTailItem = fifthItem;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (!expectedTailItem.equals(actualTailItem)) {
				return false;
			}
			
			boolean expectedContainsSecondItem = false;
			boolean actualContainsSecondItem = shoppingList.contains(secondItem);
			
			if (expectedContainsSecondItem != actualContainsSecondItem) {
				return false;
			}
			
			int expectedSize = 1;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
			 
		}
		
		
		//Test 4: Finally, delete the lone item (fifth item)
		// and verify structure
		shoppingList.delete(0);  //post-call index range:  N/A
		{	
			//Since list is now empty, we should catch an illegal state exception
			try {
				shoppingList.contains(fifthItem);
				
			} catch (IllegalStateException illStE) {
				//do nothing, move on
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			//verify size
			int expectedSize = 0;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
			
			//verify emptiness (really a reflection of size)
			boolean expectedEmpty = true;
			boolean actualEmpty = shoppingList.isEmpty();
			
			if (expectedEmpty != actualEmpty) {
				return false;
			}
		}
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	
	/**
	 * Tests the valid calls of clear() method of ShoppingList
	 * @return true if all tests pass, false otherwise
	 */
	private static boolean testClear() {
		//First, create the list and the items to go into the list
		//and add as necessary
		
		ShoppingList shoppingList = new ShoppingList();
		
		Item firstItem = new Item("First", 1, 1.02333, ItemPriority.WANT);
		Item secondItem = new Item("Second", 2, 2.94053, ItemPriority.NEED);
		Item thirdItem = new Item("Third", 3, 3.34, ItemPriority.URGENT);
		
		shoppingList.add(firstItem, 0);
		shoppingList.add(secondItem, 1);
		shoppingList.add(thirdItem, 2);
		
		//Test: call clear on the shopping list and verify structure
		shoppingList.clear();
		{
			Item expectedHeadItem = null;
			Item actualHeadItem = shoppingList.getHeadItem();
			
			if (expectedHeadItem != actualHeadItem) {
				return false;
			}
			
			Item expectedTailItem = null;
			Item actualTailItem = shoppingList.getTailItem();
			
			if (expectedTailItem != actualTailItem) {
				return false;
			}
			
			int expectedSize = 0;
			int actualSize = shoppingList.size();
			
			if (expectedSize != actualSize) {
				return false;
			}
			
			
		}
		
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the function of sortList() method of Shopping List
	 * @return true if all the tests pass, false otherwise
	 */
	private static boolean testSortShoppingList() {
		//EXPECTED SORTED ORDER IS ALWAYS: [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> null
		ShoppingList shoppingList = new ShoppingList();
		
		//Create all items and add out of order to the shopping list to test
		Item firstItem = new Item("Urgent First", 1, 10, ItemPriority.URGENT);
		Item secondItem = new Item("Urgent Second", 2, 8, ItemPriority.URGENT);
		Item thirdItem = new Item("Need First", 3, 10, ItemPriority.NEED);
		Item fourthItem = new Item("need Second", 4, 8, ItemPriority.NEED);
		Item fifthItem = new Item("WANT First", 5, 6, ItemPriority.WANT);
		Item sixthItem = new Item("Want Second ", 6, 4, ItemPriority.WANT);
		
		
		//Create the item nodes and link them out of order
		ItemNode firstItemNode = new ItemNode(firstItem);
		ItemNode secondItemNode = new ItemNode(secondItem);
		ItemNode thirdItemNode = new ItemNode(thirdItem);
		ItemNode fourthItemNode = new ItemNode(fourthItem);
		ItemNode fifthItemNode = new ItemNode(fifthItem);
		ItemNode sixthItemNode = new ItemNode(sixthItem);
		
		//Test 1: [1] -> [3] -> [5] -> [2] -> [4] -> [6] -> null
		{
			try {
				
				firstItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(fifthItemNode);
				fifthItemNode.setNextItemNode(secondItemNode);
				secondItemNode.setNextItemNode(fourthItemNode);
				fourthItemNode.setNextItemNode(sixthItemNode); 
			
				Item[] expectedItemOrder = new Item[] 
						{firstItem, secondItem, thirdItem, fourthItem, fifthItem, sixthItem};
				
				//Call the sort method to sort this list and store the new head so
				//we can traverse our newly sorted list and check for discrepancies
				ItemNode sortedNodeTraveler = shoppingList.sortList(firstItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = sortedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					sortedNodeTraveler = sortedNodeTraveler.getNextItemNode();
				}
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		firstItemNode.setNextItemNode(null);
		secondItemNode.setNextItemNode(null);
		thirdItemNode.setNextItemNode(null);
		fourthItemNode.setNextItemNode(null);
		fifthItemNode.setNextItemNode(null);
		sixthItemNode.setNextItemNode(null);
	
		//Test 2: // [2] -> [1] -> [6] -> [3] -> [5] -> [4] -> null
		{	
			try {
				secondItemNode.setNextItemNode(firstItemNode);
				firstItemNode.setNextItemNode(sixthItemNode);
				sixthItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(fifthItemNode);
				fifthItemNode.setNextItemNode(fourthItemNode);
				
				
				Item[] expectedItemOrder = new Item[] 
						{firstItem, secondItem, thirdItem, fourthItem, fifthItem, sixthItem};
				
				ItemNode sortedNodeTraveler = shoppingList.sortList(secondItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = sortedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					sortedNodeTraveler = sortedNodeTraveler.getNextItemNode();
				}
				
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		firstItemNode.setNextItemNode(null);
		secondItemNode.setNextItemNode(null);
		thirdItemNode.setNextItemNode(null);
		fourthItemNode.setNextItemNode(null);
		fifthItemNode.setNextItemNode(null);
		sixthItemNode.setNextItemNode(null);
	
		//Test 3: // [4] -> [1] -> [2] -> [3] -> [6] -> [5] -> null
		{	
			try {
				fourthItemNode.setNextItemNode(firstItemNode);
				firstItemNode.setNextItemNode(secondItemNode);
				secondItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(sixthItemNode);
				sixthItemNode.setNextItemNode(fifthItemNode);
				
				
				Item[] expectedItemOrder = new Item[] 
						{firstItem, secondItem, thirdItem, fourthItem, fifthItem, sixthItem};
				
				ItemNode sortedNodeTraveler = shoppingList.sortList(fourthItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = sortedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					sortedNodeTraveler = sortedNodeTraveler.getNextItemNode();
				}
				
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Tests the function of the reverseShoppingList() method of Shopping List
	 * @return true if all the tests pass, false otherwise
	 */
	private static boolean testReverseShoppingList() {
		ShoppingList shoppingList = new ShoppingList();
		
		//Create all items and add out of order to the shopping list to test
		Item firstItem = new Item("Urgent First", 1, 10, ItemPriority.URGENT);
		Item secondItem = new Item("Urgent Second", 2, 8, ItemPriority.URGENT);
		Item thirdItem = new Item("Need First", 3, 10, ItemPriority.NEED);
		Item fourthItem = new Item("need Second", 4, 8, ItemPriority.NEED);
		Item fifthItem = new Item("WANT First", 5, 6, ItemPriority.WANT);
		Item sixthItem = new Item("Want Second ", 6, 4, ItemPriority.WANT);
		
		
		//Create the item nodes and link them in a particular order
		ItemNode firstItemNode = new ItemNode(firstItem);
		ItemNode secondItemNode = new ItemNode(secondItem);
		ItemNode thirdItemNode = new ItemNode(thirdItem);
		ItemNode fourthItemNode = new ItemNode(fourthItem);
		ItemNode fifthItemNode = new ItemNode(fifthItem);
		ItemNode sixthItemNode = new ItemNode(sixthItem);
		
		//Test 1: [1] -> [2] -> [3] -> [4] -> [5] -> [6] -> null
		{
			try {
				
				firstItemNode.setNextItemNode(secondItemNode);
				secondItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(fourthItemNode);
				fourthItemNode.setNextItemNode(fifthItemNode);
				fifthItemNode.setNextItemNode(sixthItemNode); 
			
				Item[] expectedItemOrder = new Item[] 
						{sixthItem, fifthItem, fourthItem, thirdItem, secondItem, firstItem};
				
				//Call the sort method to sort this list and store the new head so
				//we can traverse our newly sorted list and check for discrepancies
				ItemNode reversedNodeTraveler = shoppingList.reverseShoppingList(firstItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = reversedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					reversedNodeTraveler = reversedNodeTraveler.getNextItemNode();
				}
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		firstItemNode.setNextItemNode(null);
		secondItemNode.setNextItemNode(null);
		thirdItemNode.setNextItemNode(null);
		fourthItemNode.setNextItemNode(null);
		fifthItemNode.setNextItemNode(null);
		sixthItemNode.setNextItemNode(null);
	
		//Test 2: // [2] -> [1] -> [6] -> [3] -> [5] -> [4] -> null
		{	
			try {
				secondItemNode.setNextItemNode(firstItemNode);
				firstItemNode.setNextItemNode(sixthItemNode);
				sixthItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(fifthItemNode);
				fifthItemNode.setNextItemNode(fourthItemNode);
				
				
				Item[] expectedItemOrder = new Item[] 
						{fourthItem, fifthItem, thirdItem, sixthItem, firstItem, secondItem};
				
				ItemNode reversedNodeTraveler = shoppingList.reverseShoppingList(secondItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = reversedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					reversedNodeTraveler = reversedNodeTraveler.getNextItemNode();
				}
				
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		
		firstItemNode.setNextItemNode(null);
		secondItemNode.setNextItemNode(null);
		thirdItemNode.setNextItemNode(null);
		fourthItemNode.setNextItemNode(null);
		fifthItemNode.setNextItemNode(null);
		sixthItemNode.setNextItemNode(null);
	
		//Test 3: // [6] -> [5] -> [4] -> [3] -> [2] -> [1] -> null
		{	
			try {
				sixthItemNode.setNextItemNode(fifthItemNode);
				fifthItemNode.setNextItemNode(fourthItemNode);
				fourthItemNode.setNextItemNode(thirdItemNode);
				thirdItemNode.setNextItemNode(secondItemNode);
				secondItemNode.setNextItemNode(firstItemNode);
				
				
				Item[] expectedItemOrder = new Item[] 
						{firstItem, secondItem, thirdItem, fourthItem, fifthItem, sixthItem};
				
				ItemNode reversedNodeTraveler = shoppingList.reverseShoppingList(sixthItemNode);
				
				for (int i = 0; i < expectedItemOrder.length; i++) {
					Item itemToCheck = reversedNodeTraveler.getItem();
					if (!itemToCheck.equals(expectedItemOrder[i])) {
						System.out.println("EXPECTED: " + expectedItemOrder[i]);
						System.out.println("ACTUAL: " + itemToCheck);
						return false;
					} 
					reversedNodeTraveler = reversedNodeTraveler.getNextItemNode();
				}
				
			} catch (Exception e) { //not supposed to catch any exceptions
				e.printStackTrace();
				return false;
			}
		}
		//if we reach here, all of our test cases pass!
		return true;
	}
	
	/**
	 * Runs all tests of the Shopping List Program and its functionalities
	 * and prints out the results of the tests
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("testItemConstructorInvalid: " + (testItemConstructorInvalid() ? "Pass" : "Failed"));
		System.out.println("testItemConstructorValid: " + (testItemConstructorValid() ? "Pass" : "Failed"));
		System.out.println("testItemCompareTo: " + (testItemCompareTo() ? "Pass" : "Failed"));
		System.out.println("testItemNode: " + (testItemNode() ? "Pass" : "Failed"));
		System.out.println("testInvalidShoppingListCalls: " + (testInvalidShoppingListCalls() ? "Pass" : "Failed"));
		System.out.println("testAddFirst: " + (testAddFirst() ? "Pass" : "Failed"));
		System.out.println("testAddLast: " + (testAddLast() ? "Pass" : "Failed"));
		System.out.println("testAdd: " + (testAdd() ? "Pass" : "Failed"));
		System.out.println("testDelete: " + (testDelete() ? "Pass" : "Failed"));
		System.out.println("testClear: " + (testClear() ? "Pass" : "Failed"));
		System.out.println("testSortShoppingList: " + (testSortShoppingList() ? "Pass" : "Failed"));
		System.out.println("testReverseShoppingList: " + (testReverseShoppingList() ? "Pass" : "Failed"));
	}

}
