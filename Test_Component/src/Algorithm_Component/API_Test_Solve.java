package Algorithm_Component;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ess.algorithm.IRoemischerVerbund;
import ess.algorithm.RoemischerVerbund;
/*
 * Sie können sich Informationen über das Unittesten mit Hilfe von JUnit unter
 * http://www.vogella.com/tutorials/JUnit/article.html aneignen. In dem dort
 * hinterlegten sehr langen und ausführlichen Dokument, sind alle notwendigen
 * Hilfsmittel erläutert.
 * 
 * Designen Sie Ihre Unit-Tests nach dem Arrange-Act-Assert-Prinzip
 * (http://c2.com/cgi/wiki?ArrangeActAssert).
 */
public class API_Test_Solve {

	@Rule
	public TemporaryFolder folder= new TemporaryFolder();
	
	/**
	 * Die Probleminstanzen müssen zwischengespeichert werden, da das
	 * XML-Dokument durch die API modifiziert wird. Das ist nötig, damit beim
	 * erneuten Durchführen (erneuter Test) die Instanzen unmodifiziert sind.
	 * 
	 * @param fileName Dateiname, der temporär zwischengespeichert werden soll
	 * @return temporärer Pfad
	 * @throws IOException
	 */
	private String WriteFileToTempDirectory(String fileName) throws IOException
	{
		// In Unit-Tests braucht kein fortgeschrittenes Exceptionhandling integriert werden.
		// Der Grund dafür ist, dass der Test genau ein Szenario abbildet.
		byte[] bytes = Files.readAllBytes(Paths.get(fileName));
		String xmlAsString = new String(bytes, "UTF8");			
		String tempFileName = String.format("%s.xml" ,UUID.randomUUID().toString());
		File testFile = folder.newFile(tempFileName);

		FileWriter fw = null;
		try {
			fw = new FileWriter(testFile);
	        fw.write(xmlAsString);
		} finally {
			if(fw != null) {
				fw.close();
			}
		}	
		
		return testFile.getPath();
	}
	
	@Test
	public void solveParameterHasToBeValid() {
		
		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		
		//Act (on the object or method under test.)
		Boolean valid = api.solve("", -1);
		
		//Assert (that the expected results have occurred.))
		assertFalse("Lösung müsste ungültig sein", valid);
	}
	
	@Test
	public void solveInstance1() throws IOException {
		
		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = WriteFileToTempDirectory("instances/solveInstances/test1.xml");
		
		//Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 140);
		
		//Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
	}
	
	@Test
	public void solveInstance2() throws IOException {
		
		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = WriteFileToTempDirectory("instances/solveInstances/test1.xml");
		
		//Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 200);
		
		//Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
	}
	
	@Test
	public void solveInstance3() throws IOException {
		
		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();
		String filePath = WriteFileToTempDirectory("instances/solveInstances/test2.xml");
		
		//Act (on the object or method under test.)
		Boolean valid = api.solve(filePath, 120);
		
		//Assert (that the expected results have occurred.))
		assertFalse("Für diese Instanz existiert mindestens eine zulässige Lösung", !valid);
	}
}
