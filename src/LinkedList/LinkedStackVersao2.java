package LinkedList;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Main.MedMinMax;

public class LinkedStackVersao2 {
	
	//Tempo estabelecido por experiencia
	public static final double timeBudgetPerExperiment = 2.0;/* seconds */
	
	/* 
	As execu��es de tempos mais curtos s�o as mais incertas, uma vez que o metodo System.nanoTime() n�o possui precis�o suficiente para medir tais tempos. 
	Em alguns sistemas, curtos tempos de execu��o podem at� ser medidos com valores iguais a 0. Consequentemente, em muitos casos � preferivel realizar uma
	s�rie de continuas repeticoes de uma experi�ncia, em vez de uma unica experiencia. O tempo total de execucao de uma s�rie de continuas repeticoes � medida.
	Ent�o, o tempo de execucao de uma unica experiencia � estimada como a m�dia de tempo de execu��o, isto �, o total de tempos de execu��es de uma s�rie de
	continuas repeticoes a dividir pelo numero de continuas repeticoes da experiencia realizada. Em vez de usar sempre o mesmo numero de continuas repeticoes,
	� preferivel estabelecer o minimo de dura��o de uma execu��o para valorizar qual � claramente o suficientemente longo para o System.nanoTime() medir com
	precis�o aceitavel.
	*/
	
	public static final double minimumTimePerContiguousRepetitions = 2e-5; /* seconds */
			
	//Guarda o resultado das somas para que o compilador do java n�o tenha que optimizar nenhuma das chamadas do sumFrom1To()
	private static long sum;
			
