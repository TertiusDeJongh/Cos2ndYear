import java.util.LinkedList;
import java.util.Queue;
public class Main{
	 public static void main(String[] args) {
		//make variable num of employees:
		
		 //make locks
		 Bakery lockD = new Bakery(6);
		 Bakery lockW = new Bakery(6);
		 
		 CarWash cw = new CarWash(lockW, lockD);
		 
		 //make 4 employees
		 int numWorkers = 12;
		Employee[] ems = new Employee[numWorkers*2];
		
		for(int i = 0; i< numWorkers*2; i++){
			if(i<numWorkers){
				ems[i] = new Employee("washer", cw);
			}
			else if(i>=numWorkers){
				ems[i] = new Employee("dryer", cw);
			}
		}
		
		for(int i =0; i<numWorkers*2; i++){
			ems[i].start();
		}
    }
}