
public interface ListADT <T> {

	public int size();
	public boolean isEmpty();

	public boolean contains(T item);
	public int indexOf(T item);
	public T get(int index);

	public void addLast(T newItem);
	public void addFirst(T newItem);
	public void add(T newItem, int index);

	public T delete(int index);
	
	public void clear();
	
}