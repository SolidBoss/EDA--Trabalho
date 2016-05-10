package LinkedList;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import Main.MedMinMax; // para utilizar os metodos desta classe
import edu.princeton.cs.introcs.In;

public class LinkedStackFile {
	// Contém o número dos ficheiros que vão ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };

	// Verificar o tempo
	static long starTime;
	static long estimatedTime = 0;

	// Variavel para os ciclos
	static int count = 0;

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
	
	// criação de uma nova cadeia ligada, numbers
	static LinkedStack<String> numbers = new LinkedStack<String>();

	public static void main(String[] args) throws IOException {
		
		//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize
		for (int Item : FileSize) {

			// variavel que diz localizacao dos ficheiros txt, o Item é referente a cada nº do FileSize
			String FilePath = "data/sorted_" + Item + ".txt";
			boolean FileExists = new File(FilePath).isFile();

			// Caso o ficheiro exista são feitas as operações de inserção e remoção da pilha
			if (FileExists == true) {

				// Cria novo ficheiro exel com o nome LinkedList e o nº do item, na directoria pretendida
				PrintWriter file = new PrintWriter("data/" + "LinkList" + "_" + Item + ".csv");

				// vai ler todo o  conteúdo dos ficheiro
				@SuppressWarnings("deprecation")
				String[] textFiles = In.readStrings(FilePath);

				// variavel com o nº de repetições, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
				int repetir = 10;
				
				Double[] tempo = new Double[repetir];
				
				out.println("-----------------------------------");
				out.println("Numero de Itens " + Item);
				out.println("-----------------------------------");

				// Ciclo for vai realizar o nº de repetições que queremos
				for (int i = 0; i != repetir; i++) {
					// Ciclo para Inserção(push) na pilha
					 
					starTime = System.nanoTime();// Iniciar a medição em nanosegundos
					for (int count = 0; count != textFiles.length; count++) {
						numbers.push(textFiles[count]);// inserir na pilha pelo topo
					}
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
					//out.println("Demora a inserir: " + estimatedTime + " ns");

					//guarda o tempo de cada execução, para cada repetição
					tempo[i] = (double) (estimatedTime);
				}

				//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
				maximo_push=MedMinMax.maximeTimes(tempo);
				out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
				file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
				
				//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
				minimo_push=MedMinMax.minimeTimes(tempo);
				out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
				file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
				
				//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
				media_push=MedMinMax.meanTimes(tempo);
				out.println("Tempo medio de inserção: " + media_push + " ns");
				file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
				
				//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
				mediana_push=MedMinMax.medianTimes(tempo);
				out.println("Mediana de inserção: " + mediana_push + " ns");
				file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
				
				//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
				desvio_push=MedMinMax.standardDeviation(tempo);
				out.println("Desvio padrão: " + desvio_push + " ns");
				file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
				
				// Ciclo for vai realizar o nº de repetições que queremos
				for (int j = 0; j != repetir; j++) {
					// Ciclo para Remoção(push) da pilha
					starTime = System.nanoTime();
					for (int count = 0; count != textFiles.length; count++) {
						numbers.pop().equals(textFiles[count]);// vai
																		// apagar
																		// todos
																		// os
																		// elemntos
																		// da
																		// pilha
					}
					estimatedTime = (System.nanoTime() - starTime);
					//out.println("Demora a apagar: " + estimatedTime + " ns");
					
					//guarda o tempo de cada execução, para cada repetição
					tempo[j] = (double) (estimatedTime);
					
				}
				
				//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
				maximo_pop=MedMinMax.maximeTimes(tempo);
				out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
				file.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
				
				//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
				minimo_pop=MedMinMax.minimeTimes(tempo);
				out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
				file.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
				
				//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
				media_pop=MedMinMax.meanTimes(tempo);
				out.println("Tempo medio de remoção: " + media_pop + " ns");
				file.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
				
				//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
				mediana_pop=MedMinMax.medianTimes(tempo);
				out.println("Mediana de remoção: " + mediana_pop + " ns");
				file.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
				
				//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
				desvio_pop=MedMinMax.standardDeviation(tempo);
				out.println("Desvio padrão: " + desvio_pop + " ns\n");
				file.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
				
				file.close();
			}
		}

	}

	// Metodo para apagar o numero da Linked List
	public static void apaga() {
		LinkedStack<String> numbers = new LinkedStack<String>();
		
		int repetir = 5;
		Double[] tempo = new Double[repetir];
		
		// Def o caminho do ficheiro e a palavra que queremos apagar
		String singlefile = "data/sorted_512.txt";
		String searchingText = "0.2073884211515022"; //Meio

		@SuppressWarnings("deprecation")
		String[] textFile = In.readStrings(singlefile);// vai analisar o
														// ficheir
		// inserir na pilha para depois apagar, caso contrario da stack underflow
			for (int i = 0; i != textFile.length; i++) {
				numbers.push(textFile[i]);
		}

		out.println("Item que vai ser apagado: " + searchingText);
		// Ciclo for vai realizar o nº de repetições que queremos
		for (int y = 0; y != repetir; y++) {
			long startTime = System.nanoTime();// Iniciar a medição em
												// nanosegundos
			// inserir na pilha para depois apagar, caso contrario da static
			// underflowfor
			if (numbers.pop().equals(searchingText)) // ao percorrer os
															// ficheiro,
															// verifica se
															// existe o nº que
															// procuramos
				out.println("Está na " + count + " posição");

			estimatedTime = System.nanoTime() - startTime;// Tempo Final
															// guardado na
															// variavel
			out.println("Operação demorou " + estimatedTime + " ns");
			
			//guarda o tempo de cada execução, para cada repetição
			tempo[y] = (double) (estimatedTime);
			
		}
		media_pop=MedMinMax.meanTimes(tempo);
		out.println("Tempo medio de remoção: " + media_pop + " ns");
		
		maximo_pop=MedMinMax.maximeTimes(tempo);
		out.println("Tempo maximo de remoção: " + maximo_pop + " ns");
		
		minimo_pop=MedMinMax.minimeTimes(tempo);
		out.println("Tempo minimo de remoção: " + minimo_pop + " ns");
		
		mediana_pop=MedMinMax.medianTimes(tempo);
		out.println("Mediana: " + mediana_pop + " ns");
		
		desvio_pop=MedMinMax.standardDeviation(tempo);
		out.println("Desvio padrão: " + desvio_pop + " ns\n");
	}
}