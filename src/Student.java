import java.util.ArrayList;
import java.util.Date;


public class Student {

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
	private Date phdProposalConfirmationDate;
	private ArrayList<Date> suspensionDates;
	private String thesisSubmissionAndExaminersAppointedDate;

	private String fgrCompletesExamination;
	private String revisionsFinalised;
	private String depositedInLibrary;
	private String notes;
	private String origin;

	public Student(String name, int id, String degree, String efts,
			String primarySupervisor, String supervisionSplit1,
			String secondarySupervisor, String supervisionSplit2,
			String thirdSupervisor, String supervisionSplit3,
			String scholarship, Date startDate, String phdProposalSubmission,
			String phdProposalSeminar, Date phdProposalConfirmationDate,
			ArrayList<Date> suspensionDates,
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
		this.startDate = startDate;
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

	public String[] getValues(String[] headers){
		return null;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Date getPhdProposalConfirmationDate() {
		return phdProposalConfirmationDate;
	}

	public void setPhdProposalConfirmationDate(
			Date phdProposalConfirmationDate) {
		this.phdProposalConfirmationDate = phdProposalConfirmationDate;
	}

	public String getThesisSubmissionAndExaminersAppointedDate() {
		return thesisSubmissionAndExaminersAppointedDate;
	}

	public void setThesisSubmissionAndExaminersAppointedDate(
			String thesisSubmissionAndExaminersAppointedDate) {
		this.thesisSubmissionAndExaminersAppointedDate = thesisSubmissionAndExaminersAppointedDate;
	}

	public ArrayList<Date> getSuspensionDates() {
		return suspensionDates;
	}

	public void setSuspensionDates(ArrayList<Date> suspensionDates) {
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




}
