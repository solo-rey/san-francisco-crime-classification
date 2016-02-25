import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import weka.core.FastVector;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
public class J48Classifier {

	public static void main(String[] args) throws Exception {
		BufferedReader readerTrain = new BufferedReader(new FileReader(args[0]));
		 BufferedReader readerTest = new BufferedReader(new FileReader(args[1]));
		 Instances train = new Instances(readerTrain);
		 Instances test =  new Instances(readerTest);
		 train.setClassIndex(0);
		 test.setClassIndex(0);
		 Classifier cls = new J48();
		 cls.buildClassifier(train);
		 Evaluation eval = new Evaluation(train);
		 eval.evaluateModel(cls,test);
		 System.out.println(eval.toSummaryString("\nResults\n======\n", true));
		 System.out.println(eval.toClassDetailsString());
		 readerTrain.close();
		 readerTest.close();
	  }
	}
