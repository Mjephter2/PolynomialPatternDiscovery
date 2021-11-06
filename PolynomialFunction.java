import java.util.Comparator;
import java.util.LinkedList;

public class PolynomialFunction {

    LinkedList<Term> terms;
    int degree = 0;
    String name;
    Comparator<Term> tComp = Term.tComp;

    public PolynomialFunction(LinkedList<Term> terms, String name){
        if(terms.isEmpty()){
            this.terms = new LinkedList<>();
            terms.add(new Term(0.0, 1));
        }else{
            this.terms = terms;
        }
        this.name = name;
        for(Term t: terms){
            if(this.degree < t.exponent){
                this.degree = t.exponent;
            }
        }
        terms.sort(tComp);
    }

    public void addTerm(Term t){
        if(degExists(t) != null){
            Term match = degExists(t);
            match.coefficient = match.coefficient + t.coefficient;
        }else{
            terms.addLast(t);
            terms.sort(tComp);
        }
    }

    public void add(PolynomialFunction other){
        for(Term term : other.terms){
            this.addTerm(term);
        }
    }

    public Term degExists(Term t){
        for(Term term : terms){
            if(term.exponent == t.exponent){
                return term;
            }
        }
        return null;
    }

    public void multiplyByTerm(Term t){
        for(Term term : this.terms){
            term.multiplyBy(t);
        }
    }

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

    public Double evaluate(Double input){
        Double value = 0.0;
        for(Term term : this.terms){
            value += term.evaluate(input);
        }
        return value;
    }

    public String toString(){
        StringBuilder print = new StringBuilder();
        int i;
        print.append(this.name + "(x) = ");
        for(i = 0; i < terms.size() - 1; i++){
            if(terms.get(i).coefficient == 0){
                continue;
            }
            print.append(terms.get(i).toString() + " + ");
        }
        if(terms.get(i).coefficient != 0){
            print.append(terms.get(i).toString());
        }else{
            print.replace(print.length() - 2, print.length(), "");
        }
        return print.toString();
    }
}
