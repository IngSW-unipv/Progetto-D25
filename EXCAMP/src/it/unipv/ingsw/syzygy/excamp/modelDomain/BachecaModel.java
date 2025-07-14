package it.unipv.ingsw.syzygy.excamp.modelDomain;

import it.unipv.ingsw.syzygy.excamp.database.dao.BachecaDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class BachecaModel {
	private BachecaDAO dao;
	
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
	
	public BachecaModel() throws SQLException {
		dao = new BachecaDAO();
	}
	
	public String insertPhoto(File photo) throws SQLException, FileNotFoundException, IOException,
	InvalidImageFormatException, FileExceededException {
		if (!isValidImageFormat(photo)) {
			throw new InvalidImageFormatException();
		}
		
		if (photo.length() > MAX_FILE_SIZE) {
			throw new FileExceededException();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(photo.lastModified());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTaken = dateFormat.format(calendar.getTime());
		String albumPath = "photos/" + calendar.get(Calendar.YEAR) + "/Week_" + calendar.get(Calendar.WEEK_OF_YEAR);
		
		File albumDirectory = new File(albumPath);
		if(!albumDirectory.exists()) {
			albumDirectory.mkdirs();
		}
		
		File destinationFile = getUniqueFileName(albumDirectory, photo.getName());
		copyFile(photo, destinationFile);
		
		dao.insertPhoto(destinationFile.getAbsolutePath(), dateTaken, albumPath);
		
		return albumPath;
	}
	
	private boolean isValidImageFormat(File photo) {
		String fileName = photo.getName().toLowerCase();
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
		
		return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
	}
	
	private File getUniqueFileName(File albumDirectory, String originalFileName) {
		File destinationFile = new File(albumDirectory, originalFileName);
		int counter = 1;
		
		while (destinationFile.exists()) {
			String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
			String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
			String newFileName = baseName + counter++ + extension;
			destinationFile = new File(albumDirectory, newFileName);
		}
		
		return destinationFile;
	}
	
	private void copyFile(File source, File destination) throws FileNotFoundException, IOException {
		try (InputStream in = new FileInputStream(source);
			 OutputStream out = new FileOutputStream(destination)) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
	}
	
	public ResultSet getAllPhotos() throws SQLException {
		return dao.getAllPhotos();
	}
	
	public List<String> getAllWeeks() throws SQLException {
		return dao.getAllWeeks();
	}
	
	public void close() throws SQLException {
		dao.closeConnection();
	}
}
