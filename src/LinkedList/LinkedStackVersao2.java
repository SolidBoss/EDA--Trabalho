package LinkedList;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import Main.MedMinMax;

public class LinkedStackVersao2 {

	public static void main(String[] args) throws IOException {

		// Contém o número dos ficheiros que vão ser analizados
		final int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536,
				131072, 262144, 524288, 1048576 };

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

		//Antes que a experiencia seja realizada, o WarmUp vai faxer o "aquecimento" do compilador JIT, para que seja evitado os "picos" dos tempo iniciais   
		for (int Item : FileSize){
			LinkedStack<String> numbers = new LinkedStack<String>();
			numbers.push("LinkedStack");
			numbers.pop();			
    	}
		
		// Ciclo que analisa cada posicao do array ou seja cada Item do
		// FileSize, dentro de cada tipo de ficheiro
		for (int Item : FileSize){

			// Cria novo ficheiro exel com o nome LinkedList e o nº do item, na
			// directoria pretendida
			PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + Item + ".csv");

			// variavel com o nº de repetições, onde assegura que os resultados
			// sejam testados varias vezes para verificar a sua veracidade
			int repetir = 10;
			Double[] tempo = new Double[repetir];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");

			// Verificar o tempo
			long starTime, estimatedTime = 0;
			
			// criação de uma nova cadeia ligada, numbers
			LinkedStack<String> numbers = new LinkedStack<String>();

			// Ciclo for vai realizar o nº de repetições que queremos
			// Ciclo para Inserção(push) na pilha
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medição em nanosegundos
				for (int exponent = 0; exponent != Item; exponent++) {
					numbers.push("LinkedStack");// inserir na pilha numbers o valor "LinkStack" as
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
				// guarda o tempo de cada execução, para cada repetição
				tempo[a] = (double) (estimatedTime);
			}

			// vai chamar o metodo (maximeTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			maximo_push = MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de inserção: " + maximo_push + " ns");// imprime na consola
			file.println("Tempo maximo de inserção: " + maximo_push + "ns");// imprime no exel

			// vai chamar o metodo (minimeTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			minimo_push = MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de inserção: " + minimo_push + " ns");// imprime na consola
			file.println("Tempo minimo de inserção: " + minimo_push + "ns");// imprime no exel

			// vai chamar o metodo (medianTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			mediana_push = MedMinMax.medianTimes(tempo);
			out.println("Mediana da inserção: " + mediana_push + " ns");// imprime na consola
			file.println("Mediana da inserção: " + mediana_push + "ns");// imprime no exel

			// vai chamar o metodo (meanTimes) que se encontra no pacote Main e
			// passa a variavel tempo
			media_push = MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de inserção: " + media_push + " ns");// imprime na consola
			file.println("Tempo medio de inserção: " + media_push + "ns");// imprime no exel

			// vai chamar o metodo (standartDeviation) que se encontra no pacote
			// Main e passa a variavel tempo
			desvio_push = MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_push + " ns");// imprime na consola
			file.println("Desvio padrão: " + desvio_push + "ns");// imprime no exel

			file.close();

			// Cria novo ficheiro exel com o nome LinkedList e o nº do item que
			// vamos apagar, na directoria pretendida
			PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + Item + ".csv");

			// Ciclo for vai realizar o nº de repetições que queremos
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medição em nanosegundos
				for (int exponent = 0; exponent != Item; exponent++) {
					numbers.pop();// Apagar os items que estão na pilha numbers
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
				// guarda o tempo de cada execução, para cada repetição
				tempo[a] = (double) (estimatedTime);
			}

			// vai chamar o metodo (maximeTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			maximo_pop = MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de remoção: " + maximo_pop + " ns");// imprime na consola
			file1.println("Tempo maximo de remoção: " + maximo_pop + "ns");// imprime no exel

			// vai chamar o metodo (minimeTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			minimo_pop = MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de remoção: " + minimo_pop + " ns");// imprime na consola
			file1.println("Tempo minimo de remoção: " + minimo_pop + "ns");// imprime no exel

			// vai chamar o metodo (medianTimes) que se encontra no pacote Main
			// e passa a variavel tempo
			mediana_pop = MedMinMax.medianTimes(tempo);
			out.println("Mediana de remoção: " + mediana_pop + " ns");
			file1.println("Mediana de remoção: " + mediana_pop + "ns");// imprime no exel

			// vai chamar o metodo (meanTimes) que se encontra no pacote Main e
			// passa a variavel tempo
			media_pop = MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de remoção: " + media_pop + " ns");
			file1.println("Tempo medio de remoção: " + media_pop + "ns");// imprime no exel

			// vai chamar o metodo (standardDeviation) que se encontra no pacote
			// Main e passa a variavel tempo
			desvio_pop = MedMinMax.standardDeviation(tempo);
			out.println("Desvio padrão: " + desvio_pop + " ns\n");
			file1.println("Desvio padrão: " + desvio_pop + "ns");// imprime no exel

			file1.close();
		}
	}
}