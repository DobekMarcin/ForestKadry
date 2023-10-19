package md.enovaImport.sql.models;

public class BookKeepingPatternType {

    private Integer id;
    private String name;

    public BookKeepingPatternType() {
    }

    @Override
    public String toString() {
        return "BooKeepingPatternType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public BookKeepingPatternType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
