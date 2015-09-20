package System;
import java.util.ArrayList;


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

	private boolean isHighlighted;
	private boolean isMarked;

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
	}

	private Date convertToDate(String startDate) {
		Date date;
		// Format of string YYYYMMDD

		date = new Date(startDate);


		return date;
		/**
		// Get year
		ArrayList<String> year = new ArrayList<String>();
		for(int i = 0; i < )
		return null; */
	}

	public String[] getValues(String[] headers){
		String[] values = new String[headers.length];
		for(int i = 0; i<headers.length; i++){
			String header = headers[i];
			String value;
			switch (header){
				case "Name":
					value = name;
					break;
				case "ID":
					value = ""+id;
					break;
				case "Degree":
					value = degree;
					break;
				case "EFTS":
					value = efts;
					break;
				case "Primary Supervisor":
					value = primarySupervisor;
					break;
				case "Supervision Split 1":
					value = supervisionSplit1;
					break;
				case "Secondary Supervisor":
					value = secondarySupervisor;
					break;
				case "Supervision Split 2":
					value = supervisionSplit2;
					break;
				case "Third Supervisor":
					value = thirdSupervisor;
					break;
				case "Supervision Split 3":
					value = supervisionSplit3;
					break;
				case "Scholarship":
					value = scholarship;
					break;
				case "Start Date":
					value = startDate.toString();
					break;
				case "PhD Proposal Submission":
					value = phdProposalSubmission;
					break;
				case "PhD Proposal Seminar":
					value = phdProposalSeminar;
					break;
				case "PhD Proposal Confirmation Date":
					value = phdProposalConfirmationDate.toString();
					break;
				case "Suspension Dates":
					value = "";
					value = suspensionDates;
					break;
				case "Thesis Submission And Examiners Appointed Date":
					value = thesisSubmissionAndExaminersAppointedDate;
					break;
				case "FGR Completes Examination":
					value = fgrCompletesExamination;
					break;
				case "Revisions Finalised":
					value = revisionsFinalised;
					break;
				case "Deposited in Library":
					value = depositedInLibrary;
					break;
				case "Notes":
					value = notes;
					break;
				case "Origin":
					value = origin;
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
		foswikiString.add(startDate.toString()); // this may need to be chan
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

	public void toogleMark(){
		if(isMarked == true){
			isMarked = false;
		}
		else{
			isMarked = true;
		}
	}

}
