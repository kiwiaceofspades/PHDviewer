package System;
public class PhDData {

	private UnderExamination underExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;
	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

	public PhDData(
			UnderExamination underExamination,
			CurrentFullyRegistered currentFullyRegistered,
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents,
			NotFullyAdmitted notFullyAdmitted,
			OtherSchoolsAtVUW otherSchoolsAtVUW,
			OtherUniversities otherUniversities) {
		this.underExamination = underExamination;
		this.currentFullyRegistered = currentFullyRegistered;
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
		this.notFullyAdmitted = notFullyAdmitted;
		this.otherSchoolsAtVUW = otherSchoolsAtVUW;
		this.otherUniversities = otherUniversities;
	}

	/* Construtor used for testing */
	public PhDData(
			UnderExamination underExamination,
			CurrentFullyRegistered currentFullyRegistered,
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents,
			NotFullyAdmitted notFullyAdmitted) {
		this.underExamination = underExamination;
		this.currentFullyRegistered = currentFullyRegistered;
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
		this.notFullyAdmitted = notFullyAdmitted;
	}
}
