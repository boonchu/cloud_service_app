package WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Tutorial Array Demo
// http://www.tutorialspoint.com/java/util/arrays_aslist.htm
// http://stackoverflow.com/questions/16748030/arrays-aslistarray-in-java

public class ArrayDemo {

       public static void main (String args[]) {

         // create an copy of "a" array of strings
         String a[] = new String[] {"abc","klm","xyz","pqr"};
         ArrayList<String> list = new ArrayList<String>(Arrays.asList(a));
	 ArrayListDemo(list);

       }

       private static void ArrayListDemo(ArrayList<String> list) {
	
        // printing the list
         System.out.println("Index for pqr is " + list.indexOf("pqr"));
         System.out.println("The list is:" + list);

         // adding two elements
         list.add("def");
         list.add("uwx");
         System.out.println("Index for uwx is " + list.indexOf("uwx"));
         System.out.println("The list is:" + list);
	 
       }
}
