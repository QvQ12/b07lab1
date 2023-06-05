public class Polynomial {
    double[]non_zero_coefficients;
    int[]exponents;
    
    public Polynomial() {
    	non_zero_coefficients = new double[1];
    	non_zero_coefficients[0]=0;
    	exponents = new int[1];
    	exponents[0]=0;
    	
    }
    
    public Polynomial(double[]Input) {
    	int non_zero_length = 0;
    	for(int i=0;i<Input.length;i++){
    		if(Input[i]!=0)
    		{
    			non_zero_length = non_zero_length+1;
    		}
    	}
    	if(non_zero_length==0)
    	{
    		non_zero_coefficients =new double[1];
    		non_zero_coefficients[0]=0;
        	exponents = new int[1];
        	exponents[0]=0;
    	}
    	else {
    	   non_zero_coefficients = new double[non_zero_length];
    	   exponents = new int[non_zero_length]
    	
           int j=0;
    	   while (j<=non_zero_length) {
    	   for(int i=0;i<Input.length;i++){
    		
    		   if(Input[i]!=0)
    		    {
    			  exponents[j]=i;
    			  non_zero_coefficients[j]=Input[i];
    			  j = j+1;
    		    }
    		   
    	    }
    	  }
       }
    }
    
    
 //  public Polynomial(File[]Input) {
	   
	   
	   
 //  }
    
    
   public Polynomial add(Polynomial A) {
    	if(A.exponents.length==0)
    	{return this;}
    	
    	if(exponents.length == 0)
    	{return A;}
    	
    	if(A.exponents.length==1)
    	{  if(A.exponents.length[0]==0) {
    			if(A.non_zero_coefficients==0)
    			{return this;}
    		}
    	}
    	
    	if(exponents.length==1)
    	{  if(exponents.length[0]==0) {
    			if(non_zero_coefficients==0)
    			{return A;}
    		}
    	}
    	
    	int total_len;
    		
    	if(A.exponents[A.exponents.length-1]>=exponents[exponents.length-1]) {
    		total_len = A.exponents[A.exponents.length-1]+1;
    	}
    	else {
    		total_len=exponents[exponents.length-1]+1;
    	}
    	
    	double[]total_result = new double[total_len];
    	for(int i = 0; i<total_len;i++) {
    		total_result[i]=0;
    	}
    	for(int j = 0; i<A.exponents.length;j++) {
    		int k = A.exponents[j]
    		total_result[k]=A.non_zero_coefficients[j];
    	}
    	for(int j = 0; i<exponents.length;j++) {
    		int k = exponents[j]
    		total_result[k]=non_zero_coefficients[j];
    	}
    	Polynomial Result = new Polynomial(total_result);
    	return Result;
    }
    
   
   
    
    
    public double evaluate(double x) {
    	double output=0;
    	for(int i=0;i<non_zero_coefficients.length;i++)
    	{
    		double y=1;
    		for(int j=0;j<exponents[i];j++) 
    		{y=y*x;}
    	   output = output + non_zero_coefficients[i]*y;
    	}
    	return output;
    }
    
    	
    public boolean hasRoot(double Num) {
    	return this.evaluate(Num)==0;
    }
   
    
    
   public Polynomial multiply(Polynomial A) {
    	
    	
    	
   }

    
    
    
//    public saveToFile(String file_name){
    
    
    
    
//    }    
    
    
    
    
    
    
}