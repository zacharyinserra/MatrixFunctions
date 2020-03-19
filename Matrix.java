//Matrix class, contains methods for transposing, adding, multiplying, and printing matrices

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Matrix {
	
	public double [][] data;
	public int columns; //first []
	public int rows; // second []
	
	public Matrix(double [][] data) {
		this.data = data;
		this.rows = data.length;
		this.columns = data[0].length;		
	}
	
	public Matrix(String file) { //takes input of file and creates matrix, overrides constructor
		int j = 0;
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) { // reads through for size variables
			String line;
			while ((line = br.readLine()) != null) {
				String[] stuff = line.trim().split(" ");
				for (i = 0; i < stuff.length; i++) {
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();		
		}	
		double [][] newList = new double [j][i]; // sets new 2d array size
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			j = 0;
			while ((line = br.readLine()) != null) { // iterates through file
				String[] stuff = line.trim().split(" ");
				for (i = 0; i < stuff.length; i++) { //iterates through values in each line, adds to new 2D array
					double val = Double.parseDouble(stuff[i]);
					newList[j][i] = val;
				}
				j++;
			this.data = newList;
			this.rows = newList.length;
			this.columns = newList[0].length;
			}
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
	
	public String toString() { // iterate through data, print each row in matrix
		String str = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				str += this.data[i][j] + " ";
			}
			str += "\n";
		}
		return (rows+"x"+columns + " matrix" + "\n" + str);
	}
	
	public boolean equals(Matrix m) { // checks if two matrices are equal, same values in same places
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (m.data[i][j] != this.data[i][j]) {
					return false;
				}
				else {
				}
			}
		}
		return true;
	}
	
	public Matrix transpose() { //transposes matrix, returns new matrix
	    double [][] newList = new double [this.columns][this.rows]; // set 2d array size
		for (int i = 0; i < this.data.length; i++) {			
			for (int j = 0; j < this.data[i].length; j++) {
				double val = data[i][j];
				newList[j][i] = val;
			}
		}
		return new Matrix(newList); 
	}
	
	public Matrix add(Matrix m) {
		double [][] newList = new double [this.rows][this.columns]; // adds two matrices if their dimensions are equal
		if (this.rows == m.rows && this.columns == m.columns) { // check dimensions
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					double newVal = this.data[i][j] + m.data[i][j];
					newList[i][j] = newVal;
				}
			}
			return new Matrix(newList);
		}
		else {
			System.out.println("Dimensions do not match"); //if dimensions do not match
			return null;
		}
	}
	
	public Matrix mult(double val) { // multiplies all values in matrix by set value
		double [][] newList = new double [this.rows][this.columns];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[i].length; j++) {
				double newVal = this.data[i][j];
				double finVal = val * newVal;
				newList[i][j] = finVal;
			}
		}
		return new Matrix(newList);
	}
	
	public Matrix mult(Matrix m) { //multiplies two matrices
		double [][] newList = new double [this.data.length][m.data[0].length];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < m.data[0].length; j++) {
				for (int k = 0; k < this.data[0].length; k++) {
					newList[i][j] += this.data[i][k] * m.data[k][j];
				}					 
			}
		}
		return new Matrix(newList);
	}

}
