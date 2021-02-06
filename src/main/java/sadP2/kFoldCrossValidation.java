package sadP2;
import weka.core.Instances;
import java.util.Random;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
public class kFoldCrossValidation {
	public static void main(String args[]) throws Exception{
		DataSource source = new DataSource(args[0]);
		Instances dataset = source.getDataSet();
		dataset.setClassIndex(dataset.numAttributes());//establece la clase
		
		NaiveBayes nb = new NaiveBayes();
		
		int seed = 1;
		int folds = 15;
		Random rand = new Random(seed);
		nb.buildClassifier(dataset);
		
		Instances randData = new Instances(dataset);
		randData.randomize(rand);
		
		//estratificar
		if(randData.classAttribute().isNominal()) {
			randData.stratify(folds);
		}
		//cross validation
		for(int n = 0; n < folds; n++) {
			Evaluation eval = new Evaluation(randData);
			//fold
			Instances train = randData.trainCV(folds, n);
			Instances test = randData.testCV(folds, n);
			nb.buildClassifier(train);
			eval.evaluateModel(nb, test);
			//sacar la evaluacion
			
			System.out.println(eval.toMatrixString("Matriz de confusion para fold "+(n+1)+"\n"));
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
			
		}
	}
}
