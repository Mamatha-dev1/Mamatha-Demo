package Practice.framework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retryanalyzer implements IRetryAnalyzer{
         int count=0;
         int max=1;
	@Override
	public boolean retry(ITestResult result) {
		if (count<max)
		{    count++;
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
