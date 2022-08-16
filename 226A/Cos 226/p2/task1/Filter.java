import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name:Tertius de Jongh
// Student Number:u19349302

public class Filter implements Lock
{
	volatile int level[];
	volatile int victim[];
	int num;
	
	public Filter(int n){
		level = new int[n];
		victim = new int[n];
		num = n;
		for(int i = 0; i<n; i++){
			level[i] = 0;
		}
	}
	//we do lock and unlock
	
	public void lock(){
		int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
		for(int L = 1; L<num; L++){
			level[i] = L;
			victim[L] = i;
			for(int k = 0; k < num; k++){
				while((k != i) && (level[k] >= L && victim[L] == i)){}
			}
		}
	}
	
	public void unlock(){
		int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
		level[i] = 0;
	}

	public void lockInterruptibly() throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}

}
