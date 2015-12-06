package com.sarker.mongodb;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class InsertDocumentApp {

	public static void printCollection(DBCollection collection) {

		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	public static void basicDBObjectInsert(DBCollection dbCollection) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("database", "AlphDB");
		dbObject.put("table", "hosting");

		BasicDBObject dbObjectDetails = new BasicDBObject();
		dbObjectDetails.put("records", 99);
		dbObjectDetails.put("index", "vps_index1");
		dbObjectDetails.put("active", "true");
		dbObject.put("details", dbObjectDetails);
		dbCollection.insert(dbObject);
	}

	public static void basicDBObjectBuilderInsert(DBCollection dbCollection) {
		BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder
				.start().add("database", "BetaDB").add("table", "hosting");

		BasicDBObjectBuilder basicDBObjectDetailBuilder = BasicDBObjectBuilder
				.start().add("records", 789).add("index", "vps_index2")
				.add("active", "true");
		basicDBObjectBuilder.add("details", basicDBObjectDetailBuilder.get());
		dbCollection.insert(basicDBObjectBuilder.get());
	}

	public static void mapInsert(DBCollection dbCollection) {
		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("database", "GamaDB");
		documentMap.put("table", "hosting");

		Map<String, Object> documentDetails = new HashMap<String, Object>();
		documentDetails.put("records", 7896);
		documentDetails.put("index", "vps_index3");
		documentDetails.put("active", "false");

		documentMap.put("details", documentDetails);
		dbCollection.insert(new BasicDBObject(documentMap));

	}

	public static void jsonParseInsert(DBCollection dbCollection) {
		String json = "{'database' : 'mkyongDB','table' : 'hosting',"
				+ "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

		BasicDBObject dbObject = (BasicDBObject) JSON.parse(json);

		dbCollection.insert(dbObject);
	}

	public static void main(String[] args) {

		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");

		DBCollection dbCollection = db.getCollection("dummyCollection");

		// basicDBObjectInsert(dbCollection);
		// printCollection(dbCollection);
		// basicDBObjectBuilderInsert(dbCollection);
		// printCollection(dbCollection);
		// mapInsert(dbCollection);
		// printCollection(dbCollection);
		// jsonParseInsert(dbCollection);
		printCollection(dbCollection);

	}
}
