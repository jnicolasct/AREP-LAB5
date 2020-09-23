package edu.escuelaing.AREP.App;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class MongoDB {

    private MongoCollection collection;
    private MongoDatabase db;

    /**
     * Costructor de la clase, que permita crear la conexion a la base de datos, Mongo
     */
    public MongoDB(){
        MongoClientURI uri = new MongoClientURI(
                "mongodb://ec2-34-224-66-113.compute-1.amazonaws.com:27017/Arep5Docker?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&3t.uriVersion=3&3t.databases=Arep5Docker");
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("Arep5Docker");
        collection = db.getCollection("areplab5");
    }

    /**
     * Metodo que agrega documentos a la base de datos mongo
     */
    public void add(String texto){
        Document object = new Document();
        object.append("text",texto);
        object.append("date", new Date().toString());
        collection.insertOne(object);
    }

    /**
     * Metodo que retorna los documentos que existen en a base de datos
     * @return Cadena de los documentos que se encuetran en la base de datos
     */
    public String select(){
        FindIterable<Document> registros = collection.find();
        ArrayList<String> listaFinal = new ArrayList<String>();
        String datos = "";
        String dato = "";
        int cont = 0;
        for (Document r : registros){
            dato= "\n"+r.getString("text")+"-"+r.getString("date") + ",";
            listaFinal.add(dato);
        }
        for (int i= listaFinal.size()-1; i>=0;i--) {
            cont += 1;
            datos += listaFinal.get(i);
            if (cont == 10) {
                return datos;
            }

        }
        return datos;
    }




}