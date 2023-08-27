package md.enovaImport.sql.models;

public class PersonMail {

    private Integer id_importu;
    private Integer id;
    private Integer code;
    private String name;
    private String path;
    private Boolean isFile;
    private Boolean isSend;

    public Integer getId_importu() {
        return id_importu;
    }

    public void setId_importu(Integer id_importu) {
        this.id_importu = id_importu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }
}
