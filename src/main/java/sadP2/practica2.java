package sadP2;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;

public class Practica2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void cargarDatos() {
		DataSource source = new DataSource("/adult.train.arff");
		Instances dataset = new source.getDataset();
	}
}
