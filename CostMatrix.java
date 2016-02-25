import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class CostMatrix {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(args[0]));
		File file=new File(args[1]);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String line;
		String linePattern="(\\d{4})-(\\d{2})-(\\d{.*})";
		HashMap hm = new HashMap();
		String Categories[]={"WARRANTS","OTHER OFFENSES","LARCENY/THEFT","VEHICLE THEFT","VANDALISM","NON-CRIMINAL","ROBBERY","ASSAULT","WEAPON LAWS","BURGLARY","SUSPICIOUS OCC","DRUNKENNESS","FORGERY/COUNTERFEITING","DRUG/NARCOTIC","STOLEN PROPERTY","SECONDARY CODES","TRESPASS","MISSING PERSON","FRAUD","KIDNAPPING","RUNAWAY","DRIVING UNDER THE INFLUENCE","SEX OFFENSES FORCIBLE","PROSTITUTION","DISORDERLY CONDUCT","ARSON","FAMILY OFFENSES","LIQUOR LAWS","BRIBERY","EMBEZZLEMENT","SUICIDE","LOITERING","SEX OFFENSES NON FORCIBLE","EXTORTION","GAMBLING","BAD CHECKS","TREA","RECOVERED VEHICLE","PORNOGRAPHY/OBSCENE MAT"}; 
		for(int i=0;i<39;i++){
			hm.put(Categories[i],0);
		}

		while(sc.hasNextLine()){
			line=sc.nextLine();
			String[] lineSplit=line.split("\t");
			int instanceCount=Integer.parseInt(lineSplit[2]);
			hm.put(lineSplit[1],instanceCount);
		}

		/*
		% Rows	Columns
		3	3
		% Matrix elements
		 */

		String headerLines="% Rows\tColumns\n39\t39\n% Matrix\telements\n";
		bw.write(headerLines);

		String actual,predicted;
		int cost;
		for(int i=0;i<39;i++){
			actual=Categories[i];
			String buffer="";
			for(int j=0;j<39;j++){
				predicted=Categories[j];
				if(i!=j){
					int actualCost=(Integer)hm.get(actual);
					int predictedCost=(Integer)hm.get(predicted);
					cost=(predictedCost/actualCost)/100;
				}else{
					cost=0;
				}
				buffer+=cost+" ";
			}
			buffer=buffer.trim()+"\n";
			bw.write(buffer);
		}
		bw.close();

	}

}
