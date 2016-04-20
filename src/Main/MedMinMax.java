package Main;

public class MedMinMax {
	
	public static Double meanTimes(Double[] tempo, int repetir) {
		double count = 0.0;

		for (int i = 0; i != tempo.length; i++) {

			count += tempo[i];
		}
		return (count / repetir);
	}

	public static Double medianTimes(Double[] tempo) {

		int middle = tempo.length / 2;
		if (tempo.length % 2 == 1) {
			return tempo[middle];
		}

		return (tempo[middle - 1] + tempo[middle]) / 2.0;
	}

	public static Double minimeTimes(final Double[] tempo) {

		double min=tempo[0];
		
		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]<min){
				min=tempo[i];
			}
		}
		return min;
	}

	public static Double maximeTimes(final Double[] tempo) {

		double max=tempo[0];

		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]>max){
				max=tempo[i];
			}
		}
		return max;
	}

	public static double standardDeviation(final Double[] tempo) {  
        double standardDeviation;
        double media= medianTimes(tempo);
        double sum = 0,result;
        
        //cria os arrays que vao ser necessarios para o calculo do desvio padrao
        double[] listOfDifferences=new double[tempo.length];
        double[] squares=new double[tempo.length];
        
      //preenche o array "listofDifferences" com o valor da subtrac�‹o entre os tempos calculados e o tempo mŽdio(media)
        for(int i=0;i<tempo.length;i++){
        	listOfDifferences[i]=tempo[i]-media;        	
        }
        
        //preenche o array "squares" com os valores que foram calculados no array interior mas ao quadrado
        
        for(int i=0;i<tempo.length;i++){
        	squares[i]=listOfDifferences[i]*listOfDifferences[i];
        }
        
        //calcula a soma de todos os valores do array "squares"
        
        for(int i=0;i<tempo.length;i++){
        	sum+=squares[i];
        }
        
        //Divide a soma calculada anteriormente pelo tamanho do array values -1
        result= sum/(tempo.length-1);
        
        //desvio padrao Ž a raiz quadrada desse valor
        standardDeviation=Math.sqrt(result);
		
		return standardDeviation;
    }  
	
}
