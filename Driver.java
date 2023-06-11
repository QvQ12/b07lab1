import java.io.IOException;
import java.io.File;

public class Driver {
	
	
	public static void main(String [] args) {	 
		// modified part:
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int [] e1= {0,3};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {-2,-9};
		int []e2= {1,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
     
     	// My test part:
		Polynomial pnull = new Polynomial();
		pnull.prnpo();
		
		double[] c132= {1,3,2};
		int[] e102= {1,0,2};
		Polynomial p3=new Polynomial(c132,e102);
		System.out.print("p3 is: ");
		p3.prnpo();
		
		double[] c_212= {-2,1,2};
		int[] e234= {2,3,4};
		Polynomial p4=new Polynomial(c_212,e234);
		System.out.print("p4 is: ");
		p4.prnpo();
		
		double[] c142_1= {1,4,2,-1};
		int[] e2743= {2,7,4,3};
		Polynomial p5=new Polynomial(c142_1,e2743);     
		System.out.print("p5 is: ");
		p5.prnpo();
		
		double[] c_1_4_21= {-1,-4,-2,1};
		Polynomial p6=new Polynomial(c_1_4_21,e2743);     
		System.out.print("p6 is: ");
		p6.prnpo();
     
		p=p3.add(p4);
		System.out.print("p3 + p4 is: ");
		p.prnpo();
		p=p4.add(pnull);
		System.out.print("p4 + 0 is: ");
		p.prnpo();
		p=pnull.add(p3);
		System.out.print("0 + p3 is: ");
		p.prnpo();
		p=p5.add(p6);
		System.out.print("p5 + p6 is: ");
		p.prnpo();
		
		p=p3.multiply(p4);
		System.out.print("p3 * p4 is: ");
		p.prnpo();
		p=p4.multiply(pnull);
		System.out.print("p4 * 0 is: ");
		p.prnpo();
		p=pnull.multiply(p3);
		System.out.print("0 * p3 is: ");
		p.prnpo();
		
		System.out.print("pnull(10) = ");
		System.out.println(pnull.evaluate(10));
		System.out.print("p3(-2) = ");
		System.out.println(p3.evaluate(-2));
		
		System.out.print("pnull has root 6.74: ");
		System.out.println(pnull.hasRoot(6.74));
		System.out.print("p3 has root 4: ");
		System.out.println(p3.hasRoot(4));
		System.out.print("p4 has root 0: ");
		System.out.println(p4.hasRoot(0));
		System.out.print("p5 has root -1: ");
		System.out.println(p5.hasRoot(-1));
		
		try{
			p6.saveToFile("p6.txt");
			pnull.saveToFile("pnull.txt");
		}
		catch (IOException ex) {
			ex.getStackTrace();
		}
		
		
		try {
			
			File f =new File("test.txt");
			p = new Polynomial(f);
			System.out.print("Read file: ");
			p.prnpo();
		}
		catch (IOException ex) {
			ex.getStackTrace();
		}
     
	}
}