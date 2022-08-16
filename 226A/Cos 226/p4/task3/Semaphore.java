//import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.Condition.await

public class Semaphore{
	ReentrantLock lock = new ReentrantLock();//which one whould this be??? needs to be reentrant or something...
	Condition condition = lock.newCondition();//this is latin tho... just assume it works...
	int capacity = 3;
	int state = 0;
	
	public void lock(){
		lock.lock();// no idea what this is... needs to be reentrant lock?
		try{
			//int space = capacity-state;
			//space++;
			//System.out.println("Remaining space left "+space);
			while(state == capacity){//this so it simulates only three spaces... this a bit different...
				try{
					condition.await();//the heck??
				}
				catch(Exception e){
					//actualluy just output the exception...because they are useful
				}
			}
			state++;
		}
		finally{
			lock.unlock();
		}
	}
	
	public void unlock(){
		lock.lock();
		try{
			state--;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public int getState(){
		//int cap = capacity-1;
		return capacity-state;
	}
}