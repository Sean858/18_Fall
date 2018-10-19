package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.net.UnknownHostException;


public class CreateMongoDB {
    private static MongoCollection<Document> collection;

    public static void main(String args[]) {

        try {
            initConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void initConnection() throws UnknownHostException, MongoException {

        // Connect to database
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DA");
        System.out.println("Connect db successfully!");

        // create a collection
        mongoDatabase.createCollection("assignment2_ratings");
        System.out.println("Create collection successfully!");

        mongoDatabase.createCollection("assignment2_tags");
        System.out.println("Create collection successfully!");

        mongoDatabase.createCollection("assignment2_movies");
        System.out.println("Create collection successfully!");

    }
}

