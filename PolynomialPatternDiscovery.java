import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class PolynomialPatternDiscovery {

    Double[][] xyPairs;

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
    
    public PolynomialFunction calculateH1(){
        Term y01 = new Term(xyPairs[0][1], 0);
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(y01);
        PolynomialFunction h1 = new PolynomialFunction(t, "h1");

        for(int j = 1; j < xyPairs.length; j++){
            Term t1 = new Term(1.0, 1);
            Term t2 = new Term(-1 * xyPairs[j][0], 0);
            LinkedList<Term> exp = new LinkedList<>();
            exp.add(t1);
            exp.add(t2);
            PolynomialFunction currExpression = new PolynomialFunction(exp, "");

            Term t3 = new Term(1.0/(xyPairs[0][0] - xyPairs[j][0]), 0);

            currExpression.multiplyByTerm(t3);
            h1 = h1.multiplyBy(currExpression);
        }
        return h1;
    }

    public PolynomialFunction calculateH2(){
        Term t0 = new Term(0.0, 1);
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(t0);
        PolynomialFunction h2 = new PolynomialFunction(t, "h2");

        for(int i = 1; i < xyPairs.length - 1; i++){
            Term ti1 = new Term(xyPairs[i][1], 0);
            LinkedList<Term> tt = new LinkedList<>();
            tt.addLast(ti1);
            PolynomialFunction currentProduct = new PolynomialFunction(tt, "");

            // calculate first set of product
            for(int j = 0; j < i; j++){
                Term t1 = new Term(1.0, 1);
                Term t2 = new Term(-xyPairs[j][0], 0);
                LinkedList<Term> ts = new LinkedList<>();
                ts.addLast(t1);
                ts.addLast(t2);
                PolynomialFunction p1 = new PolynomialFunction(ts, "p1");

                Term t3 = new Term(1.0/(xyPairs[i][0] - xyPairs[j][0]), 0);
                p1.multiplyByTerm(t3);

                currentProduct = currentProduct.multiplyBy(p1);
            }
            // calculate second set of product 
            for(int j = i + 1; j < xyPairs.length; j++){
                Term t1 = new Term(1.0, 1);
                Term t2 = new Term(-xyPairs[j][0], 0);
                LinkedList<Term> ts = new LinkedList<>();
                ts.addLast(t1);
                ts.addLast(t2);
                PolynomialFunction p2 = new PolynomialFunction(ts, "p2");

                Term t3 = new Term(1.0/(xyPairs[i][0] - xyPairs[j][0]), 0);
                p2.multiplyByTerm(t3);

                currentProduct = currentProduct.multiplyBy(p2);
            }
            h2.add(currentProduct);
        }
        return h2;
    }

    public PolynomialFunction calculateH3(){
        // TODO
        Term y = new Term(xyPairs[xyPairs.length - 1][1], 0);
        LinkedList<Term> t = new LinkedList<>();
        t.addLast(y);
        PolynomialFunction h2 = new PolynomialFunction(t, "h2");

        for(int j = 0; j < xyPairs.length - 1; j++){
            Term t1 = new Term(1.0, 1);
            Term t2 = new Term(-1 * xyPairs[j][0], 0);
            LinkedList<Term> exp = new LinkedList<>();
            exp.add(t1);
            exp.add(t2);
            PolynomialFunction currExpression = new PolynomialFunction(exp, "");

            Term t3 = new Term(1.0/(xyPairs[xyPairs.length - 1][0] - xyPairs[j][0]), 0);

            currExpression.multiplyByTerm(t3);
            h2 = h2.multiplyBy(currExpression);
        }
        return h2;
    }   
}
