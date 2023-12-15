package md.enovaImport.sql.models;

public class OrderWork {

    private Integer orderYear;
    private Integer orderNumber;
    private Integer orderId;
    private Double time;
    private String orderName;

    public OrderWork(Integer orderYear, Integer orderNumber, Integer orderId, Double time, String orderName) {
        this.orderYear = orderYear;
        this.orderNumber = orderNumber;
        this.orderId = orderId;
        this.time = time;
        this.orderName = orderName;
    }

    public OrderWork() {
    }

    public Integer getOrderYear() {

        return orderYear;
    }

    public void setOrderYear(Integer orderYear) {
        this.orderYear = orderYear;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
