package HW1;
import java.util.Scanner;
import java.io.File;

public class LLMainClass {	
	public static SparseVec ParseVector(String file_name){
	    Scanner sc = null;
	    String tmps;
	    SparseVec V = null;
	    try {
	        sc = new Scanner(new File(file_name));
		    while (sc.hasNext()) {
		    	tmps = sc.next();
		    	if(tmps.equals("VECTOR")){
		    		// initialize the matrix
		    		int len = sc.nextInt();
		    		V = new LLSparseVec(len);	
		    	}else if(tmps.equals("END")){
		    		// finished, return the matrix
		    		sc.close();
		    		return V;
		    	}else if(tmps.equals("SET")){
		    		// set an element
		    		int idx = sc.nextInt(); // index
		    		int val = sc.nextInt(); // value
		    		V.setElement(idx, val);
		    	}else if(tmps.equals("CLEAR")){
		    		// clear an element
		    		int idx = sc.nextInt(); // index
		    		V.clearElement(idx);	
		    	}		    	
		    }
		    sc.close();
		    return V;
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	
	public static void main(String[] args) {
		try{
			int n = args.length;
			if(n < 2){
				System.out.println("java LLMainClass VEC File1 A/S/M File2 ...");
				return;
			}
			String fname;
			String operator;
			String dtype = args[0];	// matrix or vector
			if(dtype.equals("VEC")){
				fname = args[1];
				SparseVec V = ParseVector(fname);	// read V from the first file
				
				if(V == null){	
					// if invalid input file, print NULL and exit
					System.out.println("NULL: Illegal Input File "+fname);
					return;
				}
				
				SparseVec tmpV;
				for(int i = 2; i < n; i+=2){
					operator = args[i];
					fname = args[i+1];
					tmpV = ParseVector(fname);	// read tmpM from the next file
					if(tmpV == null)			// if the file is invalid, skip
					{	System.out.println("NULL: Illegal Input File "+fname);
						return;
					}
					if(operator.equals("A"))
						V = V.addition(tmpV); 			// add tmpV to V
					else if(operator.equals("S"))
						V = V.substraction(tmpV); 		// substract tmpV from V
					else if(operator.equals("M"))
						V = V.multiplication(tmpV); 	// multiply tmpV to V
					else{
						System.out.println("NULL: Illegal operator: " + operator );
						return;
					}
					if(V == null)			// operation failed
					{	System.out.println("NULL: Operator "+operator+" Failed, Fname "+fname);
						return;
					}
				}
				// output the final matrix
				System.out.println("Final_Vector");
				int[] idx = V.getAllIndices();
				int[] val = V.getAllValues();
				int len, ne;
				len = V.getLength();	// length
				ne = V.numElements();	// number of elements
				System.out.println("LENGTH " + len + " NELEMS " + ne);
				for(int i = 0; i < ne; i++)
					System.out.println("IDX " + idx[i] + " VAL " + val[i]);
				System.out.println("END");			
			}
	    } catch (Exception e) {
	    	System.out.println("NULL: Something is wrong");
	        return;
	    }
	}
}
