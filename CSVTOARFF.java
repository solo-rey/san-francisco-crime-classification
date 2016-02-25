import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
import java.io.File;
 
public class CSVTOARFF {
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("\nUsage: CSVTOARFF <train.csv> <train.arff>\n");
      System.exit(1);
    }
    CSVLoader loader = new CSVLoader();
    loader.setSource(new File(args[0]));
    Instances data = loader.getDataSet();
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    saver.setFile(new File(args[1]));
    saver.setDestination(new File(args[1]));
    saver.writeBatch();
  }
}