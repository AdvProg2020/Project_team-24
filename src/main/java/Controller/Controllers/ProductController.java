package Controller.Controllers;

import Controller.Exceptions.AddToCartNotConfirmedException;
import Controller.Exceptions.CannotShopAsAGuestException;
import Model.Models.ProductGeneralSpecifications;

import java.util.ArrayList;

public class ProductController {
    public ArrayList<ProductGeneralSpecifications> digest(){}
    public void addToCart(){}
    private void checkConfirmAddToCart() throws AddToCartNotConfirmedException {}
    private void checkIfLogedIn()throws CannotShopAsAGuestException {}
    public void selectSeller(String sellerUsername){}
    public ArrayList<String> attributes(){}
    public ArrayList<String> compare(long productId){}
    public ArrayList<String> comments(){}
    public void addComment(String title,String content){}
    private String getCommentTitle(String title){}
    private String getCommentContent(String title){}
}
