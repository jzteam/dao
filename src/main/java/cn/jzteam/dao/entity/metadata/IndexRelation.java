package cn.jzteam.dao.entity.metadata;

public class IndexRelation {
    private String from;
    private String to;
    private boolean oneToOne;

    public IndexRelation(String from, boolean oneToOne) {
        this.from = from;
        this.oneToOne = oneToOne;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean isOneToOne() {
        return oneToOne;
    }

    @Override
    public String toString() {
        return "relation@" + from + "=>" + to + ":" + oneToOne;
    }

}
