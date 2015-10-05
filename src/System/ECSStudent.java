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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Limitations
 */
public class ECSStudent implements Student {

	private String name;
	private int id;
	private String degree;
	private String efts;

	private String primarySupervisor;
	private String supervisionSplit1;
	private String secondarySupervisor;
	private String supervisionSplit2;
	private String thirdSupervisor;
	private String supervisionSplit3;

	private String scholarship;
	private Date startDate;

	private String phdProposalSubmission;
	private String phdProposalSeminar;
	private String phdProposalConfirmationDate;
	private String suspensionDates;
	private String thesisSubmissionAndExaminersAppointedDate;

	private String fgrCompletesExamination;
	private String revisionsFinalised;
	private String depositedInLibrary;
	private String notes;
	private String origin;

	private int timeSinceStartDate; // generated column

	private boolean isHighlighted;
	private boolean isMarked;
	private boolean isIncorrectlyFormatted = false;

	public ECSStudent(String name, int id, String degree, String efts,
			String primarySupervisor, String supervisionSplit1,
			String secondarySupervisor, String supervisionSplit2,
			String thirdSupervisor, String supervisionSplit3,
			String scholarship, String startDate, String phdProposalSubmission,
			String phdProposalSeminar, String phdProposalConfirmationDate,
			String suspensionDates,
			String thesisSubmissionAndExaminersAppointedDate,
			String fgrCompletesExamination, String revisionsFinalised,
			String depositedInLibrary, String notes, String origin) {
		this.name = name;
		this.id = id;
		this.degree = degree;
		this.efts = efts;
		this.primarySupervisor = primarySupervisor;
		this.supervisionSplit1 = supervisionSplit1;
		this.secondarySupervisor = secondarySupervisor;
		this.supervisionSplit2 = supervisionSplit2;
		this.thirdSupervisor = thirdSupervisor;
		this.supervisionSplit3 = supervisionSplit3;
		this.scholarship = scholarship;

		// Need to try convert the date
		Date stDate = convertToDate(startDate);
		this.startDate = stDate;
		if (!stDate.isConverted()) {
			isIncorrectlyFormatted = true;
		}

		this.phdProposalSubmission = phdProposalSubmission;
		this.phdProposalSeminar = phdProposalSeminar;
		this.phdProposalConfirmationDate = phdProposalConfirmationDate;
		this.suspensionDates = suspensionDates;
		this.thesisSubmissionAndExaminersAppointedDate = thesisSubmissionAndExaminersAppointedDate;
		this.fgrCompletesExamination = fgrCompletesExamination;
		this.revisionsFinalised = revisionsFinalised;
		this.depositedInLibrary = depositedInLibrary;
		this.notes = notes;
		this.origin = origin;

		this.timeSinceStartDate = generateTimeSinceStartDate();
	}

	public ECSStudent(String[][] headersAndValues) {
		String value = null;
		int valueInt = 0;
		Date valueDate = null;

		value = findValueForHeader("Name", headersAndValues);
		this.name = value;

		valueInt = Integer.parseInt(findValueForHeader("ID", headersAndValues));
		this.id = valueInt;

		value = findValueForHeader("Degree", headersAndValues);
		this.degree = value;

		value = findValueForHeader("EFTS", headersAndValues);
		this.efts = value;

		value = findValueForHeader("Primary Supervisor", headersAndValues);
		this.primarySupervisor = value;

		value = findValueForHeader("Supervision Split 1", headersAndValues);
		this.supervisionSplit1 = value;

		value = findValueForHeader("Secondary Supervisor", headersAndValues);
		this.secondarySupervisor = value;

		value = findValueForHeader("Supervision Split 2", headersAndValues);
		this.supervisionSplit2 = value;

		value = findValueForHeader("Third Supervisor", headersAndValues);
		this.thirdSupervisor = value;

		value = findValueForHeader("Supervision Split 3", headersAndValues);
		this.supervisionSplit3 = value;

		value = findValueForHeader("Scholarship", headersAndValues);
		this.scholarship = value;

		value = findValueForHeader("Start Date", headersAndValues);
		valueDate = convertToDate(value);
		this.startDate = valueDate;
		if (!this.startDate.isConverted()) {
			this.isIncorrectlyFormatted = true;
		}

		value = findValueForHeader("PhD Proposal Submission", headersAndValues);
		this.phdProposalSubmission = value;

		value = findValueForHeader("PhD Proposal Seminar", headersAndValues);
		this.phdProposalSeminar = value;

		value = findValueForHeader("PhD Proposal Confirmation Date",
				headersAndValues);
		this.phdProposalConfirmationDate = value;

		value = findValueForHeader("Suspension Dates", headersAndValues);
		this.suspensionDates = value;

		value = findValueForHeader(
				"Thesis Submission+Examiners Appointed Date",
				headersAndValues);
		this.thesisSubmissionAndExaminersAppointedDate = value;

		value = findValueForHeader("FGR Completes Examination",
				headersAndValues);
		this.fgrCompletesExamination = value;

		value = findValueForHeader("Revisions Finalised", headersAndValues);
		this.revisionsFinalised = value;

		value = findValueForHeader("Deposited In Library", headersAndValues);
		this.depositedInLibrary = value;

		value = findValueForHeader("Origin", headersAndValues);
		this.origin = value;

		value = findValueForHeader("Notes", headersAndValues);
		this.notes = value;

		this.timeSinceStartDate = generateTimeSinceStartDate();
	}

