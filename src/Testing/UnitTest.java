/**
 *
 * Copyright (C) 2015  Harry King
 *
 * This file is part of PHDViewer.
 *
 * PHDViewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PHDViewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import System.CurrentFullyRegistered;
import System.CurrentProvisionallyRegisteredStudents;
import System.NotFullyAdmitted;
import System.Parser;
import System.PhDData;
import System.PhDProposalUnderExamination;
import System.PhDTable;
import System.Student;
import System.UnderExamination;

/**
 *
 * @author Harry King
 * The class that has some unit tests in it.
 */
public class UnitTest {

	private int sizeOfArray = 23;

	private String testFile = "test.txt";
	private String[][] stu = new String[sizeOfArray][2];
	private String fullyRegiTable = "CurrentFullyRegistered";
	private String underExamTable = "UnderExamination";

	private Student student1 = new Student("Harry", 1236, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "20150930", "20151031", "Prolly", "20160225", "", "20170101", "sds", "Nop", "Nope", "Why is the rum gone?", "Wellington");
	private Student student2 = new Student("Harry", 1232, "Comp", "Yes", "Jim", "50", "Jess", "50", "", "", "Awesome", "20150930", "20151031", "Prolly", "20160225", "", "20170101", "sds", "Nop", "Nope", "Why is the rum gone?", "Auckland");
	private Student student3 = new Student("Alistair Eichler", 1, "COMP690", "EFTSdata", "Person:JamesNoble", "50%", "Person:NicholasCameron", "50%", "", "",  "Telstra Clear Postgraduate Scholarship", "20090427", " SUBMITTED", "PRESENTED",  "CONFIRMED", "20150116", "", "20150518", "", "", " Revisions: 20150901.", "D");
	private Student student4 = new Student("Homer Simpson", 5, "NWEN690", "", "Person:IanWelch", "70%", " Person:WinstonSeah", " 30%", "", "", "", "20100719", "20120904", "20121019" , "20121220" ,"20130601 - 20130630 , 20130801 - 20130913, 20150101 - 20150228 ", "", "", "", "", "Expected submission date: 20150531.", "I ");

	private ArrayList<Student> stues = new ArrayList<Student>();
	private ArrayList<String> headers = new ArrayList<String>();

	//adds some students to the table.
	private void addToArrays(){
		setupHeaders();
		stues.add(student1);
		stues.add(student2);
	}

	/**
	 * This can be called on its own.
	 */
	private void setupHeaders(){
		headers.add("Total Time Taken");
		headers.add("Name");
		headers.add("ID");
		headers.add("Degree");
		headers.add("EFTS");
		headers.add("Primary Supervisor");
		headers.add("Supervision Split 1");
		headers.add("Secondary Supervisor");
		headers.add("Supervision Split 2");
		headers.add("Third Supervisor");
		headers.add("Supervision Split 3");
		headers.add("Scholarship");
		headers.add("Start Date");
		headers.add("PhD Proposal Submission");
		headers.add("PhD Proposal Seminar");
		headers.add("PhD Proposal Confirmation Date");
		headers.add("Suspension Dates");
		headers.add("Thesis Submission+Examiners Appointed Date");
		headers.add("FGR Completes Examination");
		headers.add("Revisions Finalised");
		headers.add("Deposited in Library");
		headers.add("Notes");
		headers.add("Origin");
	}

	//must be called after either setupHeaders or addToArrays as this doesn't set up the headers and addToArrays does.
	private void addExtras(){
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
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";

		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		assertTrue(phd.addEntry(stu, fullyRegiTable));

	}
	//Irrelevant test now
	//test that add works and should fail on this
	/**
	@Test
	public void test1(){
		addToArrays();
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		assertFalse(phd.addEntry(stu, fullyRegiTable));
	}
	*/

	//Test irrelevant now
	/**
	@Test
	public void test1_2(){
		addToArrays();
		stu = new String[sizeOfArray+2][2];
		for(int i=0; i<sizeOfArray+2; i++){
			stu[i][1] = ""+i;
			if(i < headers.size()){
				stu[i][0] = headers.get(i);
			}
			else {
				stu[i][0] = ""+i;
			}
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		try{
			phd.addEntry(stu, fullyRegiTable);
			fail();
		} catch (IndexOutOfBoundsException e) { assertTrue(true); }

	}
	*/

