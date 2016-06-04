package SymbolTables;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class Binary2 {

	public static long runAlgorithmPut(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTimePut, starTimePut = 0;
		
		BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
		
		starTimePut = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);
		}
		estimatedTimePut = System.nanoTime() - starTimePut;
		
		return estimatedTimePut;
		//out.println(binary.keys());
		//out.println(binary.size());
	}
	
	public static long searchBinaryFail(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String searchNumber = "0.489779991366667";
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTime, starTime = 0;
		
		BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);
		}
		
		//out.println(binary.max());
		//out.println(binary.min());
		
		starTime = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
		binary.get(searchNumber);
		}
		estimatedTime = System.nanoTime() - starTime;
	
		//out.println(binary.contains(searchNumber));
		//out.println(binary.keys());
		//out.println(binary.size());
		
		return estimatedTime;
	}
	
	public static long searchBinarySuccess(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String searchNumber = "0.5023764462686966";
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTime, starTime = 0;
		
		BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);
		}
		
		starTime = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
		binary.get(searchNumber);
		}
		estimatedTime = System.nanoTime() - starTime;
		
		//out.println(binary.contains(searchNumber));
		//out.println(binary.keys());
		//out.println(binary.size());
		
		return estimatedTime;
		
	}

	public static void TSBinaryWarm(String orderType, int numberOfItem) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		if (FileExists == true) {
			
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
			for (int count = 0; count != numberOfItem; count++) {
				String key = textFiles[count];
				binary.put(key, count);
				binary.get(key);
				binary.delete(key);
			}
		}
	}
	
	
	public static long runAlgorithmDelete(String orderType, int numberOfItem) throws FileNotFoundException {
		long estimatedTimeDelete, starTimeDelete = 0;

		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);
		}
		
		starTimeDelete = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.delete(key);
		}
		estimatedTimeDelete = System.nanoTime() - starTimeDelete;
		
		return estimatedTimeDelete;
	}
}
