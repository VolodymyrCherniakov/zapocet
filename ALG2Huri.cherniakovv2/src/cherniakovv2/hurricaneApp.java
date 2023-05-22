package cherniakovv2;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ul
 */
public class hurricaneApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String FILE_SEPARATOR = System.getProperty("file.separator");
        hurricaneList arr = new hurricaneList();
        hurricaneList tempArr;
        boolean job = true;
        int temp1, temp2;
        String line;
        //reading from file
        try {
            arr.loadFromFile("data" + FILE_SEPARATOR + "HurricaneData.txt");
        } catch (IOException e) {
            System.out.println("Problem with file");
        }
        System.out.println("\n\n");
        //print all hurricanes
        System.out.println("All hurricanes: ");
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }
        //print hurricanes from.. to ..
        System.out.print("\n\nEnter the year, from which you want to see hurricanes: ");
        temp1 = sc.nextInt();
        System.out.print("Enter the year, to which you want to see hurricanes: ");
        temp2 = sc.nextInt();
        tempArr = new hurricaneList(arr.yearHurricanes(temp1, temp2));
        for (int i = 0; i < tempArr.getSize(); i++) {
            System.out.println(tempArr.get(i));
        }
        System.out.println("\n\n");
        //Main data about 1 hurricane (using name of hurricane)
        while (job) {
            System.out.print("Enter the name of hurricane you want to see the scale: ");
            sc.skip("[\r\n]+");
            line = sc.nextLine();
            if (arr.getHurricaneCategory(line) == null) {
                System.out.println("Wrong name, try again");
            } else {
                System.out.println(arr.getHurricaneCategory(line));
                job = false;
            }
        }
        System.out.println("\n\n");
        //print hurricanes sorted by speed
        tempArr.setArr(arr.getArr());
        tempArr.sort("speed");
        for (int i = 0; i < tempArr.getSize(); i++) {
            System.out.println(tempArr.get(i));
        }
        System.out.println("\n\n");
        //write text and binary files
        try {
            File result = new File("data" + FILE_SEPARATOR + "result.txt");
            arr.saveResultToFile(result);
            File binRes = new File("data" + FILE_SEPARATOR + "binary_result.dat");
            arr.saveResultToBinFile(binRes);
            //kontrolni vypis binarniho souboru
            checkBinaryResultsFile(binRes);

        } catch (IOException e) {
            System.out.println("Problem se souborem");
        } catch (IllegalArgumentException e) {
            System.out.println("Data v souborech nejsou validni");
            System.out.println(e.getMessage());
        }
    }
    
    public static void checkBinaryResultsFile(File resultB) throws IOException {
        try ( DataInputStream in = new DataInputStream(new FileInputStream(resultB))) {
            while (true) {
                try {
                    System.out.print(in.readInt() + " ");
                    System.out.println(in.readInt());
                } catch (EOFException e) {
                    break;
                }
            }
        }
    }
}
