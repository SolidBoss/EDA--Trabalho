package SymbolTables;

import static java.lang.System.out;
import java.io.File;
import java.io.IOException;

import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class Sequential2 {

	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };;
	static int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096 };

	// Contém o tipo de ficheiros que vão ser analizados
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	static String[] OrderTypeTest = { "sorted_test", "partially_sorted_test", "shuffled_test" };

	static double mediaput;
	static double maximoput;
	static double minimoput;
	static double medianaput;
	static double desvioput;
	static double mediadelete;
	static double maximodelete;
	static double minimodelete;
	static double medianadelete;
	static double desviodelete;
	
	public static void main(String[] args) throws IOException {

		for (String orderType : OrderType) {
			for (int numberOfItem : FileSizeWarm) {
				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();
				if (FileExists == true) {
					String[] textFiles = In.readStrings(FilePath);
					SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
					for (int count = 0; count != numberOfItem; count++) {
						String key = textFiles[count];
						sequential.put(key, count);
						sequential.delete(key);
						//sequential.get(key);
					}
				}
			}
		}
		
		// sequencial
		for (String orderType : OrderType) {
			for (int numberOfItem : FileSize) {

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

				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				int repeticions=5;
				Double[] timePut = new Double[repeticions];
				//Double[] timeDelete = new Double[repeticions];
				
				if (FileExists == true) {

					String[] textFiles = In.readStrings(FilePath);
					long estimatedTimePut, starTimePut = 0;
					//long estimatedTimeDelte, starTimeDelete = 0;
					
					
					if (!(orderType == "sorted") || !(orderType == "partially_sorted") ){
					// Binary
					for (int i = 0; i != repeticions; i++) {
						SequentialSearchST<String, Integer> sequential = new SequentialSearchST<String, Integer>();
						starTimePut = System.nanoTime();
						for (int count = 0; count != numberOfItem; count++) {
							String key = textFiles[count];
							// insere chave
							sequential.put(key, count);	
						}
						estimatedTimePut = System.nanoTime() - starTimePut;
						timePut[i] = (double) (estimatedTimePut);
					}
					
						
						//out.println(estimatedTimePut);
						
						/*starTimeDelete = System.nanoTime();
						for (int count = 0; count != numberOfItem; count++) {
							String key = textFiles[count];
							// insere chave
							sequential.delete(key);
						}
						estimatedTimeDelte = System.nanoTime() - starTimeDelete;
						timeDelete[i] = (double) (estimatedTimeDelte);
						//out.println(estimatedTimeDelte);*/
						
					
					
						maximoput=MedMinMax.maximeTimes(timePut);
			    		out.println("\nTempo maximo de inserção: " + maximoput + " ns");//imprime na consola
			    			
			    		minimoput=MedMinMax.minimeTimes(timePut);
			    		out.println("Tempo minimo de inserção: " + minimoput + " ns");//imprime na consola
			    			
			    		mediaput=MedMinMax.meanTimes(timePut);
			    		out.println("Tempo medio de inserção: " + mediaput + " ns");//imprime na consola
			    			
			    		medianaput=MedMinMax.medianTimes(timePut);
			    		out.println("Mediana de inserção: " + medianaput + " ns");//imprime na consola
			    			
			    		desvioput=MedMinMax.standardDeviation(timePut);
			    		out.println("Desvio padrão: " + desvioput + " ns");//imprime na consola
			    		
					}
			    		
			    		/*maximodelete=MedMinMax.maximeTimes(timeDelete);
			    		out.println("\nTempo maximo de Remoção: " + maximodelete + " ns");//imprime na consola
			    			
			    		minimodelete=MedMinMax.minimeTimes(timeDelete);
			    		out.println("Tempo minimo de Remoção: " + minimodelete + " ns");//imprime na consola
			    			
			    		mediadelete=MedMinMax.meanTimes(timeDelete);
			    		out.println("Tempo medio de Remoção: " + mediadelete + " ns");//imprime na consola
			    			
			    		medianadelete=MedMinMax.medianTimes(timeDelete);
			    		out.println("Mediana de Remoção: " + medianadelete + " ns");//imprime na consola
			    			
			    		desviodelete=MedMinMax.standardDeviation(timeDelete);
			    		out.println("Desvio padrão: " + desviodelete + " ns");//imprime na consola
				*/
					}
				}
			}
		
		
	}
}
