import java.util.concurrent.locks.Lock;
import java.util.Random;
// Name:u19349302
// Student Number:Tertius de Jongh

public class Store
{
	Lock l;
	public Store(Lock lock){
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
			System.out.println(Thread.currentThread().getName()+" "+person+" has entered the store");
			Thread.sleep(rand);//need to call the queue...
		}
		finally{
			l.unlock();
			System.out.println(Thread.currentThread().getName()+" "+person+" has left the store");
		}
	}
}
