package B_Server.Controller.Tools;

import B_Server.Model.Models.Info;
import B_Server.Model.Models.Product;
import Exceptions.ProductDoesNotExistException;

import java.time.format.DateTimeFormatter;

public interface AccountController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    default Info viewProductInfoById(String id) throws ProductDoesNotExistException, NumberFormatException {
        return Product.getProductById(Long.parseLong(id)).getProduct_Info();
    }

}
