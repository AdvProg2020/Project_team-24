package Model;

import Controller.ControllerUnit;

public class ModelUnit {

    private static ModelUnit modelUnit;

    private ControllerUnit controllerUnit;

    //

    public static ModelUnit getInstance(ControllerUnit controllerUnit) {
        return modelUnit == null ?
                new ModelUnit(controllerUnit) : modelUnit;
    }

    private ModelUnit(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }
}
