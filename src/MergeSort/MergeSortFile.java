package MergeSort;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;

import InsertionSort.Insertion;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {

	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;
		
		if (FileExists == true) {
				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath); //vai ler todo o conteúdo dos ficheiro 
				long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
				Merge.sort(textFiles);
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
			Merge.sort(textFiles);
		}
	}
}