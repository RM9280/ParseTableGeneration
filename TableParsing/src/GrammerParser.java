//Rebecca Mantione

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GrammerParser{
	public static void main(String[] args) {
		
		ArrayList<String> text = readFile("problem2.txt");
		System.out.println(text);

		String[][] first = { { "id" }, { "id", "(", "int" }, { "+", "epsilon" }, { "id", "(", "int" }, { "*", "epsilon" },
				{ "(", "int", "id" } };
		String[][] follow = { { "$" }, { ")",";", "$" }, { ")", ";", "$" }, { "+", ")", ";", "$" }, { "+", ")", ";", "$" },
				{ "+",")", ";", "$" } };

		String[] left = { 		"P", 		"E", 			"E'", 			 "E'", 			"T", 		   "T'", 		  "T'", 		  "F", 			"F",       "F" };
		String[][] right = { { "id", "=", "E",  ";"}, { "T", "E'" }, { "+", "T", "E'" }, { "epsilon" }, {"F", "T'"}, {"*", "F", "T'"}, {"epsilon"}, {"(", "E", ")"}, { "int" }, {"id" }};

		ArrayList<String> nonterm = new ArrayList<String>();
		nonterm.add("P");
		nonterm.add("E");
		nonterm.add("E'");
		nonterm.add("T");
		nonterm.add("T'");
		nonterm.add("F");
		ArrayList<String> term = new ArrayList<String>();
		term.add("int");
		term.add("id");
		term.add("=");
		term.add("+");
		term.add("*");
		term.add("(");
		term.add(")");
		term.add(";");
		term.add("$");
		int[][] parseTable = new int[6][9];

		String[] f;
		int index;

		for (int rule = 0; rule < left.length; rule++) {
			String alpha = right[rule][0];

			System.out.println("alpha:" + alpha);

			index = nonterm.indexOf(alpha);

			if (index == -1) {
				f = new String[1];
				f[0] = alpha;
			} else {
				f = first[nonterm.indexOf(alpha)];
			}

			for (String t : f) {

				if (t == "epsilon") {
					
					for(int i =0; i<follow[nonterm.indexOf(left[rule])].length; i++) {
						int row = nonterm.indexOf(left[rule]);
						System.out.println("row2:" + row);
						int col = term.indexOf(follow[nonterm.indexOf(left[rule])][i]);
						parseTable[row][col] = rule + 1;
					}
					
				} else {
					int row = nonterm.indexOf(left[rule]);
					System.out.println("row:"+ row);
					int col = term.indexOf(t);
					System.out.println("Col: " + col);
					System.out.println("rule:" + rule);
					parseTable[row][col] = rule + 1;
					
				}
			}
		}

		for(int k=0; k<term.size(); k++) {
			System.out.print(term.get(k)+"\t");
		}
		for (int i = 0; i < parseTable.length; i++) {
				System.out.println(" ");
			for (int j = 0; j < parseTable[0].length; j++) {
				System.out.print(parseTable[i][j]+"\t");
				
			}
		}
		
		Stack<String> stack = new Stack<String>();
		stack.push("$");
		stack.push("P");

		// Main loop
		while (!stack.empty()) {
			System.out.print("Stack: " + stack + ",");
			String X = stack.peek();
			String c = text.get(0);
			System.out.println( " Input: " + text);
			if(X.equals(c)) {
				stack.pop();
				text.remove(0);
			}
			else {
				
				stack.pop();
				int R = parseTable[nonterm.indexOf(X)][term.indexOf(c)];
				System.out.println("Apply #" + R);
				String[] alpha = right[R - 1];
				for (int i = alpha.length - 1; i >= 0; i--) {
					if (!alpha[i].equals("epsilon"))
						stack.push(alpha[i]);
				}
			}
		}
	}
	
	public static ArrayList<String> readFile(String filename) {
		ArrayList<String> read = new ArrayList<String>();
		Scanner scan;
		try {
			scan = new Scanner(new File(filename));
			String content;
			while (scan.hasNext()) {
				content=scan.next();
				System.out.println("content" + content);
				System.out.println("read" + content);
				if(content.matches("([0-9][0-9]*)")) {
					read.add("int");
					System.out.println("Int");
				}else if(content.matches("[a-z]*")) {
					read.add("id");
					System.out.println(")");
				}else if(content.matches("\\=")) {
					read.add("=");
					System.out.println(")");
				}
				else if(content.matches("\\*")) {
					read.add("*");
					System.out.println("*");
				}
				else if(content.matches("\\+")) {
					read.add("+");
					System.out.println("+");
				}
				else if(content.matches("\\(")) {
					read.add("(");
					System.out.println("(");
				}
				else if(content.matches("\\)")) {
					read.add(")");
					System.out.println(")");
				}else if(content.matches("\\$")) {
					read.add("$");
					System.out.println(")");
				}else if(content.matches("\\;")) {
					read.add(";");
					System.out.println(")");
				}else {
					System.out.println("invalid");
				}
				System.out.println("content2" + content);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(read+"read");
		return read;
	}

}
