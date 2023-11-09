package prankebandcoordinator;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class PrankeBandCoordinator {


    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        //Displays my name and email
        System.out.println("Submitted by Brandon Pranke - prankeb@csp.edu");
        
        //Displays message saying that this is my own work
        System.out.println("I certify that this is my own work\n");
        
        //Loads the band info file
        Scanner file = new Scanner(new File("bandinfo.txt"));
        
        //Makes an arrylist to store the band objects
        ArrayList<Band> bands = new ArrayList<Band>();
        
        //For each line of the file the data is turned into a band object
        while (file.hasNext()){
            String band = file.nextLine();
            
            String [] bandSpilt = band.split("\\|");
            
            String bandName = bandSpilt[0];
            
            int singleCount = Integer.parseInt(bandSpilt[1]);
            
            Band bandObject = new Band(bandName, singleCount);
            //Adds object to array
            bands.add(bandObject);
        }
        
        // Variable for what the user choice
        int userSearch = 1;
        
        //Loops until user is done searching by band name or single count
        while (userSearch == 1 || userSearch == 2){
            System.out.println("\nSearch by Band Name(1) or Single Count (2):");
            userSearch = input.nextInt();
            
            input.nextLine();
            
            if(userSearch == 1){
                //Sorts the bands by band name
                bandSort(bands);
                
                System.out.print("Enter Band Name you are looking for: ");
                
                String bandSearch = input.nextLine();
                
                System.out.println("Bandname is: " + bandSearch);
                
                //Searches for the band the user entered 
                int bandIndex = bandNameSearch(bands, bandSearch, 0, bands.size());
                
                Band band  = bands.get(bandIndex);
                
                //Displays the band found
                System.out.println("Band found is: " + band.toString());
                
                
                
                
                

            }
            
            else if(userSearch == 2){
                //Sorts the bands by single count
                singleCountSort(bands);
                
                System.out.println("Enter the Set Time you are looking for: ");
                
                int setTime = input.nextInt();
                
                //Searches for the band with the closest amount of singles
                int index = singleCountSearch(bands, setTime);
                
                Band band = bands.get(index);
                
                //Displays the band with the closest single count
                System.out.println("Band with closest set time is: " + band.toString());
                
                
            }
        }
    }
    
    //Method for sorting band by band name
    public static void bandSort(ArrayList<Band> bands){
        //Loops through each band in the array
        for(int i = 0; i < bands.size(); i++){
            int first = i;
            //Does an inner loop to compare band name with the next band name
            for(int j = i + 1; j < bands.size(); j++){
                if(bands.get(j).getBand().compareToIgnoreCase(bands.get(first).getBand()) < 0){
                    first = j;
                }
            }
                //Switches the placement of the bands if needed
                Band band = bands.get(first);
                bands.set(first, bands.get(i));
                bands.set(i, band);
        }
    }
    
    
    //Method for searching band name
    public static int bandNameSearch(ArrayList<Band> bands, String bandName, int number1, int number2) {
        //Makes sure it can still search for the band
        if (number2 >= number1) {
            //find the middle index number
            int middle = number2 + (number1 - number2) / 2;
            
            //if the band name is equal then it returns the index of the band
            if(bands.get(middle).getBand().equalsIgnoreCase(bandName)){
                return middle;
            }
            
            //If the band name is greater then then the middle then recalls the method
            if(bands.get(middle).getBand().compareToIgnoreCase(bandName) > 0){
                //Calls the function that searches the higher part of the array
                return bandNameSearch(bands, bandName, number1, middle - 1);
            }
            
            //Else it searches the lower part of the array by recalling the method
            return bandNameSearch(bands, bandName, middle + 1, number2);
        }
        
        return -1;
    }
    
    
    //Method for searching for the closest single count 
    public static int singleCountSearch(ArrayList<Band> bands, int setTime) {
        int closestTime = Integer.MAX_VALUE;;
        int index = 0;
        //Goes through each band in the array and sees if the single count is equal 
        for(int i = 0; i < bands.size(); i++){
            if(bands.get(i).getSingleCount() == setTime){
                return i;
            }
            //If not equal it sees if it is the closes difference and save it, if so
            int difference = Math.abs(bands.get(i).getSingleCount() - setTime);
            if(difference < closestTime){
                closestTime = difference;
                index = i;
            }
            
            
        }
        return index;
    }
    
    //Method for sorting band by single count
    public static void singleCountSort(ArrayList<Band> bands) {
        //Loops through each band in the array
        for(int i = 0; i < bands.size(); i++){
            int first = i;
            
            //Does an innter loop to compare single count with the next single count
            for(int j = i + 1; j < bands.size(); j++) {
                if(bands.get(j).getSingleCount() < bands.get(first).getSingleCount()) {
                    first = j;
                }
            }
            
            //Switches the placement of the bands if needed
            Band band = bands.get(first);
            bands.set(first, bands.get(i));
            bands.set(i, band);
    }
    }
    
}
