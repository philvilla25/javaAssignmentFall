import java.awt.desktop.SystemSleepEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.*;
/**
 * 
 */

/**
 * @author Phil
 *
 */
public class iterateClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner scan = new Scanner(System.in);
//		int size;
//		List<String> list = Arrays.asList("Philo ", "shit ","tae ","dump ");
//		list.forEach(System.out::print);
//	
		
		int numElements;
		Deque<String> dick = new ArrayDeque<String>(3);
		Deque<String> outDick = new ArrayDeque<String>();
		ArrayList<String> elements = new ArrayList<>();
		
		
		
		elements.add("first");
		elements.add("second");
		elements.add("third");
		elements.add("fourth");
		elements.add("fifth");
		elements.add("sixth");
		elements.add("seventh");
		elements.add("eigth");
		
		for(int i = 0; i < elements.size(); i++) {
			System.out.println("Round " + (i+1));
			
			
			
			for(int j = 0; j <= 4; j++) {
				dick.add(elements.get(j));
			}
			
		}
		System.out.println(dick);
//	    System.out.println(dick.size());	
//		
//		for(int i = 0; i < dick.size(); i++) {
//			System.out.println("Round" + (i+1));
//			//System.out.println(dick);
//			
//			String outFirst = outDick.poll();
//			for(String str: dick) {
//				outDick.addLast(str);
//			}
//			
//			System.out.println(outDick);
//			if((i + 1) % 4 == 0) {
//				System.out.println();
//			}
//		}
		
//		
//		String removeFirst;
//		for(int j = 0; j < dick.size(); j++) {
//			removeFirst= dick.poll();
//			outDick.addAll(dick);
//			outDick.addLast(removeFirst);
//		}
//		
//		System.out.println("After removing" + outDick);
//		PriorityQueue<Integer> prio = new PriorityQueue<>();
//		
//		
//		prio.add(10);
//		prio.add(100);
//		prio.add(5);
//		prio.add(69);
//		
//		
//		System.out.println(prio);
//		
//		
//		while(!prio.isEmpty()) {
//		System.out.println(prio.remove());	
//		}
		
	}

}
