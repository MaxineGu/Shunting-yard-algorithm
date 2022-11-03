
public class Stack<E> {
	URNode<E> first = null;
	URNode<E> last = null;
	int count = 0;
	public void push(E data) {
		URNode<E> newNode = new URNode<E>(data, null, null);
		if (first == null) {
			first = newNode;
			count = count + 1;
			last = newNode;
			
			
		}
		else {
			first.setPrev(newNode);
			newNode.setNext(first);
			first=newNode;			
			count++;
			
		}
	}
		public E pop() {
			if (first == null) {
				return null;
			}
			E temp = first.element();

			if (first == last) {
				first = null;
				last = null;
			}
			else {
			
			first = first.next();
			first.setPrev(null);

			}
//			System.out.println("last is" + last.element());

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
		public E get_top() {
			return first.element();
		}
		public void printStack(){
			if (first == null) {
				System.out.println("This stack is null");
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
		
		public static void main(String[] args) {
			Stack <String> stack = new Stack <String>();
			stack.push("/");
			stack.push("(");
			stack.push("*");
			
			stack.pop();
			stack.printStack();
		
			
			
			
			
		}
}

