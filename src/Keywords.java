

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author Code by Kromah,Lusenii
 *
 */
public class Keywords {

    private ArrayList<Word> array;
    private Map<Word, Integer> map;

    public Keywords() {
        array = new ArrayList<>();
    }
     

    public void addWord(Word word) {

        array.add(word);

    }

    public void setWord(int position, Word word) {

        array.add(position, word);

    }

    public Word getWord(int i) {

        return array.get(i);

    }

    public int numOfWords() {

        return array.size();

    }

    public ArrayList<Word> list() {

        return array;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < array.size(); i++) {
            output += array.get(i) + System.lineSeparator();
        }
        return output;
    }

    /**
     * Checks word for common prefix
     */
    private void preFixCheck() {
        ArrayList<Word> importantWords = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(i).toString().substring(0, 3).equals(array.get(j).toString().substring(0, 3))) {

                    if (!importantWords.contains(array.get(i))) {
                        importantWords.add(array.get(i));
                    }
                }
            }
        }
        array = importantWords;
    }

    /**
     * Reads website and looks for notable content
     *
     * @param url
     */
    public void readFromWebsite(String url) {

        String inputLine;
        Keywords dictionary = new Keywords();

        //read each line from file
        String readLine = "";

        //line with no puntuaction
        String noPunctuation = "";

        //stores each line in an array
        String[] words = null;

        Word word;

        try {
            URL webpage = new URL(url);
            BufferedReader in;

            in = new BufferedReader(
                    new InputStreamReader(webpage.openStream()));

            while ((inputLine = in.readLine()) != null) {

                //remove javascript from HTML
                readLine = Jsoup.clean(inputLine, Whitelist.basic());

                //remove HTML from String
                readLine = readLine.replaceAll("\\<.*?>", "");

                //Remove non-alpha numeric characters
                noPunctuation = readLine.replaceAll("[\\W]", " ");

                words = noPunctuation.split(" ");

                //traverse through each line
                for (int i = 0; i < words.length; i++) {

                    word = new Word(words[i]);

                    //if it is a word
                    if (word.isWord() == true) {
                        //add to diciotnary
                        this.addWord(word);

                    }

                }

            }
            in.close();
        } catch (IOException ex) {
            //Logger.getLogger(class.getName()).log(Level.SEVERE, null, ex);
        }


        //check common prefixes
        this.preFixCheck();

        map = new TreeMap<>();


        for (int i = 0; i < this.numOfWords(); i++) {

            //Get each word
            Word key = this.getWord(i);

            //if word is not in map
            if (map.get(key) == null) {
                //place it at node
                map.put(this.getWord(i), 1);


            } else {

                //if word is in map add to its value
                int value = map.get(key);

                value++;

                map.put(key, value);
            }

        }
        Map<Word, Integer> sortedMap = sortByComparator(map);
        //print map
        printMap(sortedMap);
    }

    /**
     * Reads data from output file and checks for keywords
     *
     * @param inputfile
     */
    public void readFromFile(String inputfile) {

        Keywords dictionary = new Keywords();

        //reads each line from txt
        String readEachLine = "";

        //line with no puntuaction
        String noPunctuation = "";

        //stores each line in an array
        String[] words = null;

        Word word;

        try {
            Scanner input = new Scanner(new File(inputfile));

            while (input.hasNext()) {

                //read each line
                readEachLine = input.nextLine();
                //remove all puntuation
                noPunctuation = readEachLine.replaceAll("[\\W]", " ");
                //store in array
                words = noPunctuation.split(" ");

                //traverse through each line checking for what is a word 
                for (int i = 0; i < words.length; i++) {

                    word = new Word(words[i]);

                    //if it is a word add it to dictionary
                    if (word.isWord() == true) {

                        this.addWord(word);

                    }

                }
            }

        } catch (FileNotFoundException ex) {

            System.err.println("File Not Found");
            System.exit(-1);
        }

        //check for common prefixes
        this.preFixCheck();

        map = new TreeMap<>();

        //traverse through tree
        for (int i = 0; i < this.numOfWords(); i++) {

            //get each word
            Word key = this.getWord(i);

            //if node is empty
            if (map.get(key) == null) {

                //put tree there
                map.put(this.getWord(i), 1);


            } else {

                //add to its value
                int value = map.get(key);
                value++;
                map.put(key, value);
            }

        }

        Map<Word, Integer> sortedMap = sortByComparator(map);

        //sort map
        printMap(sortedMap);


    }

    private static Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * Prints sorted map
     *
     * @param map
     */
    private static void printMap(Map<Word, Integer> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + "\t Value : " + entry.getValue());
        }
    }

  
}
