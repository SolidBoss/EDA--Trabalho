package SymbolTables;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class Binary {

	public static long runAlgorithmPut(String orderType, int numberOfItem) throws FileNotFoundException {
		//ficheiro a analisar
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";

		@SuppressWarnings("deprecation")
		String[] textFiles = In.readStrings(FilePath);
		
		long estimatedTimePut, starTimePut = 0;
		//criação de uma nova BinarySearchST
		BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
	
		//ciclo para inserção na tabela de simbolos
		starTimePut = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);//insere a chave (que vai ser um número do txt) e o count vai ser o value
		}
		estimatedTimePut = System.nanoTime() - starTimePut;
		
		return estimatedTimePut;
		//out.println(binary.keys());
		//out.println(binary.size());
	}
	
	public static long searchBinaryFail(String orderType, int numberOfItem) throws FileNotFoundException {
		
		//numero que vamos procurar
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
		
		//operações que serviram para encontrar o número a procurar em todos os ficheiros
		//mostra o maximo da tabela de simbolos
		//out.println(binary.max());
		//mostra o minimo da tabela de simbolos
		//out.println(binary.min());
		
		//vê o tempo que se demora até encontrar o número
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
		//inserimos para depois remover
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.put(key, count);
		}
		
		//medidos o tempo de remoção da key
		starTimeDelete = System.nanoTime();
		for (int count = 0; count != numberOfItem; count++) {
			String key = textFiles[count];
			binary.delete(key);
		}
		estimatedTimeDelete = System.nanoTime() - starTimeDelete;
		
		return estimatedTimeDelete;
	}
}
