package learnLambdaExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductSort {

	public static void main(String[] args) {
		List<Product> product=new ArrayList<Product>();
		
		product.add(new Product(1, "zim", 3000));
		product.add(new Product(30, "apple", 200));
		product.add(new Product(1, "pen", 50));
		
		product.forEach(
				(n)->System.out.println(n.getId()+","+n.getName()+","+n.getPrice())
				);
		
System.out.println("--------------------------------------------------------");	
		Collections.sort(product,(p1,p2)->{			
			return p1.name.compareTo(p2.name);
		});
		
		product.forEach(
				(n)->System.out.println(n.getId()+","+n.getName()+","+n.getPrice())
				);
			
		
		///Filter
		List<Float> list=product.stream().filter(p->p.price>2000)
				.map(p->p.price)
				.collect(Collectors.toList());
		

		System.out.println(list);
		
		Stream<Product> list1=product.stream().filter(p->p.getPrice()>2000);
		list1.forEach(
				(n)->System.out.println(n.getId()+","+n.getName()+","+n.getPrice())
				);
		
		//
		System.out.println("--------------------------------------------------------");	
		
		//iterate
		
		Stream.iterate(1, e->e+1)
		.filter(e->e%5==0)
		.limit(5)
		.forEach(System.out::println);
		
		System.out.println("--------------------------------------------------------");
		
		product.add(new Product(1,"HP Laptop",25000f));  
		product.add(new Product(2,"Dell Laptop",30000f));  
		product.add(new Product(3,"Lenevo Laptop",28000f));  
		product.add(new Product(4,"Sony Laptop",28000f));  
		product.add(new Product(5,"Apple Laptop",90000f));  
		
		product.stream()
		.filter(n->n.price>3000)
		.limit(4)
		.forEach(n->System.out.println(n.name));
		
		System.out.println("--------------------------------------------------------");
		
		Product product1=product.stream().max((p1,p2)->p1.getPrice()>p2.getPrice() ? 1:-1).get();
		System.out.println(product1.getPrice());
		
		System.out.println("--------------------------------------------------------");
		
		Product product2=product.stream().min((p1,p2)->p1.getPrice()>p2.getPrice() ? 1:-1).get();
		System.out.println(product2.getPrice());
		
		System.out.println("--------------------------------------------------------");
		
		long count=product.stream().filter(price->price.price>300).count();
		System.out.println(count);
	}

}