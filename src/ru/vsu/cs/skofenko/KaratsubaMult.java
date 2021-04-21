package ru.vsu.cs.skofenko;

import java.util.Iterator;
import java.util.LinkedList;

public class KaratsubaMult {

    private static LinkedList<Character> multOneDig(char x, char y) {
        int answ = (x - '0') * (y - '0');
        LinkedList<Character> list = new LinkedList<>();
        if (answ > 10) {
            list.add((char) ('0' + answ / 10));
            list.add((char) ('0' + answ % 10));
        } else {
            list.add((char) ('0' + answ));
        }
        return list;
    }

    private static int makeEqualLength(LinkedList<Character> x, LinkedList<Character> y) {
        int len1 = x.size();
        int len2 = y.size();
        if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++)
                x.addFirst('0');
        } else if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++)
                y.addFirst('0');
        }
        int len = Math.max(len1, len2);
        if (len != 1 && len % 2 == 1) {
            len++;
            x.addFirst('0');
            y.addFirst('0');
        }
        return len;
    }

    private static LinkedList<Character> add(LinkedList<Character> x, LinkedList<Character> y) {
        makeEqualLength(x, y);
        Iterator<Character> itX = x.descendingIterator();
        Iterator<Character> itY = y.descendingIterator();
        LinkedList<Character> answer = new LinkedList<>();
        boolean carryover = false;
        while (itX.hasNext()) {
            int x1 = itX.next() - '0';
            int y1 = itY.next() - '0';
            int xy = x1 + y1 + (carryover ? 1 : 0);
            if (xy < 10) {
                carryover = false;
                answer.addFirst((char) (xy + '0'));
            } else {
                carryover = true;
                answer.addFirst((char) (xy - 10 + '0'));
            }
        }
        if (carryover)
            answer.addFirst('1');
        return answer;
    }

    private static LinkedList<Character> karatsubaMult(LinkedList<Character> x, LinkedList<Character> y) {
        int n = makeEqualLength(x, y);

        if (n == 1) return multOneDig(x.getFirst(), y.getFirst());

        LinkedList<Character> a = sublist(x, 0, n / 2);
        LinkedList<Character> b = sublist(x, n / 2, n);

        LinkedList<Character> c = sublist(y, 0, n / 2);
        LinkedList<Character> d = sublist(y, n / 2, n);

        LinkedList<Character> p = add(a, b);
        LinkedList<Character> q = add(c, d);

        LinkedList<Character> ac = karatsubaMult(a, c);
        LinkedList<Character> bd = karatsubaMult(b, d);
        LinkedList<Character> pq = karatsubaMult(p, q);
        LinkedList<Character> adbc = subtract(subtract(pq, ac), bd);

        shift(ac, n);
        shift(adbc, n / 2);

        return add(add(ac, adbc), bd);
    }

    private static void shift(LinkedList<Character> list, int n) {
        for (int i = 0; i < n; i++)
            list.add('0');
    }

    private static LinkedList<Character> subtract(LinkedList<Character> x, LinkedList<Character> y) {
        makeEqualLength(x, y);
        Iterator<Character> itX = x.descendingIterator();
        Iterator<Character> itY = y.descendingIterator();
        LinkedList<Character> answer = new LinkedList<>();
        boolean carryover = false;
        while (itX.hasNext()) {
            int x1 = itX.next() - '0';
            int y1 = itY.next() - '0';
            int xy = x1 - y1 + (carryover ? -1 : 0);
            if (xy >= 0) {
                carryover = false;
                answer.addFirst((char) (xy + '0'));
            } else {
                carryover = true;
                answer.addFirst((char) (xy + 10 + '0'));
            }
        }
        return answer;
    }

    private static LinkedList<Character> sublist(LinkedList<Character> from, int start, int end) {
        LinkedList<Character> list = new LinkedList<>();
        int i = 0;
        for (Character ch : from) {
            if (i >= start && i < end)
                list.add(ch);
            i++;
        }
        return list;
    }

    private static void deleteZeros(LinkedList<Character> from) {
        while (from.getFirst()=='0'){
            from.removeFirst();
        }
    }

    public static Number mult(Number x, Number y) {
        LinkedList<Character> answ;
        boolean isPos = x.isPositive() == y.isPositive();
        answ = karatsubaMult(x.getList(), y.getList());
        deleteZeros(answ);
        return new Number(isPos, answ);
    }
}
