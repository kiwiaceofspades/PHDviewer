package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import System.CurrentFullyRegistered;
import System.CurrentProvisionallyRegisteredStudents;
import System.ECSStudent;
import System.NotFullyAdmitted;
import System.Parser;
import System.PhDData;
import System.PhDProposalUnderExamination;
import System.PhDTable;
import System.Student;
import System.UnderExamination;

public class UnitTest {

	private int sizeOfArray = 22;

	private String testFile = "test.txt";
	private String[][] stu = new String[sizeOfArray][2];
	private String fullyRegiTable = "currentFullyRegistered";
	private String underExamTable = "UnderExamination";

	private Student student1 = new ECSStudent("Harry", 1236, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "14/08/2015", "31/10/2015", "Prolly", "25/15/2016", "", "01/01/2017", "sds", "Nop", "Nope", "Why is the rum gone?", "Wellington");
	private Student student2 = new ECSStudent("Harry", 1232, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "14/08/2015", "31/10/2015", "Prolly", "25/15/2016", "", "01/01/2017", "sds", "Nop", "Nope", "Why is the rum gone?", "Auckland");
	private Student student3 = new ECSStudent("Alistair Eichler", 1, "COMP690", "EFTSdata", "Person:JamesNoble", "50%", "Person:NicholasCameron", "50%", "", "",  "Telstra Clear Postgraduate Scholarship", "20090427", " SUBMITTED", "PRESENTED",  "CONFIRMED", "20150116", "", "20150518", "", "", " Revisions: 20150901.", "D");
	private Student student4 = new ECSStudent("Homer Simpson", 5, "NWEN690", "", "Person:IanWelch", "70%", " Person:WinstonSeah", " 30%", "", "", "", "20100719", "20120904", "20121019" , "20121220" ,"20130601 - 20130630 , 20130801 - 20130913, 20150101 - 20150228 ", "", "", "", "", "Expected submission date: 20150531.", "I ");



	private ArrayList<Student> stues = new ArrayList<Student>();
	private ArrayList<String> headers = new ArrayList<String>();

	public void addToArrays(){
		headers.add("Name");
		headers.add("ID");
		headers.add("Degree");
		headers.add("EFTS");
		headers.add("Primary Supervisor");
		headers.add("Supervision Split");
		headers.add("Secondary Supervisor");
		headers.add("Supervision Split");
		headers.add("Third Supervisor");
		headers.add("Supervision Split");
		headers.add("Scholarship");
		headers.add("Start Date");
		headers.add("PhD Proposal Submission");
		headers.add("PhD Proposal Seminar");
		headers.add("PhD Proposal Confirmation Date");
		headers.add("Suspension Dates");
		headers.add("Thesis Submission + Examiners Appointed Date");
		headers.add("FGR Completes Examination");
		headers.add("Revisions Finalised");
		headers.add("Deposited in Library");
		headers.add("Notes");
		headers.add("Origin");
		stues.add(student1);
		stues.add(student2);

	}

	public void setupHeaders(){
		headers.add("Name");
		headers.add("ID");
		headers.add("Degree");
		headers.add("EFTS");
		headers.add("Primary Supervisor");
		headers.add("Supervision Split");
		headers.add("Secondary Supervisor");
		headers.add("Supervision Split");
		headers.add("Third Supervisor");
		headers.add("Supervision Split");
		headers.add("Scholarship");
		headers.add("Start Date");
		headers.add("PhD Proposal Submission");
		headers.add("PhD Proposal Seminar");
		headers.add("PhD Proposal Confirmation Date");
		headers.add("Suspension Dates");
		headers.add("Thesis Submission + Examiners Appointed Date");
		headers.add("FGR Completes Examination");
		headers.add("Revisions Finalised");
		headers.add("Deposited in Library");
		headers.add("Notes");
		headers.add("Origin");
	}

	public void addExtras(){
		stues.add(student3);
		stues.add(student4);
	}

	private CurrentFullyRegistered regiTable = new CurrentFullyRegistered(stues, headers);
	private UnderExamination underTable = new UnderExamination(stues, headers);
	private CurrentProvisionallyRegisteredStudents provTable = new CurrentProvisionallyRegisteredStudents(stues, headers);
	private PhDProposalUnderExamination proUnderTable = new PhDProposalUnderExamination(stues, headers);
	private NotFullyAdmitted notFullTable = new NotFullyAdmitted(stues, headers);

