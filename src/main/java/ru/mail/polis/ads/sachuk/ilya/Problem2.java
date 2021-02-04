package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem2 {

  public static class Heap {

    private final int[] mas;
    private int indexLastValue;

    public Heap(int n) {
      this.mas = new int[n];
      this.mas[0] = -1;
      this.indexLastValue = 0;
    }

    public void insert(int n) {
      indexLastValue++;
      mas[indexLastValue] = n;

      swim(indexLastValue);
    }

    public int exctract() {

      int maxValue = mas[1];

      mas[1] = mas[indexLastValue];
      mas[indexLastValue] = 0;
      indexLastValue--;

      sink(1);

      return maxValue;
    }

    private void swim(int index) {
      while (mas[index] > mas[index / 2]) {
        if (index / 2 > 0) {
          int tmp = mas[index];
          mas[index] = mas[index / 2];
          mas[index / 2] = tmp;

          index /= 2;
        }
        else {
          break;
        }
      }
    }

    private void sink(int indexToSink) {
      int k = indexToSink * 2;

      while (k < mas.length) {
        if (k + 1 < mas.length && mas[k] < mas[k + 1]) {
          k++;
        }

        if (mas[indexToSink] < mas[k]) {
          int tmp = mas[k];
          mas[k] = mas[indexToSink];
          mas[indexToSink] = tmp;
          indexToSink = k;
        }
        else {
          break;
        }

        k *= 2;
      }
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int commandNumber = in.nextInt();

    Heap heap = new Heap(commandNumber + 1);

    for (int i = 0; i < commandNumber; i++) {
      int command = in.nextInt();
      switch (command) {
        case 0:
          int number = in.nextInt();
          heap.insert(number);
          break;
        case 1:
          out.println(heap.exctract());
          break;
      }
    }
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}