package Main;


import static java.lang.System.out;
import MergeSort.BottomUpMergeFile;
import InsertionSort.InsertionSortFile;
import InsertionSort.InstrumentedInsertion;
import LinkedList.LinkedStack;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
	// le input do teclado
	static Scanner inputData; 
	
	public static final double timeBudgetPerExperiment = 2.0;
	
	public static final double minimumTimePerContiguousRepetitions = 2e-5;
	
	//Guarda o resultado das somas para que o compilador do java n�o tenha que optimizar nenhuma das chamadas do sumFrom1To()
	private static long sum;
	
	// Cont�m o n�mero dos ficheiros que v�o ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
	
	// Cont�m o tipo de ficheiros que v�o ser analizados
	static String[] FileType = { "sorted", "partially_sorted", "shuffled" };
		
	// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao
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
		out.println("3 - Verificar acrescimos e decrementos na pilha");
		out.println("Merge Sort");
		out.println("4 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("5 - Verificar compara��es e acessos ao array");
		out.println("6 - BottomUpMerge");
		out.println("Insertion Sort");
		out.println("7 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("8 - Verificar compara��es e acessos ao array");
		out.println("11 - Sair");
		out.println("Op��o: ");
		return inputData.nextInt(); // Retorna o input do teclado
	}
	
	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			//LinkedList
			//Medir tempo de inser��o e remo��o dados na pilha
			if(opcao==1){
				
				for (int exponent = 0, limit = 1; exponent != 1000000; exponent++, limit *= 2)
		            performExperimentsFor(limit, true);
		        
		        //Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
				for (int Item : FileSize) {
					
					// Cria novo ficheiro exel com o nome LinkedList e o n� do item, na directoria pretendida
					PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + Item + ".csv");
					
					// cria��o de uma nova cadeia ligada, numbers
					LinkedStack<String> numbers = new LinkedStack<String>();

					// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
					int repetir = 10;
					Double[] tempo = new Double[repetir];
					out.println("-----------------------------------");
					out.println("Numero de Itens " + Item);
					out.println("-----------------------------------");
						
					// Verificar o tempo
			        long starTime, estimatedTime = 0;
			        
			        // Ciclo for vai realizar o n� de repeti��es que queremos
					// Ciclo para Inser��o(push) na pilha
					for (int a = 0; a != repetir; a++) {
						starTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
						for (int exponent = 0; exponent != Item; exponent++) {
							numbers.push("LinkStack");//inserir na pilha numbers o valor "LinkStack" as 
							estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
						}
						//guarda o tempo de cada execu��o, para cada repeti��o
						tempo[a] = (double) (estimatedTime);
					}			
					
					//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
					maximo=MedMinMax.maximeTimes(tempo);
					out.println("\nTempo maximo de inser��o: " + maximo + " ns");//imprime na consola
					file.println("Tempo maximo de inser��o: " + maximo + "ns");//imprime no exel
					
					//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo
					minimo=MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de inser��o: " + minimo + " ns");//imprime na consola
					file.println("Tempo minimo de inser��o: " + minimo + "ns");//imprime no exel
					
					//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo
					mediana=MedMinMax.medianTimes(tempo);
					out.println("Mediana da inser��o: " + mediana + " ns");//imprime na consola
					file.println("Mediana da inser��o: " + mediana + "ns");//imprime no exel
					
					//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
					media=MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de inser��o: " + media + " ns");//imprime na consola
					file.println("Tempo medio de inser��o: " + media + "ns");//imprime no exel
					
					//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo
					desvio=MedMinMax.standardDeviation(tempo);
					out.println("Desvio padr�o: " + desvio + " ns");//imprime na consola
					file.println("Desvio padr�o: " + desvio + "ns");//imprime no exel
					
					file.close();
					
					// Cria novo ficheiro exel com o nome LinkedList e o n� do item que vamos apagar, na directoria pretendida
					PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + Item + ".csv");
					
					// Ciclo for vai realizar o n� de repeti��es que queremos
					for (int a = 0; a != repetir; a++) {
						starTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
						for (int exponent = 0; exponent != Item; exponent++) {
							numbers.pop();//Apagar os items que est�o na pilha numbers
							estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
						}
						//guarda o tempo de cada execu��o, para cada repeti��o
						tempo[a] = (double) (estimatedTime);
					}
					
					//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
					maximo=MedMinMax.maximeTimes(tempo);
					out.println("\nTempo maximo de remo��o: " + maximo + " ns");//imprime na consola
					file1.println("Tempo maximo de remo��o: " + maximo + "ns");//imprime no exel
					
					//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
					minimo=MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de remo��o: " + minimo + " ns");//imprime na consola
					file1.println("Tempo minimo de remo��o: " + minimo + "ns");//imprime no exel
					
					//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
					mediana=MedMinMax.medianTimes(tempo);
					out.println("Mediana de remo��o: " + mediana + " ns");
					file1.println("Mediana de remo��o: " + mediana + "ns");//imprime no exel
					
					//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
					media=MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de remo��o: " + media + " ns");
					file1.println("Tempo medio de remo��o: " + media + "ns");//imprime no exel
					
					//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
					desvio=MedMinMax.standardDeviation(tempo);
					out.println("Desvio padr�o: " + desvio + " ns\n");
					file1.println("Desvio padr�o: " + desvio + "ns");//imprime no exel
					
					file1.close();
				}
		        
			}
			else if(opcao==8){
				BottomUpMergeFile.main(args);
			}
			else if(opcao==9){
				InsertionSortFile.main(args);
			}
			else if(opcao==10){
				InstrumentedInsertion.main(args);
			}
			
		}while (opcao != 11);{System.exit(0);}
	}
	
	public static void performExperimentsFor(final int limit,
	            final boolean isWarmup) {
	        final ArrayList<Double> executionTimes = new ArrayList<Double>();
	        long estimatedTime=0;
	        long starTime = System.nanoTime();
	        final int contiguousRepetitions = contiguousRepetitionsFor(limit);
	        long repetitions = 0;
	        do {
	            executionTimes.add(executionTimeFor(limit, contiguousRepetitions));
	            repetitions++;
	            estimatedTime = System.nanoTime() - starTime;
	        } while (estimatedTime < timeBudgetPerExperiment);

	        final double median = medianOf(executionTimes);

	        if (!isWarmup)
	            out.println(
	                    limit + "\t" + median + "\t" + repetitions + "\t" + sum);
	        /*- 
	        out.println("Sum from 1 to " + limit + " = " + sum + " [" + median
	                + "s median time based on " + repetitions
	                + " repetitions of " + contiguousRepetitions
	                + " contiguous repetitions]");
	        */
	    }

	public static int contiguousRepetitionsFor(final int limit) {
        int contiguousRepetitions = 0;
        long estimatedTime=0;
        long starTime = System.nanoTime();
		do {
            sum = sumFrom1To(limit);
            contiguousRepetitions++;
            estimatedTime = System.nanoTime() - starTime;
        } while (estimatedTime < minimumTimePerContiguousRepetitions);

        return contiguousRepetitions;
    }
	
	 public static double executionTimeFor(final int limit,
	            final int contiguousRepetitions) {
	        long estimatedTime=0;
	        long starTime = System.nanoTime();
	        for (int i = 0; i != contiguousRepetitions; i++)
	            sum = sumFrom1To(limit);
	        estimatedTime = System.nanoTime() - starTime;
	        return estimatedTime / contiguousRepetitions;
	    }
	
	public static long sumFrom1To(final int limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++)
            sum += i;
        return sum;
    }
	
	 public static double medianOf(final ArrayList<Double> values) {
	        final int size = values.size();

	        values.sort(null);

	        if (size % 2 == 0)
	            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
	        else
	            return values.get(size / 2);
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