package System;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

	public ECSStudent(String[][] headersAndValues){
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
		valueDate = convertToDate(convertDateString(value));
		this.startDate = valueDate;

		value = findValueForHeader("PhD Proposal Submission", headersAndValues);
		this.phdProposalSubmission = value;

		value = findValueForHeader("PhD Proposal Seminar", headersAndValues);
		this.phdProposalSeminar = value;

		value = findValueForHeader("PhD Proposal Confirmation Date", headersAndValues);
		this.phdProposalConfirmationDate = value;

		value = findValueForHeader("Suspension Dates", headersAndValues);
		this.suspensionDates = value;

		value = findValueForHeader("Thesis Submission+Examiners Appointed Date", headersAndValues);
		this.thesisSubmissionAndExaminersAppointedDate = value;

		value = findValueForHeader("FGR Completes Examination", headersAndValues);
		this.supervisionSplit3 = value;

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

	public String findValueForHeader(String header, String[][] student){
		// Go through the student object looking for header ID
		String value = null;
		for(int i = 0; i<student.length; i++){
			if(student[i][0].equalsIgnoreCase(header)){
				value = student[i][1];
			}
		}
		if(value == null){
			System.out.println("Couldn't find value for header: " + header);
		}
		return value;
	}

	/**
	 * When editing a student, the date the GUI holds needs to be a date that
	 * the student object recognizes
	 *
	 * @param date
	 * @return
	 */
	public String convertDateString(String date) throws StringIndexOutOfBoundsException {
		// format here is dd-mm-yyyy. needs to be format yyyymmdd
		// TODO error checking to make sure date is of correct format... in case
		// the user changes it
		String day = date.substring(0, 2);
		String month = date.substring(3, 5);
		String year = date.substring(6, 10);
		return year + month + day;
	}

	/**
	 *
	 * @return
	 */
	private int generateTimeSinceStartDate() {
		if(isIncorrectlyFormatted == true){
			// The startDate has is incorrectly formatted, therefore time cannot be calculated
			return 0;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String currentDateString = dateFormat.format(calobj.getTime());
		String formattedCurrentDate = currentDateString.substring(0, 10).replace("/", "");
		int currentYear = Integer.parseInt(formattedCurrentDate.substring(0, 4));
		int currentMonth = Integer.parseInt(formattedCurrentDate.substring(4, 6));
		// year difference
		int yearDiff = currentYear - startDate.getYear();
		System.out.println("current months: " + yearDiff*12);
		int	monthDiff = currentMonth - startDate.getMonth();
		return (yearDiff*12 + monthDiff);
	}

	/*
	 * Date gets parsed as a String. convertToDate converts the String into a Date object
	 */
	public Date convertToDate(String dateArgument) {
		dateArgument = dateArgument.trim();
		Date date = null;
		int year = -1;
		int month = -1;
		int day = -1;

		// Format of string YYYYMMDD
		if(dateArgument.length() != 8){
			date = new Date(dateArgument);
			isIncorrectlyFormatted = true;
			System.out.println("Not correct length " + dateArgument);
			return date;
		}

		int mode = 0; // 0 = year, 1 = month, 2 = day
		int pointer = 0;
		char[] buffer = new char[4];
		for(int i = 0; i<dateArgument.length(); i++){
			char character = dateArgument.charAt(i);
			if(Character.isDigit(character) == false){
				date = new Date(dateArgument);
				isIncorrectlyFormatted = true;
				return date;
			}
			buffer[pointer] = character;
			pointer++;
			if(pointer == 4 && mode == 0){
				// We have a year done
				String yearString = new String(buffer);
				year = Integer.parseInt(yearString);
				mode++;
				buffer = new char[2];
				pointer = 0;
			}
			if(pointer == 2 && mode == 1){
				// We have a month done
				String monthString = new String(buffer);
				month = Integer.parseInt(monthString);
				mode++;
				buffer = new char[2];
				pointer = 0;
			}
			if(pointer == 2 && mode == 2){
				// We have a month done
				String dayString = new String(buffer);
				day = Integer.parseInt(dayString);
				break;
			}
		}

		// All done!
		if(day != -1 && month != -1 && year != -2){
			date = new Date(day, month, year);
			isIncorrectlyFormatted = false;
		}

		if(date == null){
			System.out.println("Converting date did not work!");
		}

		return date;
	}

	/*
	 * Requests the values for a list of headers provided as an argument
	 */
	public String[] getValues(String[] headers){
		String[] values = new String[headers.length];
		for(int i = 0; i<headers.length; i++){
			String header = headers[i];
			String value;
			switch (header.toUpperCase()){
				case "NAME":
					value = name;
					break;
				case "ID":
					value = ""+id;
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
					value = null; // added as null cause it will cause an error, which makes the issue easily identifiable
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

	public String toFoswiki(){
		ArrayList<String> foswikiString = new ArrayList<String>();
		foswikiString.add(name);
		foswikiString.add(""+id);
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

		String list = "|";
		for(String s : foswikiString){
			list += s + "|";
		}

		return list;
	}

	public boolean isHighlighted(){
		return isHighlighted;
	}

	public boolean isMarked(){
		return isMarked;
	}

	public boolean isIncorrectlyFormatted(){
		return isIncorrectlyFormatted;
	}

	public void toggleMark(){
		if(isMarked == true){
			isMarked = false;
		}
		else{
			isMarked = true;
		}
	}

}
