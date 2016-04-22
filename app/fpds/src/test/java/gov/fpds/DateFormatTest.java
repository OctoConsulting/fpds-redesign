package gov.fpds;

import java.text.DateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DateFormatTest {

	public static void main(String[] args) {
		final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();  
		final String jsonString = "2015-10-01";
		Date javaDate = gson.fromJson(jsonString, Date.class); 
		System.out.println("Java date is : " + javaDate);

	}

}
