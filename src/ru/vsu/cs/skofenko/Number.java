package ru.vsu.cs.skofenko;

import java.util.LinkedList;

public class Number {
    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public void setList(LinkedList<Character> list) {
        this.list = list;
    }

    public Number(boolean isPositive, LinkedList<Character> list) {
        this.isPositive = isPositive;
        this.list = list;
    }

    public LinkedList<Character> getList() {
        return list;
    }

    private boolean isPositive;
    private LinkedList<Character> list;

    @Override
    public String toString() {
        if (list == null)
            return null;
        StringBuilder sb = new StringBuilder();
        if (!isPositive)
            sb.append('-');
        for (char ch : list) {
            sb.append(ch);
        }
        return sb.toString();
    }


}
