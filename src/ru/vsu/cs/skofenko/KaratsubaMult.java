package ru.vsu.cs.skofenko;

import java.util.Iterator;
import java.util.LinkedList;

public class KaratsubaMult {

    private static LinkedList<Byte> multOneDig(byte x, byte y) {
        byte answ = (byte) (x * y);
        LinkedList<Byte> list = new LinkedList<>();
        if (answ > 10) {
            list.add((byte) (answ / 10));
            list.add((byte) (answ % 10));
        } else {
            list.add(answ);
        }
        return list;
    }

    private static int makeEqualLength(LinkedList<Byte> x, LinkedList<Byte> y) {
        int len1 = x.size();
        int len2 = y.size();
        if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++)
                x.addFirst((byte) 0);
        } else if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++)
                y.addFirst((byte) 0);
        }
        int len = Math.max(len1, len2);
        if (len != 1 && len % 2 == 1) {
            len++;
            x.addFirst((byte) 0);
            y.addFirst((byte) 0);
        }
        return len;
    }

    private static LinkedList<Byte> add(LinkedList<Byte> x, LinkedList<Byte> y) {
        makeEqualLength(x, y);
        Iterator<Byte> itX = x.descendingIterator();
        Iterator<Byte> itY = y.descendingIterator();
        LinkedList<Byte> answer = new LinkedList<>();
        boolean carryover = false;
        while (itX.hasNext()) {
            byte xy = (byte) (itX.next() + itY.next() + (carryover ? 1 : 0));
            if (xy < 10) {
                carryover = false;
                answer.addFirst(xy);
            } else {
                carryover = true;
                answer.addFirst((byte) (xy - 10));
            }
        }
        if (carryover)
            answer.addFirst((byte) 1);
        return answer;
    }

    private static LinkedList<Byte> karatsubaMult(LinkedList<Byte> x, LinkedList<Byte> y) {
        int n = makeEqualLength(x, y);

        if (n == 1) return multOneDig(x.getFirst(), y.getFirst());

        LinkedList<Byte> a = sublist(x, 0, n / 2);
        LinkedList<Byte> b = sublist(x, n / 2, n);

        LinkedList<Byte> c = sublist(y, 0, n / 2);
        LinkedList<Byte> d = sublist(y, n / 2, n);

        LinkedList<Byte> p = add(a, b);
        LinkedList<Byte> q = add(c, d);

        LinkedList<Byte> ac = karatsubaMult(a, c);
        LinkedList<Byte> bd = karatsubaMult(b, d);
        LinkedList<Byte> pq = karatsubaMult(p, q);
        LinkedList<Byte> adbc = subtract(subtract(pq, ac), bd);

        shift(ac, n);
        shift(adbc, n / 2);

        return add(add(ac, adbc), bd);
    }

    private static void shift(LinkedList<Byte> list, int n) {
        for (int i = 0; i < n; i++)
            list.add((byte) 0);
    }

    private static LinkedList<Byte> subtract(LinkedList<Byte> x, LinkedList<Byte> y) {
        makeEqualLength(x, y);
        Iterator<Byte> itX = x.descendingIterator();
        Iterator<Byte> itY = y.descendingIterator();
        LinkedList<Byte> answer = new LinkedList<>();
        boolean carryover = false;
        while (itX.hasNext()) {
            byte xy = (byte) (itX.next() - itY.next() + (carryover ? -1 : 0));
            if (xy >= 0) {
                carryover = false;
                answer.addFirst(xy);
            } else {
                carryover = true;
                answer.addFirst((byte) (xy + 10));
            }
        }
        return answer;
    }

    private static LinkedList<Byte> sublist(LinkedList<Byte> from, int start, int end) {
        LinkedList<Byte> list = new LinkedList<>();
        int i = 0;
        for (byte b : from) {
            if (i >= start && i < end)
                list.add(b);
            i++;
        }
        return list;
    }

    private static void deleteZeros(LinkedList<Byte> from) {
        while (from.getFirst() == 0 && from.size() > 1) {
            from.removeFirst();
        }
    }

    public static Number mult(Number x, Number y) {
        boolean isPos = x.isPositive() == y.isPositive();
        LinkedList<Byte> answ = karatsubaMult(x.getList(), y.getList());
        deleteZeros(answ);
        return new Number(isPos, answ);
    }
}
