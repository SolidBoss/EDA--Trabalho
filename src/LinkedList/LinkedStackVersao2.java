package LinkedList;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Main.MedMinMax;

public class LinkedStackVersao2 {
	
	// A time budget is established per experiment. Each experiment is repeated
    // as many times as necessary to expend this budget. That is, each
    // experiment is repeated until the total time spent repeating it exceeds
    // the budget.
	public static final double timeBudgetPerExperiment = 2.0;/* seconds */
	
	 // Small execution times are very "noisy", since the System.nanoTime()
    // method does not have sufficient precision to measure them. In some
    // systems, smaller execution times may even be measured as 0.0! Hence, in
    // many cases it is preferable to perform a run of contiguous repetitions of
    // an experiment, instead of a single experiment. The total
    // execution time of that run of contiguous repetitions is measured. Then,
    // the execution time of a single experiment is estimated as the average
    // execution time, that is, the total execution time of the contiguous
    // repetitions divided by the number of contiguous repetitions of the
    // experiment performed. Instead of using always the same number of
    // contiguous repetitions, however, it is preferable to establish the
    // minimum
    // duration of a run to value which is clearly long enough for
    // System.nanoTime() to measure with acceptable accuracy.
	public static final double minimumTimePerContiguousRepetitions = 2e-5; /* seconds */
			
	// Used to store the results of the sums, so that the Java compiler does not
	// optimize away our calls to sumFrom1To() (this variable is used when
	// showing the experimental results, so we don't get warnings
	// about unused variables):
	private static long sum;
			
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
		 // Warm up (this attempts to force the JIT compiler to do its work
        // before the experiments actually begin):
        
		for (int Item : FileSize) {
			PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + Item + ".csv");
			LinkedStack<String> numbers = new LinkedStack<String>();

			int repetir = 2;
			Double[] tempo = new Double[repetir];
			out.println("-----------------------------------");
			out.println("Numero de Itens " + Item);
			out.println("-----------------------------------");
						
			//Antes que a experiencia seja realizada, o WarmUp vai faxer o "aquecimento" do compilador JIT, para que seja evitado os "picos" de tempo iniciais   
	        for (int exponent = 0, limit = 1; exponent != Item; exponent++, limit *= 2)
	            performExperimentsFor(limit, true);
	        
	        long starTime, estimatedTime = 0;
	        
			for (int a = 0; a != repetir; a++) {
				for (int exponent = 0; exponent != Item; exponent++) {
					starTime = System.nanoTime();
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
				for (int exponent = 0; exponent != Item; exponent++) {
					starTime = System.nanoTime();
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
	
	 // Estimate the number of contiguous repetitions to perform for a given
    // limit of the numbers to sum in the experiment:
	public static int contiguousRepetitionsFor(final int limit) {
        int contiguousRepetitions = 0;
        long estimatedTime=0;
        long starTime = System.nanoTime();
		do {
            sum = sumFrom1To(limit);
            contiguousRepetitions++;
            estimatedTime = System.nanoTime() - starTime;
        } while (estimatedTime < minimumTimePerContiguousRepetitions);

        // The loop stops when the minimum time per contiguous repetitions is
        // reached. For longer experiments, this will mostly turn out to be one,
        // which is what we would expect, since contiguous repetitions are
        // useful only for small execution times.

        return contiguousRepetitions;
    }
	
	public static long sumFrom1To(final int limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++)
            sum += i;
        return sum;
    }
	
	 public static double executionTimeFor(final int limit,
	            final int contiguousRepetitions) {
	        long estimatedTime=0;
	        long starTime = System.nanoTime();
	        for (int i = 0; i != contiguousRepetitions; i++)
	            sum = sumFrom1To(limit);
	        estimatedTime = System.nanoTime() - starTime;
	        return estimatedTime / contiguousRepetitions;
	    }
	 
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
	 
	 public static double medianOf(final ArrayList<Double> values) {
	        final int size = values.size();

	        values.sort(null);

	        if (size % 2 == 0)
	            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
	        else
	            return values.get(size / 2);
	    }
}