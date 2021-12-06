import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;

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
        try(BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"))){
            out.write(h1.toString() + "\n");
            out.write(h2.toString() + "\n");
            out.write(h3.toString() + "\n");
            out.write(f.toString() + "\n");
            for(int i = 0; i < patternDiscov.xyPairs.length; i++){
                out.write("f(" + df.format(patternDiscov.xyPairs[i][0]) + ") = " + df.format(f.evaluate(patternDiscov.xyPairs[i][0])) + "\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
