package at.porscheinformatik.sonarqube.licensecheck.maven;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SettingsXmlHandler extends DefaultHandler
{

    private Boolean enableReadElementData = false;
    private String tagName = "";
    private Setting setting;

    @Override
    public void startDocument() throws SAXException
    {
        setting = new Setting();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        if (qName.equals("localRepository"))
        {
            tagName = "localRepository";
        }
        else
        {
            tagName = "";
        }

        enableReadElementData = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {

        enableReadElementData = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {

        if (enableReadElementData)
        {
            if (tagName.equals("localRepository"))
            {
                setting.setLocalRepositoryPath(new String(ch, start, length));
            }
        }
    }

    public Setting getSetting()
    {
        return setting;
    }

}
