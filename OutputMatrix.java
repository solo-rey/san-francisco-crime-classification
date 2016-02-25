import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutputMatrix {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(args[0]));//"/Users/kodalisatyakrishna/Desktop/WEKA_INPUTS/RFOREST"));
		String Categories[]={"WARRANTS","OTHER OFFENSES","LARCENY/THEFT","VEHICLE THEFT","VANDALISM","NON-CRIMINAL","ROBBERY","ASSAULT","WEAPON LAWS","BURGLARY","SUSPICIOUS OCC","DRUNKENNESS","FORGERY/COUNTERFEITING","DRUG/NARCOTIC","STOLEN PROPERTY","SECONDARY CODES","TRESPASS","MISSING PERSON","FRAUD","KIDNAPPING","RUNAWAY","DRIVING UNDER THE INFLUENCE","SEX OFFENSES FORCIBLE","PROSTITUTION","DISORDERLY CONDUCT","ARSON","FAMILY OFFENSES","LIQUOR LAWS","BRIBERY","EMBEZZLEMENT","SUICIDE","LOITERING","SEX OFFENSES NON FORCIBLE","EXTORTION","GAMBLING","BAD CHECKS","TREA","RECOVERED VEHICLE","PORNOGRAPHY/OBSCENE MAT"};
		int matrix[][]=new int[884262][39];
		String start;
		while(sc.hasNextLine()){
			start=sc.nextLine();
			if(start.matches("Weighted Avg(.*)")){
				System.out.println("Starting line found: "+start);
				break;
			}
		}
		sc.nextLine();//skipping one empty line
		String end;
		while(sc.hasNextLine()){
			end=sc.nextLine();
			if(end.matches("")){
				System.out.println("Last line found");
				break;
			}else{
				end=end.trim();
				String instString=end.substring(0,end.indexOf(" ")).trim();
				String cateString=end.substring(end.indexOf("?")+1,end.indexOf(":",end.indexOf(":")+1)).trim();
				int instance=Integer.parseInt(instString)-1;
				int category=Integer.parseInt(cateString)-1;
				matrix[instance][category]=1;
			}
		}
		File file=new File(args[1]);//"/Users/kodalisatyakrishna/Desktop/WEKA_INPUTS/RFOREST_Submission.csv");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		bw.write("Id,");
		for(int j=0;j<39;j++){
			bw.write(Categories[j]);
			if(j<38)
			bw.write(",");
			}
		bw.write("\n");
		
		for(int i=0;i<884262;i++){
			bw.write(i+"");
			bw.write(",");
			for(int j=0;j<39;j++){
				bw.write(matrix[i][j]+"");
				if(j<38)
				bw.write(",");
				}
			bw.write("\n");
		}
		bw.close();
		sc.close();
	}

}
