public class Meta {
    private String name;
    private String license;
    private String website;
    private long page;
    private long limit;
    private long found;

    @Override
    public String toString() {
        return "Meta{" +
                "name='" + name + '\'' +
                ", license='" + license + '\'' +
                ", website='" + website + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", found=" + found +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getFound() {
        return found;
    }

    public void setFound(long found) {
        this.found = found;
    }

    public Meta(String name, String license, String website, long page, long limit, long found) {
        this.name = name;
        this.license = license;
        this.website = website;
        this.page = page;
        this.limit = limit;
        this.found = found;
    }
}
