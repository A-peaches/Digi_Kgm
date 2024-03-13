package Day8;

import java.util.HashMap;
import java.util.Set;

public class MapEx1 {
	public static void main(String[] args) {
		String[] msg = {"강아지","어피치","커비","푸바오"};

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		for (int i = 0; i < msg.length; i++) {
			map.put(i, msg[i]); //map에 1.강아지 2.어피치 3.커비 4.푸바오
		}
			Set<Integer> keys = map.keySet();
			// keys에 map의key를 set형식으루 담눈다.`
			for (Integer n : keys) {
				System.out.println(map.
						get(n));
			
			}	
			System.out.println(map.get(3));
	}
	
}
