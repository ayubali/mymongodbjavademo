package com.sarker.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class ConvertJSONtoDBObject {

	public static DBObject covertJsonToDBObject(String json) {

		return (DBObject) JSON.parse(json);
	}
	
	public static void printAllDocuments(DBCollection collection) {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public static void main(String[] args) {

		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");
		DBCollection dbCollection = db.getCollection("dummyJson");
		DBObject dbObject = covertJsonToDBObject("{'name':'mkyong', 'age':30}");
		dbCollection.insert(dbObject);
		printAllDocuments(dbCollection);
	}

}
