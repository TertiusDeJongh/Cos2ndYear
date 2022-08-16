import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name:Tertius de Jongh
// Student Number:u19349302

public class TTAS implements Lock
{
	AtomicBoolean state = new AtomicBoolean(false);
	/*public TTAS(int n){
		
	}//*/
	//we do lock and unlock
	
	public void lock(){
		while(true){
			while(state.get()){}
			if(!state.getAndSet(true)){
				return;
			}
			
		}
	}
	
	public void unlock(){
		state.set(false);
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