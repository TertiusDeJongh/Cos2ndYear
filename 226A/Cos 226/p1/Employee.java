// Name:u19349302
// Student Number:Tertius de Jongh
public class Employee extends Thread{
	public String type;
	CarWash CW;
	
	public Employee(String type, CarWash cw){
		this.type = type;
		this.CW = cw;
	}
	
	public void run(){
		try{
			//wash the car here
			for(int i = 0; i<7; i++){
				while(CW.getWashSize() != 0 || CW.getDrySize() != 0){
					int low = 100;
					int high = 500;
					int range = high-low+1;
					int rand = (int)(Math.random()*range)+low;
					
					if(type == "washer" && CW.getWashSize() > 0){
						System.out.println(Thread.currentThread().getName()+" is ready to wash a car");
						CW.wash(rand);
						low = 50;
						high = 100;
						range = high-low+1;
						rand = (int)(Math.random()*range)+low;
						//now wash for rand amount of time
						
						System.out.println(Thread.currentThread().getName()+" is taking a break");
						Thread.sleep(rand);
					}
					if(type == "dryer" && CW.getDrySize() > 0){
						System.out.println(Thread.currentThread().getName()+" is ready to dry a car");
						CW.dry(rand);
						low = 50;
						high = 100;
						range = high-low+1;
						rand = (int)(Math.random()*range)+low;
						//now wash for rand amount of time
					
						System.out.println(Thread.currentThread().getName()+" is taking a break");
						Thread.sleep(rand);
					}
				
				}
				
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}