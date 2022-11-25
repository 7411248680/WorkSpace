package String;

import java.util.HashSet;
import java.util.Set;

public class DublicateNumber {

	public static void main(String[] args) {
		
		int[] number=new int[] {1,3,5,2,2,3};
		
		Set<Integer> set=new HashSet<Integer>();
		
		for(int a:number)
		{
			set.add(a);
		}
		System.out.println(set);

		
		
		String str="sunil mail thing sunil";
		String[] words=str.split(" ");
		
		Set<String> set1=new HashSet<String>();
		for(String s:words)
		{
			set1.add(s);
		}
		System.out.println(set1);
		
	}
}
