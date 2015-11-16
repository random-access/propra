package File_Processing_Component;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;

public class XML_Import_Test {
	
	@Test
	public void importValidationTest1() {
		testValidationIntegrity("instances/validationInstances/test1.xml");
	}
	
	@Test
	public void importValidationTest2() {
		testValidationIntegrity("instances/validationInstances/test2.xml");
	}
	
	@Test
	public void importValidationTest3() {
		testValidationIntegrity("instances/validationInstances/test3.xml");
	}
	
	@Test
	public void importValidationTest4() throws DataExchangeException {
		testValidationIntegrity("instances/validationInstances/test4.xml");
	}

	@Test
	public void importValidationTest5() {
		testValidationIntegrity("instances/validationInstances/test5.xml");
	}
	
	@Test
	public void importSolveTest1() {
		testSolveIntegrity("instances/solveInstances/test4.xml");
	}
	
	private void testSolveIntegrity(String pathToSource) {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = null;
		
		try {
			c = xmlExchanger.readFromSource(pathToSource);
			// System.out.println(c);
		} catch (DataExchangeException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(c.getSurface().getRows() != 0);
		assertTrue(c.getSurface().getCols() != 0);
		assertTrue(c.getTileSorts() != null && c.getTileSorts().size() > 0);
		assertTrue(c.getSurfaceTileList() != null);
	}
	
	private void testValidationIntegrity(String pathToSource) {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = null;
		
		try {
			c = xmlExchanger.readFromSource(pathToSource);
			// System.out.println(c);
		} catch (DataExchangeException e) {
			System.out.println(e.getMessage());
		}

		assertTrue(c.getSurface().getRows() != 0);
		assertTrue(c.getSurface().getCols() != 0);
		assertTrue(c.getTileSorts() != null && c.getTileSorts().size() > 0);
		assertTrue(c.getSurfaceTileList() != null && c.getSurfaceTileList().size() > 0);
	}

}
