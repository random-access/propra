package Main_Component;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ess.main.ExecMode;
import com.ess.main.InputParser;

import ess.exc.InvalidInputException;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;

public class Input_Parser_Test {
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidInput() throws InvalidInputException {
        String[] input = new String[] {"r=vd", "if=/valid/path", "l=100"};
        
        InputParser inputParser = new InputParser(input);
        
        assertEquals(inputParser.getMode(), ExecMode.VALIDATE_DISPLAY);
        assertEquals(inputParser.getMaxTileLength(), 100);
        assertEquals(inputParser.getPath(), "/valid/path");
    }
    
    @Test
    public void testCorrectMode() throws InvalidInputException {
        String[][] inputs = new String[][] {{"r=s", "if=/valid/path", "l=100"},
                {"r=v", "if=/valid/path", "l=100"}, {"r=sd", "if=/valid/path", "l=100"},
                {"r=vd", "if=/valid/path", "l=100"}, {"r=d", "if=/valid/path", "l=100"}};
        
        ExecMode[] expectedModes = new ExecMode[] {ExecMode.SOLVE, ExecMode.VALIDATE,
                ExecMode.SOLVE_DISPLAY, ExecMode.VALIDATE_DISPLAY, ExecMode.DISPLAY};
        
        InputParser parser;
        for (int i = 0; i < expectedModes.length; i++) {
            parser = new InputParser(inputs[i]);
            assertEquals(parser.getMode(), expectedModes[i]);
        }
    }
    
    @Test
    public void testNotEnoughParameters() throws InvalidInputException {
        String[] input = new String[] {"r=vd", "if=/valid/path"};
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_PARAM_COUNT);
        
        new InputParser(input);
        
    }
    
    @Test
    public void testNoParameters() throws InvalidInputException {
        String[] input = new String[0];
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomInfoMessages.INFO_USAGE);
        
        new InputParser(input);
        
    }
    
    @Test
    public void testInvalidModeKey() throws InvalidInputException {
        String[] input = new String[] {"b=vd", "if=/valid/path", "l=100"};
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_PARAM);
        
        new InputParser(input);  
    }
    
    @Test
    public void testInvalidPathKey() throws InvalidInputException {
        String[] input = new String[] {"r=vd", "p=/valid/path", "l=100"};
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_PARAM);
        
        new InputParser(input);  
    }
    
    @Test
    public void testInvalidLengthKey() throws InvalidInputException {
        String[] input = new String[] {"r=vd", "if=/valid/path", "x=100"};
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_PARAM);
        
        new InputParser(input);  
    }
    
    @Test
    public void testInvalidLengthValue() throws InvalidInputException {
        String[] input = new String[] {"r=vd", "if=/valid/path", "l=abc"};
        
        exception.expect(InvalidInputException.class);
        exception.expectMessage(CustomErrorMessages.ERROR_INVALID_LENGTH);
        
        new InputParser(input);  
    }
   
}
