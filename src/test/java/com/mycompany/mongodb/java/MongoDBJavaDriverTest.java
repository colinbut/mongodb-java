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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDBJavaDriverTest {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase testDb = mongoClient.getDatabase("test");

    MongoCollection<Document> usersCollection = testDb.getCollection("users");

    @BeforeClass
    public void setUp() {
        // drop collection
        usersCollection.drop();
    }

    @AfterClass
    public void tearDown() {
        mongoClient.close();
    }

    @Test
    public void testInsertOne() {
        Document daniel = new Document();
        daniel.append("name", "Daniel");
        daniel.append("age", 45);

        usersCollection.insertOne(daniel);
    }

    @Test
    public void testInsertMany() {
        Document jamie = new Document("name", "Jamie").append("age", 27);
        Document andy = new Document("name", "Andy").append("age", 12);
        Document steven = new Document("name", "Steven").append("age", 18);
        Document david = new Document("name", "David").append("age", 29);

        usersCollection.insertMany(Arrays.asList(jamie, andy, steven, david));
    }

    @Test
    public void testFind() {
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