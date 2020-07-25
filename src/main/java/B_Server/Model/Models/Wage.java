package B_Server.Model.Models;

import Exceptions.InvalidWagePercentageExeption;

public class Wage {
    /*****************************************************fields*******************************************************/
    protected static double wagePercentage;

    /*****************************************************getters*******************************************************/
    public static double getWagePercentage() {
        return wagePercentage;
    }

    /*****************************************************setters*******************************************************/
    public static void setWagePercentage(double wagePercentage) throws InvalidWagePercentageExeption {
        if(wagePercentage<=100){
            Wage.wagePercentage = wagePercentage;
        }else{
            throw new InvalidWagePercentageExeption("InvalidWagePercentageExeption");
        }
    }
}
