/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cherniakovv2;
import java.util.Comparator;
/**
 *
 * @author ul
 */
public class ComparatorByName implements Comparator<hurricane> {

    @Override
    public int compare(hurricane o1, hurricane o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
