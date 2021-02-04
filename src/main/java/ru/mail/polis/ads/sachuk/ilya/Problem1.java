package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {

  private static int[] secondMas;

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();

    int[] mas = new int[n];
    secondMas = new int[n];

    for (int i = 0; i < n; i++) {
      mas[i] = in.nextInt();
    }

    sort(mas, 0, mas.length - 1);
    printMas(mas, out);
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
        secondMas[k] = mas[i];
        i++;
      }
      else {
        secondMas[k] = mas[j];
        j++;
      }
      k++;
    }

    while (i <= middle) {
      secondMas[k] = mas[i];
      i++;
      k++;
    }

    while (j <= right) {
      secondMas[k] = mas[j];
      j++;
      k++;
    }

    System.arraycopy(secondMas, left, mas, left, right - left + 1);
  }

  private static void printMas(int[] arr, final PrintWriter out) {

    for (int i : arr) {
      out.print(i + " ");
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
    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
      solve(in, out);
    }
  }
}