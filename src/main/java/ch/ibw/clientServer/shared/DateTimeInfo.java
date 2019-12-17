package ch.ibw.clientServer.shared;

public class DateTimeInfo {
    private String info;

    // Jackson braucht zwingend einen default constructor.
    public DateTimeInfo() { }

    public DateTimeInfo(String info){
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "DateTimeInfo{" +
                "info='" + info + '\'' +
                '}';
    }
}
