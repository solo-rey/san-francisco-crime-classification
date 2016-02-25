import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class AttributeDifferences {

	public static void main(String[] args) throws IOException {
		Scanner scTrain=new Scanner(new File(args[0]));
		Scanner scTest=new Scanner(new File(args[1]));
		String attributeFilter="attribute "+args[2];
		HashMap hm = new HashMap();
		String line=null;
		
		while(scTrain.hasNextLine()){
			line=scTrain.nextLine();
			if(line.indexOf(attributeFilter)!=-1){
				line=line.substring(line.indexOf("{")+1, line.indexOf("}"));
				break;
			}
		}
		
		for(String str: line.split(",")){
			hm.put(str.replaceAll("'", ""), 0);
		}
		
		while(scTest.hasNextLine()){
			line=scTest.nextLine();
			if(line.indexOf(attributeFilter)!=-1){
				line=line.substring(line.indexOf("{")+1, line.indexOf("}"));
				break;
			}
		}
		
		for(String str: line.split(",")){
			if(!hm.containsKey(str.replaceAll("'", ""))){
				String address=str.replaceAll("'", "");
				System.out.print(","+"'"+address+"'");
			}
		}
		
		scTrain.close();
		scTest.close();

	}
	

}
