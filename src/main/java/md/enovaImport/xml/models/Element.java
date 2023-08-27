package md.enovaImport.xml.models;

public class Element {

    private String elementName;

    public Element() {
    }

    public Element(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String toString() {
        return "StringElement{" +
                "elementName='" + elementName + '\'' +
                '}';
    }
}
