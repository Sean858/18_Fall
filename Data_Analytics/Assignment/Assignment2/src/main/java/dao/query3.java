package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class query3 {
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

        ArrayList list = new ArrayList();
        int[] num = new int[100];
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        long startTime = System.currentTimeMillis();
        while (mongoCursor.hasNext()) {
            String[] temp = (mongoCursor.next().get("Genres").toString()).split("\\|");
            for(int j = 0; j < temp.length; j++){
                if(!list.contains(temp[j])) {
                    list.add(temp[j]);
                }
                num[list.indexOf(temp[j])] ++;
            }
        }
        for(int m = 0; m < list.size() - 1; m++){
            System.out.println(list.get(m) + ", " + num[m]);
        }
        System.out.println("Query time: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }
}
