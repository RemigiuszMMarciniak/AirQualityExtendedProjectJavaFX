import java.util.ArrayList;

public class ResultsOutput {
    private Meta meta;
    private ArrayList<Results> results;

    @Override
    public String toString() {
        return "ResultsOutput{" +
                "meta=" + meta +
                ", results=" + results +
                '}';
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public ResultsOutput(Meta meta, ArrayList<Results> results) {
        this.meta = meta;
        this.results = results;
    }
}
