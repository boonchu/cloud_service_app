package WordCount;

//from www  .j  a va  2 s .c om
// http://www.java2s.com/Tutorials/Java/java.nio.file/Files/Java_Files_readAllLines_Path_path_Charset_cs_.htm
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class readLines {

  public static void main(String[] args) {

    Path wiki_path = Paths.get(".", "output.txt");

    Charset charset = Charset.forName("ISO-8859-1");
    try {
      List<String> lines = Files.readAllLines(wiki_path, charset);

      for (String line : lines) {
        System.out.println(line);
      }

    } catch (IOException e) {
      System.out.println(e);
    }

  }
}
