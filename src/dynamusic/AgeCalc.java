package dynamusic;

import java.util.Date;

public class AgeCalc {
  
//	given a date, return the number of days since that date (this does not take in to account leap years)
	public static int ageInDays(Date date) {
		double secondsInDay = 24*60*60;
		long ageAsSeconds = ageInSeconds(date);
		long ageAsDays = (long) (ageAsSeconds / secondsInDay);
		return (int) ageAsDays;
	}
	
//	given a date, return the number of years since that date 
	public static int ageInYears(Date date) {
		double secondsInYear = 365.25*24*60*60;
		long ageAsSeconds = ageInSeconds(date);
		long ageAsYears = (long) (ageAsSeconds / secondsInYear);
		return (int) ageAsYears;
	}
	
//	given a date, return the number of seconds since that date 
	public static long ageInSeconds(Date date) {
		Date now = new Date();
		long ageAsTime = now.getTime() - date.getTime();
		long ageAsSeconds = ageAsTime / 1000;
		return ageAsSeconds;
	}

}
