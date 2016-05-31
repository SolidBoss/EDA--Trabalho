package SymbolTables;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class Binary {

	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
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
		
		final Scanner input = new Scanner(in);
        out.print("Quantas experiênçias?(numero inteiro): ");
        int repeticions = input.nextInt(); //guarda input do numero de experiencias
        
		
		for (String orderType : OrderType) {
			for (int numberOfItem : FileSizeWarm) {
				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();
				if (FileExists == true) {
					String[] textFiles = In.readStrings(FilePath);
					BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
					for (int count = 0; count != numberOfItem; count++) {
						String key = textFiles[count];
						binary.put(key, count);
						binary.delete(key);
					}
				}
			}
		}
		
		// Binary
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
				
				Double[] timePut = new Double[repeticions];
				Double[] timeDelete = new Double[repeticions];
				
				if (FileExists == true) {

					String[] textFiles = In.readStrings(FilePath);
					
					long estimatedTimePut, starTimePut = 0;
					long estimatedTimeDelete, starTimeDelete = 0;
					int value = 0;
					
					PrintWriter file = new PrintWriter("data/" + "TSBinaryPut" + "_" + orderType + "_" + numberOfItem + ".csv");
					PrintWriter file1 = new PrintWriter("data/" + "TSBinaryDelete" + "_" + orderType + "_" + numberOfItem + ".csv");
				    
					// Binary
					for (int i = 0; i != repeticions; i++) {
						BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();
						
						starTimePut = System.nanoTime();
						for (int count = 0; count != numberOfItem; count++) {
							String key = textFiles[count];
							binary.put(key, i);
						}
						estimatedTimePut = System.nanoTime() - starTimePut;
						timePut[i] = (double) (estimatedTimePut);
						
						//out.println(binary.size());
						//out.println(binary.keys());
						//out.println(binary.contains(key));
						//out.println(binary.size());
						//out.println(estimatedTimePut);
						
						starTimeDelete = System.nanoTime();
						for (int count = 0; count != numberOfItem; count++) {
						String key = textFiles[count];
						binary.delete(key);
						
						}
						estimatedTimeDelete = System.nanoTime() - starTimeDelete;
						timeDelete[i] = (double) (estimatedTimeDelete);
						
						//out.println(estimatedTimeDelte);
						
					}
					
						maximoput=MedMinMax.maximeTimes(timePut);
						out.println("Tempo maximo de inserção: " + maximoput + " ns");//imprime na consola
						file.println("Tempo maximo de inserção: " + maximoput + " ns");//imprime no exel
		    		
			    		minimoput=MedMinMax.minimeTimes(timePut);
			    		out.println("Tempo minimo de inserção: " + minimoput + " ns");//imprime na consola
			    		file.println("Tempo minimo de inserção: " + minimoput + " ns");//imprime no exel
			    		
			    		mediaput=MedMinMax.meanTimes(timePut);
			    		out.println("Tempo medio de inserção: " + mediaput + " ns");//imprime na consola
			    		file.println("Tempo medio de inserção: " + mediaput + " ns");//imprime no exel
			    	
			    		medianaput=MedMinMax.medianTimes(timePut);
		    			out.println("Mediana de inserção: " + medianaput + " ns");//imprime na consola
		    			file.println("Mediana de inserção: " + medianaput + " ns");//imprime no exel
			    		
			    		desvioput=MedMinMax.standardDeviation(timePut);
			    		out.println("Desvio padrão: " + desvioput + " ns");//imprime na consola
			    		file.println("Desvio padrão: " + desvioput + " ns");//imprime no exel
		    			
	        			file.close();
			    		
			    		maximodelete=MedMinMax.maximeTimes(timeDelete);
			    		out.println("\nTempo maximo de Remoção: " + maximodelete + " ns");//imprime na consola
			    		file1.println("Tempo maximo de Remoção: " + maximodelete + " ns");//imprime no exel	
			    			
			    		minimodelete=MedMinMax.minimeTimes(timeDelete);
			    		out.println("Tempo minimo de Remoção: " + minimodelete + " ns");//imprime na consola
			    		file1.println("Tempo medio de Remoção: " + minimodelete + " ns");//imprime no exel	
			    		
			    		mediadelete=MedMinMax.meanTimes(timeDelete);
			    		out.println("Tempo medio de Remoção: " + mediadelete + " ns");//imprime na consola
			    		file1.println("Tempo medio de Remoção: " + mediadelete + " ns");//imprime no exel
			    			
			    		medianadelete=MedMinMax.medianTimes(timeDelete);
			    		out.println("Mediana de Remoção: " + medianadelete + " ns");//imprime na consola
			    		file1.println("Mediana de Remoção: " + medianadelete + " ns");//imprime no exel
			    			
			    		desviodelete=MedMinMax.standardDeviation(timeDelete);
			    		out.println("Desvio padrão: " + desviodelete + " ns");//imprime na consola
			    		file1.println("Desvio padrão: " + desviodelete + " ns");//imprime no exel
		    			
	        			file1.close();
	        			
					}
				}
			}
	}
	

	// procurar
	public static void searchSTBinary() {

		for (String orderType : OrderType) {
			for (int numberOfItem : FileSize) {

				String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
				boolean FileExists = new File(FilePath).isFile();

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
				
				// variavel do tempo
				long estimatedTimeGet = 0, starTimeGet = 0;

				// Caso o ficheiro exista vai fazer as operações em baixo
				if (FileExists == true) {
					String[] textFiles = In.readStrings(FilePath);
					BinarySearchST<String, Integer> binary = new BinarySearchST<String, Integer>();

					for (int count = 0; count != numberOfItem; count++) {
						String key = textFiles[count];
						binary.put(key, count);
					}
					String number = "0.7340631614132147";
					out.println(binary.contains(number));
					
					starTimeGet = System.nanoTime();
					out.println(binary.get(number));
					estimatedTimeGet = System.nanoTime() - starTimeGet;
					out.println("Demorou" +estimatedTimeGet+"ns");
				}
				

			}
		}
	}

}
