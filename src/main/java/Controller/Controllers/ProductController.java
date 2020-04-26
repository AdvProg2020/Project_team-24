package Controller.Controllers;

import Model.Models.ProductGeneralSpecifications;

import java.util.ArrayList;

public class ProductController {
    public ArrayList<ProductGeneralSpecifications> digest(){}
    public void addToCart(){}
    private void checkConfirmAddToCart() throws addToCartNotConfirmedException {}
    private void checkIfLogedIn()throws cannotShopAsAGuestException{}
    public void selectSeller(String sellerUsername){}
    public ArrayList<String> attributes(){}
    public ArrayList<String> compare(long productId){}
    public ArrayList<String> comments(){}
    public void addComment(String title,String content){}
    private String getCommentTitle(String title){}
    private String getCommentContent(String title){}
}
