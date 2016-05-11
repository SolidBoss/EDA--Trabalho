package ResizingArray;

import static java.lang.System.out;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Main.MedMinMax; // para utilizar os metodos desta classe

public class ResizingArrayFileVersao2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int[] FileSize = { 2, 4};
		double media_push;
		double media_pop;
		double maximo_push;
		double maximo_pop;
		double minimo_push;
		double minimo_pop;
		double mediana_push;
		double mediana_pop;
		double desvio_pop;
		double desvio_push;
		
		for (int Item : FileSize) {
			PrintWriter file = new PrintWriter("data/" + "ResizingArrayInsert" + "_" + Item + ".csv");
			ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();

			int repetir = 10;
			Double[] tempo = new Double[repetir];
			long starTime = 0, estimatedTime = 0;
			long end = (long) (starTime + (0.2*60*1000000000));
			starTime = System.nanoTime();
				
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
			
			do
			{
				for (int a = 0; a != repetir; a++) {
					for (int i = 0; i != Item; i++) {
						numbers.push("ResizingArray");
						estimatedTime = System.nanoTime() - starTime; // ?
						//out.println("Demora a inserir: " + estimatedTime + " ns" + " --- experiencia " + a);
					}
				tempo[a] = (double) (estimatedTime);						
				}	
			}while (System.nanoTime() < end);
			
			
			maximo_push=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
			file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
				
			minimo_push=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
			file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
				
			media_push=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de inserção: " + media_push + " ns");//imprime na consola
			file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
				
			mediana_push=MedMinMax.medianTimes(tempo);
			out.println("Mediana de inserção: " + mediana_push + " ns");//imprime na consola
			file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
				
			desvio_push=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_push + " ns\n");//imprime na consola
			file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
			
			file.close();
			
			PrintWriter file1 = new PrintWriter("data/" + "ResizingArrayDelete" + "_" + Item + ".csv");
												
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();	// ?
				for (int i = 0; i != Item; i++) {
					numbers.pop();
					estimatedTime = System.nanoTime() - starTime; // ?
				}
			tempo[a] = (double) (estimatedTime);						
			}				
				
			maximo_pop=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
			file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
				
			minimo_pop=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
			file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
				
			media_pop=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de remoção: " + media_pop + " ns");//imprime na consola
			file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
				
			mediana_pop=MedMinMax.medianTimes(tempo);
			out.println("Mediana de remoção: " + mediana_pop + " ns");//imprime na consola
			file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
				
			desvio_pop=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_pop + " ns");//imprime na consola
			file1.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
				
			file1.close();
			
		}

	}
	
	public static void Resizing(){
		
		out.println("--------------------------------------");
		out.println("Resizing Array");
		out.println("--------------------------------------");
	
		//Variavel com o número de vezes que o ciclo vai repetir
		final int[] ResizingNumbers = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072,
			262144, 524288, 1048576 };
	
		for (int counter = 0; counter != ResizingNumbers.length; counter++) {
		
			//Criação de uma nova pilha para o redimensionamento do array
			ResizingArrayStack<String> number = new ResizingArrayStack<String>();
		
			@SuppressWarnings("unused")
			long starTime, estimatedTime = 0;

			for (int i = 0; i != ResizingNumbers[counter]; i++){
			number.push("0.2073884211515022"); //valor que vai ser inserido na pilha, i vezes dependendo do ciclo
			}
		
			starTime = System.nanoTime(); 
			while (!number.isEmpty())
				number.pop();
			estimatedTime = System.nanoTime() - starTime;
		
			out.println("Items:  " + ResizingNumbers[counter]);

			//retorna nº de aumentos que foram feitos na pilha number
			out.println("Número de aumentos: " + number.getNumberOfIncreases()); 
		
			//retorna o nº de numero de diminuições feitas
			out.println("Número de decrementos: " + number.getNumberOfDecreases()); 
		
			out.println("--------------------------------------");	
		}
	}
}