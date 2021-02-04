package sadP2;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;

public class practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void cargarDatos() {
		DataSource sourceTrain = new DataSource("/adult.train.arff");
		Instances datasetTrain = new sourceTrain.getDataset();
		if(datasetTrain.classIndex() == -1) {
			datasetTrain.setClassIndex(datasetTrain.numAttributes() - 1);
		}
		DataSource sourceTest = new DataSource("/adult.test.arff");
		Instances datasetTrain = new sourceTest.getDataset();
		if(datasetTrain.classIndex() == -1) {
			datasetTrain.setClassIndex(datasetTrain.numAttributes() - 1);
		}
	}
	
}
