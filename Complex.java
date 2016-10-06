import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Andrew on 10/1/2016.
 */

public class Complex {
    public static void main(String[] args) {
        Complex testNumber = new Complex(5.1, -4.7);
        Complex secondNumber = new Complex(3, 2);
        testNumber.testTrigExpForms();
        testNumber.testAddition(secondNumber);
        testNumber.testSubtraction(secondNumber);
        testNumber.testMultiplication(secondNumber);
        testNumber.testDivision(secondNumber);
    }

    private double real;
    private double imagine;
    private static final int PRECISION = 4;

    private Complex() {
        real = 0;
        imagine = 0;
    }

    private Complex(Complex second) {
        real = second.real;
        imagine = second.imagine;
    }

    private Complex(double real, double imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    private void print(String someText) {
        String sign = (imagine < 0) ? "-" : "+";
        BigDecimal roundReal = round(real, PRECISION);
        BigDecimal roundImagine = round(Math.abs(imagine), PRECISION);
        System.out.println(someText + roundReal + sign + "i*" + roundImagine);
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
        String sign = (imagine < 0) ? "-" : "+";
        BigDecimal modulus = round(getModulus(), PRECISION);
        BigDecimal argument = round(getArgument(), PRECISION);
        System.out.println(modulus + "*(cos(" + argument + ")" + sign + Math.abs(imagine) + "*sin(" + argument + "))");
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

    private Complex multiply(Complex secondNumber) {
        double newReal = real * secondNumber.real - imagine * secondNumber.imagine;
        double newImagine = real * secondNumber.imagine + secondNumber.real * imagine;
        real = newReal;
        imagine = newImagine;
        return this;
    }

    private Complex divide(Complex secondNumber) {
        try {
            double denominator = Math.pow(secondNumber.imagine, 2) + Math.pow(secondNumber.real, 2);
            double newReal = (real * secondNumber.real + imagine * secondNumber.imagine) / denominator;
            double newImagine = (secondNumber.real * imagine - real * secondNumber.imagine) / denominator;
            real = newReal;
            imagine = newImagine;
        } catch (Exception e) {
            System.err.println("Zero division.");
        }
        return this;
    }

    private void testTrigExpForms() {
        print("Complex number: ");
        showTrigForm();
        showExponentialForm();
    }

    private void testAddition(Complex secondNumber) {
        System.out.println("\nAddition");
        print("First number: ");
        secondNumber.print("Second number: ");
        add(secondNumber);
        print("Result: ");
    }

    private void testSubtraction(Complex secondNumber) {
        System.out.println("\nSubtraction");
        print("First number: ");
        secondNumber.print("Second number: ");
        subtract(secondNumber);
        print("Result: ");
    }

    private void testMultiplication(Complex secondNumber) {
        System.out.println("\nMultiplication");
        print("First number: ");
        secondNumber.print("Second number: ");
        multiply(secondNumber);
        print("Result: ");
    }

    private void testDivision(Complex secondNumber) {
        System.out.println("\nDivision");
        print("First number: ");
        secondNumber.print("Second number: ");
        divide(secondNumber);
        print("Result: ");
    }

}
