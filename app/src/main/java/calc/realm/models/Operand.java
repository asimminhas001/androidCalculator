package calc.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kamalhamoud on 2016-12-26.
 */

public class Operand extends RealmObject {

    @PrimaryKey
    private int id;

    private double operand;
    private int equationId;


    public int getId() {
        return id;
    }

    public double getOperand() {
        return operand;
    }

    public void setOperand(double operand) {
        this.operand = operand;
    }

    public int getEquationId() {
        return equationId;
    }

    public void setEquationId(int equationId) {
        this.equationId = equationId;
    }

}
