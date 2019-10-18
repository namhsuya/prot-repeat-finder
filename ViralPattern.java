package viralproteinmotiffinder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
/**
 *
 * @author namhsuyA
 */
public class ViralPattern {
    
    public static ArrayList<String> VIRAL_PATTERNS = new ArrayList<String>();
    
    private final String MOTIF_LIST = "C:\\Users\\namhsuyA\\Documents\\NetBeansProjects\\"+
    "ViralProteinMotifFinder\\src\\viralproteinmotiffinder\\viralpatterns.txt";
    
    public ArrayList<String> loadMotifPatterns()throws IOException{
        try (BufferedReader csvReader = new BufferedReader(new FileReader(MOTIF_LIST))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split("\\t");
                VIRAL_PATTERNS.addAll(Arrays.asList(data));
            }
        }
        System.out.println("motifs loaded");
//        VIRAL_PATTERNS.forEach((motif) -> {
//            System.out.println(""+motif);
//        });
        return VIRAL_PATTERNS;
    }
    
    public LinkedHashMap<String, String> createHashMap(){
        System.out.println("Creating hashmap");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        ArrayList<String>k =  new ArrayList<>();
        ArrayList<String>v = new ArrayList<>();
        
        for(int i=0; i<VIRAL_PATTERNS.size();i+=2){
            v.add(VIRAL_PATTERNS.get(i));
            k.add(VIRAL_PATTERNS.get(i+1));
        }
        for(int i = 0; i < k.size(); i++) map.put(k.get(i), v.get(i));
        return map;
    }
}
