import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.io.IOException;

public class Polynomial {
    double[]none_zero_coefficients;
    int[]exponents;
    
    public Polynomial() {
    	none_zero_coefficients = null;
    	exponents = null;
    }
      
    public Polynomial(double[] c, int[] e){
    	none_zero_coefficients = new double[c.length];
    	exponents = new int[c.length];
    	for (int j=0;j<c.length;j++){
    		none_zero_coefficients[j]=c[j];
    		exponents[j]=e[j];
    	}
    }

    
    public Polynomial(File f) throws IOException {
    	char[] array = new char[10000];
    	FileReader file = new FileReader(f);
    	BufferedReader input = new BufferedReader(file);
    	input.read(array);
    	input.close();
    	int n = 0;
    	while(array[n]!='\u0000'){
    		n++;
    	}
    	char[] array_short=new char[n];
    	for (int i=0;i<n;i++){
    		array_short[i]=array[i];
    	}
    	Polynomial p = Polynomial.a2p(array_short);
    	if (p.exponents==null){
    		none_zero_coefficients=null;
    		exponents=null;
    	}
    	else{
    		none_zero_coefficients = new double[p.none_zero_coefficients.length];
    		exponents = new int[p.exponents.length];
    		for (int j=0;j<p.exponents.length;j++){
    			none_zero_coefficients[j]=p.none_zero_coefficients[j];
    			exponents[j]=p.exponents[j];
    		}
    	}
    }
    
    public int Max_Nu(){
    	// it will return the max number of the exponents
        // if exponents == null, then return -1
    	   int flag = -1;
    	   if (exponents==null){
    	     return flag;
    	   }
    	   for (int i=0; i<exponents.length;i++){
    		   if(flag<exponents[i]){
    			   flag=exponents[i];
    			   }
    	   }
    	   return flag;
    }
    
    public int Max_Nu(Polynomial A) {
    	// it will return the max number of the exponents between this polynomial 
    	// and the given polynomial.
    	//if one of the exponents == null, return the max number of the other one
        // if both exponents == null, then return -1
    	int flag_max_A = A.Max_Nu();
    	int flag_max_B = this.Max_Nu();
    	int flag = Math.max(flag_max_A, flag_max_B);
    	return flag;
    }
    
    public Polynomial simplify(){
    	if (this.exponents==null){
    		return this;
    		}
    	Polynomial result;
    	int max_exponent = this.Max_Nu();
    	double[] c1 = new double[max_exponent+1];
    	int[] e1 = new int[max_exponent+1];
    	for(int i=0;i<max_exponent+1;i++){
    		e1[i]=i;
    		}
    	for(int i=0;i<this.exponents.length;i++){
    		c1[this.exponents[i]] += this.none_zero_coefficients[i];
    		}
    	    int non_zero_count = 0;
    	    for (int i=0;i<c1.length;i++){
    	    	if(c1[i]!=0){
    	    		non_zero_count++;
    	    		}
    	    	}
    	    if (non_zero_count==0){
    	    	result = new Polynomial();
    	    	return result;
    	    	}
    	    double[] c2 = new double[non_zero_count];
    	    int[] e2 = new int[non_zero_count];
    	    for (int i=0;i<c1.length;i++){
    	    	if(c1[i]!=0){
    	    		non_zero_count--;
    	    		c2[non_zero_count]=c1[i];
    	    		e2[non_zero_count]=e1[i];
    	    		}
    	    	}
    	    result = new Polynomial(c2, e2);
    	    return result;
    	    }

    
    public Polynomial add(Polynomial A) {
    	if (A.Max_Nu()==-1){
    		return this;
    		}
    	if (this.Max_Nu()==-1){
    		return A;
    		}
    	int count_A=A.exponents.length;
    	int count_this=this.exponents.length;
    	int count = count_A+count_this;
    	int n=0;
    	double[] c=new double[count];
    	int[] e=new int[count];
    	for (int i=0;i<count_A;i++){
    		c[n]=A.none_zero_coefficients[i];
    		e[n]=A.exponents[i];
    		n++;
    		}
    	for (int i=0;i<count_this;i++){
    		c[n]=this.none_zero_coefficients[i];
    		e[n]=this.exponents[i];
    		n++;
    		}
    	Polynomial p =new Polynomial(c, e);
    	Polynomial result = p.simplify();
    	return result;
    	}
    
    public double evaluate(double x){
    	if (this.exponents==null){
    		return 0;
    		}
    	int n=this.exponents.length;
    	double result=0;
    	for (int i=0;i<n;i++){
    		result += none_zero_coefficients[i] * Math.pow(x, exponents[i]);
    		}
    	return result;
    	}
    
    public boolean hasRoot(double Num) {
    	return this.evaluate(Num)==0;
    	}
    
