package test;

import java.io.*;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;
import acm.util.ErrorException;

public class Statistics extends ConsoleProgram{
	private int countScoreSize = 0;
	private double[] scores = new double[20];
	private double sum=0;
	private double mean;
	private double deviation;
	
	public void run(){

		
		scores = readScoresArray("Midterm.txt");
		for (int i=0;i<countScoreSize;i++){
			sum+=scores[i];
		}
		mean=sum/countScoreSize;
		double term=0;
		for (int i=0;i<countScoreSize;i++){
			term += (mean-scores[i])*(mean-scores[i]);
		}
		deviation = Math.sqrt(term/countScoreSize);
		println(mean);
		println(deviation);
		printHistogram();
	}
	
	public double[] readScoresArray(String filename){

		try{
		BufferedReader rd = new BufferedReader(new FileReader(filename));
		while (true){
			String line = rd.readLine();
			if (line==null) break;
			StringTokenizer tokenizer = new StringTokenizer(line," ,=");
			while(tokenizer.hasMoreTokens()){				
				scores[countScoreSize]=Integer.parseInt(tokenizer.nextToken());
				countScoreSize++;
			}
		}
		rd.close();
		} catch(IOException ex) {throw new ErrorException(ex);}
		return scores;
	}
	
	private void printHistogram(){
		int[] countStar = new int[11]; 
		for (int i=0;i<countScoreSize;i++){
			switch((int)scores[i]/10){
			case 0:countStar[0]++;break;
			case 1:countStar[1]++;break;
			case 2:countStar[2]++;break;
			case 3:countStar[3]++;break;
			case 4:countStar[4]++;break;
			case 5:countStar[5]++;break;
			case 6:countStar[6]++;break;
			case 7:countStar[7]++;break;
			case 8:countStar[8]++;break;
			case 9:countStar[9]++;break;
			case 10:countStar[10]++;break;
			}
		}
		print("0-9 :");
		printStar(countStar[0]);
		print("10-19 :");
		printStar(countStar[1]);
		print("20-29 :");
		printStar(countStar[2]);
		print("30-39 :");
		printStar(countStar[3]);
		print("40-49 :");
		printStar(countStar[4]);
		print("50-59 :");
		printStar(countStar[5]);
		print("60-69 :");
		printStar(countStar[6]);
		print("70-79 :");
		printStar(countStar[7]);
		print("80-89 :");
		printStar(countStar[8]);
		print("90-99 :");
		printStar(countStar[9]);
		print("100 :");
		printStar(countStar[10]);
	}
	private void printStar(int n){
		for (int i=0;i<n;i++){
			print("*");
		}
		println();
	}
}
