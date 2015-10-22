package File_Processing_Component;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;

public class XML_Import_Test {
	
	@Test
	public void importTest1() {
		testImportIntegrity("instances/validationInstances/test1.xml");
	}
	
	@Test
	public void importTest2() {
		testImportIntegrity("instances/validationInstances/test2.xml");
	}
	
	@Test
	public void importTest3() {
		testImportIntegrity("instances/validationInstances/test3.xml");
	}
	
	@Test
	public void importTest4() throws DataExchangeException {
		testImportIntegrity("instances/validationInstances/test4.xml");
	}

	@Test
	public void importTest5() {
		testImportIntegrity("instances/validationInstances/test5.xml");
	}
	
	private void testImportIntegrity(String pathToSource) {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = null;
		
		try {
			c = xmlExchanger.readFromSource(pathToSource);
			System.out.println(c);
		} catch (DataExchangeException e) {
			System.out.println(e.getMessage());
		}

		assertTrue(c.getRows() != 0);
		assertTrue(c.getCols() != 0);
		assertTrue(c.getSurfaceTileList() != null && c.getSurfaceTileList().size() > 0);
		assertTrue(c.getTileSorts() != null && c.getTileSorts().size() > 0);
	}

}
