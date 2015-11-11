package ess.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(formatMessage(record)).append(System.getProperty("line.separator"));;
		Throwable throwable = record.getThrown();
		if (throwable != null) {
			try (PrintWriter pw = new PrintWriter(new StringWriter())) {
				throwable.printStackTrace(pw);
				sb.append(pw.toString());
			}
		}
		return sb.toString();
	}

}
