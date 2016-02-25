import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.RandomForest;
import weka.core.FastVector;
import weka.core.Instances;
import java.lang.Object;
import weka.classifiers.evaluation.output.prediction.PlainText;



public class RandomForestClassifier {
	public static void main(String[] args) throws Exception {
		BufferedReader readerTrain = new BufferedReader(new FileReader(args[0]));
		BufferedReader readerTest = new BufferedReader(new FileReader(args[1]));
		Instances train = new Instances(readerTrain);
		Instances test =  new Instances(readerTest);
		train.setClassIndex(0);
		test.setClassIndex(0);
		StringBuffer predsBuffer = new StringBuffer();
        PlainText plainText = new PlainText();
        plainText.setHeader(train);
        plainText.setBuffer(predsBuffer);
		int treeNum=Integer.parseInt(args[2]);
		RandomForest cls = new RandomForest();
		cls.setNumTrees(treeNum);
		cls.buildClassifier(train);
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls,test,plainText);
		System.out.println(eval.toSummaryString("\nResults\n======\n", true));
		System.out.println(eval.toClassDetailsString());
		System.out.println(predsBuffer.toString());
		readerTrain.close();
		readerTest.close();
	}
}
