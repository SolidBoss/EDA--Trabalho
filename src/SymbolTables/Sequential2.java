package SymbolTables;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException; 
import edu.princeton.cs.introcs.In;

public class Sequential2 {

public static long runAlgorithmPut(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTimePut, starTimePut = 0;
		SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
		starTimePut = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			sequential.put(key, count);
			
			
			//out.println(sequential.keys());
		}
		estimatedTimePut = System.nanoTime() - starTimePut;
		
		return estimatedTimePut;
		
	}
	
	public static void TSSequentialWarm(String orderType, int numberOfItem) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
			for (int count = 0; count != numberOfItem; count++) {
				String key = textFiles[count];
				sequential.put(key, count);
				sequential.delete(key);
			}
		}
	} 
	
	public static long runAlgorithmDelete(String orderType, int numberOfItem) throws FileNotFoundException {
		long estimatedTimeDelete, starTimeDelete = 0;

		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			sequential.put(key, count);
		}
		starTimeDelete = System.nanoTime();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			sequential.delete(key);
		}
		estimatedTimeDelete = System.nanoTime() - starTimeDelete;
		
		return estimatedTimeDelete;
	}
}
