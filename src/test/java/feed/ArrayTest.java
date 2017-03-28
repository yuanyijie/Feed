package feed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayTest {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("1", "a");
		map.put("2", "b");
		System.out.println(Arrays.toString(map.keySet().toArray(new String[0])));
	}

}
