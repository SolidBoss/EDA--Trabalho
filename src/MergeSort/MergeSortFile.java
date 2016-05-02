package MergeSort;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import LinkedList.LinkedStackFile;

import java.util.Scanner;
import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {
	
		static double media;
		static double maximo;
		static double minimo;
		static double mediana;
		static double desvio;
		
		static int[] FileSize = { 2, 4, 8, 16, 32};
		static String[] FileType = { "sorted", "partially_sorted", "shuffled" };
		
	public static void main(String[] args) throws IOException {

		for (String Type : FileType) {
			
			for (int Item : FileSize) {
			
				PrintWriter file = new PrintWriter("data/" + "MergeSort" + "_" + FileType + "_"+ Item + ".csv");
				
				String FilePath = "data/" + Type + "_" + Item + ".txt";

				boolean FileExists = new File(FilePath).isFile();
				
				if (FileExists == true) {
					
					long estimatedTime = 0;

					@SuppressWarnings("deprecation")
					String[] textFiles = In.readStrings(FilePath);
					
					
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

					int repetir = 10;
					Double[] tempo = new Double[repetir];
					for (int i = 0; i != repetir; i++) {
						long starTime = System.nanoTime();
						Merge.sort(textFiles);
						estimatedTime = System.nanoTime() - starTime;
						
						tempo[i] = (double) (estimatedTime);
					}

					maximo = MedMinMax.maximeTimes(tempo);
					out.println("Tempo maximo de inserção: " + maximo + " ns");
					file.println("Tempo maximo de inserção: " + maximo + "ns");
					
					minimo = MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de inserção: " + minimo + " ns");
					file.println("Tempo minimo de inserção: " + minimo + "ns");
					
					media = MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de inserção: " + media + " ns");
					file.println("Tempo medio de inserção: " + media + "ns");
					
					mediana = MedMinMax.medianTimes(tempo);
					out.println("Mediana de inserção: " + mediana + " ns");
					file.println("Mediana de inserção: " + mediana + "ns");
					
					desvio = MedMinMax.standardDeviation(tempo);
					out.println("Desvio padrão: " + desvio + " ns");
					file.println("Desvio padrão: " + desvio + "ns");
					
					file.close();
				}
			}
		}
	}
	
	public static void verifica() {

		for (int Item : FileSize) {
			for (String Type : FileType) {
				
				String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();
				if (FileExists == true) {
					@SuppressWarnings("deprecation")
					String[] textFiles = In.readStrings(FilePath);
					
					InstrumentedMerge.sort(textFiles);

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
					out.println("Número de comparações: " + InstrumentedMerge.getNumberOfComparisons());
					out.println("Número de acesso ao Array: " + InstrumentedMerge.getNumberOfArrayAccesses());
					out.println("Número de leitura dos Array: " + InstrumentedMerge.getNumberOfArrayReads());
					out.println("Número de escritas no Array: " + InstrumentedMerge.getNumberOfArrayWrites());
				}
			}
		}
	}
	
	public static void BottomUpMerge() {
		
	for (int Item : FileSize) {
		
		for (String Type : FileType) {
			String FilePath = "data/" + Type + "_" + Item + ".txt";

			boolean FileExists = new File(FilePath).isFile();
			
			if (FileExists == true) {
				long estimatedTime = 0;

				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath);

			
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

				int repetir = 10;
				Double[] tempo = new Double[repetir];
				for (int i = 0; i != repetir; i++) {
					long starTime = System.nanoTime();
					BottomUpMerge.sort(textFiles);
					estimatedTime = System.nanoTime() - starTime;
					
					tempo[i] = (double) (estimatedTime);
				}

				maximo = MedMinMax.maximeTimes(tempo);
				out.println("Tempo maximo de inserção: " + maximo + " ns");
				
				minimo = MedMinMax.minimeTimes(tempo);
				out.println("Tempo minimo de inserção: " + minimo + " ns");
				
				media = MedMinMax.meanTimes(tempo);
				out.println("Tempo medio de inserção: " + media + " ns");
				
				mediana = MedMinMax.medianTimes(tempo);
				out.println("Mediana de inserção: " + mediana + " ns");
				
				desvio = MedMinMax.standardDeviation(tempo);
				out.println("Desvio padrão: " + desvio + " ns");
				
			}
		}
	}}
}
