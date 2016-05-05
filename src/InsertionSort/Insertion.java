package InsertionSort;

public final class Insertion {
	
	private static int comparisons;
	private static int arrayaccess;
	private static int swaps;

    private Insertion() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    //Ordena��o de todos os items usando o algoritmo Insert
    public static <Item extends Comparable<? super Item>> void sort(final Item[] values) {
    	comparisons = 0;
		arrayaccess = 0;
		swaps = 0;
		//Ciclo, andando da esquerda para a direita, para separar os items ordenados dos que ainda n�o foram
        for (int numberOfSortedItems = 1; numberOfSortedItems < values.length; numberOfSortedItems++)
            //Ciclo interno, andando da direita para a esquerda, onde � efectuada a troca dos values com cada um dos items maiores a sua esquerda
        	for (int i = numberOfSortedItems; i != 0 && isLess(values[i], values[i - 1]); i--)
                swap(values, i - 1, i);

        assert isIncreasing(values) :
            "Array should be increasing after sorting.";
    }
    
    //Verifica os dois values e compara o primeiro com o segundo e retorna true se o primeiro for inferior ao segundo
    private static <Value extends Comparable<? super Value>> boolean isLess(final Value first, final Value second) {
    	comparisons++;
        return first.compareTo(second) < 0;
    }
	
    //Faz a troca entre os values da 1� posi��o e 2� posi��o, criando um array temporario onde guarda um dos values, para depois fazer a troca com o outro
    private static void swap(final Object[] values, final int firstPosition, final int secondPosition) {
        final Object temporary = values[firstPosition];
        arrayaccess++;
        values[firstPosition] = values[secondPosition];
        values[secondPosition] = temporary;
        swaps++;
		arrayaccess++;
    }
	
    //Verifica todo array values com a fun��o isLess e caso esta seja falsa retorna true
    private static <Item extends Comparable<? super Item>> boolean isIncreasing(final Item[] values) {
        for (int i = 1; i < values.length; i++)
            if (isLess(values[i], values[i - 1]))
                return false;
        return true;
    }
    
    //Cria��o do array data,com 3 posi��es, onde cada um vai ocupar o n� de compara��es, acessos ao array e trocas
    public static int[] getCountData() {
		int[] data = new int[3];
		
		data[0] = comparisons;
		data[1] = arrayaccess;
		data[2] = swaps;
		
		return data;	
	}

}

