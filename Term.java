import java.util.Comparator;

public class Term {

    public static final Comparator<Term> tComp = (Term o1, Term o2) -> (o2.exponent - o1.exponent);

    Double coefficient;
    int exponent;

    public Term(Double coeff, int exp){
        this.coefficient = coeff;
        this.exponent = exp;
    }

    public void multiplyBy(Term other){
        this.coefficient = coefficient * other.coefficient;
        this.exponent = this.exponent + other.exponent;
    }

    public Double evaluate(Double input){
        return this.coefficient * Math.pow(input, this.exponent);
    }

    public String toString(){
        if(coefficient == 0.0){
            return "0";
        }
        if(exponent == 0){
            return String.valueOf(coefficient);
        }
        return (exponent == 1 ? "(" + coefficient + ")" + "x" : "(" + coefficient + ")" + "x^" + exponent);
    }
}
