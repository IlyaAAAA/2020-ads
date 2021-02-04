package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem5 {

  private static boolean check(int x, int cowsNumber, int[] mas) {
    int cows = 1;
    int last_cow = mas[0];

    for (int i: mas) {
      if (i - last_cow >= x) {
        cows++;
        last_cow = i;
      }
    }
    return cows >= cowsNumber;
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int stallsNumber = in.nextInt();
    int cowsNumber = in.nextInt();

    int[] mas = new int[stallsNumber];

    for (int i = 0; i < mas.length; i++) {
      mas[i] = in.nextInt();
    }

    int left = 0;
    int right = mas[mas.length - 1] - mas[0] + 1;

    while (right - left > 1) {
      int middle = (right + left) / 2;

      if (check(middle, cowsNumber,  mas)) {
        left = middle;
      }
      else {
        right = middle;
      }
    }
    out.println(left);
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
