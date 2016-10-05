import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Andrew on 10/1/2016.
 */

public class Complex {
    double real;
    double imagine;
    public static final int PRECISION = 4;

    Complex() {
        real = 0;
        imagine = 0;
    }

    Complex(Complex second) {
        real = second.real;
        imagine = second.imagine;
    }

    Complex(double real, double imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    private void print(String someText) {
        System.out.println(someText + real + "+i*" + imagine);
    }

    private double getModulus() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imagine, 2));
    }

    private double getArgument() {
        return Math.atan(imagine / real);

    }

    private BigDecimal round(double number, int precision) {
        BigDecimal copyNumber = new BigDecimal(number);
        MathContext mathPrecision = new MathContext(precision);
        return copyNumber.round(mathPrecision);

    }

    private void showTrigForm() {
        BigDecimal modulus = round(getModulus(), PRECISION);
        BigDecimal argument = round(getArgument(), PRECISION);
        System.out.println(modulus + "*(cos(" + argument + ")+" + imagine + "*sin(" + argument + "))");
    }

    private void showExponentialForm() {
        BigDecimal modulus = round(getModulus(), PRECISION);
        BigDecimal argument = round(getArgument(), PRECISION);
        System.out.println(modulus + "*e^(i*" + argument + ")");
    }

    private Complex add(Complex secondNumber) {
        real = real + secondNumber.real;
        imagine = imagine + secondNumber.imagine;
        return this;
    }

    private Complex subtract(Complex secondNumber) {
        real = real - secondNumber.real;
        imagine = imagine - secondNumber.imagine;
        return this;
    }

    private Complex multiplicate(Complex secondNumber) {
        double newReal = real * secondNumber.real - imagine * secondNumber.imagine;
        double newImagine = real * secondNumber.imagine + secondNumber.real * imagine;
        real = newReal;
        imagine = newImagine;
        return this;
    }

    public Complex divide(Complex secondNumber) {
        double denominator = Math.pow(secondNumber.imagine, 2) + Math.pow(secondNumber.real, 2);
        double newReal = (real * secondNumber.real + imagine * secondNumber.imagine) / denominator;
        double newImagine = (secondNumber.real * imagine - real * secondNumber.imagine) / denominator;
        real = newReal;
        imagine = newImagine;
        return this;


    }

    public static void main(String[] args) {
        Complex testNumber = new Complex(5.1, 4);
        testNumber.print("");
        testNumber.showTrigForm();
        testNumber.showExponentialForm();
        Complex secondNumber = new Complex(3, 2);
        testNumber.add(secondNumber);
        testNumber.print("plus ");
        testNumber.subtract(secondNumber);
        testNumber.print("First ");
        secondNumber.print("Second ");
        testNumber.multiplicate(secondNumber);
        testNumber.print("Result ");
        testNumber.print("First ");
        secondNumber.print("Second ");
        testNumber.divide(secondNumber);
        testNumber.print("Dev Result");
    }
}
