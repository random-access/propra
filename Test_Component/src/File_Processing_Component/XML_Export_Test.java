package File_Processing_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ess.algorithm.IRoemischerVerbund;
import ess.algorithm.RoemischerVerbund;
import ess.algorithm.RoemischerVerbund.Validation;

public class XML_Export_Test {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * Die Probleminstanzen müssen zwischengespeichert werden, da das
	 * XML-Dokument durch die API modifiziert wird. Das ist nötig, damit beim
	 * erneuten Durchführen (erneuter Test) die Instanzen unmodifiziert sind.
	 * 
	 * @param fileName
	 *            Dateiname, der temporär zwischengespeichert werden soll
	 * @return temporärer Pfad
	 * @throws IOException
	 */
	private String writeFileToTempDirectory(String fileName) throws IOException {
		// In Unit-Tests braucht kein fortgeschrittenes Exceptionhandling
		// integriert werden.
		// Der Grund dafuer ist, dass der Test genau ein Szenario abbildet.
		byte[] bytes = Files.readAllBytes(Paths.get(fileName));
		String xmlAsString = new String(bytes, "UTF8");
		String tempFileName = String.format("%s.xml", UUID.randomUUID().toString());
		File testFile = folder.newFile(tempFileName);
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(testFile);
			fw.write(xmlAsString);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}

		return testFile.getPath();
	}
	
	
//    @Test
//    public void exportSolutionInvalidPath() throws IOException {
//
//        // Arrange (set all necessary preconditions and inputs.)
//        IRoemischerVerbund api = new RoemischerVerbund();
//        String filePath = WriteFileToTempDirectory("instances/solveInstances/test1.xml");
//
//        // Act (on the object or method under test.)
//        Boolean valid = api.solve(filePath, 140);
//
//        // Assert (that the expected results have occurred.))
//        assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
//        
//        List<Validation> errorList = api.validateSolution(filePath, 140);
//        
//        // Assert (that the expected results have occurred.))
//        assertTrue("errorList ist null", errorList != null);
//        assertTrue("Es sollte kein Fehler gefunden werden!", errorList.size() == 0);
//        
//    }
	
	@Test
	public void exportSolutionWithoutOldPlan() throws IOException {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = writeFileToTempDirectory("instances/solveInstances/test1.xml");

		// Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 140);

		// Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
		
		List<Validation> errorList = api.validateSolution(filePath, 140);
		
		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es sollte kein Fehler gefunden werden!", errorList.size() == 0);
		
	}
	
	@Test
	public void exportSolutionWithOldPlan() throws IOException {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = writeFileToTempDirectory("instances/validationInstances/test1.xml");

		// Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 140);

		// Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
		
		List<Validation> errorList = api.validateSolution(filePath, 140);
		
		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es sollte kein Fehler gefunden werden!", errorList.size() == 0);
		
	}
	
	/* @Test
	public void exportSolutionTestReal() throws IOException {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = "/home/monika/Schreibtisch/test1b.xml";

		// Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 140);

		// Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
		
		String inputPath = "/home/monika/Schreibtisch/test1b-output.xml";
		
		List<Validation> errorList = api.validateSolution(inputPath, 140);
		
		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es sollte kein Fehler gefunden werden!", errorList.size() == 0);
		
	} */

}
