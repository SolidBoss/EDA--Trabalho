package InsertionSort;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class InsertionSortFile {
	
	static double media; 
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;
	
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	
	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		final Scanner input = new Scanner(in);

        out.print("Quantas experi�n�ias?: ");

        int repetir = input.nextInt();
        Double[] tempo = new Double[repetir];

		for (String orderType : OrderType) {
			
			for (int numberOfItem : FileSize) {
				
				PrintWriter file = new PrintWriter("data/" + "InsertionSort" + "_" + orderType + "_" + numberOfItem + ".csv");
				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				if (FileExists == true) {
					
					if (numberOfItem < 65536){
					
						long estimatedTime = 0;
						
						@SuppressWarnings("deprecation")
						String[] textFiles = In.readStrings(FilePath);
						
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
					
						for (int i = 0; i != repetir; i++) {
							long startTime = System.nanoTime();
							Insertion.sort(textFiles);
							estimatedTime = System.nanoTime() - startTime;
							tempo[i] = (double) (estimatedTime);
							out.println("Tempo de ordena��o da " + (i+1) + "� experi�n�ia: " + estimatedTime + " ns");
					
						}	
				
						out.println("-----------------------------------");
						maximo = MedMinMax.maximeTimes(tempo);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");
					
						minimo = MedMinMax.minimeTimes(tempo);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");
					
						media = MedMinMax.meanTimes(tempo);
						out.println("Tempo medio de ordena��o: " + media + " ns");
						file.println("Tempo medio de ordena��o: " + media + " ns");
					
						mediana = MedMinMax.medianTimes(tempo);
						out.println("Mediana de ordena��o: " + mediana + " ns");
						file.println("Mediana de ordena��o: " + mediana + " ns");
					
						desvio = MedMinMax.standardDeviation(tempo);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");
					
						file.close();
					}
				}
			}
		}
	}	
}