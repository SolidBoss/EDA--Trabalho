package InsertionSort;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Main.MedMinMax;
import MergeSort.Merge;
import edu.princeton.cs.introcs.In;

public class InsertionSortFile {
	
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;
	
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };
	static int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };
	
	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		final Scanner input = new Scanner(in);

        out.print("Quantas experi�n�ias?(numero inteiro): ");
        
        int repetir = input.nextInt(); //guarda input do numero de experiencias
        Double[] tempo = new Double[repetir]; //cria array tempo com o numero de posi��es indicadas no input
        
      //Antes que a experiencia seja realizada, o WarmUp vai faxer o "aquecimento" do compilador JIT, para que seja evitado os "picos" dos tempo iniciais   
        for (String Type : OrderType) {	
        	for (int Item : FileSizeWarm){
        		String FilePath = "data/" + Type + "_" + Item + ".txt";
				boolean FileExists = new File(FilePath).isFile();
				if (FileExists == true) {
					String[] textFiles = In.readStrings(FilePath);
					Insertion.sort(textFiles);
					}
          	}
        }
        
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
					if (!(orderType == "shuffled") || (numberOfItem <= 65536)){
					
						long estimatedTime = 0;
						
						@SuppressWarnings("deprecation")
						String[] textFiles = In.readStrings(FilePath); //vai ler todo o conte�do dos ficheiro 
						
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
					
						//Ciclo for vai realizar o n� de repeti��es que queremos
						for (int i = 0; i != repetir; i++) {
							long startTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
							Insertion.sort(textFiles);// Iniciar a ordena��o de inser��o com os valores do textFiles
							estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel
							tempo[i] = (double) (estimatedTime);//guarda o tempo de cada execu��o, para cada repeti��o
							//mostra o tempo de cada execu��o, para cada repeti��o
							//out.println("Tempo de ordena��o da " + (i+1) + "� experi�n�ia: " + estimatedTime + " ns");
					
						}	
				
						out.println("-----------------------------------");
						//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
						maximo = MedMinMax.maximeTimes(tempo);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime na consola
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");//imprime no exel
					
						//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
						minimo = MedMinMax.minimeTimes(tempo);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime na consola
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");//imprime no exel
					
						//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
						media = MedMinMax.meanTimes(tempo);
						out.println("Tempo medio de ordena��o: " + media + " ns");//imprime na consola
						file.println("Tempo medio de ordena��o: " + media + " ns");//imprime no exel
					
						//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
						mediana = MedMinMax.medianTimes(tempo);
						out.println("Mediana de ordena��o: " + mediana + " ns");//imprime na consola
						file.println("Mediana de ordena��o: " + mediana + " ns");//imprime no exel
					
						//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
						desvio = MedMinMax.standardDeviation(tempo);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime na consola
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");//imprime no exel
					
						file.close();
					}
				}
			}
		}
	}	
}