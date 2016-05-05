package MergeSort;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import edu.princeton.cs.introcs.In;

//classe para ver o nº de comparações, nº de acessos ao array, nº de leituras e o nº de escritas
public class InstrumentedMergeFile {

	
	// Contém o número dos ficheiros que vão ser analizados
		static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
				262144, 524288, 1048576 };
		
		// Contém o tipo de ficheiros que vão ser analizados
		static String[] FileType = { "sorted", "partially_sorted", "shuffled" };
		
		public static void main(String[] args) throws IOException {
	//Ciclo para percorrer cada tipo de ficheiro
	for (String Type : FileType) {
		
		//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
		for (int Item : FileSize) {

			// variavel que diz localizacao dos ficheiros txt, o Item é referente a cada nº do FileSize
			String FilePath = "data/" + Type + "_" + Item + ".txt";
			boolean FileExists = new File(FilePath).isFile();
			
			// Caso o ficheiro exista são feitas as operações de ordenação
			if (FileExists == true) {
				
				//Ciclo que apenas serve para imprimir na consola o tipo de ficheiro que esta a ser analisado
				if (Type == "sorted") {
					out.println("-----------------------------------");
					out.println("Sorted");
					out.println("Numero de Itens " + Item);
					out.println("-----------------------------------");
				} else if (Type == "partially_sorted") {
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
}
}
