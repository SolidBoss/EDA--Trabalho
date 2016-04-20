package Main;

public class MedMinMax {
	
	// função para calcular a media dos tempos
	public static Double meanTimes(Double[] tempo, int repetir) {
		double count = 0.0;

		for (int i = 0; i != tempo.length; i++) {

			count += tempo[i];
		}
		return (count / repetir);
	}

	// função para calcular a mediana dos tempos
	public static Double medianTimes(Double[] tempo) {

		int middle = tempo.length / 2;
		if (tempo.length % 2 == 1) {
			return tempo[middle];
		}

		return (tempo[middle - 1] + tempo[middle]) / 2.0;
	}

	//função para calcular o minimo dos tempos
	public static Double minimeTimes(final Double[] tempo) {

		double min=tempo[0];
		
		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]<min){
				min=tempo[i];
			}
		}
		return min;
	}

	// funcao para calcular o maximo dos tempos
	public static Double maximeTimes(final Double[] tempo) {

		double max=tempo[0];

		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]>max){
				max=tempo[i];
			}
		}
		return max;
	}

	// função para calcular o desvio padrão
	public static double standardDeviation(final Double[] tempo) {  
        double standardDeviation;
        double media= medianTimes(tempo);
        double sum = 0,result;
        
        //cria os arrays que vao ser necessarios para o calculo do desvio padrao
        double[] listOfDifferences=new double[tempo.length];
        double[] squares=new double[tempo.length];
        
      //preenche o array "listofDifferences" com o valor da subtraccao entre os tempos calculados e a media do tempo
        for(int i=0;i<tempo.length;i++){
        	listOfDifferences[i]=tempo[i]-media;        	
        }
        
        //preenche o array "squares" com os valores que foram calculados no array interior ao quadrado
        
        for(int i=0;i<tempo.length;i++){
        	squares[i]=listOfDifferences[i]*listOfDifferences[i];
        }
        
        //calcula a soma de todos os valores do array "squares"
        
        for(int i=0;i<tempo.length;i++){
        	sum+=squares[i];
        }
        
        //Divide a soma calculada anteriormente pelo tamanho do array values -1
        result= sum/(tempo.length-1);
        
        //desvio padrao elevado a raiz quadrada desse valor
        standardDeviation=Math.sqrt(result);
		
		return standardDeviation;
    }  
	
}
