/**
 * An enum to categorize items in three states:
 * <p>
 * 1) Urgent (Highest Priority)
 * <p>
 * 2) Need (High Priority)
 * <p>
 * 3) Want (Lowest Priority)
 * </p>
 */
public enum ItemPriority {
	URGENT(3), NEED(2), WANT(1);
	
	int numericPriority; //quantifies these ItemPriorities
	
	/**
	 * Built in constructor to associate a number to each ItemPriority
	 * @param numericPriority the number to associate to this ItemPriority
	 */
	ItemPriority (int numericPriority) {
		this.numericPriority = numericPriority;
	}
}
