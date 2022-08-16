import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.AtomicReference;

// Name:Tertius de Jongh
// Student Number:u19349302

public class MCS implements Lock
{
	AtomicReference<Qnode> tail = new AtomicReference<Qnode>(null);//but this not good??
	Qnode qnode;
	//Qnode pred;
	boolean debug = false;
	
	public void lock(){
		qnode = new Qnode();//could be here... false here
		//System.out.println("run here??");
		Qnode pred = tail.getAndSet(qnode);
		//System.out.println("run here?? again");
		if(pred != null){
			qnode.locked = true;//set it to true
			pred.next = qnode;
			//boolean thing = qnode.locked;//just to waste time if i need to...
			while(qnode.locked){//above thing not working tho...
				if(debug){
					//System.out.println("while in lock");
					OutputList(pred);
				}//*/
			}
		}
	}
	
	public void unlock()
	{
		if(qnode.next == null)
		{
			if(tail.compareAndSet(qnode, null))
				return;
			
			//Qnode thingy = qnode.next;
			System.out.println("tail "+tail);
			while(qnode.next == null)
			{
				if(debug)
				{
					System.out.println("from the while in unlock "+tail);
				}//*/
			}
		}
		qnode.next.locked = false;//we just make the next one 
		qnode.next = null;
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
	
	public void OutputList(Qnode ptr){
		Qnode curr = ptr;
		while(curr != null){
			
			System.out.println(" output next node "+curr.next);
			curr = curr.next;
		}
		
	}

}