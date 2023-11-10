
package prankepetdatabase;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


/**
Author: Brandon Pranke
* Date 11/2/2023
* Assignment 1 - Create a database for pets
 */


public class PrankePetDatabase {

    public static void main(String[] args) throws Exception {
        //Displays the message to the user
        System.out.println("Pet Database Program");
        
        //Makes an arraylist to store pet objects
        ArrayList<Pet> pets = new ArrayList<Pet>();
        
        //Variable for the file name
        String filename = "pets.txt";
        
        //Loads the database if needed
        loadDatabase(pets,filename);
        
        //Calls the main menu method to display the main menu
        mainMenu(pets);
        System.out.println("GoodBye!");
        
        //Saves the database to a text file
        saveDatabase(pets,filename);
    }
    

    //Method for displaying the main menu
    public static void mainMenu(ArrayList<Pet> pets){
        int choice = 0;
        while (choice !=4){
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat would you like to do\n1) View all pets\n2) Add more pets");
	System.out.println("3) Remove a pet \n4) Exit program");
	//System.out.println("5) Search pets by name\n6) Search pets by age\n7)Exist program");
	System.out.print("Your choice: ");
    
        //Grabs the user selection of the main menu
         choice = input.nextInt();
        System.out.print("\n");
        //Calls a different method depending on the choice the made
        switch(choice){
            case 1: showAllpets(pets);break;
            case 2: addPets(pets);break;
            case 3: removePet(pets);break;
            //case 4: removePet(pets);break;
            //case 5: searchPetName(pets);break;
            //case 6: searchPetAge(pets);break;
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
            
            //Checks the size of the array and if it is over 5 gives user error
            if(pets.size() >= 5 && ! userInput.equals("done")) {
                System.out.println("Error: Database is full");
                //Ends the loop
                userInput = "done";
            }
            
            else{
                //If user adds a pet, it uses the info to make a new Pet oject
                if(! userInput.equals("done")){
                    String [] petData = userInput.split(" ");
                    
                    //if there is not 2 values inputed by user, gives user error
                    if(petData.length != 2){
                        System.out.println("Error: " + userInput + " is not a valid input");
                        continue;
                    }
                    
                    String petName = petData[0];
                    int petAge = Integer.parseInt(petData[1]);
                    
                    //If pet age is not between 1 and 20 gives error to user
                    if(petAge < 1 || petAge > 20){
                        System.out.println("Error: " + petAge + " is not a valid age");
                    }
                    else{
                        //Adds thenew pet to the array and counts how many pets have beeen added
                        Pet pet = new Pet(petName, petAge);
                        pets.add(pet);
                        petCounter++;
                    }
            }
                else{
                    //Shows users how many pets have been added 
                    System.out.println(petCounter + " pets added");
            }
            }
        }
    }
    
    //Method for loading the file of pets
    static void loadDatabase(ArrayList<Pet> pets, String filename)throws Exception{
        //makes a file object
        File fileCheck = new File(filename);
        
        //Checks if the database has been saved before
        if(fileCheck.exists()){
        Scanner file = new Scanner(fileCheck);
        
        //Goes through each line of the file and turns the data into pet objects
        while (file.hasNext()){
            String petName = file.next();
            int petAge = file.nextInt();
            
            Pet pet = new Pet(petName, petAge);
            //Adds the pets to the array
            pets.add(pet);
            
        }
        file.close();
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
        
        //Checks if the id is valid
        if(id >= 0 && id < pet.size()){
            //Gets the removed pet name and age
            Pet noPet = pet.get(id);
            String name = noPet.getName();
            int age = noPet.getAge();
        
            //Displays message
            System.out.println(name + " " + age + " is removed");
        
            //Removes pet and brings user to main menu
            pet.remove(id);
            
        }
        
        else{
            System.out.println("Error: ID " + id + " does not exist");
        }
        
        
    }
    
    //Method for saving the database
    public static void saveDatabase(ArrayList<Pet> pets, String filename) throws FileNotFoundException{
        PrintWriter output = new PrintWriter(filename);
        //Prints each pet name and age to a line of the text file
        for(int l=0;l<pets.size();l++) {
            output.println(pets.get(l).getName() + " "+ pets.get(l).getAge());
        }
        output.close();
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
