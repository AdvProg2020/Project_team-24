package Controller.Tools;

import java.util.function.Supplier;

public interface RegisterValidator extends Supplier<RegisterValidator.RegisterValidation> {

    static RegisterValidator isFirstName(String name) {
        return () -> name.matches("^([a-z A-Z])$")
                    ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_FIRST_NAME;
    }

    static RegisterValidator isLastName(String name) {
        return () -> name.matches("^([a-z A-Z])$")
                ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_LAST_NAME;
    }

    static RegisterValidator isPhoneNumber(String numb) {
        return () -> numb.matches("^(\\d{11})$")
                ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_NUMB;
    }

    static RegisterValidator isBrand(String brand) {
        return () -> brand.matches("^(.+)$")
                ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_BRAND;
    }

    static RegisterValidator isEmail(String email) {
        return () -> email.matches("^(.+)@(gmail|yahoo)(.+)$")
                ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_EMAIL;
    }

    static RegisterValidator isUsername(String user) {
        return () -> user.matches("^(\\w+)$") ?
                user.matches("^(\\w{6}\\w*)$") ?
                RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_USERNAME_TOO_SHORT
                : RegisterValidation.IS_NOT_A_VALID_USERNAME_CHAR;
    }

    static RegisterValidator isPassword(String pass) {
        return () -> pass.matches("^(\\w+)$")
                ? RegisterValidation.IS_VALID : RegisterValidation.IS_NOT_A_VALID_PASS;
    }

    default RegisterValidator and (RegisterValidator after) {
        return () -> {
            RegisterValidation registerValidation = this.get();
            return registerValidation == RegisterValidation.IS_VALID ? after.get() : registerValidation;
        };
    }

    enum RegisterValidation {
        IS_NOT_A_VALID_EMAIL, IS_NOT_A_VALID_FIRST_NAME,
        IS_NOT_A_VALID_LAST_NAME, IS_NOT_A_VALID_NUMB,
        IS_NOT_A_VALID_BRAND , IS_NOT_A_VALID_USERNAME_CHAR,
        IS_NOT_A_VALID_USERNAME_TOO_SHORT, IS_NOT_A_VALID_PASS,
        IS_VALID
    }
}
