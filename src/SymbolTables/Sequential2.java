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
				sequential.valueFor(key);
				sequential.delete(key);
			}
		}
	} 
	
	public static long searchSequentialFail(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String searchNumber = "0.489779991366667";
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTime, starTime = 0;
		
		SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			sequential.put(key, count);
		}
		
		
		starTime = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			sequential.valueFor(searchNumber);
			estimatedTime = System.nanoTime() - starTime;
			
			return estimatedTime;
		}
		//out.println("Encontrado o texto na posicao "+sequential.get(searchingText));
		estimatedTime = System.nanoTime() - starTime;
	
		return estimatedTime;
	}
	
public static long searchSequentialSuccess(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String searchNumber = "0.5023764462686966";
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTime, starTime = 0;
		
		SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
		
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			sequential.put(key, count);
		}
		
		starTime = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			sequential.valueFor(searchNumber);
		}
		estimatedTime = System.nanoTime() - starTime;
		
		//out.println(sequential.contains(searchNumber));
		//out.println(sequential.keys());
		//out.println(sequential.size());
		
		return estimatedTime;
		
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
