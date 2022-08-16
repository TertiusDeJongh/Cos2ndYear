import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
// Name:u19349302
// Student Number:Tertius de Jongh

public class CarWash
{
	public Lock lockW;
	public Lock lockD;
	
	private volatile Queue<Car> washCars = new LinkedList<>(), dryCars = new LinkedList<>();

	public CarWash(Lock lockW, Lock lockD){
		this.lockW = lockW;
		this.lockD = lockD;
		washCars.add(new Car('s', "Panda"));
		washCars.add(new Car('m', "Cerato"));
		washCars.add(new Car('s', "Swift"));
		washCars.add(new Car('l', "Defender"));
		washCars.add(new Car('m', "A3"));
		washCars.add(new Car('l', "Ranger"));
		washCars.add(new Car('s', "GTI"));
	}
	
	public void wash(int timeWorking) throws InterruptedException{
		//the critical section...
		//locked right here
		//run the length of the queue.
		lockW.lock();//not made yet..
		try{
			//pop
			if(!washCars.isEmpty()){
				Car currentCar = washCars.peek();
				Thread.sleep(timeWorking);
				//subtract the time washed from time worked
				int timeWashed = (int)currentCar.washTime-timeWorking;
				currentCar.washTime = currentCar.washTime-(long)timeWorking;
				if(currentCar.washTime <= 0){
					currentCar = washCars.remove();
					dryCars.add(currentCar);
					System.out.println(Thread.currentThread().getName()+" is finished washing "+currentCar.name);
				}
				else{
					System.out.println(Thread.currentThread().getName()+" washed "+currentCar.name+" for "+timeWorking+". Time remaining: "+timeWashed);
				}
				
			}
			
		}
		finally{
			lockW.unlock();
		}
	}
	
	public void dry(int timeWorking) throws InterruptedException{
		//the critical section...
		//locked right here
		//run the length of the queue.
		lockD.lock();//not made yet..
		try{
			//pop
			if(!dryCars.isEmpty()){
				Car currentCar = dryCars.peek();
				Thread.sleep(timeWorking);
				//subtract the time washed from time worked
				int timeDried = (int)currentCar.dryTime-timeWorking;
				currentCar.dryTime = currentCar.dryTime-(long)timeWorking;
				if(currentCar.dryTime <= 0){
					currentCar = dryCars.remove();
					//dryCars.add(currentCar);
					System.out.println(Thread.currentThread().getName()+" is finished drying "+currentCar.name);
				}
				else{
					System.out.println(Thread.currentThread().getName()+" dried "+currentCar.name+" for "+timeWorking+". Time remaining: "+timeDried);
				}
				
			}
			
		}
		finally{
			lockD.unlock();
		}
	}
	
	//helper 
	public int getWashSize(){
		return this.washCars.size();
	}
	
	public int getDrySize(){
		return this.dryCars.size();
	}


}
