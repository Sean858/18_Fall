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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class query2 {
    private static MongoCollection<Document> collection;

    public static void main(String args[]) {
        /**
         * 1. Connect to MongoDB
         *    If need, create a new collection and link
         *    Link to an already existed collection
         * 2. Link to an already existed collection
         * 3. If need, print the collection
         * 4. Enter a targetID to test
         * 5. Show the result of query target
         * */
        try {
            initConnectionToMongoDB();
            String targetID = "13";
            query(targetID);
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
//        mongoDatabase.createCollection("assignment2_tags");
//        System.out.println("Create collection successfully!");


        // Link to the collection
        collection = mongoDatabase.getCollection("assignment2_ratings");
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

    private static void query(String targetID) {

        MultiValueMap<String, String> movieUser = new LinkedMultiValueMap<String, String>();
        MultiValueMap<String, String> userMovie = new LinkedMultiValueMap<String, String>();

        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        long startTime = System.currentTimeMillis();
        while (mongoCursor.hasNext()) {
            Object E = mongoCursor.next();
            String User = (((Document) E).get("UserID")).toString();
            String Movie = (((Document) E).get("MovieID")).toString();
            userMovie.add(User, Movie);
            movieUser.add(Movie,User);
        }


        if(userMovie.getValues(targetID) == null) System.out.println("No similar User");
        else {
            Set<String> values = new HashSet<String>();
            for (String key : userMovie.getValues(targetID)) {
                List<String> V = movieUser.getValues(key);
                for(String m : V) {
                    values.add(m);
                }
            }
            System.out.println(values.toString());
        }

        System.out.println("Query time: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }
}
