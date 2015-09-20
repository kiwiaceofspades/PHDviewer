package System;

public class OtherVUWStudents implements Student {

	private boolean isHighlighted;
	private boolean isMarked;
	private boolean isIncorrectlyFormatted;

	@Override
	public String[] getValues(String[] headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toFoswiki() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHighlighted() {
		return isHighlighted;
	}

	public boolean isIncorrectlyFormatted(){
		return isIncorrectlyFormatted;
	}

	public boolean isMarked(){
		return isMarked;
	}

}
