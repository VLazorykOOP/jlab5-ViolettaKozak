import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class First {
    static void main(){
        System.out.println("First task");
        ArrayList<Complex> complexList = new ArrayList<>();
        complexList.add(new Complex(1, 2));
        complexList.add(new Complex(3, 4));
        complexList.add(new Complex(5, 6));

        System.out.println("Complex Numbers:");
        for (Complex complex : complexList) {
            System.out.println(complex);
        }

        ArrayList<Rational> rationalList = new ArrayList<>();
        rationalList.add(new Rational(1, 2));
        rationalList.add(new Rational(3, 4));
        rationalList.add(new Rational(5, 6));

        System.out.println("\nRational Numbers:");
        for (Rational rational : rationalList) {
            System.out.println(rational);
        }
        Scanner scanner = new Scanner(System.in);
        Collections.sort(complexList);
        Collections.sort(rationalList);

        System.out.println("\nSorted Complex Numbers:");
        for (Complex complex : complexList) {
            System.out.println(complex);
        }

        System.out.println("\nSorted Rational Numbers:");
        for (Rational rational : rationalList) {
            System.out.println(rational);
        }

        System.out.println("\nEnter a new complex number (real imaginary): ");
        double real = scanner.nextDouble();
        double imaginary = scanner.nextDouble();
        Complex newComplex = new Complex(real, imaginary);
        complexList.add(newComplex);

        System.out.println("\nEnter a new rational number (numerator denominator): ");
        int numerator = scanner.nextInt();
        int denominator = scanner.nextInt();
        Rational newRational = new Rational(numerator, denominator);
        rationalList.add(newRational);

        Collections.sort(complexList);
        Collections.sort(rationalList);

        System.out.println("\nSorted Complex Numbers:");
        for (Complex complex : complexList) {
            System.out.println(complex);
        }

        System.out.println("\nSorted Rational Numbers:");
        for (Rational rational : rationalList) {
            System.out.println(rational);
        }
        ArrayList<Pair> pairList = new ArrayList<Pair>();

        pairList.addAll(complexList);
        pairList.addAll(rationalList);

        Collections.sort(pairList, new PairComparator());
        try (FileWriter writer = new FileWriter("sorted_pairs.txt")) {
            for (Pair pair : pairList) {
                writer.write(pair.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class PairComparator implements Comparator<Pair> {
    double d1, d2;
    public int compare(Pair p1, Pair p2){
        if(p1.getClass()==Complex.class && p2.getClass()==Complex.class){
            return p1.compareTo(p2);
        }else if (p1.getClass()==Rational.class && p2.getClass()==Rational.class){
            return p1.compareTo(p2);
        }else if (p1.getClass()==Complex.class){
            d1 = ((Complex) p1).real;
            d2 = ((Rational) p2).numerator / ((Rational) p2).denominator;
            if (d1 < d2){
                return -1;
            }else if (d1 > d2){
                return 1;
            }
            return 0;
        }else if (p2.getClass()==Complex.class) {
            d2 = ((Complex) p2).real;
            d1 =(double) ((Rational) p1).numerator / ((Rational) p1).denominator;
            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            }
            return 0;
        }
        return 0;
    }

}
class Complex extends Pair{
    double real, imagine;

    Complex() {
        this(2.3, 5.1);
    }

    Complex(double r, double i) {
        real = Math.round(r * 100.0)/100.0;
        imagine = Math.round(i * 100.0)/100.0;
    }

    public Pair add(Pair other) {
        Complex o = (Complex) other;
        return new Complex(this.real + o.real, this.imagine + o.imagine);
    }

    public Pair subtract(Pair other){
        Complex o = (Complex) other;
        return new Complex(this.real - o.real, this.imagine - o.imagine);
    }

    public Pair multiply(Pair other){
        Complex o = (Complex) other;
        double newReal = this.real * o.real - this.imagine * o.imagine;
        double newImagine = this.real * o.imagine + this.imagine * o.real;
        return new Complex(newReal, newImagine);
    }

    @Override
    public String toString(){
        return "real: " + this.real + "  imagine: " + this.imagine;
    }


    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other.getClass() != this.getClass() || other == null) {
            return false;
        }
        Complex o = (Complex) other;
        return (this.real == o.real)&& (this.imagine == o.imagine);
    }

    @Override
    public int compareTo(Pair other){
        Complex o = (Complex) other;
        if(this.real < o.real){
            return -1;
        }else if (this.real > o.real){
            return 1;
        }else {
            if (this.imagine < o.imagine){
                return -1;
            }else if (this.imagine > o.imagine){
                return 1;
            }
        }
        return 0;
    }


}

class Rational extends Pair{
    int numerator, denominator;

    public Rational(){this(3, 5);}
    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Pair add(Pair other) {
        Rational r = (Rational) other;
        int newNumerator = this.numerator * r.denominator + r.numerator * this.denominator;
        int newDenominator = this.denominator * r.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Pair subtract(Pair other) {
        Rational r = (Rational) other;
        int newNumerator = this.numerator * r.denominator - r.numerator * this.denominator;
        int newDenominator = this.denominator * r.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Pair multiply(Pair other) {
        Rational r = (Rational) other;
        int newNumerator = this.numerator * r.numerator;
        int newDenominator = this.denominator * r.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    @Override
    public String toString(){
        return this.numerator + "/" + this.denominator;
    }


    @Override
    public boolean equals(Object other){
        Rational o = (Rational) other;
        if (o.numerator % this.numerator == 0){
            int k = o.numerator /this.numerator;
            return this.denominator * k == o.denominator;
        }
        else if (this.numerator % o.numerator == 0 ){
            int k = this.numerator /o.numerator;
            return this.denominator == o.denominator * k;
        }

        return false;
    }

    @Override
    public int compareTo(Pair other){
        Rational o = (Rational) other;
        float d1 = (float) numerator / denominator;
        float d2 = (float) o.numerator / o.denominator;
        if (d1 < d2){
            return -1;
        }else if (d1 > d2){
            return 1;
        }
        return 0;
    }
}

