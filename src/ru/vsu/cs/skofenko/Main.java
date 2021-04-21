package ru.vsu.cs.skofenko;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    static final Random RND = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Number2ways twoNum1 = getRandomNumber();
            Number num1 = twoNum1.getNumber();
            BigInteger bigInteger1 = twoNum1.getbNumber();

            Number2ways twoNum2 = getRandomNumber();
            Number num2 = twoNum2.getNumber();
            BigInteger bigInteger2 = twoNum2.getbNumber();

            System.out.println(KaratsubaMult.mult(num1, num2).toString().equals(bigInteger1.multiply(bigInteger2).toString()));
        }
    }

    private static Number2ways getRandomNumber() {
        int n = RND.nextInt(200) + 200;
        LinkedList<Character> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = (char) (RND.nextInt(10) + '0');
            sb.append(ch);
            list.add(ch);
        }
        Number num = new Number(RND.nextBoolean(), list);
        BigInteger bigInteger = new BigInteger(sb.toString());
        if (!num.isPositive())
            bigInteger = bigInteger.negate();
        return new Number2ways(bigInteger, num);
    }

}
