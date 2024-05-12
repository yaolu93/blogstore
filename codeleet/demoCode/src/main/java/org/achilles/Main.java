package org.achilles;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Entry[] entry = { new Entry(2, 1),
                new Entry(3, 7),
                new Entry(7, 3),
                new Entry(1, 6),
                new Entry(5, 5),
                new Entry(3, 2),
                new Entry(4, 4) };

        int maxX = 0;
        for (Entry sizeX : entry) {
            maxX = Math.max(maxX, sizeX.getX());
        }

        int maxY = 0;
        for (Entry sizeY : entry) {
            maxY = Math.max(maxY, sizeY.getX());
        }

        for (int i = maxY - 1; i >= 0; i--) {
            for (int j = maxX; j > 0; j--) {
                System.out.print("- ");
            }
            System.out.println();
        }


        char[][] chart = createChart(entry, maxX, maxY);
        printStars(chart);
    }

    public static char[][] createChart(Entry[] entry, int maxX, int maxY) {
        char[][] chart = new char[maxY][maxX];
        for (char[] row : chart) {
            Arrays.fill(row, ' ');
        }

        for (Entry point : entry) {
            int x = point.getX() - 1;//这里改一下以后再调一下
            int y = maxY - point.getY();
            chart[y][x] = '*';
        }

        return chart;
    }


    public static void printStars(char[][] chart) {
        for (char[] row : chart) {
            for (char c : row) {
                if (c == '*') {
                    System.out.print(c);
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }
    }
    public static class Entry {
        private int x;
        private int y;

        Entry(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}