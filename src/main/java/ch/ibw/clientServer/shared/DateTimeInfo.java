package ch.ibw.clientServer.shared;

import java.io.Serializable;

public class DateTimeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String info;

    public DateTimeInfo() {
    }

    public DateTimeInfo(String info) {
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
