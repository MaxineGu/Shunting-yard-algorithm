//Jiayi Gu


public class Queue<E> {
	URNode<E> first = null;
	URNode<E> last = null;
	int count = 0;

	public void enqueue(E data) {
		URNode<E> newNode = new URNode<E>(data, null, null);
	if (first == null) {
		//if queue is null
		first = newNode;
		last = newNode;	
		count = count + 1;
	}
	else {
		
		last.setNext(newNode);
		last = newNode;
		last.setNext(null);
		count = count + 1;		
	}
}
	public E dequeue() {
		if (first == null) {
			return null;
		}
		E temp = first.element();
		if(first == last) {
			last = null;
			first = null;
		}
		else {
//			last = last.prev();
//			last.setNext(null);
			first = first.next();
			first.setPrev(null);
		}
		count = count - 1;
		
		
		return temp;
	}
	public boolean isEmpty() {
		if (first == null) {
			return true;
		}
		else {
			return false;
		}
	}
	public E first() {
		return first.element();
	}
	public void printqueue() {
		if (first == null) {
			System.out.println("This queue is null");
		}
		else {
			URNode<E> current = first;
			
			while(current != null) {
			System.out.print(current.element());			
			current = current.next();
			if (current != null) {
				System.out.print("->");
			}
		}
	}
	}
	public E get_first() {
		return first.element();
	}
	public static void main(String[] args) {
		Queue <String> stack = new Queue <String>();
		stack.enqueue("17");
		stack.enqueue("24");
		stack.enqueue("18");
		stack.dequeue();
		stack.printqueue();
	
		
		
		
		
	}
}
