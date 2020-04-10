package Model.Models;

import Model.ModelUnit.PersonalInformation;

import java.io.IOException;

public abstract class Role {

    public static void updatePersonalInformation(PersonalInformation personalInformation) throws IOException {
        personalInformation.updateResources();
    }

}
