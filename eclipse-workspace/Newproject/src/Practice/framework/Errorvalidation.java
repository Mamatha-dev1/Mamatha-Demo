package Practice.framework;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Errorvalidation extends source{
	@Test
	public void error() throws IOException, InterruptedException
	{
		Landingpage landingpage=launchapplication();
        String productname="ZARA COAT 3";  
		landingpage.logintoapplication("anshika@gmail.com", "Iamking@00");
		String errormessage=landingpage.login1();
		System.out.println(errormessage);
		Assert.assertEquals("Incorrect email or password.",errormessage );
	}

}
