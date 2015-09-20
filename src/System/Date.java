package System;

public class Date {

	private int day;
	private int month;
	private int year;

	private String date; // If date is not set correctly

	private boolean converted = false;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		converted = true;
	}

	public Date(String state){
		this.date = state;
	}

	public String toString(){
		if(converted == false){
			return date;
		}
		else{
			return day+"-"+month+"-"+year;
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isConverted() {
		return converted;
	}

}
