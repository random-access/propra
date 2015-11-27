package ess.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
/**
 * This class provides a custom log formatter for a Logger object.
 * 
 * @author Monika Schrenk
 *
 */
public class CustomLogFormatter extends Formatter {
	
	/**
	 * Formats the LogRecord object. 
	 * This formatter minimizes the log data.
	 * Only the message itself and uncatched throwables (if any) get logged.
	 */
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(formatMessage(record)).append(System.getProperty("line.separator"));
		Throwable throwable = record.getThrown();
		if (throwable != null) {
			try (Writer sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
				throwable.printStackTrace(pw);
				sb.append(sw.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
