package calc.realm.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kamalhamoud on 2016-12-26.
 */

public class Equation extends RealmObject {

    @PrimaryKey
    private int id;

    private RealmList<Operand> operandsList;
    private RealmList<Operator> operatorsList;

    public void setOperandsList(RealmList<Operand> operandsList) {
        this.operandsList = operandsList;
    }

    public void setOperatorsList(RealmList<Operator> operatorsList) {
        this.operatorsList = operatorsList;
    }

    public int getId() {

        return id;
    }

    public RealmList<Operand> getOperandsList() {
        return operandsList;
    }

    public RealmList<Operator> getOperatorsList() {
        return operatorsList;
    }
}