	/**
	 * Maps the header to a value in the 2D array
	 *
	 * @param header
	 *            to look for in the 2D array
	 * @param student
	 *            which is the value that corresponds to the header
	 * @return the value
	 */
	public String findValueForHeader(String header, String[][] student) {
		// Go through the student 2D array looking for header
		String value = null;
		for (int i = 0; i < student.length; i++) {
			if (student[i][0].equalsIgnoreCase(header)) {
				value = student[i][1];
			}
		}
		if (value == null) {
			System.out.println("Couldn't find value for header: " + header);
		}
		return value;
	}

	/**
	 * When editing a student, the date String that GUI uses is of a readable
	 * format. The Student object stores the date String in a different format,
	 * so need to convert it the readable string back into the format that the
	 * Student class recognizes.
	 *
	 * @param date
	 * @return the String reformated in the way the ECSStudent class understands
	 */ /*
	public String convertDateString(String date)
			throws StringIndexOutOfBoundsException {
		// TODO error checking to make sure date is of correct format... in case
		// the user changes it
		String day = date.substring(0, 2);
		String month = date.substring(3, 5);
		String year = date.substring(6, 10);
		return year + month + day;
	}
*/
	/**
	 * First converts the suspended dates field into Dates. Then calculates the
	 * number of days that the user has suspended their PHD for
	 */
	public int suspendedMonths() {
		// Must be in the format YYYYMMDD - YYYYMMDD, ....
		int monthsSuspended = 0;
		if (!suspensionDates.equals("")) {
			String[] suspendedDatesPeriods = suspensionDates.split(",");

			for (int i = 0; i < suspendedDatesPeriods.length; i++) {
				String period = suspendedDatesPeriods[i];
				// Format for period YYYYMMDD - YYYYMMDD
				String[] dates = period.split("-");
				if (dates.length != 2) {
					isIncorrectlyFormatted = true;
					return 0;
				}

				Date startDate = convertToDate(dates[0].trim());
				if (!startDate.isConverted()) {
					isIncorrectlyFormatted = true;
					return 0;
				}

				Date endDate = convertToDate(dates[1].trim());
				if (!endDate.isConverted()) {
					isIncorrectlyFormatted = true;
					return 0;
				}
				int yearDiff = endDate.getYear() - startDate.getYear();
				int monthDiff = endDate.getMonth() - startDate.getMonth();
				int dayDiff = endDate.getDay() - startDate.getDay();

				// Edge Cases begin --------
				if (dayDiff == 0 && monthDiff == 0 && yearDiff == 0) {
					isIncorrectlyFormatted = true;
					return 0;
				}
				if (yearDiff < 0) {
					isIncorrectlyFormatted = true;
				}
				if (monthDiff < 0 && yearDiff == 0) {
					isIncorrectlyFormatted = true;
					return 0;
				}
				if (dayDiff < 0 && monthDiff == 0 && yearDiff == 0) {
					isIncorrectlyFormatted = true;
					return 0;
				}
				// Edge Cases end ----------

				monthsSuspended = (yearDiff * 12) + monthDiff + 1;
			}
		}
		return monthsSuspended;
	}

	/**
	 * Calculates how many months since the student began their PhD.
	 *
	 * @return int of how many months since the student began their PhD
	 */
	private int generateTimeSinceStartDate() {
		if (isIncorrectlyFormatted == true) {
			// The startDate has is incorrectly formatted, therefore time cannot
			// be calculated
			return 0;
		}
		// Find the current year and month
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String currentDateString = dateFormat.format(calobj.getTime());
		String formattedCurrentDate = currentDateString.substring(0, 10)
				.replace("/", "");

		int currentYear = Integer
				.parseInt(formattedCurrentDate.substring(0, 4));
		int currentMonth = Integer.parseInt(formattedCurrentDate
				.substring(4, 6));
		int currentDay = Integer.parseInt(formattedCurrentDate.substring(6, 8));

		Date currentDate = new Date(currentDay, currentMonth, currentYear);

		// Get year and month difference and use that to calculate the end
		// result
		int yearDiff = currentYear - startDate.getYear();
		int monthDiff = currentMonth - startDate.getMonth();

		int daysSuspended = suspendedMonths();
		return (yearDiff * 12 + monthDiff - suspendedMonths());
	}

