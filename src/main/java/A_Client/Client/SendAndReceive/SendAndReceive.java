package A_Client.Client.SendAndReceive;

import A_Client.Client.Client;
import MessageFormates.MessagePattern;
import Toolkit.JsonHandler.JsonHandler;
import MessageFormates.MessageSupplier;
import Structs.*;
import Structs.FieldAndFieldList.Field;
import com.gilecode.yagson.YaGson;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SendAndReceive implements MessagePattern {

    private final static Client client = new Client("localhost", 8013);

    public static Client getClient() {
        return client;
    }

    // GetToken
    public static String getToken() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetToken, null);
        return answer.get(1);
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

    public static MiniCart getCartByUserId(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCartByUserId,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniCart>().JsonToObject(answer.get(0), MiniCart.class);
    }

    public static MiniCate getCateById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCateById,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        return new JsonHandler<MiniCate>().JsonToObject(answer.get(0), MiniCate.class);
    }

    public static MiniDiscountCode getCodeById(String codeId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCateById,
                Arrays.asList(client.getClientInfo().getToken(), codeId));
        return new JsonHandler<MiniDiscountCode>().JsonToObject(answer.get(0), MiniDiscountCode.class);
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

    public static void setImageById(String mediasId, File file) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.add(mediasId);
        list.add(GetByteOfFile(file));
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.SetImageById, list);
    }

    private static String GetByteOfFile(File file) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new YaGson().toJson(bytes);
    }

    public static void setMedias(String productId, File image, File movie) {
        String s = GetByteOfFile(image);
        String o = GetByteOfFile(movie);
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.SetMediasOfProduct, Arrays.asList(client.getClientInfo().getToken(), productId, s, o));
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

    public static List<MiniProduct> getAllProductsPrime() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductsPrime,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    public static List<MiniRequest> getAllRequest() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllRequest,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniRequest>().JsonsToObjectList(answer, MiniRequest.class);
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

    public static List<MiniLogHistory> GetLogsOfUserById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAuctions,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniLogHistory>().JsonsToObjectList(answer, MiniLogHistory.class);
    }

    public static List<MiniDiscountCode> GetCodesOfUserById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCodesOfUserById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniDiscountCode>().JsonsToObjectList(answer, MiniDiscountCode.class);
    }

    public static List<MiniComment> getAllCommentOfProduct(String productId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllCommentOfProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<MiniComment>().JsonsToObjectList(answer, MiniComment.class);
    }

    public static List<MiniAuction> getAllAuctions() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAuctions,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAuction>().JsonsToObjectList(answer, MiniAuction.class);
    }

    public static List<MiniProduct> getAllProductsOfCategoryById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfCate,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    public static List<MiniProduct> getAllProductOfUserCart(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfCart,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
    }

    public static List<MiniProduct> getAllProductOfAuction(String auctionId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfAuction,
                Arrays.asList(client.getClientInfo().getToken(), auctionId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, MiniProduct.class);
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
    public static void addAccount(List<String> fields, String type) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        MessageSupplier.RequestType addNewAccount = type.equals("Seller") ? MessageSupplier.RequestType.addNewSeller
                : MessageSupplier.RequestType.addNewCustomerOrManager;
        List<String> answer = client.sendAndReceive(addNewAccount, list);
    }

    public static void addProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewProduct, list);
    }

    public static void addAuction(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewAuction, list);
    }

    public static void addCategory(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewCate, list);
    }

    public static void addDiscountCode(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewDiscountCode, list);
    }

    public static void addToCodesList(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addToCodesList, list);
    }

    public static void EditAccount(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.EditAccount, list);
    }

    public static void EditProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.EditProduct, list);
    }

    public static void EditAuction(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.EditAuction, list);
    }

    public static void EditCategory(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.EditCate, list);
    }

    public static void EditDiscountCode(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.EditDiscountCode, list);
    }

    // Others
    public static void addNewFilter(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewFilter, list);
    }

    public static void addNewSellerOfPro(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewSellerOfPro, list);
    }

    public static void saveInfoOfProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addNewSellerOfPro, list);
    }

    public static void addCommentToProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addCommentToProduct, list);
    }

    public static void sendPaymentInfo(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.sendPaymentInfo, list);
    }

    public static void DeleteAccountById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.DeleteAccountById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
    }

    public static void addChatMode(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addChatMode,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
    }

    public static void DeleteProductById(String productId, String howDelete) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.DeleteProductById,
                Arrays.asList(client.getClientInfo().getToken(), productId, howDelete));
    }

    public static void increaseProduct(String productId, String sellerId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.increaseProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId, sellerId));
    }

    public static void decreaseProduct(String productId, String sellerId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.decreaseProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId, sellerId));
    }

    public static List<Field> getCateInfoOdProduct(String productId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.getCateInfoOfProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<Field>().JsonsToObjectList(answers, Field.class);
    }

    public static List<Field> getProductInfoById(String productId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.getProductInfoById,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<Field>().JsonsToObjectList(answers, Field.class);
    }

    public static void addProductToCart(String productId, String sellerId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addProductToCart,
                Arrays.asList(client.getClientInfo().getToken(), productId, sellerId));
    }

    public static void acceptRequest(String requestId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.acceptRequest,
                Arrays.asList(client.getClientInfo().getToken(), requestId));
    }

    public static void declineRequest(String requestId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.declineRequest,
                Arrays.asList(client.getClientInfo().getToken(), requestId));
    }

    public static void CheckMyDiscountCodes() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.CheckDiscountCodes,
                Collections.singletonList(client.getClientInfo().getToken()));
    }

    public static String Login(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Login, list);
        return answer.get(0);
    }

    public static void Logout() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Logout, Collections.singletonList(client.getClientInfo().getToken()));
    }

    public static void rate(String productId, String accountId, String rate) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Logout,
                Arrays.asList(client.getClientInfo().getToken(), productId, accountId, rate));
    }

    public static MiniLogHistory Purchase() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Purchase,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniLogHistory>().JsonToObject(answer.get(0), MiniLogHistory.class);
    }

    public static void ProductSort(String sort) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Sort,
                Arrays.asList(client.getClientInfo().getToken(), sort));
    }

    // Set Current
    public static void SetCurrentCate(String cateId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.SetCurrentCate,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
    }

    public static void SetCurrentCode(String codeId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.SetCurrentCode,
                Arrays.asList(client.getClientInfo().getToken(), codeId));
    }

    public static void SetCurrentProduct(String productId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.SetCurrentProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId));
    }
}
