package sadP2;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

public class HoldOut {

	public static void main(String[] args) throws Exception {
		
		DataSource source = new DataSource(args[0]);
		Instances dataset = source.getDataSet();
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		// randomizar las Instancias
		Randomize rand = new Randomize();
		rand.setRandomSeed(42);
		rand.setInputFormat(dataset);
		Instances randDataset = Filter.useFilter(dataset, rand);
		// separa el dataset en train y test
		int percent = 70;
		RemovePercentage removePercenge = new RemovePercentage();
		removePercenge.setPercentage(percent);
		removePercenge.setInputFormat(randDataset);
		Instances train = Filter.useFilter(dataset, removePercenge);
		removePercenge.setInvertSelection(true);
		removePercenge.setPercentage(percent);
		removePercenge.setInputFormat(randDataset);
		Instances test = Filter.useFilter(randDataset, removePercenge);
		
		// datos de instancias
		System.out.println("instancias de dataset original: "+randDataset.numInstances());
		System.out.println("instancias de train: "+train.numInstances());
		System.out.println("instancias de test: "+test.numInstances());
		//construir classifier
		
		J48 tree = new J48();
		tree.buildClassifier(train);
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(tree, test);
		
		System.out.println(eval.toMatrixString("matriz de confusion \n"));
		
		
	}

}
