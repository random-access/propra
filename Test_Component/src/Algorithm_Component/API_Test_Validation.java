package Algorithm_Component;

import java.util.List;

import ess.algorithm.*;
import ess.algorithm.RoemischerVerbund.Validation;
import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Sie koennen sich Informationen ueber das Unittesten mit Hilfe von JUnit unter
 * http://www.vogella.com/tutorials/JUnit/article.html aneignen. In dem dort
 * hinterlegten sehr langen und ausfuehrlichen Dokument, sind alle notwendigen
 * Hilfsmittel erlaeutert.
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
        assertTrue("Alle 5 Fehler muessen in diesem Fall ausgegeben werden", errorList.size() == 6);
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
        assertTrue("Maximale Fugenlaenge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
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
        assertTrue("Maximale Fugenlaenge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
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
        assertTrue("Fliese austauschbar nicht erkannt", errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
        assertTrue("Maximale Fugenlaenge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
    }

    @Test
    public void validateSolutionTestFlorian1() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/test1_florian.xml", 34000);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue(errorList.size() == 0);
    }

    @Test
    public void validateSolutionTestFlorian2() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/test2_florian.xml", 560);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Dies ist ein gueltiger Roemischer Verbund!", errorList.size() == 0);
    }

    @Test
    public void validateSolutionNonsenseInFile() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/nonsense.xml", 90);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Alle 5 Fehler muessen in diesem Fall ausgegeben werden", errorList.size() == 6);
    }

    @Test
    public void validateSolutionKlaus1() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/klaus-validation1.xml", 100);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 1);
        assertTrue("Max. Fugenlaenge wurde nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
    }

    @Test
    public void validateSolutionKlaus2() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/klaus-validation2.xml", 100);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 6);
    }
    
    @Test
    public void validateOverlappingTiles() {

        // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/overlapping-tiles.xml", 100);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Es wurden keine Fehler erkannt", errorList.size() > 0);
        assertTrue("Sonstige Fehler wurde nicht erkannt", errorList.contains(Validation.FLIESE_UNPASSEND));
    }
    
    @Test
    public void validateInvalidTileLength() {
     // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/test1.xml", -1);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Alle 5 Fehler muessen in diesem Fall ausgegeben werden", errorList.size() == 6);
    }
    
    // @Test TODO
    public void validateTooManyTiles() {
     // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/too-many-tiles.xml", 120);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        System.out.println(errorList);
        System.out.println(((RoemischerVerbund)api).getComposite());
        assertTrue("Alle 5 Fehler muessen in diesem Fall ausgegeben werden", errorList.contains(Validation.FLIESE_UNPASSEND));
    }
    
    @Test
    public void validateNoTiles() {
     // Arrange (set all necessary preconditions and inputs.)
        IRoemischerVerbund api = new RoemischerVerbund();

        // Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution("instances/validationInstances/no-tiles.xml", 120);

        // Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Alle 5 Fehler muessen in diesem Fall ausgegeben werden", errorList.contains(Validation.FLIESE_UNPASSEND));
    }
    
    
}
