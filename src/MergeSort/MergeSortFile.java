package MergeSort;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {

	// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;

	// Cont�m o n�mero dos ficheiros que v�o ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };
	
	// Cont�m o tipo de ficheiros que v�o ser analizados
	static String[] FileType = { "sorted", "partially_sorted", "shuffled" };

	public static void main(String[] args) throws IOException {

		// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
		@SuppressWarnings("resource")
		final Scanner input = new Scanner(in);
		
		//Ciclo para percorrer cada tipo de ficheiro
		for (String Type : FileType) {

			//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
			for (int Item : FileSize) {

				// Cria novo ficheiro exel com o nome LinkedList e o n� do item, na directoria pretendida
				PrintWriter file = new PrintWriter("data/" + "MergeSort" + "_" + FileType + "_" + Item + ".csv");

				// variavel que diz localizacao dos ficheiros txt, o Item � referente a cada n� do FileSize
				String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				// Caso o ficheiro exista s�o feitas as opera��es de ordena��o
				if (FileExists == true) {

					// Variavel para medir o tempo
					long estimatedTime = 0;

					// vai ler todo o conte�do dos ficheiros
					@SuppressWarnings("deprecation")
					String[] textFiles = In.readStrings(FilePath);

					//Ciclo que apenas serve para imprimir na consola o tipo de ficheiro que esta a ser analisado
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

					// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
					int repetir = 10;
					Double[] tempo = new Double[repetir];
					
					// Ciclo for vai realizar o n� de repeti��es que queremos
					for (int i = 0; i != repetir; i++) {
						long starTime = System.nanoTime();// Variavel que vai iniciar a medi��o em nanosegundos
						Merge.sort(textFiles);//Atrav�s da classe merge, vai ordenar todos os items de cada ficheiro
						estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
						//guarda o tempo de cada execu��o, para cada repeti��o
						//out.println("Tempo de ordena��o da " + (i+1) + "� experi�n�ia: " + estimatedTime + " ns");
						
						tempo[i] = (double) (estimatedTime);
					}

					//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
					maximo = MedMinMax.maximeTimes(tempo);
					out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola
					file.println("Tempo maximo de ordena��o: " + maximo + "ns");//imprime no exel

					//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
					minimo = MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola
					file.println("Tempo minimo de ordena��o: " + minimo + "ns");//imprime no exel

					//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
					media = MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola
					file.println("Tempo medio de ordena��o: " + media + "ns");//imprime no exel

					//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
					mediana = MedMinMax.medianTimes(tempo);
					out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola
					file.println("Mediana de ordena��o: " + mediana + "ns");//imprime no exel

					//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
					desvio = MedMinMax.standardDeviation(tempo);
					out.println("Desvio padr�o: " + desvio + " ns");//imprime na consola
					file.println("Desvio padr�o: " + desvio + "ns");//imprime no exel

					file.close();
				}
			}
		}
	}

	//M�todo para ver o n� de compara��es, n� de acessos ao array, n� de leituras e o n� de escritas
	public static void verifica() {

		//Ciclo para percorrer cada tipo de ficheiro
		for (String Type : FileType) {
			
			//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
			for (int Item : FileSize) {

				// variavel que diz localizacao dos ficheiros txt, o Item � referente a cada n� do FileSize
				String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();
				
				// Caso o ficheiro exista s�o feitas as opera��es de ordena��o
				if (FileExists == true) {
					
					//Ciclo que apenas serve para imprimir na consola o tipo de ficheiro que esta a ser analisado
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
					
					// vai ler todo o conte�do dos ficheiros
					@SuppressWarnings("deprecation")
					String[] textFiles = In.readStrings(FilePath);

					//Tivemos que utilizar a classe InstrumentedMerge para ordenar cada ficheiro, porque s� nessa classe � que conseguimos saber as intera��es que est�o acontecer no Array
					InstrumentedMerge.sort(textFiles);

					//vai aceder ao metodo getNumberOfComparisons() da classe InstrumentedMerge e retorna o n� de compara��es feitos no merge, do textFiles em causa  
					out.println("N�mero de compara��es: " + InstrumentedMerge.getNumberOfComparisons());
					//vai aceder ao metodo getNumberOfArrayAccesses() da classe InstrumentedMerge e retorna o n� de acessos feitos no merge, do textFiles em causa  
					out.println("N�mero de acesso ao Array: " + InstrumentedMerge.getNumberOfArrayAccesses());
					//vai aceder ao metodo getNumberOfArrayReads() da classe InstrumentedMerge e retorna o n� de leituras feitas no merge, do textFiles em causa  
					out.println("N�mero de leitura dos Array: " + InstrumentedMerge.getNumberOfArrayReads());
					//vai aceder ao metodo getNumberOfArrayWrites() da classe InstrumentedMerge e retorna o n� de escritas feitas no merge, do textFiles em causa  
					out.println("N�mero de escritas no Array: " + InstrumentedMerge.getNumberOfArrayWrites());
				}
			}
		}
	}

	//
	public static void BottomUpMerge() {
		
		//Ciclo para percorrer cada tipo de ficheiro
		for (String Type : FileType) {

			//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
			for (int Item : FileSize) {

				// variavel que diz localizacao dos ficheiros txt, o Item � referente a cada n� do FileSize
				String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				// Caso o ficheiro exista s�o feitas as opera��es de ordena��o
				if (FileExists == true) {
					long estimatedTime = 0;

					// vai ler todo o conte�do dos ficheiros
					@SuppressWarnings("deprecation")
					String[] textFiles = In.readStrings(FilePath);

					//Ciclo que apenas serve para imprimir na consola o tipo de ficheiro que esta a ser analisado
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

					// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
					int repetir = 10;
					Double[] tempo = new Double[repetir];
					// Ciclo for vai realizar o n� de repeti��es que queremos
					for (int i = 0; i != repetir; i++) {
						long starTime = System.nanoTime(); // Variavel que vai iniciar a medi��o em nanosegundos
						BottomUpMerge.sort(textFiles); //Atrav�s da classe BottomUpMerge, vai ordenar todos os items de cada ficheiro
						estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
						//guarda o tempo de cada execu��o, para cada repeti��o
						tempo[i] = (double) (estimatedTime);
					}

					//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
					maximo = MedMinMax.maximeTimes(tempo);
					out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola

					//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
					minimo = MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola

					//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
					media = MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola

					//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
					mediana = MedMinMax.medianTimes(tempo);
					out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola

					//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
					desvio = MedMinMax.standardDeviation(tempo);
					out.println("Desvio padr�o: " + desvio + " ns");//imprime na consola

				}
			}
		}
	}
}
