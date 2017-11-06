# MongoDB - Java

This repo provides some examples of basic CRUD operations using the MongoDB Java Driver which interfaces Java with MongoDB Database.


#### Representing Documents

```java
Document document = new Document()
            .append("String", "Hello String")
            .append("int", 23)
            .append("Long", 3L)
            .append("Double", 10.2)
            .append("Boolean", true)
            .append("Date", new Date())
            .append("ObjectId", new ObjectId())
            .append("Null", null)
            .append("embeddedDoc", new Document("x", 0))
            .append("List", Arrays.asList(1,2,3));
```

#### Inserting Documents (Create)

```java
Document daniel = new Document();
daniel.append("name", "Daniel");
daniel.append("age", 45);

usersCollection.insertOne(daniel);
```

#### Finding Documents (Read)

```java
// find all
List<Document> users = usersCollection.find().into(new ArrayList<Document>());
for (Document user : users) {
    System.out.println(user);
}

// find a particular
Document user = usersCollection.find(new Document("name", "Jamie")).first();
System.out.println("User:" +  + user);

// Find a particular with filters
Document jamie = usersCollection.find(new Document("name", "Jamie")
    .append("age", new Document("$gte", 25))).first();
System.out.println(jamie);
```

#### Updating Documents (Update)

```java
Document documentToUpdate = new Document("name", "Jamie").append("age", 27);

usersCollection.updateOne(documentToUpdate, new Document("$set", new Document("name", "Jamie Book")));
```

#### Deleting Documents (Delete)
