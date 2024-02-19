package org.course_work.service;

public class BMSearch {
    public BMSearch() {

    }

    public boolean job(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] last = buildLastTable(pattern);

        int i = m - 1;
        int j = m - 1;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == 0) {
                    return true;
                } else {
                    i--;
                    j--;
                }
            } else {
                int index = last[text.charAt(i)];
                i += m - Math.min(j, 1 + index);
                j = m - 1;
            }
        }

        return false;
    }

    private int[] buildLastTable(String pattern) {
        int[] table = new int[Character.MAX_VALUE + 1];

        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            table[currentChar] = i;
        }

        return table;
    }
}