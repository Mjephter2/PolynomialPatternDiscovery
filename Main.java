import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * inputs
 * args[0]: text file with x1 f values pair with header consisting of the number of pairs
 * args[1]: text file with x2 f values pair with header consisting of the number of pairs
 * args[2]: text file containing x1 values with missing y values
 * argv[3]: text file containing x2 values with missing y values
 * 
 * outputs
 * g1.txt: text that contains information about the function g1 generated from args[0], 
 * g2.txt: text that contains information about the function g2 generated from args[1],
 *  also as a verification all the y values of the given x1 and x2 values are calculated
 * outY1.txt: text file containing y values corresponding to given x1 values
 * outY2.txt: text file containing y values corresponding to given x2 values
 */
public class Main {

    static DecimalFormat df = new DecimalFormat("#.#####");

    public static void main(String[] args){
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        PolynomialFunction[] funcs = new PolynomialFunction[2];

        for(int i = 0; i <= 1; i++){
            PolynomialPatternDiscovery patternDiscov = new PolynomialPatternDiscovery(args[i]);
            PolynomialFunction h1 = patternDiscov.calculateH1();
            PolynomialFunction h2 = patternDiscov.calculateH2();
            PolynomialFunction h3 = patternDiscov.calculateH3();
            PolynomialFunction g = new PolynomialFunction(new LinkedList<>(), "g" + Integer.toString(i+1));
            g.add(h1);
            g.add(h2);
            g.add(h3);
            funcs[i] = g;
            try(BufferedWriter outFunction = new BufferedWriter(new FileWriter("g" + Integer.toString(i+1) + ".txt"));
                Scanner inXValues = new Scanner(new File(args[i + 2]));
                BufferedWriter outYValues = new BufferedWriter(new FileWriter("outY" + Integer.toString(i+1) + ".txt"));){
                outFunction.write(h1.toString() + "\n");
                outFunction.write(h2.toString() + "\n");
                outFunction.write(h3.toString() + "\n");
                outFunction.write(g.toString() + "\n");
                outFunction.write("* Verification * \n");
                for(int j = 0; j < patternDiscov.xyPairs.length; j++){
                    outFunction.write("g" + Integer.toString(i+1) + "(" + df.format(patternDiscov.xyPairs[j][0]) + ") = " + df.format(g.evaluate(patternDiscov.xyPairs[j][0])) + "\n");
                }
                while(inXValues.hasNextDouble()){
                    Double next = inXValues.nextDouble();
                    outYValues.write("g" + Integer.toString(i+1) + "(" + next + ") = "  + df.format(g.evaluate(next)) + "\n");
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
