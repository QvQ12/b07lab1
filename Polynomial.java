public class Polynomial {
    double[]non0_coefficients;
    
    public Polynomial() {
    	coefficients = new double[1];
    	coefficients[0]=0;
    }
    
    public Polynomial(double[]Input) {
    	coefficients = new double[Input.length];
    	for(int i=0;i<Input.length;i++){
    		coefficients[i]=Input[i];
    	}
    }
    
    public Polynomial add(Polynomial A) {
    	int len;
    	if(A.coefficients.length>=coefficients.length) {
    		len=A.coefficients.length;
    		}
    	else {
    		len=coefficients.length;
    	}
    	double[]result = new double[len];
    	for(int i = 0; i<len;i++) {
    		result[i]=0;
    		}
    	for(int i = 0; i<A.coefficients.length;i++) {
    		result[i]=A.coefficients[i];
    		}
    	for(int j = 0; j<coefficients.length;j++) {
    		result[j]+=coefficients[j];
    	   }
    	Polynomial Result = new Polynomial(result);
    	return Result;
    }
    
    public double evaluate(double x) {
    	double output=0;
    	for(int i=0;i<coefficients.length;i++)
    	{
    		double y=1;
    		for(int j=0;j<i;j++) 
    		{y=y*x;}
    	   output = output + coefficients[i]*y;
    	}
    	return output;
    }
    	
    public boolean hasRoot(double Num) {
    	return this.evaluate(Num)==0;
    }
   

}