/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cherniakovv2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ul
 */
public class hurricaneList {

    private ArrayList<hurricane> arr = new ArrayList<>();

    public hurricaneList() {
    }

    ;

    public ArrayList<hurricane> getArr() {
        return arr;
    }

    public void setArr(ArrayList<hurricane> arr) {
        this.arr = arr;
    }

    public hurricaneList(ArrayList<hurricane> arr) {
        this.arr = arr;
    }

    public int getSize() {
        return this.arr.size();
    }

    public hurricane get(int index) {
        return this.arr.get(index);
    }

    public void AddHurricane(int year, String month, int tlak, int speed, String name) {
        hurricane temp = new hurricane(year, month, tlak, speed, name);
        this.arr.add(temp);
    }

    public void sort(String line) {
        if ("name".equals(line)) {
            this.arr.sort(COMP_BY_NAME);
        } else if ("speed".equals(line)) {
            this.arr.sort(COMP_BY_SPEED);
        } else if ("year".equals(line)) {
            this.arr.sort(COMP_BY_YEAR);
        }
    }

    public ArrayList<hurricane> yearHurricanes(int from, int to) {
        ArrayList<hurricane> temp = new ArrayList<>();
        for (int i = 0; i < this.arr.size(); i++) {
            if (this.arr.get(i).getYear() >= from && this.arr.get(i).getYear() <= to) {
                temp.add(this.arr.get(i));
            }
        }
        return temp;
    }

    private int getHurricaneIndex(String name) {
        for (int i = 0; i < this.arr.size(); i++) {
            if (this.arr.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getHurricaneCategory(String name) {
        int index = getHurricaneIndex(name);
        if (index == -1) {
            return null;
        }
        return this.arr.get(index).printScale();
    }

    public static Comparator<hurricane> COMP_BY_NAME = (hurricane o1, hurricane o2) -> o1.getName().compareTo(o2.getName());

    public static Comparator<hurricane> COMP_BY_SPEED = (hurricane o1, hurricane o2) -> o1.getSpeed() - o2.getSpeed();
   
    public static Comparator<hurricane> COMP_BY_YEAR = (hurricane o1, hurricane o2) -> o1.getYear() - o2.getYear();

    public void loadFromFile(String regFile) throws IOException {

        File reg = new File(regFile);
        try ( BufferedReader br = new BufferedReader(new FileReader(reg))) {
            String line, name, month;
            String[] parts;
            int year, tlak, speed;
            try {
                while ((line = br.readLine()) != null) {
                    parts = line.split("\t");
                    year = Integer.parseInt(parts[0]);
                    month = parts[1];
                    tlak = Integer.parseInt(parts[2]);
                    speed = Integer.parseInt(parts[3]);
                    name = parts[4];
                    this.AddHurricane(year, month, tlak, speed, name);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Not founded file " + regFile + "\n" + e.getMessage());

            }
        }
    }

    //je spatnym
    public int[][] hurricanesInYear() {
        this.sort("year");
        int size = 1;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).getYear() != arr.get(i + 1).getYear()) {
                size++;
            }
        }
        int[][] res = new int[size+1][2];
        int amount = 0, indexi = 0;
        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i).getYear() != arr.get(i + 1).getYear()) {
                res[indexi][0] = arr.get(i).getYear();
                res[indexi][1] = amount + 1;
                amount = 0;
                indexi++;
            } else {
                amount++;
            }
        }
        res[indexi][0] = arr.get(arr.size()-1).getYear();
        res[indexi][1] = amount + 1;
        return res;
    }

    public void saveResultToFile(File resFile) throws IOException {
        this.sort("name");
        String line;
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(resFile))) {
            for (hurricane h : this.arr) {
                line = h.getName() + "\t" + h.getYear() + "\t" + h.getSaffirSimpsonScale();
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public void saveResultToBinFile(File resBinFile) throws IOException {
        try ( DataOutputStream out = new DataOutputStream(new FileOutputStream(resBinFile))) {
            int[][] hurricanes = hurricanesInYear();
            for (int i = 0; i < hurricanes.length - 1; i++) {
                out.writeInt(hurricanes[i][0]);
                out.writeInt(hurricanes[i][1]);
            }
        }
    }
}
