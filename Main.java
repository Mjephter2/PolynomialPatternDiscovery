import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        PolynomialPatternDiscovery discov = new PolynomialPatternDiscovery("inputFile.txt");
        PolynomialFunction H1 = discov.calculateH1();
        PolynomialFunction H2 = discov.calculateH2();
        PolynomialFunction H3 = discov.calculateH3();
        PolynomialFunction f = new PolynomialFunction(new LinkedList<>(), "f");
        f.add(H1);
        f.add(H2);
        f.add(H3);
        System.out.println(f);
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        for(int i = 0; i < discov.xyPairs.length; i++){
            System.out.println("f(" + discov.xyPairs[i][0] + ") = " + df.format(f.evaluate(discov.xyPairs[i][0])));
        }
    }
}
