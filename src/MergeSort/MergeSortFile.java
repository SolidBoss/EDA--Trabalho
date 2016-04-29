package MergeSort;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {
	
	// Variaveis para a média, mediana, maximo, minimo e desvio padrao
		static double media_push;
		static double media_pop;
		static double maximo_push;
		static double maximo_pop;
		static double minimo_push;
		static double minimo_pop;
		static double mediana_push;
		static double mediana_pop;
		static double desvio_pop;
		static double desvio_push;
		
		static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
				262144, 524288, 1048576 };
		static String[] FileType = { "sorted", "partially_sorted", "shuffled" };
		
	public static void main(String[] args) throws IOException {

		for (int Item : FileSize) {
			
			PrintWriter file = new PrintWriter("data/" + "MergeSort" + "_" + Item + ".csv");
			
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
						Merge.sort(textFiles);
						estimatedTime = System.nanoTime() - starTime;
						
						tempo[i] = (double) (estimatedTime);
					}

					maximo_push = MedMinMax.maximeTimes(tempo);
					out.println("Tempo maximo de inserção: " + maximo_push + " ns");
					file.println("Tempo maximo de inserção: " + maximo_push + "ns");
					
					minimo_push = MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de inserção: " + minimo_push + " ns");
					file.println("Tempo minimo de inserção: " + minimo_push + "ns");
					
					media_push = MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de inserção: " + media_push + " ns");
					file.println("Tempo medio de inserção: " + media_push + "ns");
					
					mediana_push = MedMinMax.medianTimes(tempo);
					out.println("Mediana de inserção: " + mediana_push + " ns");
					file.println("Mediana de inserção: " + mediana_push + "ns");
					
					desvio_push = MedMinMax.standardDeviation(tempo);
					out.println("Desvio padrão: " + desvio_push + " ns");
					file.println("Desvio padrão: " + desvio_push + "ns");
					
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
}
