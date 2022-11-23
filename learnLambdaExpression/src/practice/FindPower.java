package practice;

public class FindPower {
	public static void main(String[] args) {
		int m=1000;
		int n=10;
		int total=1;
		int count=0;
		int temp=0;
		for(int i=1;i<m;i++)
		{
			temp=total*n;
			 total+=temp;
			count++;
			 if(total==m)
			 {
				 System.out.println(count);
				 break;
			 }
		}
		
		
	}

}
