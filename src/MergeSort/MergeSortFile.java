package MergeSort;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class MergeSortFile {

	// Variaveis para a média, mediana, maximo, minimo e desvio padrao
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;

	// Contém o número dos ficheiros que vão ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };
	
	// Contém o tipo de ficheiros que vão ser analizados
	static String[] FileType = { "sorted", "partially_sorted", "shuffled" };

	public static void main(String[] args) throws IOException {

		//Ciclo para percorrer cada tipo de ficheiro
		for (String Type : FileType) {

			//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
			for (int Item : FileSize) {

				// Cria novo ficheiro exel com o nome LinkedList e o nº do item, na directoria pretendida
				PrintWriter file = new PrintWriter("data/" + "MergeSort" + "_" + FileType + "_" + Item + ".csv");

				// variavel que diz localizacao dos ficheiros txt, o Item é referente a cada nº do FileSize
				String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();

				// Caso o ficheiro exista são feitas as operações de ordenação
				if (FileExists == true) {
					
					// vai ler todo o conteúdo dos ficheiros
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

					// variavel com o nº de repetições, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
					int repetir = 10;
					Double[] tempo = new Double[repetir];
					
					// Variavel para medir o tempo
					long estimatedTime = 0;
					
					// Ciclo for vai realizar o nº de repetições 
					for (int i = 0; i != repetir; i++) {
						long starTime = System.nanoTime();// Variavel que vai iniciar a medição em nanosegundos
						Merge.sort(textFiles);//Através da classe merge, vai ordenar todos os items de cada ficheiro
						estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
						//guarda o tempo de cada execução, para cada repetição
						//out.println("Tempo de ordenação da " + (i+1) + "º experiênçia: " + estimatedTime + " ns");
						
						tempo[i] = (double) (estimatedTime);
					}

					//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
					maximo = MedMinMax.maximeTimes(tempo);
					out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola
					file.println("Tempo maximo de ordenação: " + maximo + "ns");//imprime no exel

					//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
					minimo = MedMinMax.minimeTimes(tempo);
					out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola
					file.println("Tempo minimo de ordenação: " + minimo + "ns");//imprime no exel

					//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
					media = MedMinMax.meanTimes(tempo);
					out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola
					file.println("Tempo medio de ordenação: " + media + "ns");//imprime no exel

					//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
					mediana = MedMinMax.medianTimes(tempo);
					out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola
					file.println("Mediana de ordenação: " + mediana + "ns");//imprime no exel

					//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
					desvio = MedMinMax.standardDeviation(tempo);
					out.println("Desvio padrão: " + desvio + " ns");//imprime na consola
					file.println("Desvio padrão: " + desvio + "ns");//imprime no exel

					file.close();
					
				}
			}
		}
	}
}
