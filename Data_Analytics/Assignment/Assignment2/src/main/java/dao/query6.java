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

public class query6 {
    private static MongoCollection<Document> collection;

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
//        mongoDatabase.createCollection("assignment2_movies");
//        System.out.println("Create collection successfully!");


        // Link to the collection
        collection = mongoDatabase.getCollection("assignment2_movies");
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

        int[] num = new int[3000];
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        long startTime = System.currentTimeMillis();
        while (mongoCursor.hasNext()) {
            String temp = mongoCursor.next().get("Title").toString();
            temp = temp.substring(temp.length() - 5, temp.length() - 1);
            num[Integer.parseInt(temp)] ++;
        }
        for(int m = 0; m < num.length; m++){
            if(num[m] != 0) {
                System.out.println(m + ", " + num[m]);
            }
        }
        System.out.println("Query time: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }
}
