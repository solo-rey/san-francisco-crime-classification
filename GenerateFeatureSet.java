import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateFeatureSet {

	public static void main(String[] args) throws IOException {
		//Access train.csv
		Scanner scTrain=new Scanner(new File(args[0]));
		String outputTrain=args[0].substring(0,args[0].indexOf('.'))+"_generated_newcolumns.csv";
		File fileTrain=new File(outputTrain);
		FileWriter fwTrain = new FileWriter(fileTrain.getAbsoluteFile());
		BufferedWriter bwTrain = new BufferedWriter(fwTrain);

		//Access test.csv
		Scanner scTest=new Scanner(new File(args[1]));
		String outputTest=args[1].substring(0,args[1].indexOf('.'))+"_generated_newcolumns.csv";
		File fileTest=new File(outputTest);
		FileWriter fwTest = new FileWriter(fileTest.getAbsoluteFile());
		BufferedWriter bwTest = new BufferedWriter(fwTest);

		String dateRegularExpression="(\\d{4})-(\\d{2})-(\\d{2})";
		String timeRegularExpression="(\\d{2}):(\\d{2}):(\\d{2})";
		Pattern pd = Pattern.compile(dateRegularExpression);
		Pattern pt = Pattern.compile(timeRegularExpression);

		//Read train.csv line by line
		String actualHeader;
		if(scTrain.hasNextLine()){
			actualHeader=scTrain.nextLine();//header line
			String header="Category,Dates,DayOfWeek,PdDistrict,Address,X,Y,Category_Int,Dates_Int,DayOfWeek_Int,PdDistrict_Int,Address_Int,Year,Month,Day,Season,Hour,Minute,DayDivision,AmPm,X-modified,Y-modified\n";
			bwTrain.write(header);
		}
		String lineTrain,lineTest;
		HashMap<String, Integer>[] map = new HashMap[9];
		int columnCounters[]=new int[9];//Dates,Category,Descript,DayOfWeek,PdDistrict,Resolution,Address,X,Y

		map[0]=new HashMap();
		map[1]=new HashMap();
		map[3]=new HashMap();
		map[4]=new HashMap();
		map[6]=new HashMap();
		

		Matcher md;
		Matcher mt;
		int year = 0,month = 0,day = 0,hour = 0,minute = 0;
		String season=null,dayDivision=null,ampm = null,x_modify = null,y_modify=null;
		String dateString=null,categoryString = null,dayOfWeekStr=null,pdDistString=null,addressString=null,X = null,Y=null;
		while(scTrain.hasNextLine()){
			lineTrain=scTrain.nextLine();
			String replacePattern="\".*?,.*?\"";
			lineTrain=lineTrain.replaceAll(replacePattern, "Removed");
			String splitLine[]=lineTrain.split(",");
			int splitLen=splitLine.length;
			int ColumnInteger[]=new int[9];
			for(int i=0;i<splitLen;i++){
				if(i==2||i==5)
					continue;
				String split=splitLine[i];
				if(i<=6){
					//Put the unique values in the hashmap with a unique integer
					if(map[i].containsKey(split)){
						ColumnInteger[i]=map[i].get(split);
					}else{
						ColumnInteger[i]=columnCounters[i];
						map[i].put(split, ColumnInteger[i]);
						columnCounters[i]+=1;
					}
				}

				//Now fetch date
				//Generate additional columns from date
				switch (i){
				case 0://date
					dateString=split;
					md = pd.matcher(split);
					mt = pt.matcher(split);
					if(md.find() && mt.find()){
						year=Integer.parseInt(md.group(1));
						month=Integer.parseInt(md.group(2));
						day=Integer.parseInt(md.group(3));
						hour=Integer.parseInt(mt.group(1));
						minute=Integer.parseInt(mt.group(2));
					}

					if(month==12||(month<=2))
					{
						season="Winter";
					}else if(month>=3 && month<=5){
						season="Spring";
					}else if(month>=6 && month<=8){
						season="Summer";
					}else{
						season="Fall";
					}

					if(hour>=12 && hour<=18)
					{
						ampm="PM";
						dayDivision="A";
					}else if(hour>18 && hour<=24)
					{
						ampm="PM";
						dayDivision="E";
					}else if(hour==24 || hour<=6)
					{
						ampm="AM";
						dayDivision="N";
					}else
					{
						ampm="AM";
						dayDivision="M";
					}
					break;
				case 1://Category
					categoryString=split;
					break;
				case 3:
					dayOfWeekStr=split;
					break;
				case 4:
					pdDistString=split;
					break;
				case 6:
					addressString=split;
					break;
				case 7:
					X=split;
					x_modify=String.format("%.2f", Float.parseFloat(X));
					break;
				case 8:
					Y=split;
					y_modify=String.format("%.2f", Float.parseFloat(Y));
					break;
				}	
			}//for loop each split in line
			String formLine=categoryString+","+dateString+","+dayOfWeekStr+","+pdDistString+","+addressString+","+X+","+Y+","+ColumnInteger[1]+","+ColumnInteger[0]+","+ColumnInteger[3]+","+ColumnInteger[4]+","+ColumnInteger[6]+","+year+","+month+","+day+","+season+","+hour+","+minute+","+dayDivision+","+ampm+","+x_modify+","+y_modify+"\n";
			bwTrain.write(formLine);
		}//while loop each line

		bwTrain.close();
		fwTrain.close();
		scTrain.close();
		
		String testHeader;
		if(scTest.hasNextLine()){
			testHeader=scTest.nextLine();//header line
			String header="Category,Dates,DayOfWeek,PdDistrict,Address,X,Y,Dates_Int,DayOfWeek_Int,PdDistrict_Int,Address_Int,Year,Month,Day,Season,Hour,Minute,DayDivision,AmPm,X-modified,Y-modified\n";
			bwTest.write(header);
		}
		

		Random rm=new Random();
		while(scTest.hasNextLine()){
			lineTest=scTest.nextLine();
			String splitLine[]=lineTest.split(",");
			int splitLen=splitLine.length;
			int ColumnInteger[]=new int[9];
			int k=0;
			for(int i=0;i<splitLen;i++){
				if(i==0)
					continue;
				if(i==1)
					k=0;
				else if(i==2)
					k=3;
				else if(i==3)
					k=4;
				else if(i==4)
					k=6;
				String split=splitLine[i];
				if(i<=4){
					//Put the unique values in the hashmap with a unique integer
					if(map[k].containsKey(split)){
						ColumnInteger[k]=map[k].get(split);
					}else{
						int len=map[k].size();
						ColumnInteger[k]=rm.nextInt(len);//columnCounters[k];
						//map[k].put(split, ColumnInteger[k]);
						//columnCounters[k]+=1;
					}
				}

				//Now fetch date
				//Generate additional columns from date
				switch (i){
				case 1://date
					dateString=split;
					md = pd.matcher(split);
					mt = pt.matcher(split);
					if(md.find() && mt.find()){
						year=Integer.parseInt(md.group(1));
						month=Integer.parseInt(md.group(2));
						day=Integer.parseInt(md.group(3));
						hour=Integer.parseInt(mt.group(1));
						minute=Integer.parseInt(mt.group(2));
					}

					if(month==12||(month<=2))
					{
						season="Winter";
					}else if(month>=3 && month<=5){
						season="Spring";
					}else if(month>=6 && month<=8){
						season="Summer";
					}else{
						season="Fall";
					}

					if(hour>=12 && hour<=18)
					{
						ampm="PM";
						dayDivision="A";
					}else if(hour>18 && hour<=24)
					{
						ampm="PM";
						dayDivision="E";
					}else if(hour==24 || hour<=6)
					{
						ampm="AM";
						dayDivision="N";
					}else
					{
						ampm="AM";
						dayDivision="M";
					}
					break;
				case 2:
					dayOfWeekStr=split;
					break;
				case 3:
					pdDistString=split;
					break;
				case 4:
					addressString=split;
					break;
				case 5:
					X=split;
					x_modify=String.format("%.2f", Float.parseFloat(X));
					break;
				case 6:
					Y=split;
					y_modify=String.format("%.2f", Float.parseFloat(Y));
					break;
				}	
			}//for loop each split in line
			String formLine="?"+","+dateString+","+dayOfWeekStr+","+pdDistString+","+addressString+","+X+","+Y+","+ColumnInteger[0]+","+ColumnInteger[3]+","+ColumnInteger[4]+","+ColumnInteger[6]+","+year+","+month+","+day+","+season+","+hour+","+minute+","+dayDivision+","+ampm+","+x_modify+","+y_modify+"\n";
			bwTest.write(formLine);
		}//while loop each line
		
		
		
		
		
		
		bwTest.close();
		fwTest.close();
		scTest.close();
		System.out.println("Generated files: "+outputTrain+", "+outputTest+"\n");
	}

}
