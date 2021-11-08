import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/* 
 * This class contains methods to calculate h1, h2, and h3 separately
*/
public class PolynomialPatternDiscovery {

    /* 
     * N x 2 array containing the given pairs (X_i, Yi)
    */
    Double[][] xyPairs;

    /* 
     * @Constructor
     * @param inputFileName : a text file containing a header and a body
     * where the header consists of a single integer value representing the 
     * number of pairs (X, Y) and the body consisting of 2 double values per line
     * Example of input file content:
     *      3
     *      1 3
     *      2 2
     *      5 -1
    */
    public PolynomialPatternDiscovery(String inputFile){
        try(Scanner in = new Scanner(new FileInputStream(inputFile))){
            this.xyPairs = new Double[in.nextInt()][2];
            int i = 0;
            while(in.hasNext()){
                xyPairs[i][0] = in.nextDouble();
                xyPairs[i][1] = in.nextDouble();
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /*
     * Calculate and return h1
    */
    public PolynomialFunction calculateH1(){
        Term y01 = new Term(xyPairs[0][1], 0); // y_(0,1)
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(y01);
        PolynomialFunction h1 = new PolynomialFunction(t, "h1"); // h1(x) = y_(0,1)

        for(int j = 1; j < xyPairs.length; j++){
            Term t1 = new Term(1.0, 1); // x
            Term t2 = new Term(-1 * xyPairs[j][0], 0); // -y_(j,0)
            LinkedList<Term> exp = new LinkedList<>();
            exp.add(t1);
            exp.add(t2);
            PolynomialFunction currExpression = new PolynomialFunction(exp, "");
            // x - y_(j,0)

            Term t3 = new Term(1.0/(xyPairs[0][0] - xyPairs[j][0]), 0);
            // 1/(y_(0,0) - y_(j,0))

            currExpression.multiplyByTerm(t3); // 1/(y_(0,0) - y_(j,0)) * (x - y_(j,0))
            h1 = h1.multiplyBy(currExpression); 
            // h1 * [1/(y_(0,0) - y_(j,0)) * (x - y_(j,0))]
        }
        return h1;
    }

    /*
     * Calculate and return h2
    */
    public PolynomialFunction calculateH2(){
        Term t0 = new Term(0.0, 1); // 0
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(t0);
        PolynomialFunction h2 = new PolynomialFunction(t, "h2"); // h2(x) = 0

        for(int i = 1; i < xyPairs.length - 1; i++){
            Term ti1 = new Term(xyPairs[i][1], 0); // y_(i,0)
            LinkedList<Term> tt = new LinkedList<>();
            tt.addLast(ti1);
            PolynomialFunction currentProduct = new PolynomialFunction(tt, ""); // y_(i,0)

            // calculate first set of product
            for(int j = 0; j < i; j++){
                Term t1 = new Term(1.0, 1); // x
                Term t2 = new Term(-xyPairs[j][0], 0); // -y_(j,0)
                LinkedList<Term> ts = new LinkedList<>();
                ts.addLast(t1);
                ts.addLast(t2);
                PolynomialFunction p1 = new PolynomialFunction(ts, "p1");
                // p1(x) = x - y_(j,0)

                Term t3 = new Term(1.0/(xyPairs[i][0] - xyPairs[j][0]), 0);
                // 1/(y_(i,0) - y_(j,0))
                p1.multiplyByTerm(t3);
                // p1 = p1 * 1/(y_(i,0) - y_(j,0))

                currentProduct = currentProduct.multiplyBy(p1);
                // currentProduct = currentProduct * p1
            }
            // calculate second set of product 
            for(int j = i + 1; j < xyPairs.length; j++){
                Term t1 = new Term(1.0, 1); // x
                Term t2 = new Term(-xyPairs[j][0], 0); // -y_(j,0)
                LinkedList<Term> ts = new LinkedList<>();
                ts.addLast(t1);
                ts.addLast(t2);
                PolynomialFunction p2 = new PolynomialFunction(ts, "p2");
                // p2(x) = x - y_(j,0)

                Term t3 = new Term(1.0/(xyPairs[i][0] - xyPairs[j][0]), 0);
                // 1/(y_(i,0) - y_(j,0))
                p2.multiplyByTerm(t3);
                // p2 = p2 * 1/(y_(i,0) - y_(j,0))

                currentProduct = currentProduct.multiplyBy(p2);
                // currentProduct = currentProduct * p2
            }
            h2.add(currentProduct);
            // h2 = h2 + currentProduct
        }
        return h2;
    }

    /*
     * Calculate and return h3
    */
    public PolynomialFunction calculateH3(){
        Term y = new Term(xyPairs[xyPairs.length - 1][1], 0);
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(y);
        PolynomialFunction h2 = new PolynomialFunction(t, "h2");

        for(int j = 0; j < xyPairs.length - 1; j++){
            Term t1 = new Term(1.0, 1); // x
            Term t2 = new Term(-1 * xyPairs[j][0], 0); // -y_(j,0) 
            LinkedList<Term> exp = new LinkedList<>();
            exp.add(t1);
            exp.add(t2);
            PolynomialFunction currExpression = new PolynomialFunction(exp, ""); 
            // x - y_(j,0) 

            Term t3 = new Term(1.0/(xyPairs[xyPairs.length - 1][0] - xyPairs[j][0]), 0);
            // 1/(y_(N - 1,0) - y_(j,0))

            currExpression.multiplyByTerm(t3);
            // (x - y_(j,0)) * 1/(y_(N - 1,0) - y_(j,0))
            h2 = h2.multiplyBy(currExpression);
            // h2 = h2 * (x - y_(j,0)) * 1/(y_(N - 1,0) - y_(j,0))
        }
        return h2;
    }   
}
