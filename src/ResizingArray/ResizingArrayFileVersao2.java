package ResizingArray;

import static java.lang.System.out;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Main.MedMinMax; // para utilizar os metodos desta classe

public class ResizingArrayFileVersao2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Cont�m o n�mero dos ficheiros que v�o ser analizados
		final int[] FileSize = { 2, 4 };
		
		// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao
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
			
			// Cria novo ficheiro exel com o nome ResizingArrayInsert e o n� do item, na directoria pretendida
			PrintWriter file = new PrintWriter("data/" + "ResizingArrayInsert" + "_" + Item + ".csv");
			
			// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
			int repetitions = 2;
			Double[] timeTotalPush = new Double[repetitions];
			Double[] timeTotalPop = new Double[repetitions];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
			
			// Ciclo for vai realizar o n� de repeti��es que queremos
			// Ciclo para Inser��o(push) na pilha
			for (int a = 0; a != repetitions; a++) {
				// cria��o de uma nova pilha, numbers
				ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
				long starTimePush = System.nanoTime();// Iniciar a medi��o em nanosegundos
				for (int i = 0; i != Item; i++) {
					numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
				}
					
			long estimatedTimePush = System.nanoTime() - starTimePush;// Tempo final de cada repeti��o guardado em variavel	
			timeTotalPush[a] = (double) (estimatedTimePush);//guarda o tempo de cada repeti��o numa posi��o do array	
			
			long startTimePop = System.nanoTime(); // Iniciar a medi��o em nanosegundos
			while (!numbers.isEmpty())
				numbers.pop();

			long estimatedTimePop = System.nanoTime() - startTimePop;// Medimos  o tempo
			timeTotalPop[a] = (double) (estimatedTimePop);
			}
			
			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
			maximo_push=MedMinMax.maximeTimes(timeTotalPush);
			out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");//imprime na consola
			file.println("Tempo maximo de inser��o: " + maximo_push + "ns");//imprime no exel
			
			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
			minimo_push=MedMinMax.minimeTimes(timeTotalPush);
			out.println("Tempo minimo de inser��o: " + minimo_push + " ns");//imprime na consola
			file.println("Tempo minimo de inser��o: " + minimo_push + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
			media_push=MedMinMax.meanTimes(timeTotalPush);
			out.println("Tempo medio de inser��o: " + media_push + " ns");//imprime na consola
			file.println("Tempo medio de inser��o: " + media_push + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			mediana_push=MedMinMax.medianTimes(timeTotalPush);
			out.println("Mediana de inser��o: " + mediana_push + " ns");//imprime na consola
			file.println("Mediana de inser��o: " + mediana_push + "ns");//imprime no exel
			
			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo 
			desvio_push=MedMinMax.standardDeviation(timeTotalPush);
			out.println("Desvio padr�o: " + desvio_push + " ns\n");//imprime na consola
			file.println("Desvio padr�o: " + desvio_push + "ns");//imprime no exel
			
			file.close();
			
			// Cria novo ficheiro exel com o nome ResizingArrayDelete e o n� do item que vamos apagar, na directoria pretendida
			PrintWriter file1 = new PrintWriter("data/" + "ResizingArrayDelete" + "_" + Item + ".csv");	
			
			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
			maximo_pop=MedMinMax.maximeTimes(timeTotalPop);
			out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");//imprime na consola
			file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
			minimo_pop=MedMinMax.minimeTimes(timeTotalPop);
			out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");//imprime na consola
			file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			media_pop=MedMinMax.meanTimes(timeTotalPop);
			out.println("Tempo medio de remo��o: " + media_pop + " ns");//imprime na consola
			file1.println("Tempo medio de remo��o: " + media_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
			mediana_pop=MedMinMax.medianTimes(timeTotalPop);
			out.println("Mediana de remo��o: " + mediana_pop + " ns");//imprime na consola
			file1.println("Mediana de remo��o: " + mediana_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
			desvio_pop=MedMinMax.standardDeviation(timeTotalPop);
			out.println("Desvio padr�o: " + desvio_pop + " ns");//imprime na consola
			file1.println("Desvio padr�o: " + desvio_pop + "ns");//imprime no exel
				
			file1.close();
			
		}

	}
	
	public static void Resizing(){
		
		out.println("--------------------------------------");
		out.println("Resizing Array");
		out.println("--------------------------------------");
	
		//Variavel com o n�mero de vezes que o ciclo vai repetir
		final int[] ResizingNumbers = { 2, 4, 8, 16, 32 };
	
		for (int counter = 0; counter != ResizingNumbers.length; counter++) {
		
			//Cria��o de uma nova pilha para o redimensionamento do array
			ResizingArrayStack<String> number = new ResizingArrayStack<String>();
			
			int repeticions = 2;
		
			@SuppressWarnings("unused")
			long startTimePush = System.nanoTime(); // Iniciar a medi��o em nanosegundos
			
			for (int a = 0; a != repeticions; a++) {
				
				out.println("Experiencia: " + (a+1));
				
				for (int i = 0; i != ResizingNumbers[counter]; i++){
					number.push("0.2073884211515022"); //valor que vai ser inserido na pilha, i vezes dependendo do ciclo
					}
				long estimatedTime = System.nanoTime() - startTimePush;// Medimos o tempo
				out.println("Tempo total inserindo " + ResizingNumbers[counter] + " ficheiro(s) na pilha: " + estimatedTime);
				
				long startTimePop = System.nanoTime(); // Iniciar a medi��o em nanosegundos
				
				while (!number.isEmpty())
					number.pop();
				
				long estimatedTimePop = System.nanoTime() - startTimePop;// Medimos  o tempo
				out.println("Tempo total retirando " + ResizingNumbers[counter] + " ficheiro(s) da pilha: " + estimatedTimePop);
			}
			
			out.println("Items:  " + ResizingNumbers[counter]);

			//retorna n� de aumentos que foram feitos na pilha number
			out.println("N�mero de aumentos do array: " + number.getNumberOfIncreases()); 
		
			//retorna o n� de numero de diminui��es feitas
			out.println("N�mero de decrementos do array: " + number.getNumberOfDecreases()); 
		
			out.println("--------------------------------------");	
		}
	}
}