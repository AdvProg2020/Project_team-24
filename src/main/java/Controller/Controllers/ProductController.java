package Controller.Controllers;



import Exceptions.AddToCartNotConfirmedException;
import Exceptions.CannotShopAsAGuestException;
import Model.Models.ProductInfo;

import java.util.ArrayList;

public class ProductController {
    public ArrayList<ProductInfo> digest(){}
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
