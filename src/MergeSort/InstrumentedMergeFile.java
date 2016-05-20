package MergeSort;

import static java.lang.System.out;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

//classe para ver o nº de comparações, nº de acessos ao array, nº de leituras e o nº de escritas
public class InstrumentedMergeFile {
		
		public static void runCountData(String orderType, int numberOfItem) throws FileNotFoundException {
			
		String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		
		if (FileExists == true) {
			
			if (orderType == "sorted") {
				out.println("-----------------------------------");
				out.println("Sorted");
				out.println("Numero de Itens " + numberOfItem);
				out.println("-----------------------------------");
			} else if (orderType == "partially_sorted") {
				out.println("-----------------------------------");
				out.println("Partially Sorted ");
				out.println("Numero de Itens " + numberOfItem);
				out.println("-----------------------------------");
			} else {
				out.println("-----------------------------------");
				out.println("Shuffled");
				out.println("Numero de Itens " + numberOfItem);
				out.println("-----------------------------------");
			}
			
			// vai ler todo o conteúdo dos ficheiros
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);

			//Tivemos que utilizar a classe InstrumentedMerge para ordenar cada ficheiro, porque só nessa classe é que conseguimos saber as interações que estão acontecer no Array
			InstrumentedMerge.sort(textFiles);

			//vai aceder ao metodo getNumberOfComparisons() da classe InstrumentedMerge e retorna o nº de comparações feitos no merge, do textFiles em causa  
			out.println("Número de comparações: " + InstrumentedMerge.getNumberOfComparisons());
			//vai aceder ao metodo getNumberOfArrayReads() da classe InstrumentedMerge e retorna o nº de leituras feitas no merge, do textFiles em causa  
			out.println("Número de leitura dos Array: " + InstrumentedMerge.getNumberOfArrayReads());
			//vai aceder ao metodo getNumberOfArrayWrites() da classe InstrumentedMerge e retorna o nº de escritas feitas no merge, do textFiles em causa  
			out.println("Número de escritas no Array: " + InstrumentedMerge.getNumberOfArrayWrites());
			//vai aceder ao metodo getNumberOfArrayAccesses() da classe InstrumentedMerge e retorna o nº de acessos feitos no merge, do textFiles em causa  
			out.println("Número de acesso ao Array: " + InstrumentedMerge.getNumberOfArrayAccesses());
		}
	
}
}
