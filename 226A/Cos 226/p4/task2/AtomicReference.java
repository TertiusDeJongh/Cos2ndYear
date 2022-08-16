public class AtomicReference{
	
	Qnode value = new Qnode();
	
	public AtomicReference(){}
	
	public synchronized Qnode getAndSet(Qnode newValue){
		Qnode prior = value;
		value = newValue;
		return prior;
	}
	
	public  synchronized  boolean compareAndSet(Qnode expected, Qnode update){//actually increment but anyway...
		Qnode prior = this.value;
		if(this.value == expected){
			this.value = update;
			return true;
		}
		return false;
	}
}