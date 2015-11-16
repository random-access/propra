package ess.io;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ProPraEntityResolver implements EntityResolver {

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		if (systemId.contains("DataModel.dtd")) {
	           InputStream is = getClass().getResourceAsStream("/resources/DataModel.dtd");
	           return new InputSource(is);
	       } else {
	           return null;
	       }
	}
	
}
