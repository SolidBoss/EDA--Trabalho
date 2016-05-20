package MergeSort;

import static java.lang.System.out;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

//classe para ver o n� de compara��es, n� de acessos ao array, n� de leituras e o n� de escritas
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
			
			// vai ler todo o conte�do dos ficheiros
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);

			//Tivemos que utilizar a classe InstrumentedMerge para ordenar cada ficheiro, porque s� nessa classe � que conseguimos saber as intera��es que est�o acontecer no Array
			InstrumentedMerge.sort(textFiles);

			//vai aceder ao metodo getNumberOfComparisons() da classe InstrumentedMerge e retorna o n� de compara��es feitos no merge, do textFiles em causa  
			out.println("N�mero de compara��es: " + InstrumentedMerge.getNumberOfComparisons());
			//vai aceder ao metodo getNumberOfArrayReads() da classe InstrumentedMerge e retorna o n� de leituras feitas no merge, do textFiles em causa  
			out.println("N�mero de leitura dos Array: " + InstrumentedMerge.getNumberOfArrayReads());
			//vai aceder ao metodo getNumberOfArrayWrites() da classe InstrumentedMerge e retorna o n� de escritas feitas no merge, do textFiles em causa  
			out.println("N�mero de escritas no Array: " + InstrumentedMerge.getNumberOfArrayWrites());
			//vai aceder ao metodo getNumberOfArrayAccesses() da classe InstrumentedMerge e retorna o n� de acessos feitos no merge, do textFiles em causa  
			out.println("N�mero de acesso ao Array: " + InstrumentedMerge.getNumberOfArrayAccesses());
		}
	
}
}
