public class Date {
    private String utc;
    private String local;

    @Override
    public String toString() {
        return "Date{" +
                "utc='" + utc + '\'' +
                ", local='" + local + '\'' +
                '}';
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date(String utc, String local) {
        this.utc = utc;
        this.local = local;
    }
}
