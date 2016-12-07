public class stackArray<AnyType> {

	AnyType [] data;
	int defaultcapacity = 100;
	int topOfStack;
	
	public stackArray () { 
		data = (AnyType[]) new Object  [defaultcapacity]; 
		topOfStack = -1;
	}
	public stackArray (int capacity) { 
		data = (AnyType[]) new Object  [capacity];  
		topOfStack = -1;
	}
	public boolean isEmpty() { 
		return topOfStack == -1; 
	}
	
	public void push (AnyType newValue) { 
			if(topOfStack >= (data.length-1)) resize ();
			data[++topOfStack] = (AnyType)newValue;
	}
	
	public void pop() { 
		if (isEmpty()) return;
		else { 
			if(topOfStack <= (data.length/2)) resize();
			topOfStack--; 
		} 
	}
	
	public void resize () { 
		AnyType [] temp = (AnyType[]) new Object [data.length *2]; 
		for (int i =0;i< data.length; i++) { 
			temp [i] = data[i]; 
		}
		data = temp;
	}
	public AnyType peek() { 
		return (AnyType)data[0];
	}
	
	public void printElement () { 
		for(int i =0; i < data.length; i++) { 
			System.out.println(data[i]);
		}
	}
}
