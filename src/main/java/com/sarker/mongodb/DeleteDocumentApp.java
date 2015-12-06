package com.sarker.mongodb;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class DeleteDocumentApp {

	public static void fillData(DBCollection dbCollection) {
		for (int i = 1; i < 11; i++) {
			dbCollection.insert(new BasicDBObject().append("number", i));
		}
	}

	/**
	 * Get first document and delete it. In this case, number = 1 is deleted.
	 * 
	 * @param collection
	 */
	public static void removeFirstDocument(DBCollection dbCollection) {
		DBObject dbObject = dbCollection.findOne(); // get first document
		dbCollection.remove(dbObject);
	}

	/**
	 * Puts query in a BasicDBObject. In this case, number = 2 is deleted.
	 * 
	 * @param dbCollection
	 */

	public static void removeBasicDBObjectOne(DBCollection dbCollection) {
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("number", 2);
		dbCollection.remove(basicDBObject);
	}

	public static void removeBasicDBObjectTwo(DBCollection dbCollection) {
		dbCollection.remove(new BasicDBObject().append("number", 3));
	}

	/**
	 * Puts a $gt operator in a BasicDBObject object. In this case, number = 10
	 * is deleted.
	 * 
	 * @param dbCollection
	 */
	public static void removeGetterThan(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		query.put("number", new BasicDBObject().append("$gt", 9));
		dbCollection.remove(query);
	}

	/**
	 * Puts a $in operator in a BasicDBObject object, constructs the query in
	 * ArrayList. In this case, number = 4 and number = 5 are deleted.
	 * 
	 * @param dbCollection
	 */
	public static void removeInList(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(5);
		query.put("number", new BasicDBObject().append("$in", list));
		dbCollection.remove(query);
	}

	/**
	 * Use cursor to delete all available documents. (Not recommended)
	 * 
	 * @param dbCollection
	 */
	public static void removeThroughCursor(DBCollection dbCollection) {
		DBCursor cursor = dbCollection.find();
		while (cursor.hasNext()) {
			dbCollection.remove(cursor.next());
		}
	}

	/**
	 * Pass an empty BasicDBObject, and the entire documents will be deleted.
	 * 
	 * @param dbCollection
	 */
	public static void removeThroughEmptyObject(DBCollection dbCollection) {
		dbCollection.remove(new BasicDBObject());
	}

	/**
	 * It deletes the entire documents and drop the collection
	 * 
	 * @param dbCollection
	 */
	public static void removeEntireDocumnetAndDropCollection(
			DBCollection dbCollection) {
		dbCollection.drop();
	}

	public static void printCollection(DBCollection collection) {

		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	public static void main(String[] args) {

		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");

		DBCollection dbCollection = db.getCollection("dummyNumber");

		fillData(dbCollection);
//		removeFirstDocument(dbCollection);
//		removeBasicDBObjectOne(dbCollection);
//		removeBasicDBObjectTwo(dbCollection);
//		removeGetterThan(dbCollection);
//		removeInList(dbCollection);
		
//		removeThroughCursor(dbCollection);
//		removeThroughEmptyObject(dbCollection);
		removeEntireDocumnetAndDropCollection(dbCollection);
		
		printCollection(dbCollection);
	}
}
