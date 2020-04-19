package Model;

import Controller.Controllers.ControllerUnit;
import Model.DataBase.DataBase;

public class ModelUnit {

    private static ModelUnit modelUnit;

    private static DataBase dataBase;

    static {
        dataBase = DataBase.getInstance();
    }

    private ControllerUnit controllerUnit;

    // R.


    public static ModelUnit getInstance(ControllerUnit controllerUnit) {
        if (modelUnit == null)
            modelUnit = new ModelUnit(controllerUnit);
        return modelUnit;
    }

    private ModelUnit(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }
}
