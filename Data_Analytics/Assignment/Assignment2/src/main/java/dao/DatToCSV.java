package dao;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class DatToCSV {
    public static void main(String[] args) throws IOException {
        String oriFile = "ml-10M100K/movies.dat";
        String tarFile = "ml-10M100K/Movies.csv";
        datToCSV(oriFile, tarFile);
    }
    private static void datToCSV(String oriFile, String tarFile) throws IOException{

        BufferedReader in = new BufferedReader(new FileReader(oriFile));
        try {
            FileOutputStream targetData = new FileOutputStream(tarFile);
            int count = 0;
            while (in.ready()) {
                String line = in.readLine().replaceAll(",", "#");
                line = line.replace("\"","^");
                line = line.replaceAll("::", ",");
                if( count <= 0) {
                    if(oriFile.equals("ml-10M100K/movies.dat")) targetData.write("MovieID,Title,Genres\n".getBytes());
                    if(oriFile.equals("ml-10M100K/tags.dat")) targetData.write("UserID,MovieID,Tag,Timestamp\n".getBytes());
                    if(oriFile.equals("ml-10M100K/ratings.dat")) targetData.write("UserID,MovieID,Rating,Timestamp\n".getBytes());
                    count++;}
                targetData.write((line + "\n").getBytes());
            }
            targetData.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
