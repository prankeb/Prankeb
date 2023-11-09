
package prankebandcoordinator;

//Class for the bands
public class Band {
    private String band;
    private int singleCount;
    
    //Constructer for the band class
    public Band(String band, int singleCount){
        this.band = band;
        this.singleCount = singleCount;
    }
    
    @Override
    public String toString(){
        return band + " had " + singleCount + " singles";
    }
    
    //Getter and setters for the class
    public String getBand(){
        return band;
}
    public int getSingleCount(){
        return singleCount;
    }
    
    public void setBand(String band){
        this.band = band;
    }
    
    public void setSingleCount(int singleCount){
        this.singleCount = singleCount;
    }
}
