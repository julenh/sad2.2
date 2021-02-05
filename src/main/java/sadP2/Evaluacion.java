package sadP2;
import weka.core.Instances;
//import java.util.Random;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
public class Evaluacion {
	public static void main(String args[]) throws Exception{
		//caragar dataset
		DataSource source = new DataSource("C:/Users/julen/Downloads/datos/adult.train.arff");
		Instances dataset = source.getDataSet();
		dataset.setClassIndex(dataset.numAttributes());//establece la clase
		
		J48 tree = new J48();
		tree.buildClassifier(dataset);
		
		Evaluation eval = new Evaluation(dataset);
		
		DataSource source2 = new DataSource("C:/Users/julen/Downloads/datos/adult.test.arff");
		Instances testDataset = source2.getDataSet();
		testDataset.setClassIndex(testDataset.numAttributes());
		
		eval.evaluateModel(tree, testDataset);
		
		System.out.println(eval.toSummaryString("Resultados de la evaluacion:\n",false));

		System.out.println("correcto %="+eval.pctCorrect());
		System.out.println("incorrecto %="+eval.pctIncorrect());
		System.out.println("AUC = "+eval.areaUnderROC(1));
		System.out.println("kappa ="+eval.kappa());
		System.out.println("MAE = "+eval.meanAbsoluteError());
		System.out.println("RMSE = "+eval.rootMeanSquaredError());
		System.out.println("RAE = "+eval.relativeAbsoluteError());
		System.out.println("RRSE = "+eval.rootRelativeSquaredError());
		System.out.println("Precision ="+eval.precision(1));
		System.out.println("Recall ="+eval.recall(1));
		System.out.println("fMeasure ="+eval.fMeasure(1));
		System.out.println("Error Rate ="+eval.errorRate());
		System.out.println(eval.toMatrixString("Matriz de confusion\n"));
	}
}
