package fr.lr.univ.sysd.validation;

import java.io.File;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class StAXValidation
{
    protected static class ErrorHandler extends DefaultHandler
    {
        public void error(SAXParseException parseException) throws SAXException
        {
            printException(parseException);
        }

        public void fatalError(SAXParseException parseException) throws SAXException
        {
            printException(parseException);
        }

        public void warning(org.xml.sax.SAXParseException parseException)
                throws org.xml.sax.SAXException
        {
            printException(parseException);
        }

        private void printException(SAXParseException exception)
        {
            System.out.println(exception);
            System.out.println("line/column: " + exception.getLineNumber() + "/" + exception.getColumnNumber());
        }
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            System.out.println("Usage : ValidatorExample <xmlFile> <schemaFile>");
        }
        else
        {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                    Boolean.FALSE);

            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schemaGrammar = schemaFactory.newSchema(new File(args[1]));

            Validator schemaValidator = schemaGrammar.newValidator();
            schemaValidator.setErrorHandler(new ErrorHandler());

            Source source = new StreamSource(args[0]);
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(source);

            schemaValidator.validate(source);

            while (xmlr.hasNext())
            {
                int eventType = xmlr.next();
                System.out.println("Event of type: " + eventType);
            }
        }
    }
}
