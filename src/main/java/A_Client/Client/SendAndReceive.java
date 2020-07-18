package A_Client.Client;

import MessageInterfaces.MessageSupplier;
import A_Client.Graphics.MiniModels.Structs.*;
import A_Client.JsonHandler.JsonHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SendAndReceive {

    private final static Client client = new Client("localhost", 8013);

    // GetToken
    public static String getToken() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetToken,null);
        return answer.get(0);
    }

    // Single Object
    public static MiniProduct getProductById(String productId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetProductById,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<MiniProduct>().JsonToObject(answer.get(0), MiniProduct.class);
    }

    public static MiniAccount getAccountById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAccountById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniAccount>().JsonToObject(answer.get(0), MiniAccount.class);
    }

    public static MiniCate getCateById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCateById,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        return new JsonHandler<MiniCate>().JsonToObject(answer.get(0), MiniCate.class);
    }

    public static MiniAuction getAuctionById(String auctionId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAuctionById,
                Arrays.asList(client.getClientInfo().getToken(), auctionId));
        return new JsonHandler<MiniAuction>().JsonToObject(answer.get(0), MiniAuction.class);
    }

    // Get/Set image and Movie
    public static Image getImageById(String mediaId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetImageById,
                Arrays.asList(client.getClientInfo().getToken(), mediaId));
        return new JsonHandler<Image>().JsonToObject(answer.get(0), Image.class);
    }

    public static Media getMediaById(String mediaId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetMovieById,
                Arrays.asList(client.getClientInfo().getToken(), mediaId));
        return new JsonHandler<Media>().JsonToObject(answer.get(0), Media.class);
    }

    public static void setImageById(String mediaId, File file) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.SetImageById,
                file, Arrays.asList(client.getClientInfo().getToken(), mediaId));
    }

    public static void setMediaById(String mediaId, File file) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.SetMovieById,
                file, Arrays.asList(client.getClientInfo().getToken(), mediaId));
    }

    // Get All
    public static List<MiniAccount> getAllAccounts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAccounts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAccount>().JsonsToObjectList(answer, MiniAccount.class);
    }

    public static List<MiniProduct> getAllProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    public static List<MiniCate> getAllCategories() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllCate,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniCate>().JsonsToObjectList(answer, MiniCate.class);
    }

    public static List<MiniDiscountCode> getAllDiscountCodes() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAuctions,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniDiscountCode>().JsonsToObjectList(answer, MiniDiscountCode.class);
    }

    public static List<MiniAuction> getAllAuctions() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAuctions,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAuction>().JsonsToObjectList(answer, MiniAuction.class);
    }

    public static List<MiniAuction> getAllProductsOfCategoryById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfCate,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        return new JsonHandler<MiniAuction>().JsonsToObjectList(answer, MiniAuction.class);
    }

    public static List<MiniProduct> getAllMyProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllMyProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    public static List<MiniProduct> getAllPopularProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllPopularProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    // Add Or Edit
    public static void addAccount(List<String> fields) {

    }

    public static void addProduct(List<String> fields) {

    }

    public static void addAuction(List<String> fields) {

    }

    public static void addCategory(List<String> fields) {

    }

    public static void addDiscountCode(List<String> fields) {

    }
}
