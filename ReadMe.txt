####################################
Includes:
####################################
1)	Preprocessing/GenerateFeatureSet.java		-	This java file can be used to pre process the data and generate new feature set.
2)	Preprocessing/CSVTOARFF.java		 				-	This java file can be used to convert the files from csv to arff format.
3)	Preprocessing/AttributeDifferences.java -	This java file can be used to form a string of values that are present in test but not in train. The results should be appended to the corresponding attribute list in the arff file to make the test and train data set compatible for prediction.
4)	Preprocessing/FilterRows.java	 	 				-	This java file can be used to filter a given number of unique rows for each category in the dataset.
5)  Preprocessing/CostMatrix.java           - This java file can be used to generate a cost matrix that can be given as input for the cost based classifiers. The logic for the cost should be changed accordingly.
6)	Algorithms/NaiveBayes.java		 					-	This java file can be used to test the model accuracy for the train data in Navie Bayes classifier
7)	Algorithms/J48Classifier.java		 				-	This java file can be used to test the model accuracy for the train data in J48 classifier
8)	Algorithms/RandomForestClassifier.java  -	This java file can be used to predict the crime categories using the Random forest classifier. It also requires an input of no of trees.
9)	OutputFormatting/OutputMatrix.java			-	This java file can be used to convert the prediction output from classifiers to Kaggle submission format.
10) Results																	- This folder contains the model results obtained for different classifiers
11) Intermediate_Results										- This folder contains the intermediate results that are used for processing the arff files and cost matrices.
12) DataSetKaggle														- This folder contains the train and test datasets that are downloaded from Kaggle.

####################################
Compilation:
####################################
1)	Go to the respective folder of the java file
2)	Execute the below command to compile the java files numbered 1,3,4,5,9 in the Includes section.
	 	javac <file_name.java>
3)	Execute the below command to compile the java files numbered 2,6,7,8 in the Includes section. You need to have weka-3.7.0.jar file in the folder for compilation
	 	javac -cp weka-3.7.0.jar:. <file_name.java>

####################################
Execution:
####################################
1)	Execute the below command to run the java files numbered 1,3,4,5,9 in the Includes section.
	 	java <file_name>
2)	Execute the below command to run the java files numbered 2,6,7,8 in the Includes section. You need to have weka-3.7.0.jar file in the folder for running the binaries.
	 	java -Xmx100g -cp weka-3.7.0.jar:. <file_name>
	 	
####################################
Commands:
####################################
1) Use the cut command to filter the required columns in a csv file which can be later required to generate a arff.
	 E.g. cut -d',' -f1,8,9,11 test_generated_newcolumns.csv >test_generated_newcolumn_only4col.csv
####################################
Example Invocations:
####################################
1) [kodalis@tesla ~]$ javac GenerateFeatureSet.java
2) [kodalis@tesla ~]$ java -Xmx50g GenerateFeatureSet train.csv test.csv
3) [kodalis@tesla ~]$ javac -cp weka-3.7.0.jar:. RandomForestClassifier.java
4) [kodalis@tesla ~]$ java -Xmx100g -cp weka-3.7.0.jar:. RandomForestClassifier train_generated_newcolumn_only4col.arff test_generated_newcolumn_only4col.arff 10 > RandomForest10T_Predictions.txt
5) [kodalis@tesla ~]$ java -Xmx100g -cp weka-3.7.0.jar:. J48Classifier train_standard_generated_newcolumns_only_4.arff test_standard_generated_newcolumns_only_4.arff >J48_numerical_predictions.txt
6) [kodalis@tesla ~]$ java -cp weka.jar:. CSVTOARFF test_generated_newcolumn_only4col.csv test_generated_newcolumn_only4col.arff
7) [kodalis@tesla ~]$ java -Xmx50g OutputMatrix J48_numerical_predictions.txt J48_numerical_predictions_kaggle_matrix.csv
####################################
End of Read me
####################################