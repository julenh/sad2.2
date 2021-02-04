package sadP2;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;

public class practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void cargarDatos() {
		DataSource source = new DataSource("/adult.train.arff");
		Instances dataset = new source.getDataset();
		if(dataset.classIndex() == -1) {
			dataset.setClassIndex(dataset.numAttributes() - 1);
		}
	}
}
