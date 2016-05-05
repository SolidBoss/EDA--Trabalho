package MergeSort;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class BottomUpMergeFile {
	
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
		
		@SuppressWarnings("resource")
		final Scanner input = new Scanner(in);

        out.print("Quantas experiênçias?(numero inteiro): ");
        
        int repetir = input.nextInt(); //guarda input do numero de experiencias
        Double[] tempo = new Double[repetir]; //cria array tempo com o numero de posições indicadas no input
        
		
	//Ciclo para percorrer cada tipo de ficheiro
			for (String Type : FileType) {

				//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
				for (int Item : FileSize) {

					// variavel que diz localizacao dos ficheiros txt, o Item é referente a cada nº do FileSize
					String FilePath = "data/" + Type + "_" + Item + ".txt";
					boolean FileExists = new File(FilePath).isFile();

					// Caso o ficheiro exista são feitas as operações de ordenação
					if (FileExists == true) {
						long estimatedTime = 0;

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

						// Ciclo for vai realizar o nº de repetições que queremos
						for (int i = 0; i != repetir; i++) {
							long starTime = System.nanoTime(); // Variavel que vai iniciar a medição em nanosegundos
							BottomUpMerge.sort(textFiles); //Através da classe BottomUpMerge, vai ordenar todos os items de cada ficheiro
							estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
							//guarda o tempo de cada execução, para cada repetição
							tempo[i] = (double) (estimatedTime);
						}

						//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
						maximo = MedMinMax.maximeTimes(tempo);
						out.println("Tempo maximo de ordenação: " + maximo + " ns");//imprime na consola

						//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
						minimo = MedMinMax.minimeTimes(tempo);
						out.println("Tempo minimo de ordenação: " + minimo + " ns");//imprime na consola

						//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
						media = MedMinMax.meanTimes(tempo);
						out.println("Tempo medio de ordenação: " + media + " ns");//imprime na consola

						//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
						mediana = MedMinMax.medianTimes(tempo);
						out.println("Mediana de ordenação: " + mediana + " ns");//imprime na consola

						//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
						desvio = MedMinMax.standardDeviation(tempo);
						out.println("Desvio padrão: " + desvio + " ns");//imprime na consola

					}
				}
			}
	}
}
