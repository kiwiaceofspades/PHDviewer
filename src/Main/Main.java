/**
 * @author Alistair Eichler
 */

package Main;

import GUI.PhDViewer;
import System.PhDData;

public class Main {
	public static void main(String args[]){
		if(args[0] == null){
			try {
				throw new noFileException();
			} catch (noFileException e) {
				System.out.println("No file specified");
			}
		}
		else{

			new PhDViewer("PHDViewer", new PhDData(args[0]));
		}
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