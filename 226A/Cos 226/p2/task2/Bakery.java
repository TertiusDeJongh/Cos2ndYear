import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
// Name:u19349302
// Student Number:Tertius de Jongh

public class Bakery implements Lock
{
	volatile boolean flag[];
	volatile int label[];
	int num;
	
	public Bakery(int n){
		flag = new boolean[n];
		label = new int[n];
		num = n;
		for(int i = 0;  i<n; i++){
			flag[i] = false;
			label[i] = 0;
		}
	}
	public void lock(){
		int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
		flag[i] = true;
		int ii = 0;
		int big = label[i];
		while(ii<num){
			if(label[ii] >= big){
				big = label[ii]+1;
			}
			ii++;
		}
		label[i] = big;
		for(int k = 0; k<num; k++){
			while(flag[k] && (label[k] < label[i])){}
		}
	}
	
	public void unlock(){
		int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
		flag[i] = false;
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
