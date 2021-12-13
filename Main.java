import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * args[0] : a text file containing a header with the number of xy pairs
 *      followed by 2 values on each line
 *      Ex:
 *      3
 *      1.0 2.0
 *      2.0 3.0
 *      7 10
 * args[1] : a text containing a list of x values for which we want to find the y values using 
 *      the newly found polynomial
 * Outputs:
 *      results.txt : contains details about the function calculated
 *      outYValues.txt : displays the y values of the x values from args[1]
 */
public class Main {
    public static void main(String[] args){
        PolynomialPatternDiscovery patternDiscov = new PolynomialPatternDiscovery(args[0]);
        PolynomialFunction h1 = patternDiscov.calculateH1();
        PolynomialFunction h2 = patternDiscov.calculateH2();
        PolynomialFunction h3 = patternDiscov.calculateH3();
        PolynomialFunction f = new PolynomialFunction(new LinkedList<>(), "f");
        f.add(h1);
        f.add(h2);
        f.add(h3);
        DecimalFormat df = new DecimalFormat("#.##########");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        try(BufferedWriter outFunction = new BufferedWriter(new FileWriter("result.txt"));
            Scanner inXValues = new Scanner(new File(args[1]));
            BufferedWriter outYValues = new BufferedWriter(new FileWriter("outYValues.txt"));){
            outFunction.write(h1.toString() + "\n");
            outFunction.write(h2.toString() + "\n");
            outFunction.write(h3.toString() + "\n");
            outFunction.write(f.toString() + "\n");
            for(int i = 0; i < patternDiscov.xyPairs.length; i++){
                outFunction.write("f(" + df.format(patternDiscov.xyPairs[i][0]) + ") = " + df.format(f.evaluate(patternDiscov.xyPairs[i][0])) + "\n");
            }
            while(inXValues.hasNextDouble()){
                Double next = inXValues.nextDouble();
                outYValues.write("f(" + next + ") = "  + f.evaluate(next) + "\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
