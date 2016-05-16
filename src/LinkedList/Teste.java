package LinkedList;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Main.MedMinMax;

public class Teste {

	public static void main(String[] args) throws IOException {
		
		final int[] FileSize = {2, 4, 8, 16, 32};
		ArrayList<Double> timesOut = new ArrayList<Double>();

		long starTime, estimatedTime = 0;
		
		double endTimes = 500000000;

		for (int Item : FileSize){

			long times = 0;
			
			do {
				LinkedStack<Double> stackDouble = new LinkedStack<Double>();

				starTime = System.nanoTime();
				for (int i = 0; i != Item; i++) {
					stackDouble.push(null);
					estimatedTime = System.nanoTime();
				}

				timesOut.add((double) (estimatedTime - starTime));
				times +=(estimatedTime - starTime);	
				
				} while (times < endTimes);

			System.out.println("*****************" + Item
					+ "*******************");

			out.println("\nMedia: "
					+ meanTimesT(timesOut) +"\nMediana: "
					+ medianTimesT(timesOut) + "\nMaximo: " + maximeTimesT(timesOut)
					+ "\nMinimo: " + minimeTimesT(timesOut));
			
		}
	}

	
	private static Double meanTimesT(ArrayList<Double> times) {
		double count = 0.0;
		int a=0;
		for (int i = 0; i != times.size(); i++) {

			count += times.get(i);
			a++;
		}
		return (count / a);
	}

	private static Double medianTimesT(ArrayList<Double> times) {

		int middle = times.size() / 2;
		if (times.size() % 2 == 1) {
			return times.get(middle);
		}

		return (times.get(middle - 1) + times.get(middle)) / 2.0;
	}

	private static Double minimeTimesT(ArrayList<Double> times) {

		return times.get(0);
	}

	private static Double maximeTimesT(ArrayList<Double> times) {

		return times.get(times.size()-1);
	}
}