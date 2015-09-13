package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import System.CurrentFullyRegistered;
import System.ECSStudent;
import System.PhDData;
import System.PhDTable;
import System.Student;

public class UnitTest {

	private int sizeOfArray = 22;

	private String testFile = "test.txt";
	private String[][] stu = new String[sizeOfArray][2];
	private String testTable = "currentFullyRegistered";

	private Student student1 = new ECSStudent("Harry", 1234, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "14/08/2015", "31/10/2015", "Prolly", "25/15/2016", "", "01/01/2017", "sds", "Nop", "Nope", "Why is the rum gone?", "Wellington");
	private Student student2 = new ECSStudent("Harry", 1234, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "14/08/2015", "31/10/2015", "Prolly", "25/15/2016", "", "01/01/2017", "sds", "Nop", "Nope", "Why is the rum gone?", "Auckland");
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

		assertTrue(phd.addEntry(stu, testTable));

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
		assertFalse(phd.addEntry(stu, testTable));
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
		assertFalse(phd.addEntry(stu, testTable));
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
		phd.addEntry(stu, testTable);
		assertTrue(phd.editEntry(stu,testTable));
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
		phd.addEntry(stu, testTable);
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.editEntry(stu,testTable));
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
		phd.addEntry(stu, testTable);
		stu = new String[sizeOfArray+2][2];
		for(int i=0; i<sizeOfArray+2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.editEntry(stu,testTable));
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
		phd.addEntry(stu, testTable);
		assertTrue(phd.makeChanges('a', stu, testTable));
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
		phd.addEntry(stu, testTable);
		assertTrue(phd.makeChanges('e', stu, testTable));
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
		phd.addEntry(stu, testTable);
		assertFalse(phd.makeChanges('r', stu, testTable));
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
		phd.addEntry(stu, testTable);
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][0] = ""+i;
			stu[i][1] = ""+i;
		}
		assertFalse(phd.makeChanges('a', stu, testTable));
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
		phd.addEntry(stu, testTable);
		assertTrue(phd.moveStudent(stu, testTable));
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
		phd.addEntry(stu, testTable);
		stu[1][1] = "999";
		assertFalse(phd.moveStudent(stu, testTable));
	}

	@Test
	public void test10(){

	}

}
