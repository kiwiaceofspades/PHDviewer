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
			String day;
			String month;

			if(this.day < 10){
				day = "0"+this.day;
			}
			else{
				day = ""+this.day;
			}


			if(this.month < 10){
				month = "0"+this.month;
			}
			else{
				month = ""+this.month;
			}

			return day+"-"+month+"-"+year;
		}
	}

	public String toStringFormatted(){
		if(converted == false){
			return date;
		}
		else{
			return ""+year+""+month+""+day;
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

	public int since(Date dateToCompare) {
		final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
		java.util.Date currentDate = new java.util.Date(dateToCompare.year-1900, dateToCompare.month-1, dateToCompare.day);
		java.util.Date StartDate = new java.util.Date(this.year-1900, this.month-1, this.day);

		int diffInDays = Math.round((int) ((StartDate.getTime() - currentDate.getTime()) / DAY_IN_MILLIS));

		return diffInDays;
	}

}
