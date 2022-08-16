// Name:Tertius de Jongh
// Student Number:u19349302


public class Main {

    public static void main(String[] args) {
	//task1
	Queue[] queues = new Queue[4];
	    
	Filter fil = new Filter(4);

        Store store = new Store(fil);

        for(int i = 0; i < 4; i++)
            queues[i] = new Queue(store);

        for(Queue queue : queues)
            queue.start();//*/
		
	//System.out.println("Run the program again for task2, go to the main:");
	//task2:
	/*Queue[] queues = new Queue[4];
	    
	Bakery fil = new Bakery(4);

        Store store = new Store(fil);

        for(int i = 0; i < 4; i++)
            queues[i] = new Queue(store);

        for(Queue queue : queues)
            queue.start();//*/
    }
}
