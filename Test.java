public class Test {
public static void main(String [] args) {
     Polynomial p = new Polynomial();
     System.out.println(p.evaluate(3));
     double [] c1 = {6,0,0,5,3,3,-6};
     Polynomial p1 = new Polynomial(c1);
     double [] c2 = {0,-2,0,0,-9};
     Polynomial p2 = new Polynomial(c2);
     Polynomial s = p1.add(p2);
     System.out.println("s(0.1) = " + s.evaluate(0.1));
     if(s.hasRoot(1))
           System.out.println("1 is a root of s");
     else
           System.out.println("1 is not a root of s");
        
     if(s.hasRoot(2.9999993))
         System.out.println("2.999999993 is a root of s");
      else
         System.out.println("2.999999993 is not a root of s");

     if(s.hasRoot(111))
         System.out.println("111 is a root of s");
      else
         System.out.println("111 is not a root of s");
    }
}