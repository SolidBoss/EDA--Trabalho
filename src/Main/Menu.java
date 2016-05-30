package Main;


import static java.lang.System.in;
import static java.lang.System.out;
import ResizingArray.ResizingArrayFile;
import SymbolTables.BinarySearchST;
import edu.princeton.cs.introcs.In;
import InsertionSort.InsertionSortFile;
import SymbolTables.Binary2;
import LinkedList.LinkedStackFile;
import MergeSort.BottomUpMergeFile;
import MergeSort.InstrumentedMergeFile;
import MergeSort.MergeSortFile;
import QuickSort.QuickSortFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Menu {
	
	// le input do teclado
	static Scanner inputData; 
	
	// Contém o número dos ficheiros que vão ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
	static int[] FileSizeImpar = { 3, 5, 9, 17, 33, 65, 129, 257, 513, 1025, 2049, 4097, 8193, 16385, 32769, 65537, 131073, 262145, 524289, 1048577 };
	static int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
	
	// Contém o tipo de ficheiros que vão ser analizados
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	static String[] OrderTypeTest = { "sorted_test", "partially_sorted_test", "shuffled_test" };
	
	// Variaveis para a média, mediana, maximo, minimo e desvio padrao para o LinkedStack e Resizing Array
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
	
	// Variaveis para a média, mediana, maximo, minimo e desvio padrao para o Insertion e Merge
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;
	
	static int runMenu() { //Menu
		out.println("\nOperações da Pilha: ");
		out.println("LinkedList " );
		out.println("1 - Medir tempo de inserção e remoção dados na pilha");
		out.println("Resizing Array " );
		out.println("2 - Medir tempo de inserção e remoção dados na pilha");
		out.println("3 - Verificar acrescimos e decrementos na pilha com potencias de 2");
		out.println("4 - Medir tempo de inserção e remoção dados na pilha com potencias de 2+1");
		out.println("5 - Verificar acrescimos e decrementos na pilha com potencias de 2+1");
		out.println("Merge Sort");
		out.println("6 - Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled");
		out.println("7 - Verificar comparações e acessos ao array");
		out.println("8 - BottomUpMerge");
		out.println("Insertion Sort");
		out.println("9 - Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled");
		out.println("10 - Verificar comparações e acessos ao array");
		out.println("Quick Sort");
		out.println("11 - Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled");
		out.println("12 - Verificar comparações e acessos ao array");
		out.println("Tabela de Simbolos Binária");
		out.println("13 - Inserir e Apagar");
		out.println("14 - Apagar");
		out.println("15 - Pesquisar");
		out.println("Tabela de Simbolos Sequencial");
		out.println("16 - Inserir e Apagar ");
		out.println("17 - Pesquisar");
		out.println("Opção: ");
		return inputData.nextInt(); // Retorna o input do teclado
	}
	
	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			//Linked List: Medir tempo de inserção e remoção dados na pilha
			if(opcao==1){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiências?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
		        //WarmUp
		        for (int numberOfItem : FileSize){
		        	LinkedStackFile.LinkedStackTest(numberOfItem);
		    	}
		        
		        // Ciclo que analisa cada posicao do array ou seja cada Item do
				// FileSize, dentro de cada tipo de ficheiro
		        for (int numberOfItem : FileSize) {
		        	
		        	// Cria novo ficheiro exel na directoria pretendida (data/)
		        	PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + numberOfItem + ".csv");
		        	PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + numberOfItem + ".csv");	
		        	
		        	// variavel com o nº de repetições
		        	Double[] timeTotalPush = new Double[repeticions];
		    		Double[] timeTotalPop = new Double[repeticions];
		    		
		    		long estimatedTimePush = 0;
		    		long estimatedTimePop = 0;
		    			
		    		out.println("-----------------------------------");
		    		out.println("Numero de Itens " + numberOfItem);
		    		out.println("-----------------------------------");
		    			
		    		for (int a = 0; a != repeticions; a++) {
		    			estimatedTimePush = LinkedStackFile.runPushLinked(numberOfItem);
	        			timeTotalPush[a] = (double) (estimatedTimePush);
	        				
	        			estimatedTimePop = LinkedStackFile.runPopLinked(numberOfItem);
	        			timeTotalPop[a] = (double) (estimatedTimePop);
		    		}
				
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_push=MedMinMax.maximeTimes(timeTotalPush);
		    		out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
		    		file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_push=MedMinMax.minimeTimes(timeTotalPush);
		    		out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
		    		file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		media_push=MedMinMax.meanTimes(timeTotalPush);
		    		out.println("Tempo medio de inserção: " + media_push + " ns");//imprime na consola
		    		file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		mediana_push=MedMinMax.medianTimes(timeTotalPush);
		    		out.println("Mediana de inserção: " + mediana_push + " ns");//imprime na consola
		    		file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_push=MedMinMax.standardDeviation(timeTotalPush);
		    		out.println("Desvio padrão: " + desvio_push + " ns");//imprime na consola
		    		file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
		    			
		    		file.close();
		    			
		    		//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
		    		out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
		    		file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
		    		out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
		    		file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
		    		media_pop=MedMinMax.meanTimes(timeTotalPop);
		    		out.println("Tempo medio de remoção: " + media_pop + " ns");//imprime na consola
		    		file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
		    		mediana_pop=MedMinMax.medianTimes(timeTotalPop);
		    		out.println("Mediana de remoção: " + mediana_pop + " ns");//imprime na consola
		    		file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
		    			
		    		//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
		    		desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
		    		out.println("Desvio padrão: " + desvio_pop + " ns");//imprime na consola
		    		file1.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
		    				
		    		file1.close();
		        }
				   
			}
			//Resizing Array: Medir tempo de inserção e remoção dados na pilha
			else if(opcao==2){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiências?(numero inteiro): ");
		        int repeticions = input.nextInt(); 	
		        
		        //warmup
		        for (int numberOfItem : FileSize){
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
		    			//ver tempo de cada inserção
	        			//out.println("Tempo de inserção (experiência: " + (a + 1) + "): " + estimatedTimePush + " ns");// Mostra o tempo
	        			//out.println("-----------------------------------");
	        			timeTotalPush[a] = (double) (estimatedTimePush);
	        				
	        			estimatedTimePop = ResizingArrayFile.runPopStack(numberOfItem);
	        			//ver tempo de cada remoção
	        			//out.println("Tempo de remoção (experiência: " + (a + 1) + "): " + estimatedTimePop + " ns");// Mostra o tempo
	        			//out.println("-----------------------------------");
	        			timeTotalPop[a] = (double) (estimatedTimePop);
		    		}
				
		    		maximo_push=MedMinMax.maximeTimes(timeTotalPush);
		    		out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
		    		file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
		    			
		    		minimo_push=MedMinMax.minimeTimes(timeTotalPush);
		    		out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
		    		file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
		    			
		    		media_push=MedMinMax.meanTimes(timeTotalPush);
		    		out.println("Tempo medio de inserção: " + media_push + " ns");//imprime na consola
		    		file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
		    			
		    		mediana_push=MedMinMax.medianTimes(timeTotalPush);
		    		out.println("Mediana de inserção: " + mediana_push + " ns");//imprime na consola
		    		file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
		    			
		    		desvio_push=MedMinMax.standardDeviation(timeTotalPush);
		    		out.println("Desvio padrão: " + desvio_push + " ns");//imprime na consola
		    		file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
		    			
		    		file.close();
		    			
		    		maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
		    		out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
		    		file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
		    			
		    		minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
		    		out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
		    		file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
		    			
		    		media_pop=MedMinMax.meanTimes(timeTotalPop);
		    		out.println("Tempo medio de remoção: " + media_pop + " ns");//imprime na consola
		    		file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
		    			
		    		mediana_pop=MedMinMax.medianTimes(timeTotalPop);
		    		out.println("Mediana de remoção: " + mediana_pop + " ns");//imprime na consola
		    		file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
		    			
		    		desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
		    		out.println("Desvio padrão: " + desvio_pop + " ns");//imprime na consola
		    		file1.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
		    				
		    		file1.close();
		        }
		    }
			//ResizingArray verificar acrescimos e decrementos na pilha com potencias de 2
			else if(opcao==3){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt(); 
		        
				out.println("--------------------------------------");
				out.println("Resizing Array");
				out.println("--------------------------------------");
			
				//warmup
		        for (int numberOfItem : FileSize){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
				for (int numberOfItem : FileSize) {
					for (int a = 0; a != repeticions; a++) {
					//out.println("Experiência: " + (a + 1));
					ResizingArrayFile.Resizing(numberOfItem);
					}
				}	
			}
			//Medir tempo de inserção e remoção dados na pilha com potencias de 2+1
			else if(opcao==4){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiências?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias		
		        
		        //warmup
		        for (int numberOfItem : FileSizeImpar){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
		        for (int numberOfItem : FileSizeImpar) {
		        	
		        	PrintWriter file = new PrintWriter("data/" + "ResizingArray2+1Insert" + "_" + numberOfItem + ".csv");
		        	PrintWriter file1 = new PrintWriter("data/" + "ResizingArray2+1Delete" + "_" + numberOfItem + ".csv");	
		        		
		        	Double[] timeTotalPush = new Double[repeticions];
		    		Double[] timeTotalPop = new Double[repeticions];
		    		
		    		long estimatedTimePush = 0;
		    		long estimatedTimePop = 0;
		    			
		    		out.println("-----------------------------------");
		    		out.println("Numero de Itens " + numberOfItem);
		    		out.println("-----------------------------------");
		    		
		    		//Ciclo for vai realizar o nº de repetições que queremos
		    		for (int a = 0; a != repeticions; a++) {
		    			estimatedTimePush = ResizingArrayFile.runPushStack(numberOfItem);
		    			//Ver tempo de cada inserção
	        			//out.println("Tempo de inserção (experiência: " + (a + 1) + "): " + estimatedTimePush + " ns");// Mostra o tempo
	        			//out.println("-----------------------------------");
	        			timeTotalPush[a] = (double) (estimatedTimePush);
	        				
	        			estimatedTimePop = ResizingArrayFile.runPopStack(numberOfItem);
	        			//Ver tempo de cada remoção
	        			//out.println("Tempo de remoção (experiência: " + (a + 1) + "): " + estimatedTimePop + " ns");// Mostra o tempo
	        			//out.println("-----------------------------------");
	        			timeTotalPop[a] = (double) (estimatedTimePop);
		    		}
				
		    		maximo_push=MedMinMax.maximeTimes(timeTotalPush);
		    		out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
		    		file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
		    			
		    		minimo_push=MedMinMax.minimeTimes(timeTotalPush);
		    		out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
		    		file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
		    			
		    		media_push=MedMinMax.meanTimes(timeTotalPush);
		    		out.println("Tempo medio de inserção: " + media_push + " ns");//imprime na consola
		    		file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
		    			
		    		mediana_push=MedMinMax.medianTimes(timeTotalPush);
		    		out.println("Mediana de inserção: " + mediana_push + " ns");//imprime na consola
		    		file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
		    			
		    		desvio_push=MedMinMax.standardDeviation(timeTotalPush);
		    		out.println("Desvio padrão: " + desvio_push + " ns");//imprime na consola
		    		file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
		    			
		    		file.close();
		    			
		    		maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
		    		out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
		    		file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
		    			
		    		minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
		    		out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
		    		file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
		    			
		    		media_pop=MedMinMax.meanTimes(timeTotalPop);
		    		out.println("Tempo medio de remoção: " + media_pop + " ns");//imprime na consola
		    		file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
		    			
		    		mediana_pop=MedMinMax.medianTimes(timeTotalPop);
		    		out.println("Mediana de remoção: " + mediana_pop + " ns");//imprime na consola
		    		file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
		    			
		    		desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
		    		out.println("Desvio padrão: " + desvio_pop + " ns");//imprime na consola
		    		file1.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
		    				
		    		file1.close();
		        }
			
			}
			//ResizingArray Verificar acrescimos e decrementos na pilha com potencias de 2+1
			else if(opcao==5){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt(); 
		        
				out.println("--------------------------------------");
				out.println("Resizing Array Potencia 2+1");
				out.println("--------------------------------------");
			
				//WarmUp
		        for (int numberOfItem : FileSizeImpar){
		        	ResizingArrayFile.ResizingTest(numberOfItem);
		    	}
		        
		        for (int numberOfItem : FileSizeImpar) {
					for (int a = 0; a != repeticions; a++) {
					ResizingArrayFile.Resizing(numberOfItem);
					}
				}		
			}
			// Merge Sort Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled
			else if(opcao==6){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
				
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias
		       
		        //WarmUp
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		MergeSortFile.runAlgorithmTest(orderType, numberOfItem);
		          	}
		        }
		        
		        //Ciclo para percorrer cada tipo de ficheiro
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		
		        		// Cria novo ficheiro exel
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
					
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime no exel
	    			
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime no exel
	    			
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordenação: " + media + " ns");//imprime no exel
	    			
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordenação: " + mediana + " ns");//imprime no exel
	    			
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio médio de ordenação: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio médio de ordenação: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
			}
			//Merge Sort Verificar comparações e acessos ao array
			else if(opcao==7){
				
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
		        			InstrumentedMergeFile.runCountData(orderType, numberOfItem);
		        	}
				}
				
			}
			//BottomUpMerge
			else if(opcao==8){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
				
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt(); 
		        
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
					
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime no exel
	    			
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime no exel
	    			
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordenação: " + media + " ns");//imprime no exel
	    			
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordenação: " + mediana + " ns");//imprime no exel
	    			
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio médio de ordenação: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio médio de ordenação: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
			}
			//Insertion Sort Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled
			else if(opcao==9){
				
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt();
		        
		        //warmup
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		InsertionSortFile.runAlgorithmTest(orderType, numberOfItem);
		          	}
		        }
		        
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		PrintWriter file = new PrintWriter("data/" + "InsertionSort" + "_" + orderType + "_" + numberOfItem + ".csv");
		        		Double[] timeTotal = new Double[repeticions];
		        		long estimatedTime = 0;
		        		
		        		// Apenas não é feito a operação de ordenação quando o orderType é shuffled e numberOfItem < 65536
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
		        				//out.println("Tempo de ordenação (experiência: " + (a + 1) + "): " + estimatedTime + " ns");// Mostra o tempo
		        				//out.println("-----------------------------------");
		        				timeTotal[a] = (double) (estimatedTime);
		        			}
					
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime no exel
	    			
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime no exel
	    			
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordenação: " + media + " ns");//imprime no exel
	    			
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordenação: " + mediana + " ns");//imprime no exel
	    			
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio médio de ordenação: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio médio de ordenação: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
		     	}             
			}
			//Insertion Sort: verificar comparações e acessos ao array
			else if(opcao==10){
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
						data = InsertionSortFile.runCountData(orderType, numberOfItem); //o array data vai guardar o numero de comparações, acessos e trocas 
						out.println("Número de comparações: "+data[0]);
						out.println("Número de Acessos a array: "+data[1]);
						out.println("Número de Trocas: "+data[2]);
					}
				}
			}
			//Quick Sort: Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled
			else if(opcao==11){
				@SuppressWarnings("resource")
				final Scanner input = new Scanner(in);
				
		        out.print("Quantas experiênçias?(numero inteiro): ");
		        int repeticions = input.nextInt(); //guarda input do numero de experiencias
		       
		    
		        //Ciclo para percorrer cada tipo de ficheiro
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSize) {
		        		
		        		// Cria novo ficheiro exel
		        		PrintWriter file = new PrintWriter("data/" + "QuickSort" + "_" + orderType + "_" + numberOfItem + ".csv");
		    
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
		        				estimatedTime = QuickSortFile.runAlgorithm(orderType, numberOfItem);
		        				timeTotal[a] = (double) (estimatedTime);
		        			}
					
		        			maximo = MedMinMax.maximeTimes(timeTotal);
		        			out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola
		        			file.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime no exel
	    			
		        			minimo = MedMinMax.minimeTimes(timeTotal);
		        			out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola
		        			file.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime no exel
	    			
		        			media = MedMinMax.meanTimes(timeTotal);
		        			out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola
		        			file.println("Tempo medio de ordenação: " + media + " ns");//imprime no exel
	    			
		        			mediana = MedMinMax.medianTimes(timeTotal);
		        			out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola
		        			file.println("Mediana de ordenação: " + mediana + " ns");//imprime no exel
	    			
		        			desvio = MedMinMax.standardDeviation(timeTotal);
		        			out.println("Desvio médio de ordenação: " + desvio + " ns");//imprime na consola
		        			file.println("Desvio médio de ordenação: " + desvio + " ns");//imprime no exel
	    			
		        			file.close();
		        		}
		    		}
			}
			else if(opcao==13){
				
		        
		        
			}
		}while (opcao != 17);{System.exit(0);}
	}
}


/*
 * Este projeto foi concebido utilizando as seguintes fontes como recurso para
 * implementação do código:
 * 
 * Tipos abstratos de dados: 
 * - http://www.e-reading.club/bookreader.php/138175/Abstract_Data_Types_in_Java.pdf
 *
 * Código utilizado e adaptado de: 
 * - Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 *   Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * - Código disponibilizado pelo professor Manuel Menezes Sequeira
 * - http://www.java2s.com/
 */