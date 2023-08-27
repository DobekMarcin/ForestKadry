package md.enovaImport.sql.models;

public class PdfElement {
    private Integer id;
    private Integer order;
    private String elementName;
    private String elementAlias;
    private String elementTime;
    private Integer elementDays;
    private Double elementVaule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementAlias() {
        return elementAlias;
    }

    public void setElementAlias(String elementAlias) {
        this.elementAlias = elementAlias;
    }

    public String getElementTime() {
        return elementTime;
    }

    public void setElementTime(String elementTime) {
        this.elementTime = elementTime;
    }

    public Integer getElementDays() {
        return elementDays;
    }

    public void setElementDays(Integer elementDays) {
        this.elementDays = elementDays;
    }

    public Double getElementVaule() {
        return elementVaule;
    }

    public void setElementVaule(Double elementVaule) {
        this.elementVaule = elementVaule;
    }

    @Override
    public String toString() {
        return "PdfElement{" +
                "id=" + id +
                ", order=" + order +
                ", elementName='" + elementName + '\'' +
                ", elementAlias='" + elementAlias + '\'' +
                ", elementTime='" + elementTime + '\'' +
                ", elementDays=" + elementDays +
                ", elementVaule=" + elementVaule +
                '}';
    }
}
