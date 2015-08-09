
public class PhDViewer {

	private UnderExamination underExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;
	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

	public PhDViewer(
			UnderExamination underExamination,
			CurrentFullyRegistered currentFullyRegistered,
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents,
			NotFullyAdmitted notFullyAdmitted,
			OtherSchoolsAtVUW otherSchoolsAtVUW,
			OtherUniversities otherUniversities) {
		super();
		this.underExamination = underExamination;
		this.currentFullyRegistered = currentFullyRegistered;
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
		this.notFullyAdmitted = notFullyAdmitted;
		this.otherSchoolsAtVUW = otherSchoolsAtVUW;
		this.otherUniversities = otherUniversities;
	}

	public PhDViewer(
			UnderExamination underExamination,
			CurrentFullyRegistered currentFullyRegistered,
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents,
			NotFullyAdmitted notFullyAdmitted) {
		super();
		this.underExamination = underExamination;
		this.currentFullyRegistered = currentFullyRegistered;
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
		this.notFullyAdmitted = notFullyAdmitted;
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
	public OtherSchoolsAtVUW getOtherSchoolsAtVUW() {
		return otherSchoolsAtVUW;
	}
	public void setOtherSchoolsAtVUW(OtherSchoolsAtVUW otherSchoolsAtVUW) {
		this.otherSchoolsAtVUW = otherSchoolsAtVUW;
	}
	public OtherUniversities getOtherUniversities() {
		return otherUniversities;
	}
	public void setOtherUniversities(OtherUniversities otherUniversities) {
		this.otherUniversities = otherUniversities;
	}
}