	/**
	 * Converts the date string (which is in the format of YYYYMMDD) into a Date
	 * object.
	 *
	 * @param dateArgument
	 *            which is a string that needs to be converted into a Date
	 * @return Date
	 */
	public Date convertToDate(String dateArgument) {
		dateArgument = dateArgument.trim();
		Date date = null;
		int year = -1;
		int month = -1;
		int day = -1;

		// Format of string YYYYMMDD
		if (dateArgument.length() != 8) {
			date = new Date(dateArgument);
			return date;
		}

		int mode = 0; // 0 = year, 1 = month, 2 = day
		int pointer = 0;
		char[] buffer = new char[4];

		// Go through the date string, and get values for day
		// month and year
		for (int i = 0; i < dateArgument.length(); i++) {
			char character = dateArgument.charAt(i);
			if (Character.isDigit(character) == false) {
				date = new Date(dateArgument);
				return date;
			}
			buffer[pointer] = character;
			pointer++;
			if (pointer == 4 && mode == 0) {
				// We have finished making the year string
				String yearString = new String(buffer);
				year = Integer.parseInt(yearString);
				mode++;
				buffer = new char[2];
				pointer = 0;
			}
			if (pointer == 2 && mode == 1) {
				// We have finished making the month string
				String monthString = new String(buffer);
				month = Integer.parseInt(monthString);
				mode++;
				buffer = new char[2];
				pointer = 0;
			}
			if (pointer == 2 && mode == 2) {
				// We have finished making the day string
				String dayString = new String(buffer);
				day = Integer.parseInt(dayString);
				break;
			}
		}
		// If the day, month and year have been assigned correctly
		// construct a Date object
		if (day != -1 && month != -1 && year != -2) {
			date = new Date(day, month, year);
		}
		// If date has still not been assigned, the Date object will
		// have to just use the dateArgument string
		if (date == null) {
			date = new Date(dateArgument);
		}

		return date;
	}

	/**
	 * Uses the list of headers provided as an argument
	 */
	public String[] getValues(String[] headers) {
		String[] values = new String[headers.length];
		for (int i = 0; i < headers.length; i++) {
			String header = headers[i];
			String value;
			switch (header.toUpperCase()) {
			case "NAME":
				value = name;
				break;
			case "ID":
				value = "" + id;
				break;
			case "DEGREE":
				value = degree;
				break;
			case "EFTS":
				value = efts;
				break;
			case "PRIMARY SUPERVISOR":
				value = primarySupervisor;
				break;
			case "SUPERVISION SPLIT 1":
				value = supervisionSplit1;
				break;
			case "SECONDARY SUPERVISOR":
				value = secondarySupervisor;
				break;
			case "SUPERVISION SPLIT 2":
				value = supervisionSplit2;
				break;
			case "THIRD SUPERVISOR":
				value = thirdSupervisor;
				break;
			case "SUPERVISION SPLIT 3":
				value = supervisionSplit3;
				break;
			case "SCHOLARSHIP":
				value = scholarship;
				break;
			case "START DATE":
				value = startDate.toString();
				break;
			case "PHD PROPOSAL SUBMISSION":
				value = phdProposalSubmission;
				break;
			case "PHD PROPOSAL SEMINAR":
				value = phdProposalSeminar;
				break;
			case "PHD PROPOSAL CONFIRMATION DATE":
				value = phdProposalConfirmationDate.toString();
				break;
			case "SUSPENSION DATES":
				value = "";
				value = suspensionDates;
				break;
			case "THESIS SUBMISSION+EXAMINERS APPOINTED DATE":
				value = thesisSubmissionAndExaminersAppointedDate;
				break;
			case "FGR COMPLETES EXAMINATION":
				value = fgrCompletesExamination;
				break;
			case "REVISIONS FINALISED":
				value = revisionsFinalised;
				break;
			case "DEPOSITED IN LIBRARY":
				value = depositedInLibrary;
				break;
			case "NOTES":
				value = notes;
				break;
			case "ORIGIN":
				value = origin;
				break;
			case "TOTAL TIME TAKEN":
				value = "" + timeSinceStartDate;
				break;
			default:
				value = null; // added as null cause it will cause an error,
								// which makes the issue easily identifiable
				System.out.println("Could not find value for header " + header);
				break;
			}
			values[i] = value;
		}
		return values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEfts() {
		return efts;
	}

	public void setEfts(String efts) {
		this.efts = efts;
	}

	public String getPrimarySupervisor() {
		return primarySupervisor;
	}

	public void setPrimarySupervisor(String primarySupervisor) {
		this.primarySupervisor = primarySupervisor;
	}

	public String getSupervisionSplit1() {
		return supervisionSplit1;
	}

	public void setSupervisionSplit1(String supervisionSplit1) {
		this.supervisionSplit1 = supervisionSplit1;
	}

	public String getSecondarySupervisor() {
		return secondarySupervisor;
	}

	public void setSecondarySupervisor(String secondarySupervisor) {
		this.secondarySupervisor = secondarySupervisor;
	}

	public String getThirdSupervisor() {
		return thirdSupervisor;
	}

	public void setThirdSupervisor(String thirdSupervisor) {
		this.thirdSupervisor = thirdSupervisor;
	}

	public String getSupervisionSplit2() {
		return supervisionSplit2;
	}

	public void setSupervisionSplit2(String supervisionSplit2) {
		this.supervisionSplit2 = supervisionSplit2;
	}

	public String getSupervisionSplit3() {
		return supervisionSplit3;
	}

	public void setSupervisionSplit3(String supervisionSplit3) {
		this.supervisionSplit3 = supervisionSplit3;
	}

	public String getScholarship() {
		return scholarship;
	}

	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}

