package System;
public class PhDData {

	private UnderExamination underExamination;
	private PhDProposalUnderExamination phDProposalUnderExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;
	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

	private Parser parser;

	public PhDData(String filename){
		parser = new Parser(filename, this);
	}

	public UnderExamination getUnderExamination() {
		return underExamination;
	}
	public void setUnderExamination(UnderExamination underExamination) {
		this.underExamination = underExamination;
	}
	public CurrentFullyRegistered getCurrentFullyRegistered() {
		return currentFullyRegistered;
	}
	public void setCurrentFullyRegistered(CurrentFullyRegistered currentFullyRegistered) {
		this.currentFullyRegistered = currentFullyRegistered;
	}
	public CurrentProvisionallyRegisteredStudents getCurrentProvisionallyRegisteredStudents() {
		return currentProvisionallyRegisteredStudents;
	}
	public void setCurrentProvisionallyRegisteredStudents(
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents) {
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
	}
	public NotFullyAdmitted getNotFullyAdmitted() {
		return notFullyAdmitted;
	}
	public void setNotFullyAdmitted(NotFullyAdmitted notFullyAdmitted) {
		this.notFullyAdmitted = notFullyAdmitted;
	}
	public OtherUniversities getOtherUniversities() {
		return otherUniversities;
	}
	public void setOtherUniversities(OtherUniversities otherUniversities) {
		this.otherUniversities = otherUniversities;
	}
	public OtherSchoolsAtVUW getOtherSchoolsAtVUW() {
		return otherSchoolsAtVUW;
	}
	public void setOtherSchoolsAtVUW(OtherSchoolsAtVUW otherSchoolsAtVUW) {
		this.otherSchoolsAtVUW = otherSchoolsAtVUW;
	}

	public PhDProposalUnderExamination getPhDProposalUnderExamination() {
		return phDProposalUnderExamination;
	}

	public void setPhDProposalUnderExamination(
			PhDProposalUnderExamination phDProposalUnderExamination) {
		this.phDProposalUnderExamination = phDProposalUnderExamination;
	}

}
