import java.io.Serializable;

public class DateTimeInfo implements Serializable {
    private String info;

    public DateTimeInfo(String info){
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
