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

import java.io.IOException;
import java.util.ArrayList;

/**
 * Preferences class contains a list ofviewingModes - which are different ways of viewing the table.
 * Modes contain a name which identifies the mode, and the headers which should be displayed with
 * that mode.
 * @author millwamich1
 *
 */
public class Preferences {

	private ArrayList<Mode> viewingModes;
	private Parser parser;

	public Preferences(){
		this.viewingModes = new ArrayList<Mode>();
	}

	public String[] getHeadersForMode(String mode){
		for(int i = 0; i<viewingModes.size(); i++){
			if(viewingModes.get(i).getName().equalsIgnoreCase(mode)){
				return viewingModes.get(i).getHeaders();
			}
		}
		System.err.println("Couldn't find mode: " + mode);
		return null;
	}

	public String[] getNamesOfModes(){
		String[] names = new String[viewingModes.size()];
		for(int i = 0; i<viewingModes.size(); i++){
			names[i] = viewingModes.get(i).getName();
		}
		return names;
	}

	public boolean addMode(String name, String[] headers){
		Mode newMode = new Mode(name, headers);
		return viewingModes.add(newMode);
	}

	public boolean deleteMode(String name){
		for(int i = 0; i<viewingModes.size(); i++){
			if(viewingModes.get(i).getName().equalsIgnoreCase(name)){
				viewingModes.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean editMode(String name, String[] headers){
		for(Mode m : viewingModes){
			if(m.getName().equalsIgnoreCase(name)){
				m.setHeaders(headers);
				return true;
			}
		}


		return false;
	}

	public void setParser(Parser parser){
		this.parser = parser;
	}

	public boolean setModes(ArrayList<Mode> modes){
		viewingModes = modes;
		return true;
	}


	public ArrayList<String> toFoswiki(){
		ArrayList<String> foswikiList = new ArrayList<String>();
		for(int i = 0; i<viewingModes.size(); i++){
			Mode mode = viewingModes.get(i);
			foswikiList.add(mode.toFoswiki());
		}
		return foswikiList;
	}

	public void writeToFile(){
		System.out.println("Writing preferences to file");
		if(parser == null){
			System.err.println("No parser object to write to");
			return;
		}
		try {
			parser.writeToPreferencesFile(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
