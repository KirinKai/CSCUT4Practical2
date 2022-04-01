import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) throws FileNotFoundException {
        
    	String inputFileName = "";
    	String outputFileName = "";
    	Scanner in = new Scanner(System.in);
    	boolean allUp = false;
    	File inputFile;
    	File outputFile;
    	
    	//receive user input for input file name
    	do {
    		System.out.println("supply filename for input:");
    		inputFileName = in.nextLine();
    		inputFile = new File(inputFileName) ;
    		if(inputFile.exists() ==false || inputFile.isDirectory() == true) { 
    			System.out.println("Please provide a valid input file name.");
    		} else {
    			break;
    		}
    	}while(true);
    	
    	System.out.println("input file selected: " + inputFileName);
    	
    	//receive user input for the output file name
    	
    	do {
    		System.out.println("supply filename for output:");
    	
    		outputFileName = in.nextLine();
    		outputFile = new File(outputFileName);
    		if(outputFile.exists() ==true && outputFile.isDirectory() == true) { 
    			System.out.println("Please provide a valid output file name: A directory with same name exist");
    		} else {
    			break;
    		}
    	
    	}while(true);
    	
    	PrintWriter outputWriter = new PrintWriter (outputFile);
    	System.out.println("output file selected: " + outputFileName);
    	
    	
    	do{
    		System.out.println("use the -u argument? (y/n):");
    		String input = in.nextLine();
    		if(input.equalsIgnoreCase("y")) {
    			allUp = true;
    			break;
    		} else if (input.equalsIgnoreCase("n")) {
    			allUp = false;
    			break;
    		}
    	}while(true);
    	
    	//close the user input scanner, and create a scanner for the inputfile
    	in.close();
    	Scanner inFile = new Scanner(inputFile);
    	
    	//create the output file
    	try {
			outputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
    	
    	
    	while(inFile.hasNextLine()) {
    		String line = inFile.nextLine();
    		String[] lineArr = line.split(" ");
    		int dateIndex = (lineArr.length - 1);
    		String newLine = "";
    		String word = "";
    		
    		//format each non-date segment of the line
    		for (int i = 0; i < dateIndex; i++) {
    			//sets the first character to uppercase
    			if(allUp == true) {
    				word = lineArr[i].toUpperCase();
    			} else {
    				String cap = lineArr[i].substring(0,1).toUpperCase();
    				word = cap + lineArr[i].substring(1);
    			}
    			
    			
    			//if the newly formated segment is a middle initial, formats it correctly
    			if(word.length() == 1) {
    				newLine = newLine + word + ". ";
    			} else {
    			newLine = newLine + word + " ";
    			}
    		}
    		
    		//format the date correctly
    		String day = lineArr[dateIndex].substring(0,2);
    		String month = lineArr[dateIndex].substring(2,4);
    		String year = lineArr[dateIndex].substring(4);
    		String finalDate = (day + "/" + month + "/" + year);
    		
    		//create the line to be added to the output file
    		newLine = (newLine + finalDate);
    		newLine = newLine.trim();
    		
    		System.out.println(newLine);
    		
    		//write to the output file
    		if(inFile.hasNextLine()) {
    			outputWriter.println(newLine);
    		} else {
    			outputWriter.print(newLine);
    		}
    		
    		
    	}
    	//close the scanner and writer
    	inFile.close();
    	outputWriter.close();
    	
    } // main

} // FilesInOut
