package String;

import java.util.HashSet;
import java.util.Set;

public class TestString {
	public static void main(String[] args) {
		
		
		String str="sunil, anil| sunil anil sunil";
		
		String[] str2=str.split("");
		String store;
		
		Set<String> set=new HashSet<String>();
		
		
		for(int i=0;i<str2.length;i++)
		{
			int count=0;
			for(int j=i+1;j<str2.length;j++)
			{
				 {
				if(str2[i].equals(str2[j]))
				{
					count++;
					str2[j]="0";
			
				}
				}
			}
		
			if(count>1 && str2[i]!="0")
			{			
			System.out.print(str2[i]);
			//System.out.print(count);
			}
		}

	}

}