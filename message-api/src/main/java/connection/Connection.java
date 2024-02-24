package connection;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Connection {

    private final MongoDatabase database;
    private static final Connection instance = null;

    public Connection() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase("gestion_association").withCodecRegistry(pojoCodecRegistry);
    }

    public static Connection getInstance() {
        return instance == null ? new Connection() : instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
