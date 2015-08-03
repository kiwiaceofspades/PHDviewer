import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class Main {

	static JFrame Frame;
	static JPanel Table;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame = new JFrame("PhdViewers");
		String[] Header = getHeader();
		ArrayList<Student> Data = getData();
		Table = new Table(Data,Header);
		Table.setVisible(true);
		Frame.setLayout(new BorderLayout());
		Frame.add(Table, BorderLayout.CENTER);
		Frame.setVisible(true);
	}

	private static ArrayList<Student> getData() {

		return null;
	}

	private static String[] getHeader() {

		return null;
	}

}
