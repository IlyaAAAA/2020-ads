package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5 {

  private static int[] sortedMas;
  private static int counter;

  private static void solve(final FastScanner in, final PrintWriter out) {
    int number = in.nextInt();
    int[] mas = new int[number];
    sortedMas = new int[number];
    counter = 0;

    for (int i = 0; i < number; i++) {
      mas[i] = in.nextInt();
    }

    sort(mas, 0, mas.length - 1);

    out.println(counter);
  }

  private static void sort(int[] mas, int left, int right) {

    if (right <= left) {
      return;
    }

    int middle = (right + left) / 2;

    sort(mas, left, middle);
    sort(mas, middle + 1, right);
    merge(mas, left, middle, right);
  }

  private static void merge(int[] mas, int left, int middle, int right) {

    int i = left;
    int j = middle + 1;

    int k = left;

    while (i <= middle && j <= right) {
      if (mas[i] < mas[j]) {
        sortedMas[k] = mas[i];
        i++;
      }
      else {
        sortedMas[k] = mas[j];
        j++;
        counter += middle - i + 1;
      }
      k++;
    }

    while (i <= middle) {
      sortedMas[k] = mas[i];
      i++;
      k++;
    }

    while (j <= right) {
      sortedMas[k] = mas[j];
      j++;
      k++;
    }

    System.arraycopy(sortedMas, left, mas, left, right - left + 1);
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
        } catch (IOException e) {
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

