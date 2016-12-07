public class arrayQueue <AnyType> {
	private int front; 
	private int rear; 
	private int size;
	AnyType [] data;
	public arrayQueue (int capacity) { 
		data = (AnyType[]) new Object [capacity];
		front = rear = -1;
		size=0;
	}
	public boolean isEmpty() { 
		return (front == -1 && rear == -1);
	}
	public int size() { 
		return size;
	}
	
	public void Enqueue (AnyType newValue) { 
		if((rear+1) % data.length == front) return; 
		else {
			if (isEmpty()) { 
			front++; 
			rear++; 
			data[rear] = newValue;
		}
		else { 
			rear = (front + size()) %data.length; 
			data[rear] = newValue;
			}
			size++;
		}
	}
	public void Dequeue () { 
		if(isEmpty()) return;
		else {
			if (front == rear) { 
			front=-1; 
			rear =-1; 
		}
		else { 
			front = (front + 1) % data.length;
			}
			size--;
		}
	}
	public void printElement () { 
		for(int i = front%data.length; i< rear+1%data.length; i++) { 
			System.out.println(data[i]);
		}
	}
}
