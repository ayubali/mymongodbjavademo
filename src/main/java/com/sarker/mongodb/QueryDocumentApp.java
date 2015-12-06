package com.sarker.mongodb;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class QueryDocumentApp {

	public static void fillData(DBCollection dbCollection) {
		BasicDBObject dbObject = null;
		for (int i = 0; i < 10; i++) {
			dbObject = new BasicDBObject();
			dbObject.put("number", i + 1);
			dbObject.put("name", "ayub-" + (i + 1));
			dbCollection.insert(dbObject);

		}
	}

	/**
	 * Get all matched documents.
	 * 
	 * @param dbcollection
	 */
	public static void findQuery(DBCollection dbcollection) {
		DBCursor cursor = dbcollection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * Get first matched document only.
	 * 
	 * @param dbCcollection
	 */
	public static void findFirstQuery(DBCollection dbCcollection) {
		DBObject dbObject = dbCcollection.findOne();
		System.out.println(dbObject);
	}

	/**
	 * Get single field from matched document.
	 * 
	 * @param dbCollection
	 */
	public static void findSingleFiledQuery(DBCollection dbCollection) {

		BasicDBObject queryAll = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		fields.put("name", 1);
		System.out.println(queryAll.toString());
		DBCursor cursor = dbCollection.find(queryAll, fields);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * Get all documents where number = 5.
	 * 
	 * @param dbCollection
	 */

	public static void findEqualQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		query.put("number", 5);
		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * in example – Get documents where number in 2, 4 and 5.
	 * 
	 * @param dbCollection
	 */

	public static void findINListQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		query.put("number", new BasicDBObject().append("$in", list));
		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * $gt $lt example – Get documents where 8 > number > 4 .
	 * 
	 * @param dbCollection
	 */
	public static void findGretterThenLessThanQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		query.put("number",
				new BasicDBObject().append("$lt", 8).append("$gt", 4));
		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	/**
	 * $ne example – Get documents where number != 9 .
	 * 
	 * @param dbCollection
	 */
	public static void findNotEqualQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		query.put("number", new BasicDBObject().append("$ne", 9));
		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	/**
	 * $and example – get documents where number = 2 and name = 'ayub-2'.
	 * 
	 * @param dbCollection
	 */
	public static void findLogicalAndQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		ArrayList<BasicDBObject> arrayList = new ArrayList<BasicDBObject>();
		arrayList.add(new BasicDBObject().append("number", 2));
		arrayList.add(new BasicDBObject().append("name", "ayub-2"));
		query.put("$and", arrayList);

		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}
	
	/**
	 * $regex example – get documents where name like pattern 'Mky.*-[1-3]', case insensitive.
	 * 
	 * @param dbCollection
	 */
	public static void findRegexQuery(DBCollection dbCollection) {
		BasicDBObject query = new BasicDBObject();
		query.put("name", new BasicDBObject().append("$regex", "ayu.*-[1-5]").append("$options", "i"));

		System.out.println(query.toString());
		DBCursor cursor = dbCollection.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}



	public static void main(String[] args) {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");
		DBCollection dbCollection = db.getCollection("dummytable");
//		fillData(dbCollection);
//		findQuery(dbCollection);
//		findEqualQuery(dbCollection);
//		findNotEqualQuery(dbCollection);
//		findGretterThenLessThanQuery(dbCollection);
//		findLogicalAndQuery(dbCollection);
//		findINListQuery(dbCollection);
//		findRegexQuery(dbCollection);
		findSingleFiledQuery(dbCollection);
	}

}
