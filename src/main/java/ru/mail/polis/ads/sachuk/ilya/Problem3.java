package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3 {

  private static void solve(final FastScanner in, final PrintWriter out) {

    String inputLine = in.next();

    boolean correct = true;
    int bracketCounter = 0;

    for (int i = 0; i < inputLine.length(); i++) {

      if (inputLine.charAt(i) == '(') {
        bracketCounter++;
      }
      else if (inputLine.charAt(i) == ')') {
        if (bracketCounter != 0) {
          bracketCounter--;
        }
        else {
          correct = false;
          break;
        }
      }
    }
    if (bracketCounter == 0 && correct) {
      out.println("YES");
    }
    else {
      out.println("NO");
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