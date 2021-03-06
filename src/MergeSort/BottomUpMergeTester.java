package MergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class BottomUpMergeTester {
		
	public static long runBottomUpMergeSort(String orderType, int Item) throws FileNotFoundException {

		// Variavel que diz localizacao do ficheiro txt,o orderType � referente a cada tipo de ordem e o numberOfItem � referente a cada n� do FileSize
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;

		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath); // vai ler todo o conte�do dos ficheiro
			long startTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
			BottomUpMerge.sort(textFiles);//Ordena cada item do ficheiro
			estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel

			return estimatedTime;
		} else {
			return 0;
		}
	}
	
	public static void BottomUpMergeWarm(String orderType, int Item) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();

		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			BottomUpMerge.sort(textFiles);
		}
	}
}
