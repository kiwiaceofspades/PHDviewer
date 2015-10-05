/**
 *
 * PHDViewer is a program that displays PhD students for ECS and helps to show what students have issues.
 *
 * Copyright (C) 2015  Harry King, Alistair Eichler, Matthew Schmidt, Michael Millward, Shreyas Rama
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Runs the PHDViewer program
 * @author Alistair Eichler
 */

package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import GUI.PhDViewer;
import System.PhDData;

public class Main {
	/**
	 *
	 * @param args array of arguments. First element should be the Foswiki script containing the table.
	 * Us absolute path to specify file, unless file is in same directory as the program, in which case use ../filename
	 */
	public static void main(String args[]){
		if(args.length <= 0 ){//checks to make sure a file is specified
			System.out.println("No file specified");
		}
		else{
			File headers = new File("../preferences.txt");
			try {
				if(headers.createNewFile()){//checks whether there is a headers file containing prefernces
					System.out.println("File does not exist");
					fillWithDefualts(headers); //if there isn't create one with default values
				}
			} catch (IOException e) {
				System.out.println("Error reading file: "+e);
			}
			new PhDViewer("PHDViewer", new PhDData(args[0]));
		}
	}

	/**
	 * Write the default headers to the "headers" file
	 * @param headers File which will contain the headers
	 * @throws IOException -  if the file exists but is a directory rather than a regular file, does not exist but cannot
	 be created, or cannot be opened for any other reason
	*/
	private static void fillWithDefualts(File headers) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(headers.getAbsoluteFile()));
		bw.write("|Total Time Taken|Defualt View|Yes|Name|ID|Degree|EFTS|Primary Supervisor|Supervision Split 1|Secondary Supervisor|Supervision Split 2|Third Supervisor|Supervision Split 3|Scholarship|Start Date|PhD Proposal Submission|PhD Proposal Seminar|PhD Proposal Confirmation Date|Suspension Dates|Thesis Submission + Examiners Appointed Date|FGR Completes Examination|Revisions Finalised|Deposited in Library|Notes|Origin|");
		bw.newLine();
		bw.close();

	}
}

class noFileException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = -6574270090860531112L;

	noFileException(){

	}
}