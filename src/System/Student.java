package System;
import java.util.ArrayList;


public interface Student {

	public String[] getValues(String[] headers);

	public String toFoswiki();

	public boolean isHighlighted();

	public boolean isMarked();

	public boolean isIncorrectlyFormatted();

	public void toggleMark();


}