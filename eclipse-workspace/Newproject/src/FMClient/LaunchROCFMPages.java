package FMClient;



import java.util.Collections;
import org.testng.TestNG;

public class LaunchROCFMPages {
	
	public static void main(String[] args) {
		
		TestNG testng = new TestNG();
		  testng.setTestSuites(Collections.singletonList("testng.xml"));
		  testng.setVerbose(2);
		  testng.run();
		
	}
		  
}

