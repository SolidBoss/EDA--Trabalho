package MergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class BottomUpMergeFile {
		
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {

		// Variavel que diz localizacao do ficheiro txt,o orderType é referente a cada tipo de ordem e o numberOfItem é referente a cada nº do FileSize
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;

		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath); // vai ler todo o conteúdo dos ficheiro
			long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
			BottomUpMerge.sort(textFiles);//Ordena cada item do ficheiro
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
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			BottomUpMerge.sort(textFiles);
		}
	}
}
