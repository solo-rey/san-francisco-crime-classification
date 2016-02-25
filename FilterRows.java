import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class FilterRows {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(args[0]));
		File file=new File(args[1]);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String line;
		int averageLines=Integer.parseInt(args[2]);
		
		HashMap hm = new HashMap();
		String Categories[]={"WARRANTS","OTHER OFFENSES","LARCENY/THEFT","VEHICLE THEFT","VANDALISM","NON-CRIMINAL","ROBBERY","ASSAULT","WEAPON LAWS","BURGLARY","SUSPICIOUS OCC","DRUNKENNESS","FORGERY/COUNTERFEITING","DRUG/NARCOTIC","STOLEN PROPERTY","SECONDARY CODES","TRESPASS","MISSING PERSON","FRAUD","KIDNAPPING","RUNAWAY","DRIVING UNDER THE INFLUENCE","SEX OFFENSES FORCIBLE","PROSTITUTION","DISORDERLY CONDUCT","ARSON","FAMILY OFFENSES","LIQUOR LAWS","BRIBERY","EMBEZZLEMENT","SUICIDE","LOITERING","SEX OFFENSES NON FORCIBLE","EXTORTION","GAMBLING","BAD CHECKS","TREA","RECOVERED VEHICLE","PORNOGRAPHY/OBSCENE MAT"}; 
		for(int i=0;i<39;i++){
			hm.put(Categories[i],0);
		}
		
		if(sc.hasNextLine()){
			bw.write(sc.nextLine()+"\n");
		}
		
		while(sc.hasNextLine()){
			line=sc.nextLine();
			String category=line.substring(line.indexOf(',')+1,line.indexOf(',',line.indexOf(',')+1));
			int counter=(Integer) hm.get(category);
			if(counter<=averageLines){
				hm.put(category,counter+1);
				bw.write(line+"\n");
			}
			
		}
		
		bw.close();

	}

}
