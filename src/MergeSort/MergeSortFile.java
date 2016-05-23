package MergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {

	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
		
		// variavel que diz localizacao do ficheiro txt 
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		
		//variavel do tempo
		long estimatedTime = 0;
		
		// Caso o ficheiro exista vai fazer as operações em baixo
		if (FileExists == true) {
				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath); //vai ler todo o conteúdo dos ficheiro 
				long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
				Merge.sort(textFiles);//Através da classe merge, vai ordenar todos os items de cada ficheiro
				estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel
				
				return estimatedTime;
		} else {
			return 0;
		}
	}
	
	public static void runAlgorithmTest(String orderType, int Item) throws FileNotFoundException {
		
		//variavel que diz localizacao do ficheiro txt,o orderType é referente a cada tipo de ordem e o numberOfItem é referente a cada nº do FileSize
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		
		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			Merge.sort(textFiles);// Iniciar a ordenação Merge dos valores do textFiles
		}
	}
}