package beehive.josh.exms;

/**
 * Created by Josh on 2016-11-28.
 */
public class Data {
    String  idx, code, name, exp;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Data( String code, String name, String exp) {


        this.code = code;
        this.name = name;
        this.exp = exp;
    }

    public Data(String idx, String code, String name, String exp) {
        this.idx = idx;
        this.code = code;
        this.name = name;
        this.exp = exp;
    }
}
