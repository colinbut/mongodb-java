/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.java;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase testDb = mongoClient.getDatabase("test");

        MongoCollection<Document> usersCollection = testDb.getCollection("users");

        // drop collection
        usersCollection.drop();

        // Insert
        insertMultiples(usersCollection);

        // Query
        queryResults(usersCollection);

        // Update

        // Delete

        mongoClient.close();
    }

    public static void insertMultiples(MongoCollection<Document> usersCollection) {
        Document jamie = new Document("name", "Jamie").append("age", 27);
        Document andy = new Document("name", "Andy").append("age", 12);
        Document steven = new Document("name", "Steven").append("age", 18);
        Document david = new Document("name", "David").append("age", 29);

        usersCollection.insertMany(Arrays.asList(jamie, andy, steven, david));
    }

    public static void queryResults(MongoCollection<Document> usersCollection) {
        List<Document> users = usersCollection.find().into(new ArrayList<Document>());
        for (Document user : users) {
            System.out.println(user);
        }

        Document user = usersCollection.find(new Document("name", "Jamie")).first();
        System.out.println("User:" + user);

        Document jamie = usersCollection.find(new Document("name", "Jamie")
            .append("age", new Document("$gte", 25))).first();
        System.out.println(jamie);
    }

}
