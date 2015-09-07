package System;

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



}
