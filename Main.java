import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;
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
