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

/**
 * Intially, we planned to implement tables that had different kinds of Students (for example, students outside victoria).
 * We eventually decided to leave the extra tables out of the program, thus removing the need for having different kinds
 * of students. 
 * @author millwamich1
 *
 */
public interface Student {

	public String[] getValues(String[] headers);

	public String toFoswiki();

	public boolean isHighlighted();

	public boolean isMarked();

	public boolean isIncorrectlyFormatted();

	public void toggleMark();

	public String getStartDate();
}