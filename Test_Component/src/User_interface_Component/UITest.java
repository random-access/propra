package User_interface_Component;

import ess.algorithm.Validator;
import ess.data.Composite;
import ess.io.XMLDataImporter;
import ess.io.exc.DataExchangeException;
import ess.ui.ICompositeView;
import ess.ui.MainWindow;
import ess.utils.PropertyException;

public class UITest {
	
	public static void main(String[] args) throws DataExchangeException, PropertyException {
		// Act (on the object or method under test.)
		final Composite c = new XMLDataImporter().importComposite("instances/validationInstances/test1.xml");
		Validator v = new Validator(c, 120);
		v.validateSolution();
		ICompositeView view = new MainWindow(c);
        view.display(new String[]{"Fehler 1", "Fehler 2", "Fehler 3"});
        // System.out.println(c);
	}
	
}
