package InsertionSort;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.In;

public class InsertionSortTester {
	
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
			
		// Variavel que diz localizacao do ficheiro txt,o orderType � referente a cada tipo de ordem e o numberOfItem � referente a cada n� do FileSize
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;
		
		// Caso o ficheiro exista, � feita a opera��o de ordena��o
		if (FileExists == true) {
				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath); //vai ler todo o conte�do dos ficheiro 
				long startTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
				Insertion.sort(textFiles);//Ordena cada ficheiro
				estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel
				
				return estimatedTime;
		} else {
			return 0;
		}
}
	//Metodo para o WarmUp
	public static void InsertionSortWarmUp(String orderType, int Item) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();

		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);
			Insertion.sort(textFiles);
		}
	}
	
	//Metodo para ler ler strings do txt, ordernar com o Insertion e passar para o metodo getCountData
	public static int[] runCountData(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		int[] countData = null;
			
		if (FileExists==true) {	
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath);//vai ler todo o conte�do dos ficheiro 
			Insertion.sort(textFiles);//Ordena cada ficheiro
			countData = Insertion.getCountData();//invoca o metodo getCountData e guarda os valores na variavel
			
			return countData;
			
		} else {
			return null;
		}
	}
}