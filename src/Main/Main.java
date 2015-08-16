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
	 * Us absoloute path to specify file, unless file is in same directory as the program, in which case use ../filename
	 */
	public static void main(String args[]){
		if(args.length <= 0 ){//checks to make sure a file is specified
				System.out.println("No file specified");
		}
		else{
			File headers = new File("../headers.txt");
			try {
				if(headers.createNewFile()){
					fillWithDefualts(headers);
				}
			} catch (IOException e) {
				System.out.println("Error reading file: "+e);
			}
			new PhDViewer("PHDViewer", new PhDData(args[0]));
		}
	}

	/**
	 * Write the defualt headers to the "headers" file
	 * @param headers File which will contain the headers
	 * @throws IOException -  if the file exists but is a directory rather than a regular file, does not exist but cannot
	 be created, or cannot be opened for any other reason
	 */
	private static void fillWithDefualts(File headers) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(headers.getAbsoluteFile()));
		bw.write("This is a test");//TODO: change me
		bw.newLine();
		bw.flush();

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