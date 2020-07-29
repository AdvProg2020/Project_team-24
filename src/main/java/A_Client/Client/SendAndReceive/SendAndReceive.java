package A_Client.Client.SendAndReceive;

import A_Client.Client.Client;
import A_Client.Graphics.MainMenu;
import MessageFormates.MessagePattern;
import MessageFormates.MessageSupplier;
import Structs.FieldAndFieldList.Field;
import Structs.*;
import Toolkit.JsonHandler.JsonHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SendAndReceive implements MessagePattern {

    private final static Client client = MainMenu.getClient();

    public static Client getClient() {
        return client;
    }

    // GetToken
    public static void getToken() {
        client.sendAndReceive(MessageSupplier.RequestType.GetToken, null);
    }

    // Single Object
    public static MiniProduct getProductById(String productId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetProductById,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniProduct>().JsonToObject(jsons.get(0), MiniProduct.class);
    }

    public static MiniAccount getAccountById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAccountById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniAccount>().JsonToObject(jsons.get(0), MiniAccount.class);
    }

    public static MiniCart getCartByUserId(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCartByUserId,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniCart>().JsonToObject(jsons.get(0), MiniCart.class);
    }

    public static MiniCate getCateById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCateById,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniCate>().JsonToObject(jsons.get(0), MiniCate.class);
    }

    public static MiniDiscountCode getCodeById(String codeId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCateById,
                Arrays.asList(client.getClientInfo().getToken(), codeId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniDiscountCode>().JsonToObject(jsons.get(0), MiniDiscountCode.class);
    }

    public static MiniAuction getAuctionById(String auctionId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAuctionById,
                Arrays.asList(client.getClientInfo().getToken(), auctionId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniAuction>().JsonToObject(jsons.get(0), MiniAuction.class);
    }

    public static MiniOffer getOfferById(String offerId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetOfferById,
                Arrays.asList(client.getClientInfo().getToken(), offerId));
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        else return new JsonHandler<MiniOffer>().JsonToObject(jsons.get(0), MiniOffer.class);
    }

    // Get/Set image and Movie
    public static Image getImageById(String mediaId) {

        try (Stream<Path> pathStream = Files.walk(Paths.get("src/main/java/A_Client/Client/Nmidoooonm/Image")).filter(Files::isRegularFile)) {

            Path orElse = pathStream.filter(path -> path.toFile().getName().equals(mediaId + ".jpg"))
                    .findFirst().orElse(null);

            if (orElse != null) return new Image(orElse.toFile().toURI().toString());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        client.sendAndReceive(MessageSupplier.RequestType.GetImageById,
                Arrays.asList(client.getClientInfo().getToken(), mediaId));

        try {
            String path = downloadFile("src/main/java/A_Client/Client/Nmidoooonm/Image/", ".jpg", mediaId);
            return new Image(new File(path).toURI().toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Media getMediaById(String mediaId) {

        try (Stream<Path> pathStream = Files.walk(Paths.get("src/main/java/A_Client/Client/Nmidoooonm/movie")).filter(Files::isRegularFile)) {

            Path orElse = pathStream.filter(path -> path.toFile().getName().equals(mediaId + ".mp4"))
                    .findFirst().orElse(null);

            if (orElse != null) return new Media(orElse.toFile().toURI().toString());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        client.sendAndReceive(MessageSupplier.RequestType.GetMovieById,
                Arrays.asList(client.getClientInfo().getToken(), mediaId));
        try {
            String path = downloadFile("src/main/java/A_Client/Client/Nmidoooonm/movie/", ".mp4", mediaId);
            return new Media(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getFileById(String mediaId) {

        try (Stream<Path> pathStream = Files.walk(Paths.get("src/main/java/A_Client/Client/Nmidoooonm/File")).filter(Files::isRegularFile)) {

            Path orElse = pathStream.filter(path -> path.toFile().getName().equals(mediaId + ".txt"))
                    .findFirst().orElse(null);

            if (orElse != null) {
                new File(orElse.toFile().toURI().toString());
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        client.sendAndReceive(MessageSupplier.RequestType.getFileById,
                Arrays.asList(client.getClientInfo().getToken(), mediaId));
        try {
            String path = downloadFile("src/main/java/A_Client/Client/Nmidoooonm/File/", ".txt", mediaId);
            new File(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String downloadFile(String addr, String ex, String mediaId) throws IOException {
        String pathname = addr + mediaId + ex;
        File file = new File(pathname);
        Files.createFile(file.toPath());
        OutputStream outputStream = new FileOutputStream(file);
        client.receiveFile(outputStream);
        outputStream.close();
        return pathname;
    }

    public static void setImageById(String accountId, File image) {

        client.sendAndReceive(MessageSupplier.RequestType.SetImageById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));

        try {

            client.sendFile(image);

            List<String> readMessage = client.readMessage(client.receiveMessage());
            client.getClientInfo().setToken(readMessage.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPercentOfWage(String percent) {
        client.sendAndReceive(MessageSupplier.RequestType.SetPercentOfWage,
                Arrays.asList(client.getClientInfo().getToken(), percent));
    }

    public static String getPercentOfWage() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetPercentOfWage,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<String>().JsonsToObjectList(answer, false).get(0);
    }

    public static void setMedias(File image, File movie, File file) {
        client.sendAndReceive(MessageSupplier.RequestType.SetMediasOfProduct,
                Arrays.asList(
                        client.getClientInfo().getToken(),
                        image == null ? "N" : "R",
                        movie == null ? "N" : "R",
                        file == null ? "N" : "R"
                ));
        try {

            List<String> readMessage;

            if (image != null) {
                client.sendFile(image);
                readMessage = client.readMessage(client.receiveMessage());
                client.getClientInfo().setToken(readMessage.get(0));
            }

            if (movie != null) {
                client.sendFile(movie);
                readMessage = client.readMessage(client.receiveMessage());
                client.getClientInfo().setToken(readMessage.get(0));
            }
            if (file != null) {
                client.sendFile(file);
                readMessage = client.readMessage(client.receiveMessage());
                client.getClientInfo().setToken(readMessage.get(0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get All
    public static List<MiniAccount> getAllAccounts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAccounts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAccount>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllProductsPrime() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductsPrime,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniRequest> getAllRequest() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllRequest,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniRequest>().JsonsToObjectList(answer, true);
    }

    public static List<MiniCate> getAllCategories() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllCate,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniCate>().JsonsToObjectList(answer, true);
    }

    public static List<MiniOffer> getAllOffers() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllOffers,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniOffer>().JsonsToObjectList(answer, true);
    }

    public static List<MiniDiscountCode> getAllDiscountCodes() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllDiscountCodes,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniDiscountCode>().JsonsToObjectList(answer, true);
    }

    public static List<MiniLogHistory> GetLogsOfUserById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetLogsOfUserById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniLogHistory>().JsonsToObjectList(answer, true);
    }

    public static List<MiniDiscountCode> GetCodesOfUserById(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetCodesOfUserById,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniDiscountCode>().JsonsToObjectList(answer, true);
    }

    public static List<MiniComment> getAllCommentOfProduct(String productId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllCommentOfProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<MiniComment>().JsonsToObjectList(answer, true);
    }

    public static List<MiniAuction> getAllAuctions() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAuctions,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAuction>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllProductsOfCategoryById(String cateId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfCate,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllProductOfUserCart(String accountId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfCart,
                Arrays.asList(client.getClientInfo().getToken(), accountId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllProductOfAuction(String auctionId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductOfAuction,
                Arrays.asList(client.getClientInfo().getToken(), auctionId));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllMyProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllMyProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    public static List<MiniProduct> getAllPopularProducts() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllPopularProducts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniProduct>().JsonsToObjectList(answer, true);
    }

    // Add Or Edit
    public static String addAccount(List<String> fields, String type) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        MessageSupplier.RequestType addNewAccount = MessageSupplier.RequestType.addNewCustomerOrManager;
        switch (type) {
            case "Manager":
            case "Customer":
                addNewAccount = MessageSupplier.RequestType.addNewCustomerOrManager;
                break;
            case "Seller":
                addNewAccount = MessageSupplier.RequestType.addNewSeller;
                break;
            case "Supporter":
                addNewAccount = MessageSupplier.RequestType.addNewSupporter;
        }
        List<String> answer = client.sendAndReceive(addNewAccount, list);
        return new JsonHandler<String>().JsonsToObjectList(answer, false).get(0);
    }

    public static void addProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        client.sendAndReceive(MessageSupplier.RequestType.addNewProduct, list);
    }

    public static void addNewBuyerToOfferById(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        client.sendAndReceive(MessageSupplier.RequestType.addNewBuyerToOfferById, list);
    }

    public static List<String> addAuction(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.addNewAuction, list);
    }

    public static List<String> addCategory(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.addNewCate, list);
    }

    public static List<String> addDiscountCode(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.addNewDiscountCode, list);
    }

    public static void addToCodesList(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        client.sendAndReceive(MessageSupplier.RequestType.addToCodesList, list);
    }

    public static void EditAccount(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        client.sendAndReceive(MessageSupplier.RequestType.EditAccount, list);
    }

    public static void EditProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        client.sendAndReceive(MessageSupplier.RequestType.EditProduct, list);
    }

    public static List<String> EditAuction(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.EditAuction, list);
    }

    public static List<String> EditCategory(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.EditCate, list);
    }

    public static List<String> EditDiscountCode(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.EditDiscountCode, list);
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
        client.sendAndReceive(MessageSupplier.RequestType.addNewSellerOfPro, list);
    }

    public static void saveInfoOfProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.saveInfoOfProduct, list);
    }

    public static void addCommentToProduct(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addCommentToProduct, list);
    }

    public static String sendPaymentInfo(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.sendPaymentInfo, list);
        return new JsonHandler<String>().JsonsToObjectList(answer, false).get(0);
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
        return new JsonHandler<Field>().JsonsToObjectList(answers, true);
    }

    public static List<Field> getProductInfoById(String productId) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.getProductInfoById,
                Arrays.asList(client.getClientInfo().getToken(), productId));
        return new JsonHandler<Field>().JsonsToObjectList(answers, true);
    }

    public static void addProductToCart(String productId, String sellerId) {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.addProductToCart,
                Arrays.asList(client.getClientInfo().getToken(), productId, sellerId));
    }

    public static void acceptRequest(String requestId) {
        client.sendAndReceive(MessageSupplier.RequestType.acceptRequest,
                Arrays.asList(client.getClientInfo().getToken(), requestId));
    }

    public static void declineRequest(String requestId) {
        client.sendAndReceive(MessageSupplier.RequestType.declineRequest,
                Arrays.asList(client.getClientInfo().getToken(), requestId));
    }

    public static void CheckMyDiscountCodes() {
        client.sendAndReceive(MessageSupplier.RequestType.CheckDiscountCodes,
                Collections.singletonList(client.getClientInfo().getToken()));
    }

    public static List<String> Login(List<String> fields) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(fields);
        return client.sendAndReceive(MessageSupplier.RequestType.Login, list);
    }

    public static void Logout() {
        client.sendAndReceive(MessageSupplier.RequestType.Logout,
                Collections.singletonList(client.getClientInfo().getToken()));
    }

    public static List<String> rate(String productId, String accountId, String rate) {
        return client.sendAndReceive(MessageSupplier.RequestType.rate,
                Arrays.asList(client.getClientInfo().getToken(), productId, accountId, rate));
    }

    public static MiniLogHistory Purchase(List<String> listPrime) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(listPrime);
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.Purchase, list);
        List<String> jsons = new JsonHandler<String>().JsonsToObjectList(answer, false);
        if (jsons.get(0).equals("FAIL")) return null;
        return new JsonHandler<MiniLogHistory>().JsonToObject(jsons.get(0), MiniLogHistory.class);
    }

    public static void ProductSort(String sort) {
        client.sendAndReceive(MessageSupplier.RequestType.Sort,
                Arrays.asList(client.getClientInfo().getToken(), sort));
    }

    public static void closeApp() {
        client.sendAndReceive(MessageSupplier.RequestType.Sort,
                Collections.singletonList(client.getClientInfo().getToken()));
    }

    // Set Current
    public static void SetCurrentCate(String cateId) {
        client.sendAndReceive(MessageSupplier.RequestType.SetCurrentCate,
                Arrays.asList(client.getClientInfo().getToken(), cateId));
    }

    public static void SetCurrentCode(String codeId) {
        client.sendAndReceive(MessageSupplier.RequestType.SetCurrentCode,
                Arrays.asList(client.getClientInfo().getToken(), codeId));
    }

    public static void SetCurrentProduct(String productId) {
        client.sendAndReceive(MessageSupplier.RequestType.SetCurrentProduct,
                Arrays.asList(client.getClientInfo().getToken(), productId));
    }

    public static void setCurrentOffer(String offerId) {
        client.sendAndReceive(MessageSupplier.RequestType.SetCurrentOffer,
                Arrays.asList(client.getClientInfo().getToken(), offerId));

    }

    public static List<MiniAccount> getAllSupporters() {
        List<String> answer = client.sendAndReceive(MessageSupplier.RequestType.GetAllAccounts,
                Collections.singletonList(client.getClientInfo().getToken()));
        return new JsonHandler<MiniAccount>().JsonsToObjectList(answer, true).stream()
                .filter(miniAccount -> miniAccount.getAccountT().equals("Supporter")).collect(Collectors.toList());
    }

    public static List<String> addOffer(ArrayList<String> objects) {
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.addAll(objects);
        return client.sendAndReceive(MessageSupplier.RequestType.addNewOffer, list);
    }

    public static void Deposit(String depositAmount) {
        client.sendAndReceive(MessageSupplier.RequestType.Deposit,
                Arrays.asList(client.getClientInfo().getToken(), depositAmount));
    }

    public static void WithDraw(String withDrawAmount) {
        client.sendAndReceive(MessageSupplier.RequestType.WithDraw,
                Arrays.asList(client.getClientInfo().getToken(), withDrawAmount));
    }
}
