package Algorithm_Component;

import java.util.List;

import ess.algorithm.*;
import ess.algorithm.RoemischerVerbund.Validation;
import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Sie k�nnen sich Informationen �ber das Unittesten mit Hilfe von JUnit unter
 * http://www.vogella.com/tutorials/JUnit/article.html aneignen. In dem dort
 * hinterlegten sehr langen und ausf�hrlichen Dokument, sind alle notwendigen
 * Hilfsmittel erl�utert.
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
		assertTrue("Alle 5 Fehler m�ssen in diesem Fall ausgegeben werden", errorList.size() == 5);
	}

	@Test
	public void validateSolutionTest1() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution("instances/validationInstances/test1.xml", 80);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 2);
		assertTrue("Fliese austauschbar nicht erkannt", errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
		assertTrue("Maximale Fugenl�nge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
	}

	@Test
	public void validateSolutionTest2() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution("instances/validationInstances/test2.xml", 80);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 2);
		assertTrue("Gleiche Fliese nicht erkannt", errorList.contains(Validation.GLEICHE_FLIESEN));
		assertTrue("Maximale Fugenl�nge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
	}

	@Test
	public void validateSolutionTest3() {

		// Arrange (set all necessary preconditions and inputs.)
		IRoemischerVerbund api = new RoemischerVerbund();

		// Act (on the object or method under test.)
		List<Validation> errorList = api.validateSolution("instances/validationInstances/test3.xml", 110);

		// Assert (that the expected results have occurred.))
		assertTrue("errorList ist null", errorList != null);
		assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 2);
		assertTrue("Fugenkreuze nicht erkannt", errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
		assertTrue("Maximale Fugenl�nge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
	}
}
