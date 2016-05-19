package Main;


import static java.lang.System.in;
import static java.lang.System.out;
//import MergeSort.BottomUpMergeFile;
import ResizingArray.ResizingArrayFile;
import edu.princeton.cs.introcs.In;
import InsertionSort.InsertionSortFile;
import LinkedList.LinkedStackFile;
import MergeSort.BottomUpMerge;
import MergeSort.BottomUpMergeFile;
import MergeSort.InstrumentedMerge;
import MergeSort.MergeSortFile;
import MergeSort.Merge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Menu {
	// le input do teclado
	static Scanner inputData; 
	
	//public static final double timeBudgetPerExperiment = 2.0;
	
	//public static final double minimumTimePerContiguousRepetitions = 2e-5;
	
	//Guarda o resultado das somas para que o compilador do java n�o tenha que optimizar nenhuma das chamadas do sumFrom1To()
	//private static long sum;
	
	// Cont�m o n�mero dos ficheiros que v�o ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
	static int[] FileSizeImpar = { 3, 5, 9, 17, 33, 65, 129, 257, 513, 1025, 2049, 4097, 8193, 16385, 32769, 65537, 131073, 262145, 524289, 1048577 };
	static int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
	// Cont�m o tipo de ficheiros que v�o ser analizados
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	static String[] OrderTypeTest = { "sorted_test", "partially_sorted_test", "shuffled_test" };
	
	// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao
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
	
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;
	
	static int runMenu() { //Menu
		out.println("\nOpera��es da Pilha: ");
		out.println("LinkedList " );
		out.println("1 - Medir tempo de inser��o e remo��o dados na pilha");
		out.println("Resizing Array " );
		out.println("2 - Medir tempo de inser��o e remo��o dados na pilha");
		out.println("3 - Verificar acrescimos e decrementos na pilha com potencias de 2");
		out.println("4 - Verificar acrescimos e decrementos na pilha com potencias de 2+1");
		out.println("Merge Sort");
		out.println("5 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("6 - Verificar compara��es e acessos ao array");
		out.println("7 - BottomUpMerge");
		out.println("Insertion Sort");
		out.println("8 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("9 - Verificar compara��es e acessos ao array");
		out.println("Quick Sort");
		out.println("10 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("11 - Verificar compara��es e acessos ao array");
		out.println("12 - Sair");
		out.println("Op��o: ");
		return inputData.nextInt(); // Retorna o input do teclado
	}
	
	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			//Linked List: Medir tempo de inser��o e remo��o dados na pilha
			if(opcao==1){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experi�ncias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
		        //aquecimento da experiencia
		        for (@SuppressWarnings("unused") 
				int numberOfItem : FileSize){
		        	LinkedStackFile.LinkedStackTest(numberOfItem);
		    	}
		        
		        for (int numberOfItem : FileSize) {
		        	PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + numberOfItem + ".csv");
		        	PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + numberOfItem + ".csv");	
		        	
		        	Double[] timeTotalPush = new Double[repeticions];
		    		Double[] timeTotalPop = new Double[repeticions];
		    		long estimatedTimePush = 0;
		    		long estimatedTimePop = 0;
		    			
		    		out.println("-----------------------------------");
		    		out.println("Numero de Itens " + numberOfItem);
		    		out.println("-----------------------------------");
		    			
		    		for (int a = 0; a != repeticions; a++) {
		    			estimatedTimePush = LinkedStackFile.runPushStack(numberOfItem);
	        			timeTotalPush[a] = (double) (estimatedTimePush);
	        				
	        			estimatedTimePop = LinkedStackFile.runPopStack(numberOfItem);
	        			timeTotalPop[a] = (double) (estimatedTimePop);
		    		}
				
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_push=MedMinMax.maximeTimes(timeTotalPush);
		    		out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");//imprime na consola
		    		file.println("Tempo maximo de inser��o: " + maximo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_push=MedMinMax.minimeTimes(timeTotalPush);
		    		out.println("Tempo minimo de inser��o: " + minimo_push + " ns");//imprime na consola
		    		file.println("Tempo minimo de inser��o: " + minimo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		media_push=MedMinMax.meanTimes(timeTotalPush);
		    		out.println("Tempo medio de inser��o: " + media_push + " ns");//imprime na consola
		    		file.println("Tempo medio de inser��o: " + media_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		mediana_push=MedMinMax.medianTimes(timeTotalPush);
		    		out.println("Mediana de inser��o: " + mediana_push + " ns");//imprime na consola
		    		file.println("Mediana de inser��o: " + mediana_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_push=MedMinMax.standardDeviation(timeTotalPush);
		    		out.println("Desvio padr�o: " + desvio_push + " ns");//imprime na consola
		    		file.println("Desvio padr�o: " + desvio_push + "ns");//imprime no exel
		    			
		    		file.close();
		    			
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
		    		out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");//imprime na consola
		    		file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
		    		out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");//imprime na consola
		    		file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		media_pop=MedMinMax.meanTimes(timeTotalPop);
		    		out.println("Tempo medio de remo��o: " + media_pop + " ns");//imprime na consola
		    		file1.println("Tempo medio de remo��o: " + media_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		mediana_pop=MedMinMax.medianTimes(timeTotalPop);
		    		out.println("Mediana de remo��o: " + mediana_pop + " ns");//imprime na consola
		    		file1.println("Mediana de remo��o: " + mediana_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
		    		out.println("Desvio padr�o: " + desvio_pop + " ns");//imprime na consola
		    		file1.println("Desvio padr�o: " + desvio_pop + "ns");//imprime no exel
		    				
		    		file1.close();
		        }
				   
			}
			//Resizing Array: Medir tempo de inser��o e remo��o dados na pilha
			else if(opcao==2){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experi�ncias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
		        //aquecimento da experiencia
		        for (@SuppressWarnings("unused") 
				int numberOfItem : FileSize){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
		        for (int numberOfItem : FileSize) {
		        	PrintWriter file = new PrintWriter("data/" + "ResizingArrayInsert" + "_" + numberOfItem + ".csv");
		        	PrintWriter file1 = new PrintWriter("data/" + "ResizingArrayDelete" + "_" + numberOfItem + ".csv");	
		        		
		        	Double[] timeTotalPush = new Double[repeticions];
		    		Double[] timeTotalPop = new Double[repeticions];
		    		long estimatedTimePush = 0;
		    		long estimatedTimePop = 0;
		    			
		    		out.println("-----------------------------------");
		    		out.println("Numero de Itens " + numberOfItem);
		    		out.println("-----------------------------------");
		    			
		    		for (int a = 0; a != repeticions; a++) {
		    			estimatedTimePush = ResizingArrayFile.runPushStack(numberOfItem);
	        			out.println("Tempo de inser��o (experi�ncia: " + (a + 1) + "): " + estimatedTimePush + " ns");// Mostra o tempo
	        			out.println("-----------------------------------");
	        			timeTotalPush[a] = (double) (estimatedTimePush);
	        				
	        			estimatedTimePop = ResizingArrayFile.runPopStack(numberOfItem);
	        			out.println("Tempo de remo��o (experi�ncia: " + (a + 1) + "): " + estimatedTimePop + " ns");// Mostra o tempo
	        			out.println("-----------------------------------");
	        			timeTotalPop[a] = (double) (estimatedTimePop);
		    		}
				
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_push=MedMinMax.maximeTimes(timeTotalPush);
		    		out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");//imprime na consola
		    		file.println("Tempo maximo de inser��o: " + maximo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_push=MedMinMax.minimeTimes(timeTotalPush);
		    		out.println("Tempo minimo de inser��o: " + minimo_push + " ns");//imprime na consola
		    		file.println("Tempo minimo de inser��o: " + minimo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		media_push=MedMinMax.meanTimes(timeTotalPush);
		    		out.println("Tempo medio de inser��o: " + media_push + " ns");//imprime na consola
		    		file.println("Tempo medio de inser��o: " + media_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		mediana_push=MedMinMax.medianTimes(timeTotalPush);
		    		out.println("Mediana de inser��o: " + mediana_push + " ns");//imprime na consola
		    		file.println("Mediana de inser��o: " + mediana_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_push=MedMinMax.standardDeviation(timeTotalPush);
		    		out.println("Desvio padr�o: " + desvio_push + " ns");//imprime na consola
		    		file.println("Desvio padr�o: " + desvio_push + "ns");//imprime no exel
		    			
		    		file.close();
		    			
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
		    		out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");//imprime na consola
		    		file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
		    		out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");//imprime na consola
		    		file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		media_pop=MedMinMax.meanTimes(timeTotalPop);
		    		out.println("Tempo medio de remo��o: " + media_pop + " ns");//imprime na consola
		    		file1.println("Tempo medio de remo��o: " + media_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		mediana_pop=MedMinMax.medianTimes(timeTotalPop);
		    		out.println("Mediana de remo��o: " + mediana_pop + " ns");//imprime na consola
		    		file1.println("Mediana de remo��o: " + mediana_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
		    		out.println("Desvio padr�o: " + desvio_pop + " ns");//imprime na consola
		    		file1.println("Desvio padr�o: " + desvio_pop + "ns");//imprime no exel
		    				
		    		file1.close();
		        }
		    }
			//ResizingArray verificar acrescimos e decrementos na pilha com potencias de 2
			else if(opcao==3){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experi�n�ias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
				out.println("--------------------------------------");
				out.println("Resizing Array");
				out.println("--------------------------------------");
			
				 //aquecimento da experiencia
		        for (@SuppressWarnings("unused") 
				int numberOfItem : FileSize){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
				for (int numberOfItem : FileSize) {
					for (int a = 0; a != repeticions; a++) {
					out.println("Experi�ncia: " + (a + 1));
					ResizingArrayFile.Resizing(numberOfItem);
					}
				}	
			}
			//ResizingArray Verificar acrescimos e decrementos na pilha com potencias de 2+1
			else if(opcao==4){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experi�n�ias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
				out.println("--------------------------------------");
				out.println("Resizing Array");
				out.println("--------------------------------------");
			
				//aquecimento da experiencia
		        for (@SuppressWarnings("unused") 
				int numberOfItem : FileSize){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
				for (int numberOfItem : FileSizeImpar) {
					for (int a = 0; a != repeticions; a++) {
					ResizingArray.ResizingArrayFile.Resizing(numberOfItem);
					}
				}		
			}
			// Merge Sort Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled
			else if(opcao==5){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
				
		        out.print("Quantas experi�n�ias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias
		       
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		MergeSortFile.runAlgorithmTest(orderType, numberOfItem);
		          	}
		        }
		        
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		PrintWriter file = new PrintWriter("data/" + "MergeSort" + "_" + orderType + "_" + numberOfItem + ".csv");
		    
		        		Double[] timeTotal = new Double[repeticions];
		        		long estimatedTime = 0;
		        		
		        		
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
					
		        			for (int a = 0; a != repeticions; a++) {
		        				estimatedTime = MergeSortFile.runAlgorithm(orderType, numberOfItem);
		        				timeTotal[a] = (double) (estimatedTime);
		        			}
					
		        			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordena��o: " + media + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordena��o: " + mediana + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
			}
			//Merge Sort Verificar compara��es e acessos ao array
			else if(opcao==6){
				
				
			}
			//BottomUpMerge
			else if(opcao==7){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
				
		        out.print("Quantas experi�n�ias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias
		       
		        //warmup
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		BottomUpMergeFile.runAlgorithmTest(orderType, numberOfItem);
		          	}
		        }
		        
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		PrintWriter file = new PrintWriter("data/" + "BottomUpMergeFile" + "_" + orderType + "_" + numberOfItem + ".csv");
		    
		        		Double[] timeTotal = new Double[repeticions];
		        		long estimatedTime = 0;
		        		
		        		
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
					
		        			for (int a = 0; a != repeticions; a++) {
		        				estimatedTime = BottomUpMergeFile.runAlgorithm(orderType, numberOfItem);
		        				timeTotal[a] = (double) (estimatedTime);
		        			}
					
		        			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordena��o: " + media + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordena��o: " + mediana + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
			}
			//Insertion Sort Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled
			else if(opcao==8){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experi�n�ias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias
		        //Double[] tempo = new Double[repetir]; //cria array tempo com o numero de posi��es indicadas no input			
		        
		        //warmup
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		InsertionSortFile.runAlgorithmTest(orderType, numberOfItem);
		          	}
		        }
		        
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		PrintWriter file = new PrintWriter("data/" + "InsertionSort" + "_" + orderType + "_" + numberOfItem + ".csv");
		        		//int repeticions = 2;
		        		Double[] timeTotal = new Double[repeticions];
		        		long estimatedTime = 0;
		        		
		        		// Apenas n�o � feito a opera��o de ordena��o quando o orderType � shuffled e numberOfItem < 65536
		        		if (!(orderType == "shuffled") || (numberOfItem < 65536)){
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
					
		        			for (int a = 0; a != repeticions; a++) {
		        				estimatedTime = InsertionSortFile.runAlgorithm(orderType, numberOfItem);
		        				out.println("Tempo de ordena��o (experi�ncia: " + (a + 1) + "): " + estimatedTime + " ns");// Mostra o tempo
		        				out.println("-----------------------------------");
		        				timeTotal[a] = (double) (estimatedTime);
		        			}
					
		        			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordena��o: " + media + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordena��o: " + mediana + " ns");//imprime no exel
	    			
		        			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
		     	}             
			}
			//Insertion Sort Verificar compara��es e acessos ao array
			else if(opcao==9){
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
						
						int[] data = {0, 0, 0};
						data = InsertionSortFile.runCountData(orderType, numberOfItem);
						out.println("N�mero de compara��es: "+data[0]);
						out.println("N�mero de Acessos a array: "+data[1]);
						out.println("N�mero de Trocas: "+data[2]);
					}
				}
			}
			
		}while (opcao != 12);{System.exit(0);}
	}
}

/*
 * Este projeto foi concebido utilizando as seguintes fontes como recurso para
 * implementa��o do c�digo:
 * 
 * Tipos abstratos de dados: 
 * - http://www.e-reading.club/bookreader.php/138175/Abstract_Data_Types_in_Java.pdf
 *
 * C�digo utilizado e adaptado de: 
 * - Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 *   Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * - C�digo disponibilizado pelo professor Manuel Menezes Sequeira
 * - http://www.java2s.com/
 */