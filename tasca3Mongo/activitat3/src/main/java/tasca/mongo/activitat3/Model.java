package tasca.mongo.activitat3;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Model {

    private MongoCollection<Document> col;
    public Model() {
        MongoDatabase db = ConnectionManager.getConnection();
        col = db.getCollection("events");
    }

    public void inserirEvent(Event e) {
        col.insertOne(e.toDocument());
    }

    public List<Document> getTotsEvents() {
        return col.find().into(new ArrayList<>());
    }

    public void eliminarEvent(ObjectId id) {
        col.deleteOne(Filters.eq("_id", id));
    }

    public void modificarEvent(ObjectId id, String nouTitol) {
        col.updateOne(
                Filters.eq("_id", id),Updates.set("title", nouTitol) );
    }

    public List<Document> getEventsPerData(Date inici, Date fi) {
        return col.find(
                Filters.and(Filters.gte("eventDate", inici),Filters.lte("eventDate", fi))).into(new ArrayList<>());
    }

    public List<Document> getEventsPerNom(String text) {
        return col.find(
           Filters.regex("title", text, "i")).into(new ArrayList<>());
    }
}
