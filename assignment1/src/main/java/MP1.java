package WordCount;

// Word Count MP1 : Cloud Computing Application 
// Link : Coursera.org
// Boonchu Ngampairoijpibul
// Date: September 5, 2015

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
    static boolean ASC = true;
    static boolean DESC = false;

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
        Integer[] indexes                 = getIndexes();
        SortedMap<String, Integer> words  = new TreeMap<String, Integer>();
	int index                         = -1;

	//for (Integer i : indexes) {
	//  System.out.println(i);
        //}
        // http://codereview.stackexchange.com/questions/44135/is-it-ok-to-use-while-line-r-readline-null-construct
        String line = null;
        while ((line = br.readLine()) != null) {
	  // maximum array list size is 50k (used from getIndexes())
	  if (list.size() < 50000) {
          	list.add(line);  
	  }
        }
	
        /**
        ** To make the application more interesting, you have to 
        ** process only the titles with certain indexes. These indexes are accessible using the 
        ** “getIndexes” method, which returns an Integer Array with 0-based indexes to the input 
        ** file. It is possible to have an index appear several times. In this case, just process 
        ** the index multiple times. 
        */
        // Divide each sentence into a list of words using delimiters provided in the “delimiters” variable.
	for(int i : getIndexes()) {
          // Make all the tokens lowercase and remove any tailing and leading spaces.
          line = list.get(i).toLowerCase().trim();
	  StringTokenizer st = new StringTokenizer(line, delimiters);
          while (st.hasMoreTokens()) {
            String str = st.nextToken();
	    // System.out.println(str);
            // Ignore all common words provided in the “stopWordsArray” variable.
            if (Arrays.asList(stopWordsArray).indexOf(str) == -1){
               /**
                ** Keep track of word frequencies. If matched, increases from existing word counts.
                */
               if (words.containsKey(str)) {
                 words.put(str, words.get(str)+1);
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
	Map<String, Integer> sorted_words = sortByComparator(words, DESC);
        // System.out.println(sorted_words);

	/**
 	 ** test case outputs at testcase.txt
         */
        int count = 0;
	try {
          PrintWriter output = new PrintWriter(new FileWriter("./testcase.txt"));
          for (Map.Entry<String, Integer> entry : sorted_words.entrySet())
          {
             if (count >= 20) break;
             output.println(entry.getKey() + ": "+ entry.getValue());
             count++;
          }
	  output.close();
        } catch ( IOException e ) {
        
        }

	count = 0;
        for (Map.Entry<String, Integer> entry : sorted_words.entrySet()) {
          if (count >= 20) break;
          ret[count] = entry.getKey(); 
          count++;
        }

       return ret;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {

		// https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=931
		// return (o2.getValue() > o1.getValue())? 1 : (o1.getValue() > o2.getValue())? -1 : o1.getKey().compareTo(o2.getKey());
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
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

