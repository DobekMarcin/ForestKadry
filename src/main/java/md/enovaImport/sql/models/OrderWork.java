package md.enovaImport.sql.models;

public class OrderWork {

    private Integer orderYear;
    private Integer orderNumber;
    private Integer orderId;
    private Double time;

    public OrderWork(Integer orderYear, Integer orderNumber, Integer orderId, Double amount) {
        this.orderYear = orderYear;
        this.orderNumber = orderNumber;
        this.orderId = orderId;
        this.time = amount;
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

    @Override
    public String toString() {
        return "OrderWork{" +
                "orderYear=" + orderYear +
                ", orderNumber=" + orderNumber +
                ", orderId=" + orderId +
                ", time=" + time +
                '}';
    }
}
