package m0o0o0o.jacoco.jacocostudy;

public class Calculator {

    public int calculate(int num1, char operator, int num2) {
        if(operator == '+') return num1 + num2;
        else if(operator == '-') return num1 - num2;
        else if(operator =='*') return num1 * num2;
        else if(operator == '/') return num1 / num2;
        else throw new IllegalArgumentException("연산자 확인!!");
    }

}
