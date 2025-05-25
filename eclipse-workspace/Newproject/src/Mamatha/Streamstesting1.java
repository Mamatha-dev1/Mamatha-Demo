package Mamatha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Streamstesting1 {
  @Test(priority = 1)
	public void countNamesStartingWithA() {
		// TODO Auto-generated method stub
        WebDriver driver = new ChromeDriver();
        ArrayList<String> names= new ArrayList<String>();
        names.add("Abhijeet");
        names.add("Don");
        names.add("Adam");
        names.add("Rama");
        //To find count of names starts with A
       long c= names.stream().filter(s->s.startsWith("A")).count();
       System.out.println(c);
	}
	//To Print the names starts with a and convert it to uppercase
	@Test(priority = 2)
	
	public void Streamtest()
	{
		Stream.of("abhijeet", "don", "adam", "rama")
	    .filter(s -> s.startsWith("a"))
	    .map(s -> s.toUpperCase())
	    .forEach(s -> System.out.println(s));    
}
	//To print the names which have length>3
	
@Test(priority = 3)
	
	public void Streamtest1()
	{
		Stream.of("abhijeet", "don", "adam", "ramesh","Anusha")
	    .filter(s -> s.length()>4).forEach(s -> System.out.println(s)); 
	    	       
}

//To print the unique numbers
@Test(priority = 4)
public void Streamtest2()
{
	List<Integer> numbers= Arrays.asList(2,5,2,9,3,7,4,9,1);
	numbers.stream().distinct().forEach(s -> System.out.println(s)); 
}
}