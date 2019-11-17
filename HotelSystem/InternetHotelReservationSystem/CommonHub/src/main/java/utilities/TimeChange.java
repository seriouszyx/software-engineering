package utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class TimeChange {

	public static String date2String(LocalDate date){
		return date.toString();
	}
	
	public static LocalDate string2Date(String date){
		return LocalDate.parse(date);
	}
	
	public static String dateTime2String(LocalDateTime date){
		return date.toString();
	}
	
	public static LocalDateTime string2DateTime(String date){
		return LocalDateTime.parse(date);
	}
	
	
	
	
	
}
