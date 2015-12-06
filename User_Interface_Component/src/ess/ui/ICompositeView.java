package ess.ui;

import java.util.List;


public interface ICompositeView {
	
	public void display(List<String> errorList, String pathToSource);
	
	public void display(String pathToSource);
	
}
