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
