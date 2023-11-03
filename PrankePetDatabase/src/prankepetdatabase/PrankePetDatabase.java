
package prankepetdatabase;

import java.util.ArrayList;
import java.util.Scanner;


/**
Author: Brandon Pranke
* Date 11/2/2023
* Assignment 1 - Create a database for pets
 */


public class PrankePetDatabase {

    public static void main(String[] args) {
        //Displays the message to the user
        System.out.println("Pet Database Program");
        
        //Makes an arraylist to store pet objects
        ArrayList<Pet> pets = new ArrayList<Pet>();
        
        //Calls the main menu method to display the main menu
        mainMenu(pets);
        System.out.println("GoodBye!");
    }
    

    //Method for displaying the main menu
    public static void mainMenu(ArrayList<Pet> pets){
        int choice = 0;
        while (choice !=7){
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat would you like to do\n1) View all pets\n2) Add more pets");
	System.out.println("3) Update an existing pet\n4) Remove an existing pet");
	System.out.println("5) Search pets by name\n6) Search pets by age\n7)Exist program");
	System.out.print("Your choice: ");
        
        //Grabs the user selection of the main menu
         choice = input.nextInt();
        
        //Calls a different method depending on the choice the made
        switch(choice){
            case 1: showAllpets(pets);break;
            case 2: addPets(pets);break;
            case 3: updatePet(pets);break;
            case 4: removePet(pets);break;
            case 5: searchPetName(pets);break;
            case 6: searchPetAge(pets);break;
            default: break;
        }
    }
    }
    
    //Method for adding pets to the array list
    public static void addPets(ArrayList<Pet> pets) {
        Scanner input = new Scanner(System.in);
        String userInput = "not done";
        int petCounter = 0;
        //Loop keeps going until user inputs done
        while(! userInput.equals("done")) {
            System.out.print("add pet (name, age): ");
            userInput = input.nextLine();
            //If user adds a pet, it uses the infor to make a new Pet oject
            if(! userInput.equals("done")){
                String [] petData = userInput.split(" ");
                String petName = petData[0];
                int petAge = Integer.parseInt(petData[1]);
                //Adds thenew pet to the array and counts how many pets have beeen added
                Pet pet = new Pet(petName, petAge);
                pets.add(pet);
                petCounter++;
            }
            else{
                //Shows users how many pets have been added 
                System.out.println(petCounter + " pets added");
            }
        }
    }
    
    

    
    //Method that prints a table header 
    static void printTableHeader() { 
	System.out.printf("+-------------------------+\n| %3s | %10s | %4s | \n", "ID", "NAME", "AGE");
	System.out.print("+-------------------------+");
    }
    
    
    //Method that prints a table row 
    static void printTableRow(int id, String name, int age) { 
	System.out.printf("\n| %3d | %10s | %4d |", id, name, age);
	}
    
    //method that prints a table footer
    static void printTableFooter(int rowCount) { //Method that displays the number of rows
	System.out.println("\n+-------------------------+\n" + rowCount + " rows in set");
	}
    
    //Method allows user to search pet by age
    public static void searchPetAge(ArrayList<Pet> pets){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter age to search: ");
        int petAge = input.nextInt();
        printTableHeader();
        int petCounter = 0;
        //Loops through each pet and compares it to the name the user put in
        for(int i = 0; i < pets.size(); i++){
            Pet pet = pets.get(i);
            int age = pet.getAge();
            String name = pet.getName();
            if(age == petAge){
                printTableRow(i, name, age);
                petCounter++;
            }
        }
        printTableFooter(petCounter);
    }
    
    //Method allowing User to search for pet by name
    public static void searchPetName(ArrayList<Pet>pets) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name to search: ");
        String petName = input.next().toLowerCase();
        printTableHeader();
        int petCounter = 0;
        for(int i = 0; i < pets.size(); i++){
            Pet pet = pets.get(i);
            String name = pet.getName();
            String lowerName = name.toLowerCase();
            int age = pet.getAge();
            if(petName.equals(lowerName)){
                printTableRow(i, name, age);
                petCounter++;
            }
        }
        printTableFooter(petCounter);
    }
    
    //Method for removing Pet
    public static void removePet(ArrayList<Pet> pet){
        //Displays all the pets
        Scanner input = new Scanner(System.in);
        showAllpets(pet);
        
        //Gets the id for the removed pet from user
        System.out.print("Enter the pet Id to remove: ");
        int id = input.nextInt();
        
        //Gets the removed pet name and age
        Pet noPet = pet.get(id);
        String name = noPet.getName();
        int age = noPet.getAge();
        
        //Displays message
        System.out.print(name + " " + age + " is removed");
        
        //Removes pet and brings user to main menu
        pet.remove(id);
        
        
    }
    //Method for displaying all pets in the array list
    public static void showAllpets(ArrayList<Pet> pets){
        //Calls the table header method to display table header
        printTableHeader();
        
        //Goes through each pet object in the array and grabs its name and age
        for(int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            int id = i;
            String name = pet.getName();
            int age = pet.getAge();
            
            //Calls method to display a row with the pet
            printTableRow(id, name, age);
        }
        
        //Calls method to print table footer
        printTableFooter(pets.size());
    }
    
    public static void updatePet(ArrayList<Pet> pet){
        //Displays all the pets
        Scanner input = new Scanner(System.in);
        showAllpets(pet);
        
        //Gets the id of the pet to update
        System.out.print("Enter the pet ID you wish to update: ");
        int id = input.nextInt();
        
        //Gets the new name and age
        System.out.print("Enter new name and new age: ");
        String name = input.next();
        int age = input.nextInt();
        
        //Gets the orginal pet info
        Pet oldPet = pet.get(id);
        String oldName = oldPet.getName();
        int oldAge = oldPet.getAge();
        
        //Displays message
        System.out.println(oldName + " " + oldAge + " changed to " + name + " " + age);
        
        //Updates the pet
        oldPet.setAge(age);
        oldPet.setName(name);
        
    }

}
