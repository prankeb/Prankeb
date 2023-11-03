
package prankepetdatabase;

public class Pet {
    private String name;
    private int age;
    
    ///Constructor for the pet class
    public Pet(String name, int age) { 
	this.name = name;
	this.age = age;
		}
    
    //Method for getting the name of a pet
    public String getName() { 
	return name;
	}
	
    //Method for getting the name of a pet
    public  int getAge(){ 
	return age;
	}
    //Method for changing the name of a pet
    public void setName(String name) { 
	this.name = name;
	}
	
    //Method for changing the age of a pet
    public void setAge(int age) { 
	this.age = age;
	}
}
