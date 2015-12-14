package File_Processing_Component;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ess.data.Composite;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.strings.CustomErrorMessages;

public class XML_Import_Test {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
    public void importValidationTestInvalidSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/invalid-surface-length.xml");
    }

    @Test
    public void importValidationTestNegativeSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/negative-surface-length.xml");
    }
    
    @Test
    public void importValidationTestNanSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/nan-surface-length.xml");
    }

    @Test
    public void importValidationTestInvalidTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/invalid-tile-length.xml");
    }

    @Test
    public void importValidationTestNegativeTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/negative-tile-length.xml");
    }
    
    @Test
    public void importValidationTestNanTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/nan-tile-length.xml");
    }
    
    @Test
    public void importNonsense() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_INVALID_CONTENT, "nonsense.xml"));
        xmlExchanger.readFromSource("instances/validationInstances/nonsense.xml");
    }
    
    @Test
    public void importNotAFile() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_PATH_NOT_FOUND, "\"blubb\""));
        xmlExchanger.readFromSource("blubb");
    }
    
    @Test
    public void importInvalidXmlTag() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_PATH_NOT_FOUND, 
                "\"/instances/validationInstances/invalid-xml-tag.xml\""));
        xmlExchanger.readFromSource("/instances/validationInstances/invalid-xml-tag.xml");
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
            assertTrue(c.getSurface().getRows() != 0);
            assertTrue(c.getSurface().getCols() != 0);
            assertTrue(c.getTileSorts() != null && c.getTileSorts().size() > 0);
            assertTrue(c.getSurfaceTileList() != null);
        } catch (DataExchangeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void testValidationIntegrity(String pathToSource) {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = null;

        try {
            c = xmlExchanger.readFromSource(pathToSource);
            assertTrue(c.getSurface().getRows() != 0);
            assertTrue(c.getSurface().getCols() != 0);
            assertTrue(c.getTileSorts() != null && c.getTileSorts().size() > 0);
            assertTrue(c.getSurfaceTileList() != null && c.getSurfaceTileList().size() > 0);
        } catch (DataExchangeException e) {
            System.out.println(e.getMessage());
        }
    }
}
