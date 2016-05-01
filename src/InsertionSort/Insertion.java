package InsertionSort;

public final class Insertion {
	
	private static int comparisons;
	private static int arrayaccess;
	private static int swaps;

    private Insertion() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    public static <Item extends Comparable<? super Item>> void sort(final Item[] values) {
    	comparisons = 0;
		arrayaccess = 0;
		swaps = 0;
        for (int numberOfSortedItems = 1; numberOfSortedItems < values.length; numberOfSortedItems++)
            for (int i = numberOfSortedItems; i != 0 && isLess(values[i], values[i - 1]); i--)
                swap(values, i - 1, i);

        assert isIncreasing(values) :
            "Array should be increasing after sorting.";
    }
    
    private static <Value extends Comparable<? super Value>> boolean isLess(final Value first, final Value second) {
    	comparisons++;
        return first.compareTo(second) < 0;
    }
	
    private static void swap(final Object[] values, final int firstPosition, final int secondPosition) {
        final Object temporary = values[firstPosition];
        arrayaccess++;
        values[firstPosition] = values[secondPosition];
        values[secondPosition] = temporary;
        swaps++;
		arrayaccess++;
    }
	
    private static <Item extends Comparable<? super Item>> boolean isIncreasing(final Item[] values) {
        for (int i = 1; i < values.length; i++)
            if (isLess(values[i], values[i - 1]))
                return false;
        return true;
    }
    
    public static int[] getCountData() {
		int[] data = new int[3];
		
		data[0] = comparisons;
		data[1] = arrayaccess;
		data[2] = swaps;
		
		return data;	
	}

}

