import java.math.BigDecimal;
import java.util.LinkedList;

/* 
 * This class implements a multi Term polynomial expression in one variable (x)
 * of the form a_0 + a_1*x^1 + a_2*x^2 + ... + a_n*x^n
 * where a_i is a double value
*/
public class PolynomialFunction {

    /* 
     * A LinkedList of Terms representing the polynomial expression
    */
    LinkedList<Term> terms;

    /* 
     * String representing the name of the polynomial
     * i.e. the 'f' in 'f(x)' or the 'h' in 'h(x)'
    */
    String name;

    /* 
     * @Constructor
     * @param terms : list of the terms of the polynomial
     * @param name : name of the polynomial
    */
    public PolynomialFunction(LinkedList<Term> terms, String name){
        if(terms.isEmpty()){
            this.terms = new LinkedList<>();
            terms.add(new Term(BigDecimal.ZERO, 1));
        }else{
            this.terms = terms;
        }
        this.name = name;
        terms.sort(Term.tComp);
    }

    /* 
     * Adds a Term to current polynomial
     * @param t : Term to add to current polynomial
    */
    public void addTerm(Term t){
        if(degExists(t) != null){
            Term match = degExists(t);
            match.coefficient = match.coefficient.add(t.coefficient);
        }else{
            terms.addLast(t);
            terms.sort(Term.tComp);
        }
    }

    /* 
     * Adds a Polynomial to current Polynomial
     * @param other : Polynomial to add to current polynomial
    */
    public void add(PolynomialFunction other){
        for(Term term : other.terms){
            this.addTerm(term);
        }
    }

    /* 
     * Checks if a Term with given exponent exists in current Polynomial
     * if there is such a Term, return it
     * else return null
     * @param t : Term whose degree we are looking for in current Polynomial
    */
    public Term degExists(Term t){
        for(Term term : terms){
            if(term.exponent == t.exponent){
                return term;
            }
        }
        return null;
    }

    /* 
     * Multiply the current Polynomial by the given Term
     * @param t : Term to multiply the current polynomial by
    */
    public void multiplyByTerm(Term t){
        for(Term term : this.terms){
            term.multiplyBy(t);
        }
    }

    /* 
     * Multiply the current Polynomial by the given Polynomial
     * @param t : Polynomial to multiply the current Polynomial by
     * return the resulting Polynomial (product)
    */
    public PolynomialFunction multiplyBy(PolynomialFunction other){
        PolynomialFunction result = new PolynomialFunction(new LinkedList<>(), this.name);
        for(Term t : other.terms){
            PolynomialFunction copy = new PolynomialFunction(new LinkedList<>(), this.name);
            for(Term term : this.terms){
                copy.terms.add(new Term(term.coefficient, term.exponent));
            }
            copy.multiplyByTerm(t);
            result.add(copy);
        }
        return result;
    }

    /* 
     * Evaluate Polynomial with the given input value
     * @param input -> value to substitute x by
     * return the evaluated number
    */
    public BigDecimal evaluate(BigDecimal input){
        BigDecimal value = BigDecimal.ZERO;
        for(Term term : this.terms){
            value = value.add(term.evaluate(input));
        }
        return value;
    }

    /* 
     * returns a String representation of the polynomial expression
    */
    public String toString(){
        StringBuilder print = new StringBuilder();
        int i;
        print.append(this.name + "(x) = ");
        for(i = 0; i < terms.size() - 1; i++){
            if(terms.get(i).coefficient.compareTo(BigDecimal.ZERO) == 0){
                continue;
            }
            print.append(terms.get(i).toString() + " + ");
        }
        if(terms.get(i).coefficient.compareTo(BigDecimal.ZERO) != 0){
            print.append(terms.get(i).toString());
        }else{
            print.replace(print.length() - 2, print.length(), "");
        }
        return print.toString();
    }
}
