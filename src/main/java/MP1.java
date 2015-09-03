package WordCount;

import java.io.*;
import java.util.*;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MP1 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
        }
        return ret;
    }

    public MP1(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
    }

    public String[] process() throws Exception {
        String[] ret                      = new String[20];
        ArrayList<String> list            = new ArrayList<String>();
        BufferedReader br                 = new BufferedReader(new FileReader(this.inputFileName));
        SortedMap<String, Integer> words  = new TreeMap<String, Integer>();

        // http://codereview.stackexchange.com/questions/44135/is-it-ok-to-use-while-line-r-readline-null-construct
        String line = null;
        while ((line = br.readLine()) != null) {
          list.add(line);  
        }
	
        // Divide each sentence into a list of words using delimiters provided in the “delimiters” variable.
	for(String object: list){
	  StringTokenizer st = new StringTokenizer(object, delimiters);
          while (st.hasMoreElements()) {
            // Make all the tokens lowercase and remove any tailing and leading spaces.
            String str = st.nextElement().toString().toLowerCase().trim();
            // Ignore all common words provided in the “stopWordsArray” variable.
            if (Arrays.asList(stopWordsArray).indexOf(str) == -1){
               /**
                ** Keep track of word frequencies. To make the application more interesting, you have to 
                ** process only the titles with certain indexes. These indexes are accessible using the 
                ** “getIndexes” method, which returns an Integer Array with 0-based indexes to the input 
                ** file. It is possible to have an index appear several times. In this case, just process 
                ** the index multiple times. 
                */
               if (words.containsKey(str)) {
                 words.put(str, words.get(str) + 1);
               } else {
                 words.put(str, 1); 
               }
            }
          }
        }

        /**
         ** Sort the list by frequency in a descending order. If two words have the same number count, 
         ** use the lexigraphy. For example, the following is a sorted list: 
         ** {(Orange, 3), (Apple, 2), (Banana, 2)}
         */
        // words = SortByValue(words);

        int count = 0;
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
          if (count >= 20) break;
          ret[count] = entry.getKey(); 
          count++;
        }

       return ret;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP1 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "./input.txt";
            MP1 mp = new MP1(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}
