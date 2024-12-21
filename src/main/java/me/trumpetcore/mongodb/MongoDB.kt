package me.trumpetcore.mongodb

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoException
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.bson.BsonInt64
import org.bson.Document
import org.bson.UuidRepresentation


class MongoDB {

    val uri = "mongodb://localhost:27017/"
    private val mongoClient = MongoClient.create(
        MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(uri))
        .uuidRepresentation(UuidRepresentation.STANDARD)
        .build())
    //  private val mongoClient = MongoClient.create(uri)
    val database = mongoClient.getDatabase("trumpet")

    suspend fun ping() {
        try {
            // Send a ping to confirm a successful connection
            val command = Document("ping", BsonInt64(1))
            val commandResult = database.runCommand(command)
            println("Pinged your deployment. You successfully connected to MongoDB!")
        } catch (me: MongoException) {
            System.err.println(me)
        }
    }

}