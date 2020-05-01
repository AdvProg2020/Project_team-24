package Controller.Controllers;



import Exceptions.AddToCartNotConfirmedException;
import Exceptions.CannotShopAsAGuestException;
import Model.Models.ProductInfo;

import java.util.ArrayList;

public class ProductController {
    public ArrayList<ProductInfo> digest(){return null;}
    public void addToCart(){}
    private void checkConfirmAddToCart() throws AddToCartNotConfirmedException {}
    private void checkIfLogedIn()throws CannotShopAsAGuestException {}
    public void selectSeller(String sellerUsername){}
    public ArrayList<String> attributes(){return null;}
    public ArrayList<String> compare(long productId){return null;}
    public ArrayList<String> comments(){return null;}
    public void addComment(String title,String content){}
    private String getCommentTitle(String title){return null;}
    private String getCommentContent(String title){return null;}
}
