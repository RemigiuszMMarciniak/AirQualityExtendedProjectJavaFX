public class Parameters {
    private String date;
    private double pm25min;
    private double pm10min;
    private double so2min;
    private double no2min;
    private double o3min;
    private double comin;
    private double pm25max;
    private double pm10max;
    private double so2max;
    private double no2max;
    private double o3max;
    private double comax;
    private double pm25avg;
    private double pm10avg;
    private double so2avg;
    private double no2avg;
    private double o3avg;
    private double coavg;
    private double pm25std;
    private double pm10std;
    private double so2std;
    private double no2std;
    private double o3std;
    private double costd;

    @Override
    public String toString() {
        return "Parameters{" +
                "date='" + date + '\'' +
                ", pm25min=" + pm25min +
                ", pm10min=" + pm10min +
                ", so2min=" + so2min +
                ", no2min=" + no2min +
                ", o3min=" + o3min +
                ", comin=" + comin +
                ", pm25max=" + pm25max +
                ", pm10max=" + pm10max +
                ", so2max=" + so2max +
                ", no2max=" + no2max +
                ", o3max=" + o3max +
                ", comax=" + comax +
                ", pm25avg=" + pm25avg +
                ", pm10avg=" + pm10avg +
                ", so2avg=" + so2avg +
                ", no2avg=" + no2avg +
                ", o3avg=" + o3avg +
                ", coavg=" + coavg +
                ", pm25std=" + pm25std +
                ", pm10std=" + pm10std +
                ", so2std=" + so2std +
                ", no2std=" + no2std +
                ", o3std=" + o3std +
                ", costd=" + costd +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPm25min() {
        return pm25min;
    }

    public void setPm25min(double pm25min) {
        this.pm25min = pm25min;
    }

    public double getPm10min() {
        return pm10min;
    }

    public void setPm10min(double pm10min) {
        this.pm10min = pm10min;
    }

    public double getSo2min() {
        return so2min;
    }

    public void setSo2min(double so2min) {
        this.so2min = so2min;
    }

    public double getNo2min() {
        return no2min;
    }

    public void setNo2min(double no2min) {
        this.no2min = no2min;
    }

    public double getO3min() {
        return o3min;
    }

    public void setO3min(double o3min) {
        this.o3min = o3min;
    }

    public double getComin() {
        return comin;
    }

    public void setComin(double comin) {
        this.comin = comin;
    }

    public double getPm25max() {
        return pm25max;
    }

    public void setPm25max(double pm25max) {
        this.pm25max = pm25max;
    }

    public double getPm10max() {
        return pm10max;
    }

    public void setPm10max(double pm10max) {
        this.pm10max = pm10max;
    }

    public double getSo2max() {
        return so2max;
    }

    public void setSo2max(double so2max) {
        this.so2max = so2max;
    }

    public double getNo2max() {
        return no2max;
    }

    public void setNo2max(double no2max) {
        this.no2max = no2max;
    }

    public double getO3max() {
        return o3max;
    }

    public void setO3max(double o3max) {
        this.o3max = o3max;
    }

    public double getComax() {
        return comax;
    }

    public void setComax(double comax) {
        this.comax = comax;
    }

    public double getPm25avg() {
        return pm25avg;
    }

    public void setPm25avg(double pm25avg) {
        this.pm25avg = pm25avg;
    }

    public double getPm10avg() {
        return pm10avg;
    }

    public void setPm10avg(double pm10avg) {
        this.pm10avg = pm10avg;
    }

    public double getSo2avg() {
        return so2avg;
    }

    public void setSo2avg(double so2avg) {
        this.so2avg = so2avg;
    }

    public double getNo2avg() {
        return no2avg;
    }

    public void setNo2avg(double no2avg) {
        this.no2avg = no2avg;
    }

    public double getO3avg() {
        return o3avg;
    }

    public void setO3avg(double o3avg) {
        this.o3avg = o3avg;
    }

    public double getCoavg() {
        return coavg;
    }

    public void setCoavg(double coavg) {
        this.coavg = coavg;
    }

    public double getPm25std() {
        return pm25std;
    }

    public void setPm25std(double pm25std) {
        this.pm25std = pm25std;
    }

    public double getPm10std() {
        return pm10std;
    }

    public void setPm10std(double pm10std) {
        this.pm10std = pm10std;
    }

    public double getSo2std() {
        return so2std;
    }

    public void setSo2std(double so2std) {
        this.so2std = so2std;
    }

    public double getNo2std() {
        return no2std;
    }

    public void setNo2std(double no2std) {
        this.no2std = no2std;
    }

    public double getO3std() {
        return o3std;
    }

    public void setO3std(double o3std) {
        this.o3std = o3std;
    }

    public double getCostd() {
        return costd;
    }

    public void setCostd(double costd) {
        this.costd = costd;
    }

    public Parameters(String date, double pm25min, double pm10min, double so2min, double no2min, double o3min, double comin, double pm25max, double pm10max, double so2max, double no2max, double o3max, double comax, double pm25avg, double pm10avg, double so2avg, double no2avg, double o3avg, double coavg, double pm25std, double pm10std, double so2std, double no2std, double o3std, double costd) {

        if(date == null){
            this.date = "-1";
        }
        else{
            this.date = date;
        }
        if(Double.isNaN(pm25min) || pm25min < 0){
            this.pm25min = -1;
        }
        else{
            this.pm25min = pm25min;
        }
        if(Double.isNaN(pm10min) || pm10min < 0){
            this.pm10min = -1;
        }
        else{
            this.pm10min = pm10min;
        }

        if(Double.isNaN(so2min) || so2min < 0){
            this.so2min = -1;
        }
        else{
            this.so2min = so2min;
        }

        if(Double.isNaN(no2min) || no2min < 0){
            this.no2min = -1;
        }
        else{
            this.no2min = no2min;
        }

        if(Double.isNaN(o3min) || o3min < 0){
            this.o3min = -1;
        }
        else{
            this.o3min = o3min;
        }
        if(Double.isNaN(comin) || comin < 0){
            this.comin = -1;
        }
        else{
            this.comin = comin;
        }

        if(Double.isNaN(pm25max) || pm25max < 0){
            this.pm25max = -1;
        }
        else{
            this.pm25max = pm25max;
        }

        if(Double.isNaN(pm10max) || pm10max < 0){
            this.pm10max = -1;
        }
        else{
            this.pm10max = pm10max;
        }

        if(Double.isNaN(so2max) || so2max < 0){
            this.so2max = -1;
        }
        else{
            this.so2max = so2max;
        }
        if(Double.isNaN(no2max) || no2max < 0){
            this.no2max = -1;
        }
        else{
            this.no2max = no2max;
        }
        if(Double.isNaN(o3max) || o3max < 0){
            this.o3max = -1;
        }
        else{
            this.o3max = o3max;
        }
        if(Double.isNaN(comax) || comax < 0){
            this.comax = -1;
        }
        else{
            this.comax = comax;
        }

        if(Double.isNaN(pm25avg) || pm25avg < 0){
            this.pm25avg = -1;
        }
        else{
            this.pm25avg = pm25avg;
        }
        if(Double.isNaN(pm10avg) || pm10avg < 0){
            this.pm10avg = -1;
        }
        else{
            this.pm10avg = pm10avg;
        }
        if(Double.isNaN(so2avg) || so2avg < 0){
            this.so2avg = -1;
        }
        else{
            this.so2avg = so2avg;
        }
        if(Double.isNaN(no2avg) || no2avg < 0){
            this.no2avg = -1;
        }
        else{
            this.no2avg = no2avg;
        }
        if(Double.isNaN(o3avg) || o3avg < 0){
            this.o3avg = -1;
        }
        else{
            this.o3avg = o3avg;
        }
        if(Double.isNaN(coavg) || coavg < 0){
            this.coavg = -1;
        }
        else{
            this.coavg = coavg;
        }
        if(Double.isNaN(pm25std) || pm25std < 0){
            this.pm25std = -1;
        }
        else{
            this.pm25std = pm25std;
        }

        if(Double.isNaN(pm10std) || pm10std < 0){
            this.pm10std = -1;
        }
        else{
            this.pm10std = pm10std;
        }
        if(Double.isNaN(so2std) || so2std < 0){
            this.so2std = -1;
        }
        else{
            this.so2std = so2std;
        }

        if(Double.isNaN(no2std) || no2std < 0){
            this.no2std = -1;
        }
        else{
            this.no2std = no2std;
        }
        if(Double.isNaN(o3std) || o3std < 0){
            this.o3std = -1;
        }
        else{
            this.o3std = o3std;
        }
        if(Double.isNaN(costd) || costd < 0){
            this.costd = -1;
        }
        else{
            this.costd = costd;
        }

    }
}
