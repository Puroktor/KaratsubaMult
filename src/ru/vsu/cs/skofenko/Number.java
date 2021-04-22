package ru.vsu.cs.skofenko;

import java.util.LinkedList;

public class Number {
    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public void setList(LinkedList<Byte> list) {
        this.list = list;
    }

    public Number(boolean isPositive, LinkedList<Byte> list) {
        this.isPositive = isPositive;
        this.list = list;
    }

    public LinkedList<Byte> getList() {
        return list;
    }

    private boolean isPositive;
    private LinkedList<Byte> list;

    @Override
    public String toString() {
        if (list == null)
            return null;
        StringBuilder sb = new StringBuilder();
        if (!isPositive)
            sb.append('-');
        for (byte b : list) {
            sb.append(b);
        }
        return sb.toString();
    }


}
