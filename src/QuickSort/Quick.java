package QuickSort;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public final class Quick {
	
	private Quick() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

	//Metodo publico para ser chamado fora da classe
	public static <Item extends Comparable<? super Item>> void sort(final Item[] values) {
        StdRandom.shuffle(values);//Para baralhar o array

        sort(values, 0, values.length - 1);//Ordenação recursiva do array

        assert isIncreasing(values) : "Array should be increasing after sorting.";
    }
	
	//Metodo recursivo de ordenação rápida
	private static <Item extends Comparable<? super Item>> void sort(final Item[] values, final int first, final int last) {
        if (last <= first)//Se o ultimo valor for menor que o primeiro valor retorna 
            return;

        final int j = partition(values, first, last);//Variavel que vai guardar o pivot da partição
        sort(values, first, j - 1);//Ordenação dos valores inferiores ao pivot
        sort(values, j + 1, last);//Ordenação dos valores superiores ao pivot

        assert isIncreasing(values, first, last) : "Array segment should be increasing after sorting.";
    }
	
	//Metodo para criar as partições
    private static <Item extends Comparable<? super Item>> int partition(final Item[] values, final int first, final int last) {
        int i = first;//i comeca na 1º posição do array
        int j = last + 1;//j comeca na ultima posição do array + 1

        final Item pivot = values[first];//Guarda o primeiro valor do array como o pivot

        while (true) {//Ciclo infinito

            do
                ++i;//i percorre todos os valores do array pela esquerda...
            while (isLess(values[i], pivot) && i != last);//...enquanto o valor na posição i for menor que o pivot e o i for diferente do last

            do
                --j;//j percorre todos os valores do array pela direita...
            while (isLess(pivot, values[j]) && j != first);//...enquanto o valor na posição j for maior que o pivot e o for diferente do first

            if (i >= j)//Guarda do ciclo, quando o i se torna maior ou igual a j o ciclo termina
                break;

            swap(values, i, j);//Faz a troca dos valores na posiçao i e j
        }

        swap(values, first, j);//Faz a troca do first( pivot) pelo j

        return j;//Retorna o pivot
    }
	
    public static <Item extends Comparable<? super Item>> Item select(final Item[] values, final int k) {
        if (k < 0 || values.length <= k)
            throw new IndexOutOfBoundsException(
                    "Selected element out of bounds");

        StdRandom.shuffle(values);

        int first = 0;
        int last = values.length - 1;

        while (last > first) {
            final int j = partition(values, first, last);
            if (j > k)
                last = j - 1;
            else if (j < k)
                first = j + 1;
            else
                return values[j];
        }

        return values[first];
    }
    
    //Metodo para comparar o first com o last e se o first for inferior retorna true
    private static <Value extends Comparable<? super Value>> boolean isLess(final Value first, final Value second) {
        return first.compareTo(second) < 0;
    }
    
    //Metodo para fazer a troca entre o valor da posicao i com o valor da posicao j
    private static void swap(final Object[] values, final int i, final int j) {
        final Object temporary = values[i];
        values[i] = values[j];
        values[j] = temporary;
    }
    
    private static <Item extends Comparable<? super Item>> boolean isIncreasing(final Item[] values) {
        return isIncreasing(values, 0, values.length - 1);
    }
    
    private static <Item extends Comparable<? super Item>> boolean isIncreasing(final Item[] a, final int first, final int last) {
        for (int i = first + 1; i <= last; i++)
            if (isLess(a[i], a[i - 1]))
                return false;
        return true;
    }
    
    private static void show(final Object[] values) {
        for (Object value : values)
            StdOut.println(value);
    }
}
