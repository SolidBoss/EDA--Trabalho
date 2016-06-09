package QuickSort;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public final class Quick3way {
	
	private static int comparisons;
	private static int arrayaccess;
	private static int swaps;

    private Quick3way() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    public static <Item extends Comparable<? super Item>> void sort(final Item[] values) {
    	comparisons = 0;
		arrayaccess = 0;
		swaps = 0;
        StdRandom.shuffle(values);

        sort(values, 0, values.length - 1);

        assert isIncreasing(
                values) : "Array should be increasing after sorting.";
    }

    private static <Item extends Comparable<? super Item>> void sort(final Item[] values, final int first, final int last) {
        if (last <= first)
            return;

        int lowest = first;
        int greatest = last;

        int i = first + 1;

        final Item pivot = values[first];
        arrayaccess++;
        
        while (i <= greatest) {
            final int comparison = values[i].compareTo(pivot);
            arrayaccess++;
            comparisons++;

            if (comparison < 0) {
                swap(values, lowest, i);
                lowest++;
                i++;
            } else if (comparison > 0) {
                swap(values, i, greatest);
                greatest--;
            } else
                i++;
        }

        sort(values, first, lowest - 1);
        sort(values, greatest + 1, last);

        assert isIncreasing(values, first,
                last) : "Array segment should be increasing after sorting.";
    }

    private static <Value extends Comparable<? super Value>> boolean isLess(final Value first, final Value second) {
    	comparisons++;
        return first.compareTo(second) < 0;
    }

    private static void swap(final Object[] values, final int i, final int j) {
        final Object temporary = values[i];
        values[i] = values[j];
        values[j] = temporary;
        arrayaccess+=2;        
        swaps++;
    }

    private static <Item extends Comparable<? super Item>> boolean isIncreasing(
            final Item[] values) {
        return isIncreasing(values, 0, values.length - 1);
    }

    private static <Item extends Comparable<? super Item>> boolean isIncreasing(
            final Item[] a, final int first, final int last) {
        for (int i = first + 1; i <= last; i++)
            if (isLess(a[i], a[i - 1]))
                return false;
        return true;
    }

    @SuppressWarnings("unused")
	private static void show(final Object[] values) {
        for (Object value : values)
            StdOut.println(value);
    }
    
    public static int[] getCountData() {
		int[] data = new int[3];
		
		data[0] = comparisons;
		data[1] = arrayaccess;
		data[2] = swaps;
		
		return data;	
	}
}