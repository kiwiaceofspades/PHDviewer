package System;

import java.util.ArrayList;

public class Preferences {

	private ArrayList<Mode> viewingModes;

	public Preferences(ArrayList<Mode> viewingModes){
		this.viewingModes = viewingModes;
	}

	public String[] getHeadersForMode(String mode){
		for(int i = 0; i<viewingModes.size(); i++){
			if(viewingModes.get(i).getName().equals(mode)){
				return viewingModes.get(i).getHeaders();
			}
		}
		System.out.println("Couldn't find mode: " + mode);
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
			if(viewingModes.get(i).getName().equals(name)){
				viewingModes.remove(i);
				return true;
			}
		}
		return false;
	}


}
