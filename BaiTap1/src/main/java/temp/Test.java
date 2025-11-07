package temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Nhập một câu");
		String line = sc.nextLine();

		// 1. Tách thành từ
		String[] words = line.split("\\s+");
		
//		a a a a hehehe hoàng đẹp đẹp trai    quá  a 12

		// 2. HashSet: loại bỏ trùng lặp
		HashSet<String> wordSet = new HashSet<String>(Arrays.asList(words));

		System.out.println("Số từ duy nhất: " + wordSet.size());
		System.out.println("Các từ duy nhất (HashSet, không thứ tự): " + wordSet);
		
		// 3. LinkedHashSet: giữ thứ tự xuất hiện
		LinkedHashSet<String> linkedSet = new LinkedHashSet<String>(Arrays.asList(words));
		System.out.println("Số từ duy nhất: " + linkedSet.size());
		System.out.println("Các từ duy nhất (LinkedHashSet, thứ tự gốc): " + linkedSet);
		
		// 4. TreeSet: Sắp alphabet
		TreeSet<String> treeSet = new TreeSet<String>(linkedSet);
		System.out.println("Số từ duy nhất: " + treeSet.size());
		System.out.println("Các từ duy nhất (LinkedHashSet, thứ tự alphabet - không dấu): " + treeSet);
		
		
		
	}
}
