

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;

/* This class implements a single term / variate polynomial
 * in the form c * x ^ p 
 * where c is the coefficient (represented by a BigDecimal) and p is the exponent of the independent variable,
 * which we assume to be x
 */
public class Term {

    /* 
     * A comparator to arrange terms in a polynomial by degrees in descending order
     */
    public static final Comparator<Term> tComp = (Term o1, Term o2) -> (o2.exponent - o1.exponent);

    /* 
     * coefficient of polynomial term
     */
    BigDecimal coefficient;

    /* 
     * exponent/degree of polynomial term
     */
    int exponent;

    /*
     * Constructor
     * @param coeff -> coefficient of initialized Term
     * @param exp -> exponent of initialized Term
    */
    public Term(BigDecimal coeff, int exp){
        this.coefficient = coeff;
        this.exponent = exp;
    }
 
    /* 
     * Multiply a Term by another one
     * @param other -> Term to multiply by
    */
    public void multiplyBy(Term other){
        this.coefficient = coefficient.multiply(other.coefficient, MathContext.UNLIMITED);
        this.exponent = this.exponent + other.exponent;
    }

    /* 
     * Evaluate Term with the given input value
     * @param input -> value to substitute x by
     * return the evaluated number
    */
    public BigDecimal evaluate(BigDecimal input){
        return this.coefficient.multiply(input.pow(exponent), MathContext.UNLIMITED);
    }

    /* 
     * returns a String representation of the Term
    */
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##########");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if(coefficient.compareTo(BigDecimal.ZERO) == 0){
            return "0";
        }
        if(exponent == 0){
            return String.valueOf(df.format(coefficient));
        }
        return (exponent == 1 ? "(" + df.format(coefficient) + ")" + "x" : "(" + df.format(coefficient) + ")" + "x^" + exponent);
    }
}
