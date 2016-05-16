package LinkedList;

import static java.lang.System.out;


import java.util.ArrayList;

/**
 * A simple class/program for obtaining experimentally the execution times of a
 * method summing all integers from 1 to different limits. This may help you
 * devise your own experiments in other cases, but it is *not* intended has a
 * basis for your own code, however. You should study this code carefully and
 * apply the insights gained to your own code. This code is not intended to be
 * in any way complete or to cover all interesting cases. Try to run these
 * tests. Copy the results to Â«DataÂ» in http://gnuplot.respawned.com/. Then copy
 * the following Gnuplot script to Â«Plot scriptÂ»:
 *-
set terminal svg size 800,600 enhanced fname 'arial'  fsize 10 butt solid
set output 'out.svg'
set key inside bottom right
set xlabel 'sum limit'
set ylabel 'time (s)'
set y2label 'repetitions'
set title 'Execution times of sum of integers from 1 to limit'
set logscale x 2
set logscale y 10
set logscale y2 10
set grid
set xtics 2 rotate by 90 right format '%.0f'
set y2tics format '%g'
plot  "data.txt" using 1:2 title 'times' with linespoints, "data.txt" using 1:3 title 'number of repetitions' axes x1y2
 *
 * The output looks something like the following:
 *-
1   1.7222222222222222E-9   2994343 1
2   1.8181818181818182E-9   3042602 3
4   3.110236220472441E-9    1953040 10
8   3.4736842105263155E-9   2688197 36
16  7.256637168141593E-9    1039035 136
32  1.4788235294117648E-8   684341  528
64  3.78375E-8  303032  2080
128 6.95E-8 220936  8256
256 1.3697777777777777E-7   148977  32896
512 2.7014285714285713E-7   474764  131328
1024    5.292666666666667E-7    115504  524800
2048    1.0325E-6   111311  2098176
4096    2.06125E-6  110075  8390656
8192    4.109E-6    74700   33558528
16384   8.202E-6    56547   134225920
32768   1.6403E-5   56430   536887296
65536   3.2788E-5   28260   2147516416
131072  6.7092E-5   14111   8590000128
262144  1.30921E-4  7172    34359869440
524288  2.68528E-4  3529    137439215616
1048576 5.376924999999999E-4    1784    549756338176
2097152 0.001090812 880 2199024304128
4194304 0.002233815 433 8796095119360
8388608 0.004413868499999999    218 35184376283136
16777216    0.008761125 111 140737496743936
33554432    0.017682951000000002    54  562949970198528
67108864    0.035165623 27  2251799847239680
134217728   0.073582029 13  9007199321849856
268435456   0.1465926465    6   36028797153181696
536870912   0.29062364  3   144115188344291328
1073741824  0.57687999  1   576460752840294400
 */
public class SumExecutionTimeTester {
	
