package dam.uf3.mongo;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App_practica3 {
    public static void main(String[] args) {

        String uri = "mongodb+srv://bilal:1234@cluster0.ye8yv1r.mongodb.net/?appName=Cluster0";

        MongoClient client = MongoClients.create(uri);
        MongoDatabase db = client.getDatabase("practica3");
        MongoCollection<Document> events = db.getCollection("events");

        // crear
        Document doc = new Document("title", "Test Event")
                .append("category", "test")
                .append("price", 10)
                .append("eventDate", new java.util.Date())
                .append("location", "Online")
                .append("available", true);

        events.insertOne(doc);
        System.out.println("Document insertat");

        // llegir
        for (Document d : events.find()) {
            System.out.println(d.toJson());
        }

        // modifica
        events.updateOne(
                new Document("title", "Test Event"),
                new Document("$set", new Document("price", 15))
        );

        // eliminar
        events.deleteOne(new Document("title", "Test Event"));

        client.close();
    }
}
