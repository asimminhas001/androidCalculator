package calc.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kamalhamoud on 2016-12-26.
 */

public class Operator extends RealmObject {

    @PrimaryKey
    private int id;

    private String operator;
    private int equationId;

    public int getEquationId() {
        return equationId;
    }

    public void setEquationId(int equationId) {
        this.equationId = equationId;
    }

    public int getId() {
        return id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
