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
        System.out.println(f);
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        for(int i = 0; i < patternDiscov.xyPairs.length; i++){
            System.out.println("f(" + patternDiscov.xyPairs[i][0] + ") = " + df.format(f.evaluate(patternDiscov.xyPairs[i][0])));
        }
    }
}