	//Tempo estabelecido por experiencia
    public static final double timeBudgetPerExperiment = 2000000000; /* nanoseconds */

    
    static final int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536,
			131072, 262144, 524288, 1048576 };
    static final int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64};
    
    /* 
	As execuções de tempos mais curtos são as mais incertas, uma vez que o metodo System.nanoTime() não possui precisão suficiente para medir tais tempos. 
	Em alguns sistemas, curtos tempos de execução podem até ser medidos com valores iguais a 0. Consequentemente, em muitos casos é preferivel realizar uma
	série de continuas repeticoes de uma experiência, em vez de uma unica experiencia. O tempo total de execucao de uma série de continuas repeticoes é medida.
	Então, o tempo de execucao de uma unica experiencia é estimada como a média de tempo de execução, isto é, o total de tempos de execuções de uma série de
	continuas repeticoes a dividir pelo numero de continuas repeticoes da experiencia realizada. Em vez de usar sempre o mesmo numero de continuas repeticoes,
	é preferivel estabelecer o minimo de duração de uma execução para valorizar qual é claramente o suficientemente longo para o System.nanoTime() medir com
	precisão aceitavel.
	*/
    public static final double minimumTimePerContiguousRepetitions = 2e-9 /* nanoseconds */;

    // Forma simples e ineficiente para calcular a média dos valores dentro de uma ArrayList
    public static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }

    // O método para qual os tempos de execução são procurados
    public static long sumFrom1To(final int Item) {
        long sum = 0;
        for (int i = 1; i <= Item; i++)
            sum += i;
        return sum;
    }

    //Guarda o resultado das somas para que o compilador do java não tenha que optimizar nenhuma das chamadas do sumFrom1To()
    private static long sum;

    // Faz a estimativa do numero de continuas repeticoes a realizar 
 	// para um dado limite de numeros a somar na experiencia
    public static int contiguousRepetitionsFor(final int Item) {
        int contiguousRepetitions = 0;
        long estimatedTime=0;
        long starTime = System.nanoTime();
		do {
            sum = sumFrom1To(Item);
            contiguousRepetitions++;
            estimatedTime = System.nanoTime() - starTime;
        } while (estimatedTime < minimumTimePerContiguousRepetitions);
				// O ciclo termina quando o tempo minimo por repetições continuas é alcançado. 
				// Para experiências maiores, isto na maioria das vezes será 1, sendo o que 
				// pretendemos, uma vez que repetições continuas são uteis apenas 
				// para pequenos tempos de execução.
		return contiguousRepetitions;
    }

    // Performa a execução de repetições continuas de uma experiencia para obter o tempo 
 	// de execução do metodo parara calcular a soma dos numeros inteiros desde 1 até a um 
 	// dado limite. O numero de repetições continuas é tambem passado como argumento.
    public static double executionTimeFor(final int Item,
            final int contiguousRepetitions) {
        long estimatedTime=0;
        long starTime = System.nanoTime();
        for (int i = 0; i != contiguousRepetitions; i++)
            sum = sumFrom1To(Item);
        estimatedTime = System.nanoTime() - starTime;
        return estimatedTime / contiguousRepetitions;
    }

    /* 
	Performa experiencias para obter a sequencia de estimativa do tempo de execução do metodo, para calcular a soma dos numeros inteiros desde 1 até a um limite
	dado. O numero de experiencias a realizar não é fixo. Em vez disso, um orçamento de tempo é utilizado e as experiencias são repetidas até o orçamento esgotar. A
	sequencia dos tempos de execução obtida é usada para calcular a média do tempo de execução, que é uma estatística razoavelmente robusta. Os resultados são
	apresentados, excepto se for uma execução de aquecimento.Performa experiencias para obter a sequencia de estimativa do tempo de execução do metodo, para calcular a soma dos numeros inteiros desde 1 até a um limite
	dado. 
	*/
    public static void performExperimentsFor(final int limit,
            final boolean isWarmup) {
    	
        final ArrayList<Double> executionTimes = new ArrayList<Double>();
        LinkedStack<Double> numbers = new LinkedStack<Double>();
        long estimatedTime=0;
        long starTime = System.nanoTime();
        final int contiguousRepetitions = contiguousRepetitionsFor(limit);
        long repetitions = 0;
        
        for (int Item : FileSize){
        do {
        	numbers.push(null);
            executionTimes.add(executionTimeFor(limit, contiguousRepetitions));
            repetitions++;
            estimatedTime = System.nanoTime() - starTime;
        } while (estimatedTime < timeBudgetPerExperiment);

        }
        final double median = medianOf(executionTimes);
        if (!isWarmup)
        
        out.println("Sum from 1 to " + limit + " = " + sum + " [" + median
                + "s median time based on " + repetitions
                + " repetitions]");
        
    }

    public static void main(final String[] arguments)
            throws InterruptedException {
        // The experiments are run for limits of the sums which increase
        // geometrically, through the powers of 2:

    	
    	//Antes que a experiencia seja realizada, o WarmUp vai faxer o "aquecimento" do compilador JIT, para que seja evitado os "picos" de tempo iniciais   
    	for (int Item : FileSizeWarm){
            performExperimentsFor(Item, true);
    	}
		
        // The actual experiments are performed here, with limits going from 1
        // to 2^30:
		for (int Item : FileSize){
            performExperimentsFor(Item, false);
		}
    }

}

/*
 * Copyright 2016, Manuel Menezes de Sequeira.
 * 
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this code. If not, see http://www.gnu.org/licenses.
 */