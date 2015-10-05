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

public class Mode {

	private String name;
	private String[] headers;

	public Mode(String name, String[] headers){
		this.name = name;
		this.headers = headers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String toFoswiki(){
		ArrayList<String> foswikiString = new ArrayList<String>();
		foswikiString.add(name);
		for(int i = 0; i<headers.length; i++){
			foswikiString.add(headers[i]);

		}
		String list = "|";
		for(String s : foswikiString){
			list += s + "|";
		}
		return list;
	}


}
