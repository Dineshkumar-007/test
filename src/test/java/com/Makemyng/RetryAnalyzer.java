package com.Makemyng;


	import org.testng.IRetryAnalyzer;
	import org.testng.ITestResult;

	public class RetryAnalyzer implements IRetryAnalyzer{

		int i=3,j=1;
		public boolean retry(ITestResult result) {
			
			if(i>j) {
				j++;
				return true;
			}
			return false;
			
		}

	}



	
}
