package com.dm;

import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.evaluation.output.prediction.PlainText;

import weka.classifiers.Evaluation;
import weka.core.Instances;

public class NaiveBayes {

	public static void main(String[] args) throws Exception {
		BufferedReader readerTrain = new BufferedReader(new FileReader(args[0]));
        BufferedReader readerTest = new BufferedReader(new FileReader(args[1]));
        Instances train = new Instances(readerTrain);
        Instances test =  new Instances(readerTest);
        train.setClassIndex(0);
        test.setClassIndex(0);
        weka.classifiers.bayes.NaiveBayes cls = new weka.classifiers.bayes.NaiveBayes();
        cls.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(cls,test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", true));
        System.out.println(eval.toClassDetailsString());
        readerTrain.close();
        readerTest.close();
	}

}
