// Name:Tertius de Jongh
// Student Number:u19349302
public class Queue extends Thread
{
	Store store;

	public Queue(Store s){
		store = s;
	}

	@Override
	public void run()
	{
		//don't know how we lock this but ok...
		try{
			for(int i = 0; i<5; i++){
				System.out.println(Thread.currentThread().getName()+" Person "+i+" is trying to get inside.");
				store.enterStore(i);
			}
		}
		catch(Exception e){
			System.out.println("WHoops!!!");
		}
	}
}
