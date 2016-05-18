package ResizingArray;

import static java.lang.System.out;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Main.MedMinMax; // para utilizar os metodos desta classe

public class ResizingArrayFileVersao2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Contém o número dos ficheiros que vão ser analizados
		final int[] FileSize = { 2, 4 };
		
		// Variaveis para a média, mediana, maximo, minimo e desvio padrao
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
		
		for(@SuppressWarnings("unused") int Item : FileSize){
			ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
			numbers.push("ResizingArray");
			numbers.pop();
			//Apenas para conferir se a pilha esta vazia
			//out.println(numbers.isEmpty());
    	}
		
		//Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
		for (int Item : FileSize) {
			
			// Cria novo ficheiro exel com o nome ResizingArrayInsert e o nº do item, na directoria pretendida
			PrintWriter file = new PrintWriter("data/" + "ResizingArrayInsert" + "_" + Item + ".csv");
			
			// variavel com o nº de repetições, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
			int repetir = 2;
			Double[] tempo = new Double[repetir];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
			
			// Verificar o tempo
			long starTime, estimatedTime = 0;
			
			// criação de uma nova pilha, numbers
			ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
			// Ciclo for vai realizar o nº de repetições que queremos
			// Ciclo para Inserção(push) na pilha
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medição em nanosegundos
				for (int i = 0; i != Item; i++) {
					numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
				
			//guarda o tempo de cada execução, para cada repetição
			tempo[a] = (double) (estimatedTime);						
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
			out.println("Tempo medio de inserção: " + media_push + " ns");//imprime na consola
			file.println("Tempo medio de inserção: " + media_push + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			mediana_push=MedMinMax.medianTimes(tempo);
			out.println("Mediana de inserção: " + mediana_push + " ns");//imprime na consola
			file.println("Mediana de inserção: " + mediana_push + "ns");//imprime no exel
			
			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
			desvio_push=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_push + " ns\n");//imprime na consola
			file.println("Desvio padrão: " + desvio_push + "ns");//imprime no exel
			
			file.close();
			
			// Cria novo ficheiro exel com o nome ResizingArrayDelete e o nº do item que vamos apagar, na directoria pretendida
			PrintWriter file1 = new PrintWriter("data/" + "ResizingArrayDelete" + "_" + Item + ".csv");
				
			// Ciclo for vai realizar o nº de repetições que queremos
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medição em nanosegundos
				for (int i = 0; i != Item; i++) {
					numbers.pop();//Apagar os items que estão na pilha numbers
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
			//guarda o tempo de cada execução, para cada repetição
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
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			media_pop=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de remoção: " + media_pop + " ns");//imprime na consola
			file1.println("Tempo medio de remoção: " + media_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
			mediana_pop=MedMinMax.medianTimes(tempo);
			out.println("Mediana de remoção: " + mediana_pop + " ns");//imprime na consola
			file1.println("Mediana de remoção: " + mediana_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
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
		final int[] ResizingNumbers = { 2, 4, 8, 16, 32 };
	
		for (int counter = 0; counter != ResizingNumbers.length; counter++) {
		
			//Criação de uma nova pilha para o redimensionamento do array
			ResizingArrayStack<String> number = new ResizingArrayStack<String>();
			
			int repetir = 2;
		
			@SuppressWarnings("unused")
			long startTimePush = System.nanoTime(); // Iniciar a medição em nanosegundos
			
			for (int a = 0; a != repetir; a++) {
				
				out.println("Experiencia: " + (a+1));
				
				for (int i = 0; i != ResizingNumbers[counter]; i++){
					number.push("0.2073884211515022"); //valor que vai ser inserido na pilha, i vezes dependendo do ciclo
					}
				long estimatedTime = System.nanoTime() - startTimePush;// Medimos o tempo
				out.println("Tempo total inserindo " + ResizingNumbers[counter] + " ficheiro(s) na pilha: " + estimatedTime);
				
				long startTimePop = System.nanoTime(); // Iniciar a medição em nanosegundos
				
				while (!number.isEmpty())
					number.pop();
				
				long estimatedTimePop = System.nanoTime() - startTimePop;// Medimos  o tempo
				out.println("Tempo total retirando " + ResizingNumbers[counter] + " ficheiro(s) da pilha: " + estimatedTimePop);
			}
			
			out.println("Items:  " + ResizingNumbers[counter]);

			//retorna nº de aumentos que foram feitos na pilha number
			out.println("Número de aumentos do array: " + number.getNumberOfIncreases()); 
		
			//retorna o nº de numero de diminuições feitas
			out.println("Número de decrementos do array: " + number.getNumberOfDecreases()); 
		
			out.println("--------------------------------------");	
		}
	}
}