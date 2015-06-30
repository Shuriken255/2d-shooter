package ua.kiev.shuriken.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileWorker {
	
	public static String loadStringFromFile(String path){
		Scanner scan = null;
		try{
			try {
				scan = new Scanner(new File(path));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: Can't find file '" + path + "'");
				return null;
			}
			return scan.nextLine();
		} finally {
			scan.close();
		}
	}
	
	public static String[] loadMultilineFromFile(String path){
		Scanner scan = null;
		try{
			try {
				scan = new Scanner(new File(path));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: Can't find file '" + path + "'");
				return null;
			}
			String lines[] = new String[0];
			while(scan.hasNext()){
				lines = Arrays.copyOf(lines, lines.length+1);
				lines[lines.length-1] = scan.nextLine();
			}
			return lines;
		} finally {
			scan.close();
		}
	}
}
