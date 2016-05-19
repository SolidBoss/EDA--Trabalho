package InsertionSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import MergeSort.Merge;
import edu.princeton.cs.introcs.In;

public class InsertionSortFile {
	
	public static void main(String[] args) throws IOException {
      
	}
		
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
			
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;
		
		if (FileExists == true) {
				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath); //vai ler todo o conteúdo dos ficheiro 
				long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
				Insertion.sort(textFiles);// Iniciar a ordenação de inserção com os valores do textFiles
				estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel
				
				return estimatedTime;
		} else {
			return 0;
		}
	}
	
public static void runAlgorithmTest(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		
		if (FileExists == true) {
			String[] textFiles = In.readStrings(FilePath);
			Insertion.sort(textFiles);
		}
	}
	
	public static int[] runCountData(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		int[] countData = null;
			
		if (FileExists==true) {	
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			Insertion.sort(textFiles);
			countData = Insertion.getCountData();	
			
			return countData;
			
		} else {
			return null;
		}
	}
}