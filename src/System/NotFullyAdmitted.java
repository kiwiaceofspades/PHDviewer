/**
 *
 * Copyright (C) 2015  Michael Millward
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

package System;
import java.util.ArrayList;


public class NotFullyAdmitted extends PhDTable {

	public NotFullyAdmitted(ArrayList<Student> students,
			ArrayList<String> headers) {
		super(students, headers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] getHighlighted() {
		int[] highlighting = new int[students.size()];
		for(int i = 0; i<students.size(); i++){
			// Fetch the time taken
			Student student = students.get(i);
			int monthsSinceStart = student.getTimeSinceStartDate();

			// Take into account whether the student is part time or not
			if(student.isPartTime()){
				monthsSinceStart = monthsSinceStart / 2;
			}

			if(monthsSinceStart > 15){
				highlighting[i] = 3;
			}
			else if(monthsSinceStart > 12){
				highlighting[i] = 2;
			}
			else if(monthsSinceStart > 9){
				highlighting[i] = 1;
			}
			else{
				highlighting[i] = 0;
			}
		}
		return highlighting;
	}


}