	//test that edit works
	@Test
	public void test2(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.editEntry(stu,fullyRegiTable));
	}

	//irrevent tests now
	/**
	@Test
	public void test3(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu = new String[sizeOfArray-2][2];
		for(int i=0; i<sizeOfArray-2; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		assertFalse(phd.editEntry(stu,fullyRegiTable));
	}
	*/

	//test irrelevant now
	/**
	@Test
	public void test3_2(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu = new String[sizeOfArray+2][2];
		for(int i=0; i<sizeOfArray+2; i++){
			stu[i][1] = ""+i;
			if(i<headers.size()){
				stu[i][0] = headers.get(i);
			}
			else{
				stu[i][0] = ""+i;
			}
		}

		stu[12][1] = "02-02-2015";
		try{
			phd.editEntry(stu,fullyRegiTable);
			fail();
		} catch (IndexOutOfBoundsException e) { assertTrue(true); }

	}
	*/

	//test that make change works
	@Test
	public void test4(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.makeChanges('a', stu, fullyRegiTable));
	}

	@Test
	public void test5(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.makeChanges('e', stu, fullyRegiTable));
	}

	//make changes should fail
	@Test
	public void test6(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		assertFalse(phd.makeChanges('r', stu, fullyRegiTable));
	}

	//Irrelevant test now
	/**
	@Test
	public void test7(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
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
	*/

	//test that move works
	@Test
	public void test8(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.setUnderExamination(underTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(phd.moveStudent(stu, fullyRegiTable, "1234567"));
	}

	@Test
	public void test9(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		phd.addEntry(stu, fullyRegiTable);
		stu[2][1] = "999";
		assertFalse(phd.moveStudent(stu, fullyRegiTable, "7654321"));
	}


	//test for nulls
	@Test
	public void test10(){
		setupHeaders();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
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
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable );
		try{
			phd.addEntry(null, null);
			fail();
		} catch (NullPointerException e ){assertTrue(true);}
	}

	//test that the convertDate works
	@Test
	public void test12(){
		addToArrays();
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		//assertEquals(phd.convertDate("25-02-2015"), "20150225");
	}

	//tests that incorrect format is working
	@Test
	public void test13(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "2015-02-02";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		int[] wrong = phd.getIncorrectlyFormated(fullyRegiTable);
		System.out.println("Wrong : " + wrong.length);
		assertTrue(wrong[2]==1);
	}

	@Test
	public void test14(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		int[] wrong = phd.getIncorrectlyFormated(fullyRegiTable);
		assertTrue(wrong[2]==0);
	}

	//test removing
	@Test
	public void test15(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(regiTable.removeStudent(1236)!=null);
	}

	//test removing
	@Test
	public void test16(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		assertFalse(regiTable.removeStudent(123)!=null);
	}

	//find student
	@Test
	public void test17(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(regiTable.findStudent(1236)!=-1);
	}

	//find student
	@Test
	public void test18(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(regiTable.findStudent(1236)==0);
	}

	//find student
	@Test
	public void test19(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		assertTrue(regiTable.findStudent(123)==-1);
	}

	//Test for marking students
	@Test
	public void test20(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		regiTable.toggleMark(1236);
		assertTrue(regiTable.getStudents().get(0).isMarked());
	}

	@Test
	public void test21(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}

		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		regiTable.toggleMark(1236);
		assertFalse(regiTable.getStudents().get(1).isMarked());
	}

	//testing that the suspendedMonths works correctly
	@Test
	public void test22(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}
		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		Student stud =  stues.get(0);
		stud.setSuspensionDates("20150202 - 20150302");
		assertEquals(stud.suspendedMonths(),2);
	}

	@Test
	public void test23(){
		addToArrays();
		for(int i=0; i<sizeOfArray; i++){
			stu[i][1] = ""+i;
			stu[i][0] = headers.get(i);
		}
		stu[12][1] = "02-02-2015";
		PhDData phd = new PhDData(testFile);
		phd.setCurrentFullyRegistered(regiTable);
		phd.addEntry(stu, fullyRegiTable);
		Student stud = stues.get(0);
		stud.setSuspensionDates("20150201 - 20150201");
		assertEquals(stud.suspendedMonths(),1);
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

	private CurrentFullyRegistered currentFullySetup(){
		setupHeaders();
		PhDData phd = new PhDData("SanitizedStudentswNames.txt");
		Parser p = new Parser("SanitizedStudentswNames.txt", phd );
		System.out.println("phd "+phd.getUnderExamination());
		return phd.getCurrentFullyRegistered();
	}




	//Irrelevant tests now
	/**
	@Test
	public void testParser(){
		CurrentFullyRegistered cfr = currentFullySetup();
		if(cfr ==  null) {fail();}
		System.out.println(cfr.getHeaders()+"");
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
	 */

}
