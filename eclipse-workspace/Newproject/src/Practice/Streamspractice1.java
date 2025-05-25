package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.collect.Streams;

public class Streamspractice1 {
	public static void main(String[] args)
	{
		
		ArrayList<String> names= new ArrayList<String>();
		names.add("Anusha");
		names.add("Tanusha");
		names.add("Ranusha");
		names.add("Anu");
		Long Count=names.stream().filter(s->s.startsWith("A")).count();
		System.out.println(Count);
		Long count1=Stream.of("Mayur","Kanase","Tanish","Mamatha").filter(s->s.length()>4).count();
		System.out.println(count1);
		names.stream().filter(s->s.startsWith("A")).forEach(s->System.out.println(s));
		names.stream().filter(s->s.startsWith("A")).map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		Stream.of(5,6,7,2,5,6,3,7).distinct().forEach(s->System.out.println(s));
		names.stream().filter(s->s.startsWith("A")).limit(1).forEach(s->System.out.println(s));
		ArrayList<String> names1= new ArrayList<String>();
		names1.add("Mayur");
		names1.add("Tanu");
		names1.add("Bhanu");
		Stream<String> Newstream= Stream.concat(names.stream(), names1.stream());
		Newstream.sorted().forEach(s->System.out.println(s));
		List<Integer> list=Arrays.asList(3,5,5,2,2);
		list.stream().distinct().forEach(s->System.out.println(s));
		List<String> states=Stream.of("Karnataka","Delhi","Maharshatra").map(s->s.toLowerCase()).collect(Collectors.toList());
		System.out.println(states.get(1));
	}

	
}
