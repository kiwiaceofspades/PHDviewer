package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import System.CurrentFullyRegistered;
import System.ECSStudent;
import System.PhDData;
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

	private CurrentFullyRegistered regiTable = new CurrentFullyRegistered(stues, headers);
	private UnderExamination underTable = new UnderExamination(stues, headers);

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

	@Test
	public void test10(){

	}

	@Test
	public void testSort(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		CurrentFullyRegistered cfr = phd.getCurrentFullyRegistered();
		String[] headers = {"ID"};
		cfr.getStudents().get(0).getValues(headers);
		assertTrue(phd.sort("ID"));
		String[] values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
		phd.getCurrentFullyRegistered().sort("ID");
		values = cfr.getStudents().get(0).getValues(headers);
		System.out.println(values[0]);
	}
}