	/**
	 * Tests to add a file to a new PhDData object.
	 */
	@Test
	public void test0(){

		addToArrays();

		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );

		assertTrue(phd.addEntry(stu, fullyRegiTable));

	}

	//test that add works
	@Test
	public void test1(){
		addToArrays();
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		assertFalse(phd.addEntry(stu, fullyRegiTable));
	}

	@Test
	public void test1_2(){
		addToArrays();
		stu = new String[sizeOfArray+2][2];
		for(int i=0; i<sizeOfArray+2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		assertFalse(phd.addEntry(stu, fullyRegiTable));
	}


	//test that edit works
	@Test
	public void test2(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.editEntry(stu,fullyRegiTable));
	}

	@Test
	public void test3(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.editEntry(stu,fullyRegiTable));
	}

	@Test
	public void test3_2(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu = new String[sizeOfArray+2][2];
		for(int i=0; i<sizeOfArray+2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.editEntry(stu,fullyRegiTable));
	}

	//test that make change works
	@Test
	public void test4(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.makeChanges('a', stu, fullyRegiTable));
	}

	@Test
	public void test5(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.makeChanges('e', stu, fullyRegiTable));
	}

	@Test
	public void test6(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertFalse(phd.makeChanges('r', stu, fullyRegiTable));
	}

	@Test
	public void test7(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.makeChanges('a', stu, fullyRegiTable));
	}

	//test that move works
	@Test
	public void test8(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.setUnderExamination(underTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.moveStudent(stu, fullyRegiTable));
	}

	@Test
	public void test9(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu[1][1] = "999";
		assertFalse(phd.moveStudent(stu, fullyRegiTable));
	}


	//test for nulls
	@Test
	public void test10(){
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		try{
			phd.addEntry(stu, fullyRegiTable);
			fail();
		} catch (NullPointerException e ){ assertTrue(true);}
	}

	@Test
	public void test11(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		try{
			phd.addEntry(null, null);
			fail();
		} catch (NullPointerException e ){assertTrue(true);}

	}

	//test for sorting
	@Test
	public void testSort(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		CurrentFullyRegistered cfr = phd.getCurrentFullyRegistered();
		String[] headers = {"ID"};
		String[] values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
		phd.getCurrentFullyRegistered().sort("ID");
		values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
		assertEquals(phd.getCurrentFullyRegistered().getStudents().get(0).getValues(headers)[0],"1232");
	}

	@Test
	public void testSort1(){
		addToArrays();
		addExtras();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		CurrentFullyRegistered cfr = phd.getCurrentFullyRegistered();
		String[] headers = {"ID"};
		String[] values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
		phd.getCurrentFullyRegistered().sort("ID");
		values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
		assertEquals(phd.getCurrentFullyRegistered().getStudents().get(0).getValues(headers)[0],"1");

	}

	@Test
	public void testSort2(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.setUnderExamination(underTable);
		phd.setCurrentProvisionallyRegisteredStudents(provTable);
		phd.setNotFullyAdmitted(notFullTable);
		phd.setPhDProposalUnderExamination(proUnderTable);
		assertTrue(phd.sort("ID"));
	}

	@Test
	public void testSort3(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		try{
			phd.sort("ID");
			fail();
		} catch (NullPointerException e ){ assertTrue(true);}
	}

	@Test
	public void testSort4(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		try{
			phd.sort("ID");
			fail();
		} catch (NullPointerException e ){ assertTrue(true);}
	}

	@Test
	public void testSort5(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.setUnderExamination(underTable);
		phd.setCurrentProvisionallyRegisteredStudents(provTable);
		try{
			phd.sort("ID");
			fail();
		} catch (NullPointerException e ){ assertTrue(true);}
	}

	public PhDTable parserSetup(){
		setupHeaders();
		PhDData phd = new PhDData("SanitizedStudentswNames.txt");
		Parser p = new Parser("SanitizedStudentswNames.txt", phd );
		System.out.println("phd "+phd.getUnderExamination());
		return phd.getCurrentFullyRegistered();
	}

	@Test
	public void testParser(){
		CurrentFullyRegistered cfr = (CurrentFullyRegistered)parserSetup();
		System.out.println(cfr.getHeaders()+"");
		if(cfr ==  null) {fail();}
		for(Student s : cfr.getStudents()){
			if(s == null) {fail();}
			String arr[];
			arr = s.getValues(cfr.getHeaders().toArray(new String[0]));
			for (String str : arr){
				if (str == null){fail();}
			}

		}
		assertTrue(true);

	}

}
