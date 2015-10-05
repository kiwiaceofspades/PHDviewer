/**
 *
 * Copyright (C) 2015  Michael Millward
 *
 * This file is part of PHDViewer.
 *
 * PHDViewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PHDViewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

			String dayString;
			String monthString;

			if(day < 10){
				dayString = "0"+day;
			}
			else{
				dayString = ""+day;
			}

			if(month < 10){
				monthString = "0"+month;
			}
			else{
				monthString = ""+month;
			}

			return ""+year+""+monthString+""+dayString;
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
