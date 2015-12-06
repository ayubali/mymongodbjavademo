package com.sarker.mongodb;

import java.io.File;
import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class SaveImageDocumentApp {

	public static void saveImageIntoDB(GridFS gfs, String fileName,
			String saveFileName) throws IOException {
		File file = new File(fileName);
		GridFSInputFile gfsFile = gfs.createFile(file);
		gfsFile.setFilename(saveFileName);
		gfsFile.save();
	}

	public static void getImage(GridFS gfs, String imageName)
			throws IOException {
		GridFSDBFile gfsoutFile = gfs.findOne(imageName);
		System.out.println("gfsoutFile:\n" + gfsoutFile);
	}

	public static void printAllPhoto(GridFS gfs) {
		DBCursor cursor = gfs.getFileList();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public static void saveImageIntoLocal(GridFS gfs, String imageName,
			String saveLocalFileName) throws IOException {
		GridFSDBFile gfsFile = gfs.findOne(imageName);
		gfsFile.writeTo(saveLocalFileName);
	}

	public static void remove(GridFS gfs, String newFileName) {
		gfs.remove(gfs.findOne(newFileName));
	}

	public static void main(String[] args) throws IOException {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("company");
		GridFS gfs = new GridFS(db, "photo");
		// saveImageIntoDB(gfs,
		// "C:\\Users\\Zia\\Desktop\\Stellar Design Image\\main_console_games.png","ayub-java-image");
		printAllPhoto(gfs);
		saveImageIntoLocal(gfs, "ayub-java-image",
				"C:\\Users\\Zia\\Desktop\\Stellar Design Image\\ayub-java-image.png");
		
		remove(gfs, "ayub-java-image");
		getImage(gfs, "ayub-java-image");

	}

}
