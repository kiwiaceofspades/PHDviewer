import java.util.ArrayList;
import java.util.Scanner;


public class Parser {

	private static String testHeader = "| *Name* | *ID* | *Degree* | *EFTS* | *Primary Supervisor* | *Supervision Split* | *Secondary Supervisor* | *Supervision Split* | *Third Supervisor* | *Supervision Split* | *Scholarship* | *Start Date* | *PhD Proposal Submission* | *PhD Proposal Seminar* | *PhD Proposal Confirmation Date* | *Suspension Dates* | *Thesis Submission* + *Examiners Appointed Date* | *FGR Completes Examination* | *Revisions Finalised* | *Deposited in Library* | *Notes* | *Origin* |";
	private static String testStudent = "| Michael Millward | 123456789 | COMP690 | EFTSdata | Person:JamesNoble | 50% | Person:NicholasCameron | 50% | |  | Telstra Clear Postgraduate Scholarship | 20090427 | SUBMITTED | PRESENTED | CONFIRMED | | 20150116 | 20150518 | | | Revisions: 20150901. | D |";
	private Date testDate = new Date(99, 99, 99);

	public Parser() {

	}

	private void parseTitling() {

	}

	private void parseHeaders(String line) {
		String[] splitHeaders = line.split("\\|", 0);
			for (String s : splitHeaders) {
				if(s.isEmpty() || s.matches("^\\s*$")) {
					//System.out.print("----------");
				}
				s = s.replace("*", "");
				//System.out.println(s);
			}
	}

	private void parseLine(String line) {
		String[] splitLine = line.split("\\|", 0);
		for (String s : splitLine) {
			if(s.isEmpty() || s.matches("^\\s*$")) {
				System.out.print("----------");
			}
			System.out.println(s);
		}

		// Create objects for Student constructor
		int id = Integer.parseInt(splitLine[2]);

		Date startDate = parseDate(splitLine[12]);

		Date phdProposalConfirmationDate = parseDate(splitLine[15]);

		ArrayList<Date> suspensionDates = new ArrayList<Date>();
		suspensionDates.add(testDate);

		Student s = new Student(splitLine[1], id, splitLine[3], splitLine[4],
				splitLine[5], splitLine[6], splitLine[7], splitLine[8], splitLine[9], splitLine[10],
				splitLine[11], startDate,
				splitLine[13], splitLine[14], phdProposalConfirmationDate, suspensionDates, splitLine[17],
				splitLine[18], splitLine[19], splitLine[20], splitLine[21], splitLine[22]);
	}

	private Date parseDate(String date) {
		int startDateYear = Integer.parseInt(date.substring(0, 3));
		int startDateMonth = Integer.parseInt(date.substring(4, 5));
		int startDateDay = Integer.parseInt(date.substring(6, 7));

		Date d = new Date(startDateYear, startDateMonth, startDateDay);
		return d;
	}

	public static void main(String[] args) {

	}

}
