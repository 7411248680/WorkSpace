package practice;

public class Find {
	public static void main(String[] args) {
		
		long m=1000;
		int n=10;
		int total=n;
		boolean status=false;
		int count=1;
		int temp=1;
while(total<m)
{

	for(int k=1;k<20;k++)
	{
		 temp=total*n;
		total=temp;	
		count++;
		
	if(total==m)		
	{
		System.out.println("true");
		status=true;
		System.out.println("Power is:"+count);
		break;
	}
	
	}

}
if(status==false)
{
	System.out.println("false");
}

	}
	
}