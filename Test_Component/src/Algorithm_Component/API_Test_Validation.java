package Algorithm_Component;

import java.util.List;

import ess.algorithm.*;
import ess.algorithm.RoemischerVerbund.Validation;
import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Sie können sich Informationen über das Unittesten mit Hilfe von JUnit unter
 * http://www.vogella.com/tutorials/JUnit/article.html aneignen. In dem dort
 * hinterlegten sehr langen und ausführlichen Dokument, sind alle notwendigen
 * Hilfsmittel erläutert.
 * 
 * Designen Sie Ihre Unit-Tests nach dem Arrange-Act-Assert-Prinzip
 * (http://c2.com/cgi/wiki?ArrangeActAssert).
 */
public class API_Test_Validation {

	@Test
	public void validationParametersHasToBeValid() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution("", -1);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Alle 5 Fehler müssen in diesem Fall ausgegeben werden",
				errorList.size() == 5);
	}

	@Test
	public void validateSolutionTest1() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/test1.xml", 80);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue(
				"Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt",
				errorList.size() == 2);
		assertTrue("Fliese austauschbar nicht erkannt",
				errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
		assertTrue("Maximale Fugenlänge nicht erkannt",
				errorList.contains(Validation.MAX_FUGENLAENGE));
	}

	@Test
	public void validateSolutionTest2() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/test2.xml", 80);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue(
				"Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt",
				errorList.size() == 2);
		assertTrue("Gleiche Fliese nicht erkannt",
				errorList.contains(Validation.GLEICHE_FLIESEN));
		assertTrue("Maximale Fugenlänge nicht erkannt",
				errorList.contains(Validation.MAX_FUGENLAENGE));
	}

	@Test
	public void validateSolutionTest3() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/test3.xml", 110);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue(
				"Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt",
				errorList.size() == 2);
		assertTrue("Fugenkreuze nicht erkannt",
				errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
		assertTrue("Maximale Fugenlänge nicht erkannt",
				errorList.contains(Validation.MAX_FUGENLAENGE));
	}

	@Test
	public void validateSolutionTestFlorian1() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/test1_florian.xml", 34000);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue(errorList.size() == 0);
		System.out.println(errorList.size());
	}

	@Test
	public void validateSolutionNonsenseInFile() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/nonsense.xml", 90);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Alle 5 Fehler müssen in diesem Fall ausgegeben werden",
				errorList.size() == 5);
	}
	
	@Test
	public void validateSolutionKlaus1() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution(
				"instances/validationInstances/klaus-validation1.xml", 100);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt",
				errorList.size() == 1);
		assertTrue("Max. Fugenlänge wurde nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
	}
}
