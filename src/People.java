import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by alhanger on 10/19/15.
 */
public class People {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Person>> citizens = new HashMap<>();

        parseData("people.csv", citizens);
        sortMap(citizens);
        System.out.println(citizens);
        writeToJson(citizens);
    }

    static void parseData(String file, HashMap<String, ArrayList<Person>> citizens) {
        String content = readFile(file);
        String[] lines = content.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] col = lines[i].split(",");

            String id = col[0];
            String firstName = col[1];
            String lastName = col[2];
            String email = col[3];
            String country = col[4];
            String ipAddress = col[5];

            Person individual = new Person(id, firstName, lastName, email, country, ipAddress);

            ArrayList<Person> list = citizens.get(country);

            if (list == null) {
                list = new ArrayList<>();
                list.add(individual);
                citizens.put(individual.country, list);
            } else {
                list.add(individual);
            }
        }
        //System.out.println();
    }

    static void sortMap(HashMap<String, ArrayList<Person>> citizens) {
        for (ArrayList<Person> peopleInCountry : citizens.values()) {
            Collections.sort(peopleInCountry);
        }
    }

    static void writeToJson (HashMap<String, ArrayList<Person>> citizens) {
        //ArrayList<Person> list = citizens.get(entry);
        JsonSerializer serializer = new JsonSerializer();
        String output = serializer.serialize(citizens);

        writeFile("people.json", output);
    }

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String (fileContent);
        } catch (Exception e){
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }
}
