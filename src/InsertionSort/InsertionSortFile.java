package InsertionSort;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.princeton.cs.introcs.In;

public class InsertionSortFile {
	
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128 };
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	
	public static void main(String[] args) throws IOException {

		for (String orderType : OrderType) {
			for (int numberOfItem : FileSize) {
				long estimatedTime = runAlgorithm(orderType, numberOfItem);
		        out.println("Tempo de ordena��o (" + orderType + ", " + numberOfItem + ") : " + estimatedTime + " ns");
			}
		}
	}
	
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		
		boolean FileExists = new File(FilePath).isFile();
		
		long estimatedTime = 0;

		if (FileExists == true) {
			
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			
			if (orderType == "sorted") {
				out.println("-----------------------------------");
				out.println("Sorted");
				out.println("Numero de Itens " + Item);
				out.println("-----------------------------------");
			} else if (orderType == "partially_sorted") {
				out.println("-----------------------------------");
				out.println("Partially Sorted ");
				out.println("Numero de Itens " + Item);
				out.println("-----------------------------------");
			} else {
				out.println("-----------------------------------");
				out.println("Shuffled");
				out.println("Numero de Itens " + Item);
				out.println("-----------------------------------");
			}
			
			long startTime = System.nanoTime();
			Insertion.sort(textFiles);
			estimatedTime = System.nanoTime() - startTime;
			return estimatedTime;
		}

		else {
			return 0;
		}
		
	}
}