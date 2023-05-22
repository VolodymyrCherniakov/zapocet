/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cherniakovv2;


/**
 *
 * @author ul
 */
public class hurricane implements Comparable<hurricane>{
    private int year;
    private String month;
    private int tlak;
    private int speed;
    private String name;

    public hurricane() {}
    public hurricane(int year, String month, int tlak, int speed, String name){
        this.year = year;
        this.month = month;
        this.tlak = tlak;
        this.speed = speed;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTlak() {
        return tlak;
    }

    public void setTlak(int tlak) {
        this.tlak = tlak;
    }

    public int getSpeed() {
        return speed;
    }
    
    public double getSpeedKmH(){
        return speed*1.852;
    }
    
    public int getSaffirSimpsonScale(){
        if(this.getSpeedKmH() >= 119 && this.getSpeedKmH()<= 153){
            return 1;
        }else if(this.getSpeedKmH() >= 154 && this.getSpeedKmH()<= 177){
            return 2;
        }else if(this.getSpeedKmH() >= 178 && this.getSpeedKmH()<= 208){
            return 3;
        }else if(this.getSpeedKmH() >= 209 && this.getSpeedKmH()<= 251){
            return 4;
        }else if(this.getSpeedKmH() >= 252){
            return 5;
        }
        return 0;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d\t%S\t%d\t%d\t%S" ,this.year, this.month, 
                this.tlak, this.speed, this.name );
    }
    
    public String printScale(){
        return String.format("Name: %S\tCategory: %d\tSpeed: %.2f km/h",this.name, this.getSaffirSimpsonScale(), this.getSpeedKmH());
    }

    @Override
    public int compareTo(hurricane h) {
       return this.speed - h.speed;
    }
}
