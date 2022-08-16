import java.util.concurrent.locks.Lock;
import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
// Name:u19349302
// Student Number:Tertius de Jongh

public class Store
{
	Semaphore l;
	boolean debug = true;
	public Store(Semaphore lock){
		this.l = lock;
	}

	public void enterStore(int person) throws InterruptedException{
		l.lock();
		try{
			//random sleep amount...
			int low = 200;
			int high = 1000;
			int range = high-low+1;
			int rand = (int)(Math.random()*range)+low;
			//now sleep
			int space = l.getState();
			if(debug){
				System.out.println(Thread.currentThread().getName()+" Person "+person+" has entered the store. Spaces remaining: " + space+" Timestamp: "+ System.currentTimeMillis());
			}
			else{
				System.out.println(Thread.currentThread().getName()+" Person "+person+" has entered the store. Spaces remaining: " + space);
			}
			
			Thread.sleep(rand);//need to call the queue...
		}
		finally{
			if(debug){
				System.out.println(Thread.currentThread().getName()+" Person "+person+" has left the store"+" Timestamp: "+ System.currentTimeMillis());
			}
			else{
				System.out.println(Thread.currentThread().getName()+" Person "+person+" has left the store");
			}
			
			l.unlock();
		}
	}
}
