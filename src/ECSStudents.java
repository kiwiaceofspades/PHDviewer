import java.util.ArrayList;


public class ECSStudents {

	/*

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
					for(int j = 0; j<suspensionDates.size(); j++){
						if(j == 0){
							value = suspensionDates.get(j).toString();
						}
						else if(j % 2 == 1){
							value = value + " - " + suspensionDates.get(j);
						}
						else{
							value = value + ", " + suspensionDates.get(j);
						}
					}
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDegree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDegree(String degree) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEfts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEfts(String efts) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPrimarySupervisor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimarySupervisor(String primarySupervisor) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSupervisionSplit1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSupervisionSplit1(String supervisionSplit1) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSecondarySupervisor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSecondarySupervisor(String secondarySupervisor) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getThirdSupervisor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setThirdSupervisor(String thirdSupervisor) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSupervisionSplit2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSupervisionSplit2(String supervisionSplit2) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSupervisionSplit3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSupervisionSplit3(String supervisionSplit3) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getScholarship() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScholarship(String scholarship) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartDate(Date startDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPhdProposalSubmission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPhdProposalSubmission(String phdProposalSubmission) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPhdProposalSeminar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPhdProposalSeminar(String phdProposalSeminar) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getPhdProposalConfirmationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPhdProposalConfirmationDate(Date phdProposalConfirmationDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getThesisSubmissionAndExaminersAppointedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setThesisSubmissionAndExaminersAppointedDate(
			String thesisSubmissionAndExaminersAppointedDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Date> getSuspensionDates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSuspensionDates(ArrayList<Date> suspensionDates) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFgrCompletesExamination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFgrCompletesExamination(String fgrCompletesExamination) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRevisionsFinalised() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRevisionsFinalised(String revisionsFinalised) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNotes(String notes) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDepositedInLibrary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDepositedInLibrary(String depositedInLibrary) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOrigin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrigin(String origin) {
		// TODO Auto-generated method stub

	}*/

}