	public String getStartDate() {
		return startDate.toString();
	}

	public void setStartDate(String startDate) {
		Date date = new Date(startDate);
		this.startDate = date;
	}

	public String getPhdProposalSubmission() {
		return phdProposalSubmission;
	}

	public void setPhdProposalSubmission(String phdProposalSubmission) {
		this.phdProposalSubmission = phdProposalSubmission;
	}

	public String getPhdProposalSeminar() {
		return phdProposalSeminar;
	}

	public void setPhdProposalSeminar(String phdProposalSeminar) {
		this.phdProposalSeminar = phdProposalSeminar;
	}

	public String getPhdProposalConfirmationDate() {
		return phdProposalConfirmationDate;
	}

	public void setPhdProposalConfirmationDate(
			String phdProposalConfirmationDate) {
		this.phdProposalConfirmationDate = phdProposalConfirmationDate;
	}

	public String getThesisSubmissionAndExaminersAppointedDate() {
		return thesisSubmissionAndExaminersAppointedDate;
	}

	public void setThesisSubmissionAndExaminersAppointedDate(
			String thesisSubmissionAndExaminersAppointedDate) {
		this.thesisSubmissionAndExaminersAppointedDate = thesisSubmissionAndExaminersAppointedDate;
	}

	public String getSuspensionDates() {
		return suspensionDates;
	}

	public void setSuspensionDates(String suspensionDates) {
		this.suspensionDates = suspensionDates;
	}

	public String getFgrCompletesExamination() {
		return fgrCompletesExamination;
	}

	public void setFgrCompletesExamination(String fgrCompletesExamination) {
		this.fgrCompletesExamination = fgrCompletesExamination;
	}

	public String getRevisionsFinalised() {
		return revisionsFinalised;
	}

	public void setRevisionsFinalised(String revisionsFinalised) {
		this.revisionsFinalised = revisionsFinalised;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDepositedInLibrary() {
		return depositedInLibrary;
	}

	public void setDepositedInLibrary(String depositedInLibrary) {
		this.depositedInLibrary = depositedInLibrary;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getTimeSinceStartDate() {
		return timeSinceStartDate;
	}

	public String toFoswiki() {
		ArrayList<String> foswikiString = new ArrayList<String>();
		foswikiString.add(name);
		foswikiString.add("" + id);
		foswikiString.add(degree);
		foswikiString.add(efts);
		foswikiString.add(primarySupervisor);
		foswikiString.add(supervisionSplit1);
		foswikiString.add(secondarySupervisor);
		foswikiString.add(supervisionSplit2);
		foswikiString.add(thirdSupervisor);
		foswikiString.add(supervisionSplit3);
		foswikiString.add(scholarship);
		foswikiString.add(startDate.toString()); // this may need to be changed
		foswikiString.add(phdProposalSubmission);
		foswikiString.add(phdProposalSeminar);
		foswikiString.add(phdProposalConfirmationDate);
		foswikiString.add(suspensionDates);
		foswikiString.add(thesisSubmissionAndExaminersAppointedDate);
		foswikiString.add(fgrCompletesExamination);
		foswikiString.add(revisionsFinalised);
		foswikiString.add(depositedInLibrary);
		foswikiString.add(notes);
		foswikiString.add(origin);

		String list = "| ";
		for (String s : foswikiString) {
			list += s + " | ";
		}

		return list;
	}

	public boolean isHighlighted() {
		return isHighlighted;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public boolean isIncorrectlyFormatted() {
		return isIncorrectlyFormatted;
	}

	public void toggleMark() {
		if (isMarked == true) {
			isMarked = false;
		} else {
			isMarked = true;
		}
	}

}
