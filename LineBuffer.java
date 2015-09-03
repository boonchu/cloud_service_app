
    String text = "line1\n\n\nline4";
    BufferedReader br = new BufferedReader(new StringReader(text));
    String line;
    int lineNumber = 0;
    while ((line = br.readLine()) != null) {
      System.out.printf("%04d: %s%n", ++lineNumber, line);
    }
