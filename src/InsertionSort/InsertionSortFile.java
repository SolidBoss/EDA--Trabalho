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

        out.print("Quantas experi�n�ias?(numero inteiro): ");
        
        int repetir = input.nextInt(); //guarda input do numero de experiencias
        Double[] tempo = new Double[repetir]; //cria array tempo com o numero de posi��es indicadas no input
        
        //Ciclo que analisa cada posicao do array ou seja cada orderType do OrderType
		for (String orderType : OrderType) {
			
			//Ciclo que analisa cada posicao do array ou seja cada numberOfItem do FileSize
			for (int numberOfItem : FileSize) {
				
				// Cria novo ficheiro exel com o nome InsertionSort,orderType e o n� do item, na directoria pretendida
				PrintWriter file = new PrintWriter("data/" + "InsertionSort" + "_" + orderType + "_" + numberOfItem + ".csv");
				
				// variavel que diz localizacao do ficheiro txt,o orderType � referente a cada tipo de ordem e o numberOfItem � referente a cada n� do FileSize
				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				// Caso o ficheiro exista, � feito a opera��o de ordena��o de inser��o e calculos da media,mediana,max,min e desvio padrao
				if (FileExists == true) {
					
					// Apenas n�o � feito a opera��o de ordena��o quando o orderType � shuffled e numberOfItem < 65536
					if (!(orderType == "shuffled") || (numberOfItem < 65536)){
					
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