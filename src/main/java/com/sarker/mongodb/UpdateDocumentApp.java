package com.sarker.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class UpdateDocumentApp {

	public static void printAllDocuments(DBCollection collection) {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public static void removeAllDocuments(DBCollection collection) {
		collection.remove(new BasicDBObject());
	}

	/**
	 * Find document where hosting = ‘hostB’, and update it’s clients values
	 * from 100 to 110. Wait, the entire “hostB” document is replaced with
	 * another new document,
	 * 
	 * { "_id" : { "$oid" : "id"} , "clients" : 110}
	 * 
	 * @param dbCollection
	 */
	public static void update(DBCollection dbCollection) {

		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("clients", 110);
		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");
		dbCollection.update(searchQuery, basicDBObject);
	}

	/**
	 * this is not what we want. To update a particular value only, uses $set
	 * update modifier.DBCollection.update() with $set
	 * 
	 * @param dbCollection
	 */
	public static void updateWithSet(DBCollection dbCollection) {
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("$set", new BasicDBObject().append("clients", 110));

		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");
		dbCollection.update(searchQuery, basicDBObject);
	}

	/**
	 * The use of $inc modifier to increase a particular value. Find document
	 * where hosting = ‘hostB’, update it’s ‘clients’ value by increasing the
	 * value from 100 to 199, (100 + 99) = 199.
	 * 
	 * @param dbCollection
	 */
	public static void updateWithInc(DBCollection dbCollection) {
		BasicDBObject newDocument = new BasicDBObject().append("$inc",
				new BasicDBObject().append("clients", 99));
		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");

		dbCollection.update(searchQuery, newDocument);
	}

	/**
	 * The use of multi parameter to update a set of matched documents. Find
	 * document where type = ‘vps’, update all the matched documents’ ‘clients’
	 * value to 888.
	 * 
	 * @param dbCollection
	 */
	public static void updateWithMulti(DBCollection dbCollection) {
		BasicDBObject newDocument = new BasicDBObject().append("$set",
				new BasicDBObject().append("clients", 888));
		BasicDBObject searchQuery = new BasicDBObject().append("type", "vps");

		dbCollection.updateMulti(searchQuery, newDocument);
	}

	public static void fillData(DBCollection dbCollection) {
		BasicDBObject basicDBObject1 = new BasicDBObject();
		basicDBObject1.put("hosting", "hostA");
		basicDBObject1.put("type", "vps");
		basicDBObject1.put("clients", 1000);
		dbCollection.insert(basicDBObject1);

		BasicDBObject basicDBObject2 = new BasicDBObject();
		basicDBObject2.put("hosting", "hostB");
		basicDBObject2.put("type", "dedicated server");
		basicDBObject2.put("clients", 100);
		dbCollection.insert(basicDBObject2);

		BasicDBObject basicDBObject3 = new BasicDBObject();
		basicDBObject3.put("hosting", "hostC");
		basicDBObject3.put("type", "vps");
		basicDBObject3.put("clients", 900);
		dbCollection.insert(basicDBObject3);
	}

	public static void main(String[] args) {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");

		DBCollection dbCollection = db.getCollection("dummyServer");

		fillData(dbCollection);
		System.out.println("Data before update:");
		printAllDocuments(dbCollection);
//		update(dbCollection);
//		updateWithSet(dbCollection);
//		updateWithInc(dbCollection);
		updateWithMulti(dbCollection);
		System.out.println("Data after update:");
		printAllDocuments(dbCollection);

		removeAllDocuments(dbCollection);

	}

}
