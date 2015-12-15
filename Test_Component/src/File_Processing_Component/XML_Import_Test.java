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
    public void importInvalidSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/invalid-surface-length.xml");
    }

    @Test
    public void importNegativeSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/negative-surface-length.xml");
    }
    
    @Test
    public void importNanSurfaceLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_SURFACE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/nan-surface-length.xml");
    }

    @Test
    public void importInvalidTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/invalid-tile-length.xml");
    }

    @Test
    public void importNegativeTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/negative-tile-length.xml");
    }
    
    @Test
    public void importNanTileLength() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_DATATYPE_TILE_LENGTH);
        xmlExchanger.readFromSource("instances/validationInstances/nan-tile-length.xml");
    }
    
    @Test
    public void importDuplicateTileSort() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        // last parameter in String.format "" is for any message from DTD validation
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_XML_CONTENT, "duplicate-tile-sort.xml", ""));
        xmlExchanger.readFromSource("instances/validationInstances/duplicate-tile-sort.xml");
    }
    
    @Test
    public void importUnknownTileSort() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        // last parameter in String.format "" is for any message from DTD validation
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_XML_CONTENT, "unknown-tile-sort.xml", ""));
        xmlExchanger.readFromSource("instances/validationInstances/unknown-tile-sort.xml");
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
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_INVALID_PATH, "\"blubb\""));
        xmlExchanger.readFromSource("blubb");
    }
    
    /*
     * Might not work on another computer because permissions might be different.
     *  Please disown file-without-permissions.xml, remove  read & write permissions of group 
     *  and others (or Windows equivalents) before uncommenting this test.
     *  
     * @Test
    public void importFileWithoutPermissions() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_INVALID_PATH, 
                "\"/instances/validationInstances/file-without-permissions.xml\""));
        xmlExchanger.readFromSource("/instances/validationInstances/file-without-permissions.xml");
    } */
    
    @Test
    public void importInvalidXmlTag() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        exception.expect(DataExchangeException.class);
        exception.expectMessage(String.format(CustomErrorMessages.ERROR_INVALID_PATH, 
                "\"/instances/validationInstances/invalid-xml-tag.xml\""));
        xmlExchanger.readFromSource("/instances/validationInstances/invalid-xml-tag.xml");
    }

    @Test
    public void importSolveTest1() {
        testSolveIntegrity("instances/solveInstances/test4.xml");
    }

    private void testSolveIntegrity(String pathToSource) {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c;

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
        Composite c;

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
