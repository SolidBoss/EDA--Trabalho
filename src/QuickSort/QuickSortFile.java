package QuickSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class QuickSortFile {
	
	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {
			
		// Variavel que diz localizacao do ficheiro txt,o orderType é referente a cada tipo de ordem e o numberOfItem é referente a cada nº do FileSize
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;
		int i = 0; 
		Comparable[] textFiles = new Comparable[Item];
		
		// Caso o ficheiro exista, é feita a operação de ordenação
		if (FileExists == true) {
				Scanner in = new Scanner(new FileReader(FilePath)); //vai ler todo o conteúdo dos ficheiro 
				while (in.hasNextDouble()) {
					textFiles[i] = in.nextDouble();
					i++;
				}
				long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
				Quick.sort(textFiles);//Ordena cada ficheiro
				estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel
				
				return estimatedTime;
		} else {
			return 0;
		}
}
	//Metodo para o WarmUp
	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public static void runAlgorithmTest(String orderType, int Item) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		int i = 0; 
		Comparable[] textFiles = new Comparable[Item];

		if (FileExists == true) {
			Scanner in = new Scanner(new FileReader(FilePath)); //vai ler todo o conteúdo dos ficheiro 
			while (in.hasNextDouble()) {
				textFiles[i] = in.nextDouble();
				i++;
			}
			Quick.sort(textFiles);
		}
	}
	
	//Metodo para ler ler strings do txt, ordernar com o Quick e passar para o metodo getCountData
	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public static int[] runCountData(String orderType, int Item) throws FileNotFoundException {
		
		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		int[] countData = null;
		int i = 0; 
		Comparable[] textFiles = new Comparable[Item];
			
		if (FileExists==true) {	
			Scanner in = new Scanner(new FileReader(FilePath)); //vai ler todo o conteúdo dos ficheiro 
			while (in.hasNextDouble()) {
				textFiles[i] = in.nextDouble();
				i++;
			}
			Quick.sort(textFiles);//Ordena cada ficheiro
			countData = Quick.getCountData();//invoca o metodo getCountData e guarda os valores na variavel
			
			return countData;
			
		} else {
			return null;
		}
	}
}