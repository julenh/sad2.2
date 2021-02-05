package sadP2;

import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.Stats;

public class Instancias {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DataSource source = new DataSource(args[0]);
		Instances dataset = source.getDataSet();
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		int numeroAtributos = dataset.numAttributes()-1;
		for(int i=0; i<numeroAtributos; i++) {
			// estadisticas del atributo
			AttributeStats as = dataset.attributeStats(i);
			
			if(dataset.attribute(i).isNominal()) {
				// si es nominal
				System.out.println("el atributo "+dataset.attribute(i).name()+" es nominal");
				// cuantos valores difernetes
				int vTemporal = dataset.attribute(i).numValues();
				System.out.println("el atributo "+dataset.attribute(i).name()+" tiene "+vTemporal+" valores diferentes");
			}
			if(dataset.attribute(i).isNumeric()) {
				// si es numerico
				System.out.println("el atributo"+dataset.attribute(i).name()+"es numerico");
				// estadisticas del atributo numerico
				Stats s = as.numericStats;
				System.out.println("minimo: "+s.min);
				System.out.println("maximo: "+s.max);
				System.out.println("media: "+s.mean);
				System.out.println("desviacion: "+s.stdDev);
			}
			//valores diferentes de atributo
			System.out.println("nuemro de valores distintos: "+as.distinctCount);
			//valores unicos del atributo
			System.out.println("numero de valores unicos: "+as.uniqueCount);
		}
		//generales
		//numero de instancias
		int numInsTemporal = dataset.numInstances();
		System.out.println("numero de instancias"+numInsTemporal);
	}

}
