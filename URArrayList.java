import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;
public class URArrayList<E> implements Iterable<E>, URList<E> {
	// private E element = null;
	public int cap, size;
	public E[] data;

	public URArrayList() {
		this.cap = 100;
		this.data = (E[]) new Object[cap];
		this.size = 0;
	}

	public URArrayList(int cap) {
		this.cap = cap;
		this.data = (E[]) new Object[cap];
		this.size = 0;
	}
	public void Capacity_Double() {
		// capacity doubled
		cap *= 2;
		E[] tempData = (E[]) new Object[cap];
		for (int i = 0; i < size; i++) {
			tempData[i] = data[i];
		}
		data = tempData;
	}

	public boolean add(E e) {
		
		if (cap <= size) {
			Capacity_Double();}	
		data[size] = e;
		size++; 
		

		return true;
	}
	
	public void printArray() {
		System.out.println(Arrays.toString(data));
	}

	public void add(int index, E element) {

		if (size >= cap)
			cap = cap*2; 

		if (index > size) {
			System.out.println("invalid input");
		}
		else {

		for (int i = size; i != index; i--)
			data[i] = data[i - 1];

		data[index] = element;
		size++;
		}

	}

	public boolean addAll(int index, Collection<? extends E> c)  {
		if (index > size) {
			System.out.println("invalid input");
			return false;
		}
		for (E i : c) {
			add(index, i);
			index++;
		}
		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E i : c) {
			add(i);
		}
		return true;
	}

	public void clear() {
		
		for (int i = 0; i < size; i++) {
			data[i] = null;

		}
		size = 0;
	}

	public boolean contains(Object o){
		if (size == 0) {
			return false;
		}
		else{
			for (E i : data) {
			if (i.equals(o)) {
				return true;
			}
		}
		return false;
		}
	}


	public boolean remove(Object o) {
		
		int index = -1;
		for (int j = 0; j < size; j++) {
			if (data[j].equals(o)) {
				index = j;
			}
		}

		if (index != -1) {
			for (int i = index; i < size; i++) {
				data[i] = data[i+1];
			}
			size--;
			System.out.println(cap);			
			return true;
		}

		return false;
	}
	
	public E remove(int index) {
		if (index >0||index<size) {
			for (int i = index; i < size; i++) {
				data[i] = data[i+1];
			}
			size--;
			return data[index];
		
			
		}
		else {
			System.out.println("Invalid input");
			return null;
		}
	}

	public boolean containsAll(Collection<?> c){
		E[] tempData = (E[]) new Object[cap];
		for (int i = 0; i < size; i++) {
			tempData[i] = data[i];
		}
		data = tempData;
		int collectionSize = 0;
		for (Object a : c) {
			collectionSize++;
		}
		int counter = 0;
		for (int k = 0; k < size; k++) {
			for (Object j : c) {
				if (tempData[k].equals(j)) {
					counter++;

				}
			}
		}

		return (counter == collectionSize);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null) {
			return false;
		}
		return false;
	}

	public E get(int index)  {
		if(index>size || index<0)
		{
			System.out.println("Invalid input");
			return null;
		}
		else {
	
		return data[index];
	}
			}

	public int indexOf(Object o) {

		for(int i=0;i<size;i++) {
			if(data[i].equals(o))
				return i;
		}
		return -1;
	}

	public boolean isEmpty() {
		if(size == 0) {
		return true;
	}
		else {
			return false;
		}
	}



	public boolean removeAll(Collection<?> c){
		for(Object i:c) {
			remove(i);
		}		
		return true;
	}

	public E set(int index, E element){
		data[index] = element;
		return element;
	}

	public Iterator<E> iterator() {
		
		return this.iterator();
	}

	
	public Object[] toArray() {
		Object[] array=new Object[size];
		for(int i=0;i<size;i++) {
			array[i]=data[i];
		}
		return array;
	}

	
	public URArrayList<E> subList(int fromIndex, int toIndex) {
		URArrayList<E> mySubList=new URArrayList<E>();
		for(int i=fromIndex;i<toIndex;i++) {
			mySubList.add(data[i]);
		}
			
		return mySubList;
	}

	public int size() {

		return size;
	}

	public void ensureCapacity(int minCapacity) {
		while (cap <= minCapacity) {
			cap = cap *2;
		}
	}

	public int getCapacity() {
		return cap;
	}

	public static void main(String args[]) {
		URArrayList<String> myArr = new URArrayList<String>(10);

		myArr.add("a");
		myArr.add("b");
		myArr.add("c");
		myArr.add("d");
		myArr.add("e");
		myArr.add("f");
		myArr.add("a");
		myArr.add("a");
		myArr.add("b");
		myArr.add("c");
		myArr.add("d");
		myArr.add("e");
		myArr.add("f");
		myArr.add("a");
		myArr.add("b");
		myArr.add("c");
		myArr.add("d");
		myArr.add("e");
		myArr.add("f");
		myArr.add("a");
		myArr.add("z");

		myArr.printArray();
		
		System.out.println(Arrays.toString(myArr.toArray()));
		myArr.set(0, "z");

//		myArr.printArray();
//		System.out.println("here is the size of the list: " + myArr.size());
		
//		
//		
		System.out.println(myArr.contains("b"));
//		
		System.out.println("it does contain " + myArr.size());
		System.out.println(myArr.indexOf(1));
		myArr.clear();
	}



}
