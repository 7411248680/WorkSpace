package learnLambdaExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LambdaExpression {
	public static void main(String[] args) {
		
		
		List<String> list=new ArrayList<String>();
        list.add("ankit");  
        list.add("mayank");  
        list.add("irfan");  
        list.add("jai"); 
		//lambda takes three argument 
		//(argument list)
		//->row-token 
		//{body}
		
		//No Parameter Syntax
 
		int width=10;
		Drawable d=(a,b)->{
			System.out.println(a+b);
		};
		d.draw(10, 20);
		
		Set<String> set=new TreeSet<String>(list);
		
		set.forEach(
				(n)->System.out.println(n)
				
				);
	
		
		
		
		list.forEach(
				
				(l)->
				{
					System.out.println(l);
				}
				
				);
		
		
		Drawable d2=(a,b)->
		{
			System.out.println((a+b)+","+ (a*b) +","+(a-b)+","+(a/b));
//			System.out.println(a*b);
//			System.out.println(a-b);
//			System.out.println(a/b);
		};
		
		d2.draw(20, 30);
		
		
		/////Thread
		
		Runnable r=()->
		{
			System.out.println("Thread is running1");
		};
		
		Thread t=new Thread(r);
		t.start();
		System.out.println(t.getName());
		
		
		
		
		List<Product> product=new ArrayList<Product>();
		
		product.add(new Product(30, "phone", 75755));
		product.add(new Product(20, "pen", 564));
		product.add(new Product(60, "aook", 60));
		

		Collections.sort(product,(p1,p2)->{
		return p1.name.compareTo(p2.name);				
		});
			
		
		product.forEach(
				(n)->
				{
					System.out.println(n.getId()+" "+n.getName()+" "+n.getPrice());
				}
				);
		
	}

}