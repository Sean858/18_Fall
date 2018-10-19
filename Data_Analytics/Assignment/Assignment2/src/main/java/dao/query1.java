package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class query1 {
    private static MongoCollection<Document> collection;
    private static MongoCollection<Document> collection1;

    public static void main(String args[]) {
        /**
         * 1. Connect to MongoDB
         *    If need, create a new collection and link
         *    Link to an already existed collection
         * 2. Link to an already existed collection
         * 3. If need, print the collection
         * 4. Show the result of query target
         * */
        try {
            initConnectionToMongoDB();
            query();
            //Output all result
            //loadData()
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void initConnectionToMongoDB() throws UnknownHostException, MongoException {

        // Connect to database
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DA");
        System.out.println("Connect db successfully!");

        // create a collection
//        mongoDatabase.createCollection("assignment2_ratings");
//        System.out.println("Create collection successfully!");


        // Link to the collection
        collection = mongoDatabase.getCollection("assignment2_ratings");
        System.out.println("Link collection successfully!");

        collection1 = mongoDatabase.getCollection("assignment2_movies");
        System.out.println("Link collection successfully!");
    }

    private static void loadData() throws Exception {
        /**
         * 1. FindIterable<Document>
         * 2. MongoCursor<Document>
         * 3. Print
         * */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next().toJson());

        }
    }

    private static void query() {
        int[] movieId = new int[1000000];
        double[] rating = new double[1000000];
        String[] movieName = new String[1000000];
        int[] userId = new int[1000000];

        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        long startTime = System.currentTimeMillis();

        while (mongoCursor.hasNext()) {
            Object E = mongoCursor.next();
            int tempId = Integer.parseInt(((Document)E).get("MovieID").toString());
            movieId[tempId] ++;
            double tempRating = Double.parseDouble(((Document)E).get("Rating").toString());
            rating[tempId] += tempRating;
            int tempUser = Integer.parseInt(((Document)E).get("UserID").toString());
            userId[tempId] ++;
        }
//        for(int i = 0; i < movieId.length; i++){
//            if(movieId[i] != 0 && movieId[i] < 3) System.out.println(i);
//        }
        for(int i = 0; i < userId.length; i++){
            if(userId[i] == 1) System.out.println(i);
        }

        FindIterable<Document> findIterable1 = collection1.find();
        MongoCursor<Document> mongoCursor1 = findIterable1.iterator();

//        while (mongoCursor1.hasNext()) {
//            Object E = mongoCursor1.next();
//            String movieN = ((Document)E).get("Title").toString();
//            int tempId = Integer.parseInt(((Document)E).get("MovieID").toString());
//            movieN = movieN.replace("#", ",");
//            movieN = movieN.replace("^", "\"");
//            movieName[tempId] = movieN;
//        }
//
//        for(int m = 0; m < movieId.length; m++){
//            if(movieId[m] != 0){
//                System.out.println(movieName[m] + ", " + String.format("%.2f", (double)rating[m] / (double)movieId[m]));
//            }
//        }
        System.out.println("Query time: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }
}
