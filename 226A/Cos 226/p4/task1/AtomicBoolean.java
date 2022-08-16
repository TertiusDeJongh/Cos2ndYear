public class AtomicBoolean{
	boolean value;
	
	public AtomicBoolean(boolean val){
		this.value = val;
	}
	
	public synchronized boolean getAndSet(boolean newValue){
		boolean prior = value;
		value = newValue;
		return prior;
	}
	
	public synchronized void set(boolean val){
		this.value = val;
	}
	
	public synchronized boolean get(){
		return this.value;
	}
}