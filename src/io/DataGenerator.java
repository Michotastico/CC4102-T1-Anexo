package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
	
	private int exponent;
	private BufferedWriter writer;
	private final double[] runs = {0.2, 0.5, 0.8};
	private final String PATH = "Data/data";
	private Random random;
	private final int SEED = 123456789;
	
	public DataGenerator(int exponent){
		this.exponent = exponent;
		this.random = new Random(SEED);
	}
	
	
	public void run(){
		int iter = (int) Math.pow(2, this.exponent);
		try{
		for(double d : runs)
			this.run(d, iter);
		}catch(IOException e){
			;
		}
		
	}
	
	private void run(double percentage, int iter) throws IOException{
		
		String path = PATH+Double.toString(percentage)+".txt";
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		writer = new BufferedWriter(new FileWriter(path));
		
		int per_iter = (int) (iter * percentage);
		String line = "";
		
		//Initial run
		for(int i = 0; i < per_iter; i++){
			line = this.toString(i);
			writer.write(line);
		}
		
		//Random
		int tmp;
		for(int i = per_iter; i < iter; i++){
			tmp = this.random.nextInt();
			line = this.toString(tmp);
			if(i == (iter - 1))
				line = line.replace(this.NEW_LINE, "");
			writer.write(line);
		}
		
		writer.close();
		
	}
	
	private final String NEW_LINE = System.getProperty("line.separator");
	
	private final int RADIX = 10; // 2 is binary.
	
	@SuppressWarnings("unused")
	private String toString(int number){ //Binary or decimal
		String ans = "";
		if(RADIX == 2)
			ans = Integer.toBinaryString(number);
		else
			ans = Integer.toString(number);
		ans = ans + NEW_LINE;
		
		return ans;
	}
}