   public Polynomial multiply(Polynomial A){
	   if (this.exponents==null){
		   return this;
		   }
	   if (A.exponents==null){
		   return A;
		   }
	   int n = A.exponents.length * this.exponents.length;
	   double[] c=new double[n];
	   int[] e=new int[n];
	   for(int i=0;i<A.exponents.length;i++){
		   for(int j=0;j<this.exponents.length;j++){
			   n--;
			   c[n]=A.none_zero_coefficients[i] * this.none_zero_coefficients[j];
			   e[n]=A.exponents[i]+this.exponents[j];
			   }
		   }
	   Polynomial p = new Polynomial(c,e);
	   Polynomial result = p.simplify();
	   return result;
	   }
   
   public static Polynomial a2p_term(char[] char_arr) {
	   String s = new String(char_arr);
	   int x_index=s.indexOf('x');
	   // No x means is constant
	   if(x_index==-1){
		   double d = Double.parseDouble(s);
		   double[] c={d};
		   int[] e={0};
		   Polynomial p=new Polynomial(c,e);
		   p=p.simplify();
		   return p;
		   }
	   String[] ss=s.split("x");
	   // if given polynomial has the term with only "x"
	   if (ss.length==0){
		   double[] c={1};
		   int[] e={1};
		   Polynomial p=new Polynomial(c,e);
		   p=p.simplify();
		   return p;
		   }
	   // "+ax" and "-ax" and "+x" and "-x"and "ax" situation.
	   if (ss.length==1){
		   int[] e={1};
		   if (ss[0].length()==1 && ss[0].charAt(0)=='+'){
			   double[] c={1};
			   Polynomial p=new Polynomial(c,e);
			   p=p.simplify();
			   return p;
			   }
		   if (ss[0].length()==1 && ss[0].charAt(0)=='-'){
			   double[] c={-1};
			   Polynomial p=new Polynomial(c,e);
			   p=p.simplify();
			   return p;
			   }
		   double d = Double.parseDouble(ss[0]);
		   double[] c={d};
		   Polynomial p=new Polynomial(c,e);
		   p=p.simplify();
		   return p;
		   }
	   double[] c=new double[1];
	   int[] e=new int[1];
	   if (ss[0].length()==0) {
		   c[0]=1;  // situation with "xb"
		   }
	   else if (ss[0].length()==1 && ss[0].charAt(0)=='+'){
		   c[0]=1;  // situation with "+xb"
		   }
	   else if (ss[0].length()==1 && ss[0].charAt(0)=='-'){
		   c[0]=-1; // situation with "-xb"
		   }
	   else {
		   double d = Double.parseDouble(ss[0]);
		   c[0] = d; //situation with "+axb" and "-axb" and "axb"
		   }
	   int i = Integer.parseInt(ss[1]);
	   e[0] = i;
	   Polynomial p = new Polynomial(c,e);
	   p=p.simplify();
	   return p;
	   }
   
   public static Polynomial a2p(char[] char_arr){
	   String s = new String(char_arr);
	   int index_plus=s.indexOf('+', 1);
	   int index_minus=s.indexOf('-', 1);
	   int index_sec_term=Math.max(index_plus,index_minus);
	   if(index_sec_term==-1){
		   Polynomial p=a2p_term(char_arr);
		   return p;
		   }
	   String s1 =s.substring(0, index_sec_term);
	   String s2 =s.substring(index_sec_term);
	   char[] char_arr1=s1.toCharArray();
	   char[] char_arr2=s2.toCharArray();
	   Polynomial p1=a2p(char_arr1);
	   Polynomial p2=a2p(char_arr2);
	   Polynomial p=p1.add(p2);
	   return p;
	   }
   
     public void saveToFile(String file_name) throws IOException {
    	 String s;
    	 if (none_zero_coefficients==null){
    		 s="0";
    		 }
    	 else {
    		 s=String.valueOf(none_zero_coefficients[0]);
    		 if(exponents[0]>=1){
    			 s=s.concat("x");
    			 s=s.concat(String.valueOf(exponents[0]));
    			 }
    		 for (int i=1;i<none_zero_coefficients.length;i++){
    			 if(none_zero_coefficients[i]>0){
    				 s=s.concat("+");
    				 s=s.concat(String.valueOf(none_zero_coefficients[i]));
    				 }
    			 else {
    				 s=s.concat(String.valueOf(none_zero_coefficients[i]));
    				 }
    			 if(exponents[i]>=1){
    				 s=s.concat("x");
    				 s=s.concat(String.valueOf(exponents[i]));
    				 }
    			 }
    		 }
    	 FileWriter output = new FileWriter(file_name,false);
    	 output.write(s);
    	 output.close();
    	 }
     
     public void prnpo() {
    	 if(exponents==null) {
    		 System.out.println("result is null!");
    		 return;
    	 }
    	 int n=exponents.length;
    	 for(int i=0;i<n;i++) {
    		 System.out.print("+");
    		 System.out.print(none_zero_coefficients[i]);
    		 System.out.print("x^");
    		 System.out.print(exponents[i]);
    	 }
    	 System.out.print("\n");
     }
}