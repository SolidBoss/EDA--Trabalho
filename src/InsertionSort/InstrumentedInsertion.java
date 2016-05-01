package InsertionSort;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.princeton.cs.introcs.In;

public class InstrumentedInsertion {
	
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128 };
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	
	public static void main(String[] args) throws IOException {

		for (String orderType : OrderType) {
			for (int numberOfItem : FileSize) {
				int[] data = {0, 0, 0};
				data = runCountTest(orderType, numberOfItem);
				out.println("Comparações: "+data[0]);
				out.println("Acessos a array: "+data[1]);
				out.println("Trocas: "+data[2]);
			}
		}
	}
	
	public static int[] runCountTest(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		int[] countData = null;
		boolean FileExists = new File(FilePath).isFile();
			
		if (FileExists==true) {	
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			Insertion.sort(textFiles);
			countData = Insertion.getCountData();	
			
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
			
			return countData;
			
			} else {
				return null;
			}
	}
}
