public class AVGParameters {
    private String pm25;
    private String pm10;
    private String so2;
    private String no2;
    private String o3;
    private String co;

    @Override
    public String toString() {
        return "AVGParameters{" +
                "pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", so2='" + so2 + '\'' +
                ", no2='" + no2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", co='" + co + '\'' +
                '}';
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public AVGParameters(Double pm25AVG, Double pm10AVG, Double so2AVG, Double no2AVG, Double o3AVG, Double coAVG) {
        if(pm25AVG > 0 && pm25AVG <= 13){
            this.pm25 = "very good";
        }
        else if(pm25AVG > 13 && pm25AVG <= 37){
            this.pm25 = "good";
        }
        else if(pm25AVG > 37 && pm25AVG <= 61){
            this.pm25 = "average";
        }
        else if(pm25AVG > 61 && pm25AVG <= 85){
            this.pm25 = "sufficient";
        }
        else if(pm25AVG > 85 && pm25AVG <= 121){
            this.pm25 = "bad";
        }
        else if(pm25AVG > 121){
            this.pm25 = "very bad";
        }
        else{
            this.pm25 = "no data";
        }

        if(pm10AVG > 0 && pm10AVG <= 21){
            this.pm10 = "very good";
        }
        else if(pm10AVG > 21 && pm10AVG <= 61){
            this.pm10 = "good";
        }
        else if(pm10AVG > 61 && pm10AVG <= 101){
            this.pm10 = "average";
        }
        else if(pm10AVG > 101 && pm10AVG <= 141){
            this.pm10 = "sufficient";
        }
        else if(pm10AVG > 141 && pm10AVG <= 201){
            this.pm10 = "bad";
        }
        else if(pm10AVG > 201){
            this.pm10 = "very bad";
        }
        else{
            this.pm10 = "no data";
        }

        if(so2AVG > 0 && so2AVG <= 51){
            this.so2 = "very good";
        }
        else if(so2AVG > 51 && so2AVG <= 101){
            this.so2 = "good";
        }
        else if(so2AVG > 101 && so2AVG <= 201){
            this.so2 = "average";
        }
        else if(so2AVG > 201 && so2AVG <= 351){
            this.so2 = "sufficient";
        }
        else if(so2AVG > 351 && so2AVG <= 501){
            this.so2 = "bad";
        }
        else if(so2AVG > 501){
            this.so2 = "very bad";
        }
        else{
            this.so2 = "no data";
        }

        if(no2AVG > 0 && no2AVG <= 41){
            this.no2 = "very good";
        }
        else if(no2AVG > 41 && no2AVG <= 101){
            this.no2 = "good";
        }
        else if(no2AVG > 101 && no2AVG <= 151){
            this.no2 = "average";
        }
        else if(no2AVG > 151 && no2AVG <= 201){
            this.no2 = "sufficient";
        }
        else if(no2AVG > 201 && no2AVG <= 401){
            this.no2 = "bad";
        }
        else if(no2AVG > 401){
            this.no2 = "very bad";
        }
        else{
            this.no2 = "no data";
        }

        if(o3AVG > 0 && o3AVG <= 71){
            this.o3 = "very good";
        }
        else if(o3AVG > 71 && o3AVG <= 121){
            this.o3 = "good";
        }
        else if(o3AVG > 121 && o3AVG <= 151){
            this.o3 = "average";
        }
        else if(o3AVG > 151 && o3AVG <= 181){
            this.o3 = "sufficient";
        }
        else if(o3AVG > 181 && o3AVG <= 241){
            this.o3 = "bad";
        }
        else if(o3AVG > 241){
            this.o3 = "very bad";
        }
        else{
            this.o3 = "no data";
        }

        if(coAVG > 0 && coAVG <= 3){
            this.co = "very good";
        }
        else if(coAVG > 3 && coAVG <= 7){
            this.co = "good";
        }
        else if(coAVG > 7 && coAVG <= 11){
            this.co = "average";
        }
        else if(coAVG > 11 && coAVG <= 15){
            this.co = "sufficient";
        }
        else if(coAVG > 15 && coAVG <= 21){
            this.co = "bad";
        }
        else if(coAVG > 21){
            this.co = "very bad";
        }
        else{
            this.co = "no data";
        }
    }
}
