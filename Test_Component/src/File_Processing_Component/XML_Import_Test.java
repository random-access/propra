package File_Processing_Component;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.algorithm.utils.SurfaceUtils;
import ess.data.Composite;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;

public class XML_Import_Test {

	@Test
	public void importTest1() throws DataExchangeException {
		testImportIntegrity("instances/importInstances/test1.xml");
	}

	@Test
	public void importTest2() {
		testImportIntegrity("instances/importInstances/test2.xml");
	}
	
	@Test
	public void importTest3() {
		testImportIntegrity("instances/validationInstances/test1.xml");
	}
	
	@Test
	public void importTest4() {
		testImportIntegrity("instances/validationInstances/test2.xml");
	}
	
	@Test
	public void importTest5() {
		testImportIntegrity("instances/validationInstances/test3.xml");
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

		assertTrue(c.getHeight() != 0);
		assertTrue(c.getWidth() != 0);
		assertTrue(c.getSurface() != null && c.getSurface().length != 0 && c.getSurface()[0].length != 0);
		assertTrue(SurfaceUtils.getNextFreePosition(c.getSurface()).x == -1
				&& SurfaceUtils.getNextFreePosition(c.getSurface()).y == -1);
	}

}