	public static void main(String[] args) throws IOException {

		// Cont�m o n�mero dos ficheiros que v�o ser analizados
		int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
		
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
		
		//Antes que a experiencia seja realizada, o WarmUp vai faxer o "aquecimento" do compilador JIT, para que seja evitado os "picos" de tempo iniciais   
        for (int exponent = 0, limit = 1; exponent != 1000000; exponent++, limit *= 2)
            performExperimentsFor(limit, true);
        
        //Ciclo que analisa cada posicao do array ou seja cada Item do FileSize, dentro de cada tipo de ficheiro
		for (int Item : FileSize) {
			
			// Cria novo ficheiro exel com o nome LinkedList e o n� do item, na directoria pretendida
			PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + Item + ".csv");
			
			// cria��o de uma nova cadeia ligada, numbers
			LinkedStack<String> numbers = new LinkedStack<String>();

			// variavel com o n� de repeti��es, onde assegura que os resultados sejam testados varias vezes para verificar a sua veracidade
			int repetir = 10;
			Double[] tempo = new Double[repetir];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
				
			// Verificar o tempo
	        long starTime, estimatedTime = 0;
	        
	        // Ciclo for vai realizar o n� de repeti��es que queremos
			// Ciclo para Inser��o(push) na pilha
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
				for (int exponent = 0; exponent != Item; exponent++) {
					numbers.push("LinkStack");//inserir na pilha numbers o valor "LinkStack" as 
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
				//guarda o tempo de cada execu��o, para cada repeti��o
				tempo[a] = (double) (estimatedTime);
			}			
			
			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
			maximo_push=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");//imprime na consola
			file.println("Tempo maximo de inser��o: " + maximo_push + "ns");//imprime no exel
			
			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo
			minimo_push=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de inser��o: " + minimo_push + " ns");//imprime na consola
			file.println("Tempo minimo de inser��o: " + minimo_push + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo
			mediana_push=MedMinMax.medianTimes(tempo);
			out.println("Mediana da inser��o: " + mediana_push + " ns");//imprime na consola
			file.println("Mediana da inser��o: " + mediana_push + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo 
			media_push=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de inser��o: " + media_push + " ns");//imprime na consola
			file.println("Tempo medio de inser��o: " + media_push + "ns");//imprime no exel
			
			//vai chamar o metodo (standartDeviation) que se encontra no pacote Main e passa a variavel tempo
			desvio_push=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padr�o: " + desvio_push + " ns");//imprime na consola
			file.println("Desvio padr�o: " + desvio_push + "ns");//imprime no exel
			
			file.close();
			
			// Cria novo ficheiro exel com o nome LinkedList e o n� do item que vamos apagar, na directoria pretendida
			PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + Item + ".csv");
			
			// Ciclo for vai realizar o n� de repeti��es que queremos
			for (int a = 0; a != repetir; a++) {
				starTime = System.nanoTime();// Iniciar a medi��o em nanosegundos
				for (int exponent = 0; exponent != Item; exponent++) {
					numbers.pop();//Apagar os items que est�o na pilha numbers
					estimatedTime = System.nanoTime() - starTime;// Tempo final guardado em variavel
				}
				//guarda o tempo de cada execu��o, para cada repeti��o
				tempo[a] = (double) (estimatedTime);
			}
			
			//vai chamar o metodo (maximeTimes) que se encontra no pacote Main e passa a variavel tempo 
			maximo_pop=MedMinMax.maximeTimes(tempo);
			out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");//imprime na consola
			file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (minimeTimes) que se encontra no pacote Main e passa a variavel tempo 
			minimo_pop=MedMinMax.minimeTimes(tempo);
			out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");//imprime na consola
			file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (medianTimes) que se encontra no pacote Main e passa a variavel tempo 
			mediana_pop=MedMinMax.medianTimes(tempo);
			out.println("Mediana de remo��o: " + mediana_pop + " ns");
			file1.println("Mediana de remo��o: " + mediana_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (meanTimes) que se encontra no pacote Main e passa a variavel tempo
			media_pop=MedMinMax.meanTimes(tempo);
			out.println("Tempo medio de remo��o: " + media_pop + " ns");
			file1.println("Tempo medio de remo��o: " + media_pop + "ns");//imprime no exel
			
			//vai chamar o metodo (standardDeviation) que se encontra no pacote Main e passa a variavel tempo 
			desvio_pop=MedMinMax.standardDeviation(tempo);
			out.println("Desvio padr�o: " + desvio_pop + " ns\n");
			file1.println("Desvio padr�o: " + desvio_pop + "ns");//imprime no exel
			
			file1.close();
		}
		
	}
	
	/* 
	Performa experiencias para obter a sequencia de estimativa do tempo de execu��o do metodo, para calcular a soma dos numeros inteiros desde 1 at� a um limite
	dado. O numero de experiencias a realizar n�o � fixo. Em vez disso, um or�amento de tempo � utilizado e as experiencias s�o repetidas at� o or�amento esgotar. A
	sequencia dos tempos de execu��o obtida � usada para calcular a m�dia do tempo de execu��o, que � uma estat�stica razoavelmente robusta. Os resultados s�o
	apresentados, excepto se for uma execu��o de aquecimento.Performa experiencias para obter a sequencia de estimativa do tempo de execu��o do metodo, para calcular a soma dos numeros inteiros desde 1 at� a um limite
	dado. O numero de experiencias a realizar n�o � fixo. Em vez disso, um or�amento de tempo � utilizado e as experiencias s�o repetidas at� o or�amento esgotar. A
	sequencia dos tempos de execu��o obtida � usada para calcular a m�dia do tempo de execu��o, que � uma estat�stica razoavelmente robusta. Os resultados s�o
	apresentados, excepto se for uma execu��o de aquecimento.
	*/
	
	public static void performExperimentsFor(final int limit,
	            final boolean isWarmup) {
	        final ArrayList<Double> executionTimes = new ArrayList<Double>();
	        long estimatedTime=0;
	        long starTime = System.nanoTime();
	        final int contiguousRepetitions = contiguousRepetitionsFor(limit);
	        long repetitions = 0;
	        do {
	            executionTimes.add(executionTimeFor(limit, contiguousRepetitions));
	            repetitions++;
	            estimatedTime = System.nanoTime() - starTime;
	        } while (estimatedTime < timeBudgetPerExperiment);

	        final double median = medianOf(executionTimes);

	        if (!isWarmup)
	            out.println(
	                    limit + "\t" + median + "\t" + repetitions + "\t" + sum);
	        /*- 
	        out.println("Sum from 1 to " + limit + " = " + sum + " [" + median
	                + "s median time based on " + repetitions
	                + " repetitions of " + contiguousRepetitions
	                + " contiguous repetitions]");
	        */
	    }
	
	// Faz a estimativa do numero de continuas repeticoes a realizar 
	// para um dado limite de numeros a somar na experiencia
	public static int contiguousRepetitionsFor(final int limit) {
        int contiguousRepetitions = 0;
        long estimatedTime=0;
        long starTime = System.nanoTime();
		do {
            sum = sumFrom1To(limit);
            contiguousRepetitions++;
            estimatedTime = System.nanoTime() - starTime;
        } while (estimatedTime < minimumTimePerContiguousRepetitions);

		// O ciclo termina quando o tempo minimo por repeti��es continuas � alcan�ado. 
		// Para experi�ncias maiores, isto na maioria das vezes ser� 1, sendo o que 
		// pretendemos, uma vez que repeti��es continuas s�o uteis apenas 
		// para pequenos tempos de execu��o.

        return contiguousRepetitions;
    }
	
	// Performa a execu��o de repeti��es continuas de uma experiencia para obter o tempo 
	// de execu��o do metodo parara calcular a soma dos numeros inteiros desde 1 at� a um 
	// dado limite. O numero de repeti��es continuas � tambem passado como argumento.
	   
	 public static double executionTimeFor(final int limit,
	            final int contiguousRepetitions) {
	        long estimatedTime=0;
	        long starTime = System.nanoTime();
	        for (int i = 0; i != contiguousRepetitions; i++)
	            sum = sumFrom1To(limit);
	        estimatedTime = System.nanoTime() - starTime;
	        return estimatedTime / contiguousRepetitions;
	    }
	
	// O m�todo para qual os tempos de execu��o s�o procurados
	public static long sumFrom1To(final int limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++)
            sum += i;
        return sum;
    }
	
	// Forma simples e ineficiente para calcular a m�dia dos valores dentro de uma ArrayList
	 public static double medianOf(final ArrayList<Double> values) {
	        final int size = values.size();

	        values.sort(null);

	        if (size % 2 == 0)
	            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
	        else
	            return values.get(size / 2);
	    }
}