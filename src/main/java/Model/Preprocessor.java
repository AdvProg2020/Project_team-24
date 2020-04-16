package Model;

import Model.RuntimeData.AccountSource;

public class Preprocessor {

    private ModelUnit modelUnit;

    public Preprocessor(ModelUnit modelUnit) {
        this.modelUnit = modelUnit;
    }

    public void setSource() {

        modelUnit.setAccountSource(AccountSource.getInstance());
        //  .
        //  .
        //  .
    }

}
