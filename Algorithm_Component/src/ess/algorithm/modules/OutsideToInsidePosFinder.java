package ess.algorithm.modules;

import ess.data.Composite;
import ess.data.Position;

public class OutsideToInsidePosFinder implements IPositionFinder {

	@Override
	public Position findNextFreePosition(Composite composite, IRuleChecker ruleChecker) {
		
		int rows = composite.getSurface().getRows();
		int cols = composite.getSurface().getCols();
		for (int k = 0; k < rows/2; k++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(k + "," + j + "; ");
			}
			
			for (int j = 0; j < rows; j++) {
				System.out.println((rows-k) + ", " + j);
			}
		}
		for (int l = 0; l < cols/2; l++) {
			for (int i = 0; i < rows; i++) {
				System.out.println(i + "," + l + "; ");
			}
			for (int i = 0; i < rows; i++) {
				System.out.println(i + ", " + (cols-l) + "; ");
			}
		}
		
		// 0, i   -> 0,0; 0,1; 0,2; 0,3; 0,4; 0,5; 0,6; 0,7; 
		// j, 0   -> 0,0; 1,0; 2,0; 3,0; 4,0; 5,0; 6,0; 7,0;
		// n-1, i -> 7,0; 7,1; 7,2; 7,3; 7,4; 7,5; 7,6; 7,7; 
		// j, n-1 -> 0,7; 1,7; 2,7; 3,7; 4,7; 5,7; 6,7; 7,7;
		
		// 1, i
		// j, 1
		// n-2, i
		// j, n-2
		
		// ...
		
		// n/2, i
		// j, n/2
		// n-n/2, i
		// j, n-n/2
		
		return null;
		
	}
	
	public static void main(String[] args) {
		int rows = 8;
		int cols = 10;
		for (int k = 0; k < rows/2; k++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(k + "," + j + "; ");
			}
			System.out.println();
			for (int j = 0; j < cols; j++) {
				System.out.print((rows-k-1) + "," + j + "; ");
			}
			System.out.println();
		}
		System.out.println("***************************");
		for (int l = 0; l < cols/2; l++) {
			for (int i = 0; i < rows; i++) {
				System.out.print(i + "," + l + "; ");
			}
			System.out.println();
			for (int i = 0; i < rows; i++) {
				System.out.print(i + "," + (cols-l-1) + "; ");
			}
			System.out.println();
		}
	}

}
