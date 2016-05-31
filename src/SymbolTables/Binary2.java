package SymbolTables;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Main.MedMinMax;
import MergeSort.Merge;
import edu.princeton.cs.introcs.In;

public class Binary2 {

	static double mediaput;
	static double maximoput;
	static double minimoput;
	static double medianaput;
	static double desvioput;
	static double mediadelete;
	static double maximodelete;
	static double minimodelete;
	static double medianadelete;
	static double desviodelete;

	public static long runAlgorithmPut(String orderType, int numberOfItem) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

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
	}
	
	public static void TSBinaryWarm(String orderType, int numberOfItem) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		if (FileExists == true) {
			String[] textFiles = In.readStrings(FilePath);
			BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
			for (int count = 0; count != numberOfItem; count++) {
				String key = textFiles[count];
				binary.put(key, count);
				binary.delete(key);
			}
		}
	}
	
	public static long runAlgorithmDelete(String orderType, int numberOfItem) throws FileNotFoundException {
		long estimatedTimeDelete, starTimeDelete = 0;

		
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
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
	
	//Pesquisar

}
