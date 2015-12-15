package User_interface_Component;

import java.util.LinkedList;
import java.util.List;

import ess.algorithm.Validator;
import ess.data.Composite;
import ess.exc.PropertyException;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.ui.ICompositeView;
import ess.ui.MainWindow;

public class UITest {
	
	public static void main(String[] args) throws DataExchangeException, PropertyException {
		// Act (on the object or method under test.)
		final Composite c = new XMLDataExchanger().readFromSource("instances/validationInstances/test1.xml");
		c.setMaxLineLength(6);
		Validator v = new Validator(c);
		v.validateSolution();
		ICompositeView view = new MainWindow(c);
		List<String> errorList= new LinkedList<>();
		errorList.add("Fehler 1");
		errorList.add("Fehler 2");
		errorList.add("Fehler 3");
        view.display(errorList, "instances/validationInstances/test1.xml", "Testmodus");
        // System.out.println(c);
	}
	
}
