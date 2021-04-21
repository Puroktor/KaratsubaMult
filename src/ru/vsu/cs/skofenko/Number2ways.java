package ru.vsu.cs.skofenko;

import java.math.BigInteger;

public class Number2ways {
    private BigInteger bNumber;
    private Number number;

    public BigInteger getbNumber() {
        return bNumber;
    }

    public Number getNumber() {
        return number;
    }

    public Number2ways(BigInteger bNumber, Number number) {
        this.bNumber = bNumber;
        this.number = number;
    }
}
