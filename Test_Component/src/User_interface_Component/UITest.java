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
		new Validator().validateSolution(c, 120);
		ICompositeView view = new MainWindow(c);
        view.display();
        // System.out.println(c);
	}
	
}
