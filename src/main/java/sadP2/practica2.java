package sadP2;
//cargar datos
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
//filtrar datos
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
//manejo y estadisticas de atributos
import weka.experiment.Stats;
import weka.core.AttributeStats;
//clasificacion
import weka.classifiers.bayes.NaiveBayes;//clasificador naive bayes
import weka.classifiers.functions.SMO;// clasificador SMO
import weka.classifiers.trees.J48;// clasificador J48 (el que sale en la pagina de weka)


import java.io.*;

public class practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clasificacion();
	}
	
	public void cargarDatos() {
		DataSource sourceTrain = new DataSource("/adult.train.arff");
		Instances datasetTrain = new sourceTrain.getDataSet();
		if(datasetTrain.classIndex() == -1) {
			datasetTrain.setClassIndex(datasetTrain.numAttributes() - 1);
		}
		DataSource sourceTest = new DataSource("/adult.test.arff");
		Instances datasetTest = new sourceTest.getDataSet();
		if(datasetTest.classIndex() == -1) {
			datasetTest.setClassIndex(datasetTest.numAttributes() - 1);
		}
	}
	public static Instances cargarDatos2() {
		 Instances dataset = null;
		try {
			dataset = DataSource.read("/adult.train.arff");
			if (dataset.classIndex() == -1) {
	             dataset.setClassIndex(dataset.numAttributes() - 1);
	         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return dataset;
	}
	
	public void filtrarDatos() throws Exception {
		//caragr datos
		Instances viejoDataset = cargarDatos2();
		// usa el filtro para quitar el segundo atributo
		// poner las opciones para borrar el segundo atributo
		String[] opciones = new String[] {"-R","2"};
		Remove quitar = new Remove();// crear remove
	    quitar.setOptions(opciones);// configurar lo que se quita
		quitar.setInputFormat(cargarDatos2());
		Instances nuevoDataset = Filter.useFilter(viejoDataset, quitar);//aplicar el filtro
		
	}
	// manejo de atributos
	public void estadisticasAtributos() {
		Instances datos = cargarDatos2();
		int numeroAtributos = datos.numAttributes()-1;
		for(int i=0; i<numeroAtributos; i++) {
			// estadisticas del atributo
			AttributeStats as = datos.attributeStats(i);
			
			if(datos.attribute(i).isNominal()) {
				// si es nominal
				System.out.println("el atributo "+datos.attribute(i).name()+" es nominal");
				// cuantos valores difernetes
				int vTemporal = datos.attribute(i).numValues();
				System.out.println("el atributo "+datos.attribute(i).name()+" tiene "+vTemporal+" valores diferentes");
			}
			if(datos.attribute(i).isNumeric()) {
				// si es numerico
				System.out.println("el atributo"+datos.attribute(i).name()+"es numerico");
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
		int numInsTemporal = datos.numInstances();
		System.out.println("numero de instancias"+numInsTemporal);
		
	}
	// construccion de los clasificadores
	public static void clasificacion() throws Exception {
		//clasificador naive bayes
		Instances dataset = cargarDatos2();//cargar dataset
		dataset.setClassIndex(dataset.numAttributes()-1);//asignar la clase al ultimo atributo
		NaiveBayes nBayes = new NaiveBayes();//crear y construir el clasificador
		nBayes.buildClassifier(dataset);
		// clasificador SMO
		SMO smo = new SMO();
		smo.buildClassifier(dataset);
		// clasificador J48 y opciones (https://weka.sourceforge.io/doc.stable-3-8/)
		String[] opciones = new String[1];
		opciones[0] = "-U";//opcion unpruned tree
		J48 arbol = new J48();//crear arbol
		arbol.setOptions(opciones);//establecer las opciones
		arbol.buildClassifier(dataset);// contruir el clasificador
		
		
	}
	public void evaluacion() {
		
	}
	
}
