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
            System.out.printf("maxX: %d%n", maxX);
        }

        int maxY = 0;
        for (Entry sizeY : entry) {
            maxY = Math.max(maxY, sizeY.getX());
            System.out.printf("maxY: %d%n", maxY);
        }

        for (int i = maxX - 1; i >= 0; i--) {
            for (int j = maxY; j > 0; j--) {
                System.out.print("- ");
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