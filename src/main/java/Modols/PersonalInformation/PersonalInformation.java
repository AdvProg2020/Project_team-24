package Modols.PersonalInformation;

import Modols.Account.Account;
import Modols.Tools.ToJsonFunctions;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonArray;
import com.gilecode.yagson.com.google.gson.JsonElement;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PersonalInformation implements ToJsonFunctions<PersonalInformation> {

    protected static int totalNumberOfPersonalInformationCreated;
    protected static File file;

    static {
        file = new File("src/main/resources/allPersonalInformation/allPersonalInformation.json");
        Optional<JsonArray> elements = Optional.ofNullable(ToJsonFunctions.fromJsonToJsonArray(file));
        elements.ifPresent(elem -> totalNumberOfPersonalInformationCreated = elem.size());
    }

    protected int personalInformationId;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public int getPersonalInformationId() {
        return personalInformationId;
    }

    @Override
    public JsonArray addToJsonArray(PersonalInformation object) {
        Optional<JsonArray> jsonArray = Optional.ofNullable(ToJsonFunctions.fromJsonToJsonArray(file));
        JsonElement jsonElement = fromAccountToMiniJson(object);
        jsonArray.ifPresent(jsons ->  jsons.add(jsonElement));
        return jsonArray.orElse(null);
    }

    @Override
    public JsonArray updateJsonArray(PersonalInformation object) {
        Optional<JsonArray> jsonArray = Optional.ofNullable(ToJsonFunctions.fromJsonToJsonArray(file));
        JsonElement jsonElement = fromAccountToMiniJson(object);
        jsonArray.ifPresent(jsons ->  jsons.set(personalInformationId,jsonElement));
        return jsonArray.orElse(null);
    }

    @Override
    public JsonElement fromAccountToMiniJson(PersonalInformation object) {
        return new YaGson().toJsonTree(object);
    }

    @Override
    public void addToResources() throws IOException {
        ToJsonFunctions.fromJsonArrayToJson(file,addToJsonArray(this));
    }

    @Override
    public void updateResources() throws IOException {
        ToJsonFunctions.fromJsonArrayToJson(file,updateJsonArray(this));
    }

    public PersonalInformation(int personalInformationId, String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        this.personalInformationId = personalInformationId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
