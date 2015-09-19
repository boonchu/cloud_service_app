import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class asList{

   public static void main(String[] args) throws IOException {

	map.put("mango", new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12)));
	
	String key = "mango";
	int number = 42;
	if (map.get(key) == null)
		map.put(key, new ArrayList<Integer>());
	map.get(key).add(number);	

	for (Entry<String, ArrayList<Integer>> ee : map.entrySet()) {
    		String key = ee.getKey();
    		ArrayList<Integer> values = ee.getValue();
    		// TODO: Do something.
    	}

   }
}
