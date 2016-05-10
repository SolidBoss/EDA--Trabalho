package LinkedList;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;

import Main.MedMinMax;

public class LinkedStackVersao2 {

	//Falta apagar
	//Medições por tempo de execução
	
	public static void main(String[] args) throws IOException {

		int[] FileSize = {2, 4};
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
			PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + Item + ".csv");
			LinkedStack<String> numbers = new LinkedStack<String>();

			int repetir = 2;
			Double[] tempo = new Double[repetir];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
			
			long starTime, estimatedTime = 0;
			
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();
				for (int i = 0; i != Item; i++) {
					numbers.push("LinkStack");
					estimatedTime = System.nanoTime() - starTime;
				}
				tempo[a] = (double) (estimatedTime);
			}
			
			maximo_push=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de inserção: " + maximo_push + " ns");//imprime na consola
			file.println("Tempo maximo de inserção: " + maximo_push + "ns");//imprime no exel
			
			minimo_push=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de inserção: " + minimo_push + " ns");//imprime na consola
			file.println("Tempo minimo de inserção: " + minimo_push + "ns");//imprime no exel
			
			media_push=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de inserção: " + media_push + " ns");
			file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
			
			mediana_push=MedMinMax.medianTimes(tempo);
			out.println("Tempo medio de inserção: " + mediana_push + " ns");
			file.println("Tempo medio de inserção: " + mediana_push + "ns");//imprime no exel
			
			desvio_push=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_push + " ns");
			file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
			
			file.close();
			
			
			PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + Item + ".csv");
			
			// Ciclo for vai realizar o nº de repetições que queremos
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();
				for (int i = 0; i != Item; i++) {
					numbers.pop();
					estimatedTime = System.nanoTime() - starTime;
				}
				tempo[a] = (double) (estimatedTime);
			}
			
			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
			maximo_pop=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");//imprime na consola
			file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
			minimo_pop=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de remoção: " + minimo_pop + " ns");//imprime na consola
			file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
			media_pop=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de remoção: " + media_pop + " ns");
			file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			mediana_pop=MedMinMax.medianTimes(tempo);
			out.println("Mediana de remoção: " + mediana_pop + " ns");
			file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
			desvio_pop=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_pop + " ns\n");
			file1.println("Desvio padrão: " + desvio_pop + "ns");//imprime no exel
			
			file1.close();
			
		}
		
	}
}