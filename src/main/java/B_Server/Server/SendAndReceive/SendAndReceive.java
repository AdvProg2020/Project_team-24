package B_Server.Server.SendAndReceive;

import B_Server.Controller.Controllers.AccountControllers.BuyerController;
import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import B_Server.Controller.Controllers.AccountControllers.SellerController;
import B_Server.Controller.Controllers.FilterController;
import B_Server.Controller.Controllers.LoginaAndRegister.LoginController;
import B_Server.Controller.Controllers.LoginaAndRegister.SignUpController;
import B_Server.Controller.Controllers.ProductsController;
import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Structs.Medias;
import B_Server.Model.Models.Structs.ProductLog;
import B_Server.Server.InstantInfo.InstantInfo;
import B_Server.Server.RequestHandler.RequestHandler;
import B_Server.Server.Server;
import Exceptions.*;
import MessageFormates.MessageSupplier;
import Structs.FieldAndFieldList.Field;
import Structs.FieldAndFieldList.FieldList;
import Structs.*;
import Toolkit.JsonHandler.JsonHandler;
import com.gilecode.yagson.YaGson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SendAndReceive {

    private final static BuyerController buyerController = BuyerController.getInstance();
    private final static SellerController sellerController = SellerController.getInstance();
    private final static ManagerController managerController = ManagerController.getInstance();
    private final static YaGson yaGson = new YaGson();

    public static void messageAnalyser(@NotNull String token, String request, List<String> inputs, RequestHandler requestHandler) {

        if (token.equals("@") && request.equals("GetToken")) {
            OnlineNewClient(requestHandler);
            return;
        }

        InstantInfo info = Server.getInfoByToken(token);
        if (info == null) return;

        LocalClientInfo.initControllers(info);

        String newToken = Server.createToken();
        info.setMy_Token(newToken);

        switch (request) {
            case "GetAllMyProducts":
                getAllMyProducts(newToken, requestHandler);
                break;
            case "GetProductById":
                getProductById(newToken, inputs, requestHandler);
                break;
            case "GetAccountById":
                getAccountById(newToken, inputs, requestHandler);
                break;
            case "GetCateById":
                getCategoryById(newToken, inputs, requestHandler);
                break;
            case "GetAuctionById":
                getAuctionById(newToken, inputs, requestHandler);
                break;
            case "GetImageById":
                getImageById(newToken, inputs, requestHandler);
                break;
            case "GetMovieById":
                //...
                break;
            case "SetImageById":
                ///...
                break;
            case "SetMovieById":
                //...
                break;
            case "GetAllProducts":
                getAllProducts(newToken, request, requestHandler, Product.getList());
                break;
            case "GetAllAccounts":
                getAllAccounts(newToken, requestHandler);
                break;
            case "GetAllAuctions":
                getAllAuctions(newToken, requestHandler);
                break;
            case "GetAllDiscountCodes":
                getAllDiscountCodes(newToken, requestHandler);
                break;
            case "GetAllCate":
                getAllCategories(newToken, requestHandler);
                break;
            case "GetAllProductOfCate":
                getAllProductOfCate(newToken, inputs, requestHandler);
                break;
            case "GetAllPopularProducts":
                getAllPopularProducts(newToken, requestHandler);
                break;
            case "Login":
                login(newToken, inputs, requestHandler);
                break;
            case "Logout":
                //...
                break;
            case "GetCodesOfUserById":
                getCodesOfUsersById(newToken, inputs, requestHandler);
                break;
            case "GetLogsOfUserById":
                getLogsOfUserById(newToken, inputs, requestHandler);
                break;
            case "addNewCustomerOrManager":
                addNewCustomerOrManager(inputs, requestHandler);
                break;
            case "addNewSeller":
                addNewSeller(inputs, requestHandler);
                break;
            case "addNewAuction":
                addNewAuction(inputs, requestHandler);
                break;
            case "addNewCate":
                addNewCategory(inputs, requestHandler);
                break;
            case "EditCate":
                EditCate(inputs, requestHandler);
                break;
            case "addNewDiscountCode":
                addNewDiscountCode(inputs, requestHandler);
                break;
            case "addNewProduct":
                addNewProduct(inputs, requestHandler);
                break;
            case "EditAccount":
                editAccount(inputs, requestHandler);
                break;
            case "EditAuction":
                editAuction(inputs, requestHandler);
                break;
            case "EditProduct":
                editProduct(inputs, requestHandler);
                break;
            case "EditDiscountCode":
                editDiscountCode(inputs, requestHandler);
                break;
            case "DeleteAccountById":
                deleteAccountById(inputs, requestHandler);
                break;
            case "addNewFilter":
                addNewFilter(inputs, requestHandler);
                break;
            case "CheckDiscountCodes":
                checkAllDiscountCodes();
                break;
            case "addNewSellerOfPro":
                addNewSellerOfPro(inputs, requestHandler);
                break;
            case "saveInfoOfProduct":
                //...
                break;
            case "addToCodesList":
                addToCodesList(inputs, requestHandler);
                break;
            case "acceptRequest":
                acceptRequest(inputs, requestHandler);
                break;
            case "declineRequest":
                declineRequest(inputs, requestHandler);
                break;
            case "SetCurrentCate":
                //...
                break;
            case "SetCurrentCode":
                //...
                break;
            case "SetCurrentProduct":
                //...
                break;
            case "Sort":
                sort(inputs, requestHandler);
                break;
            case "increaseProduct":
                increaseProduct(inputs, requestHandler);
                break;
            case "decreaseProduct":
                decreaseProduct(inputs, requestHandler);
                break;
            case "sendPaymentInfo":
                sendPaymanetInfo(inputs, requestHandler);
                break;
            case "Purchase":
                //...
                break;
            case "addProductToCart":
                addProductToCart(inputs, requestHandler);
                break;
            case "getCateInfoOfProduct":
                // ...
                break;
            case "getProductInfoById":
                getProductInfoById(inputs, requestHandler);
                break;
            case "addCommentToProduct":
                addCommentToProduct(inputs, requestHandler);
                break;
            case "rate":
                rate(inputs, requestHandler);
                break;
            case "GetCodeById":
                GetCodeById(inputs, requestHandler);
                break;
            case "SetMediasOfProduct":
                String ProductId = inputs.get(0);
                String Str_Image = inputs.get(1);
                String Str_Movie = inputs.get(2);

                JsonHandler<byte[]> jsonHandler = new JsonHandler<>();
                byte[] image_bytes = jsonHandler.JsonToObject(Str_Image, byte[].class);
                byte[] movie_bytes = jsonHandler.JsonToObject(Str_Movie, byte[].class);

//                File image = ;
                break;
            case "GetAllProductsPrime":
                getAllProducts(newToken, "GetAllProductsPrime", requestHandler, instance.showProducts());
                break;
            case "GetAllRequest":
                GetAllRequest(newToken, requestHandler);
                break;
            case "GetAllProductOfAuction":
                GetAllProductOfAuction(inputs, requestHandler);
                break;
            case "GetAllCommentOfProduct":
                GetAllCommentOfProduct(inputs, requestHandler);
                break;
            case "DeleteProductById":
                DeleteProductById(inputs, requestHandler);
                break;
        }
    }

    private static void OnlineNewClient(RequestHandler requestHandler) {
        String newToken = Server.createToken();
        Server.addClient(new InstantInfo(newToken));
        sendToken(newToken, requestHandler);
    }

    private static void sender(String token, MessageSupplier.RequestType requestType, String message, @NotNull RequestHandler requestHandler) {
        requestHandler.sendMessage(requestHandler.generateMessage(requestType, Arrays.asList(token, message)));
    }

    private static void sendToken(String token, @NotNull RequestHandler requestHandler) {
        sender(token, MessageSupplier.RequestType.GetToken, "", requestHandler);
    }

    private static void getAllMyProducts(String token, RequestHandler requestHandler) {
        try {
            List<Product> products = sellerController.showProducts();
            List<MiniProduct> miniProducts = createMiniProducts(products);
            sender(token, MessageSupplier.RequestType.GetAllMyProducts, yaGson.toJson(miniProducts), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAllMyProducts, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getProductById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Product product = Product.getProductById(Long.parseLong(inputs.get(0)));
            sender(token, MessageSupplier.RequestType.GetProductById, yaGson.toJson(getMiniProduct(product)), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetProductById, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getAccountById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Account account = Account.getAccountById(Long.parseLong(inputs.get(0)));
            sender(token, MessageSupplier.RequestType.GetAccountById, yaGson.toJson(getMiniAccount(account)), requestHandler);
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAccountById, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getCategoryById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Category category = Category.getCategoryById(Long.parseLong(inputs.get(0)));
            sender(token, MessageSupplier.RequestType.GetCateById, yaGson.toJson(getMiniCate(category)), requestHandler);
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetCateById, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getAuctionById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Auction auction = Auction.getAuctionById(Long.parseLong(inputs.get(0)));
            sender(token, MessageSupplier.RequestType.GetAuctionById, yaGson.toJson(yaGson.toJson(getMiniAuction(auction))), requestHandler);
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAuctionById, yaGson.toJson(SuccessOrFail.FAIL.toString()), requestHandler);
        }
    }

    private static void getImageById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Medias medias = Medias.getMediasById(Long.parseLong(inputs.get(0)));
            File file = new File(medias.getImageSrc());
            byte[] bytes = Files.readAllBytes(file.toPath());
            sender(token, MessageSupplier.RequestType.GetImageById, yaGson.toJson(bytes), requestHandler);
        } catch (ProductMediaNotFoundException | IOException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetImageById, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getAllProducts(String token, String requestType, @NotNull RequestHandler requestHandler, List<Product> list) {
        sender(token, MessageSupplier.RequestType.valueOf(requestType), yaGson.toJson(createMiniProducts(list)), requestHandler);
    }

    private static void getAllAccounts(String token, RequestHandler requestHandler) {
        sender(token, MessageSupplier.RequestType.GetAllAccounts, yaGson.toJson(createMiniAccounts(Account.getList())), requestHandler);
    }

    private static void getAllAuctions(String token, RequestHandler requestHandler) {
        List<MiniAuction> miniAuctions = Auction.getList().stream().map(SendAndReceive::getMiniAuction).collect(Collectors.toList());
        sender(token, MessageSupplier.RequestType.GetAllAuctions, yaGson.toJson(miniAuctions), requestHandler);
    }

    private static void getAllDiscountCodes(String token, RequestHandler requestHandler) {
        List<MiniDiscountCode> miniDiscountCodes = DiscountCode.getList().stream().map(SendAndReceive::getMiniDiscountCode).collect(Collectors.toList());
        sender(token, MessageSupplier.RequestType.GetAllDiscountCodes, yaGson.toJson(miniDiscountCodes), requestHandler);
    }

    private static void getAllCategories(String token, RequestHandler requestHandler) {
        List<MiniCate> miniCateList = Category.getList().stream().map(SendAndReceive::getMiniCate).collect(Collectors.toList());
        sender(token, MessageSupplier.RequestType.GetAllCate, yaGson.toJson(miniCateList), requestHandler);
    }

    private static void getAllProductOfCate(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            List<MiniProduct> products = Category.getCategoryById(Integer.parseInt(inputs.get(0)))
                    .getProductList().stream().map(id -> {
                        try {
                            Product product = Product.getProductById(id);
                            return getMiniProduct(product);
                        } catch (ProductDoesNotExistException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).filter(Objects::nonNull).collect(Collectors.toList());
            sender(token, MessageSupplier.RequestType.GetAllProductOfCate, yaGson.toJson(products), requestHandler);

        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAllProductOfCate, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void getAllPopularProducts(String token, RequestHandler requestHandler) {
        try {
            ProductsController instance = ProductsController.getInstance();
            instance.sort("NumberOfVisits");
            sender(token, MessageSupplier.RequestType.GetAllPopularProducts, yaGson.toJson(createMiniProducts(instance.showProducts())), requestHandler);
        } catch (NotAvailableSortException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAllPopularProducts, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void login(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            String username = inputs.get(0);
            String password = inputs.get(1);
            Account account = LoginController.getInstance().login(username, password);
            sender(token, MessageSupplier.RequestType.Login, yaGson.toJson(getMiniAccount(account)), requestHandler);
        } catch (PassIncorrectException | UserNameInvalidException | UserNameTooShortException | AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.Login, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void GetAllRequest(String token, RequestHandler requestHandler) {
        List<MiniRequest> miniRequests = Request.getList().stream().map(SendAndReceive::getMiniRequest).collect(Collectors.toList());
        sender(token, MessageSupplier.RequestType.GetAllRequest, yaGson.toJson(miniRequests), requestHandler);
    }

    private static void getCodesOfUsersById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Customer account = (Customer) Account.getAccountById(Long.parseLong(inputs.get(0)));
            List<Long> discountCodeIds = account.getDiscountCodeList();
            List<MiniDiscountCode> miniDiscountCodes = discountCodeIds.stream().map(id -> {
                try {
                    return getMiniDiscountCode(DiscountCode.getDiscountCodeById(id));
                } catch (DiscountCodeExpiredException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            sender(token, MessageSupplier.RequestType.GetCodesOfUserById, yaGson.toJson(miniDiscountCodes), requestHandler);
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetCodesOfUserById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getLogsOfUserById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Account account = Account.getAccountById(Long.parseLong(inputs.get(0)));

            List<Long> logHistoriesIds;
            if (account instanceof Customer)
                logHistoriesIds = ((Customer) account).getLogHistoryList();
            else logHistoriesIds = ((Seller) account).getLogHistoryList();

            List<MiniLogHistory> logHistoryList = logHistoriesIds.stream().map(id -> {
                try {
                    LogHistory logHistory = LogHistory.getLogHistoryById(id);
                    return getMiniLogHistory(logHistory);

                } catch (LogHistoryDoesNotExistException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            sender(token, MessageSupplier.RequestType.GetCodesOfUserById, yaGson.toJson(logHistoryList), requestHandler);

        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetCodesOfUserById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniLogHistory getMiniLogHistory(@NotNull LogHistory logHistory) {
        return new MiniLogHistory(
                logHistory.getId() + "",
                logHistory.getFinalAmount() + "",
                logHistory.getDiscountAmount() + "",
                logHistory.getAuctionDiscount() + "",
                logHistory.getFieldList(),
                logHistory.getProductLogList().stream()
                        .map(SendAndReceive::getMiniProductLog)
                        .collect(Collectors.toList()));
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniProductLog getMiniProductLog(@NotNull ProductLog productLog) {
        return new MiniProductLog(
                productLog.getProductId() + "",
                productLog.getProductName(),
                productLog.getPrice() + "",
                productLog.getAuctionDiscount() + "",
                productLog.getFinalPrice() + "");
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniAuction getMiniAuction(@NotNull Auction auction) {
        return new MiniAuction(
                auction.getId() + "",
                auction.getName(),
                auction.getDiscount().getPercent(),
                auction.getDiscount().getAmount(),
                auction.getStart(),
                auction.getEnd());
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniCate getMiniCate(@NotNull Category category) {
        return new MiniCate(
                category.getId() + "",
                category.getName(),
                category.getCategoryFields());
    }

    @NotNull
    @Contract("_ -> new")
    private static MiniProduct getMiniProduct(@NotNull Product product) {
        return new MiniProduct(
                product.getId() + "",
                product.getName(),
                product.getAuction().getId() + "",
                product.getCategory().getId() + "",
                product.getMediaId() + "",
                product.getAverageScore() + "",
                product.getSellersOfProduct()
        );
    }

    @NotNull
    private static List<MiniAccount> createMiniAccounts(@NotNull List<Account> accountList) {
        return accountList.stream().map(account -> new MiniAccount(
                account.getMediaId() + "",
                account.getUserName() + "",
                account.getPassword() + "",
                account.getId() + "",
                account.getClass().getSimpleName(),
                account.getPersonalInfo().getList(),
                account instanceof Seller ? ((Seller) account).getCompanyInfo().getList() : null,
                account.getWallet()
        )).collect(Collectors.toList());
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniAccount getMiniAccount(@NotNull Account account) {
        return new MiniAccount(
                account.getMediaId() + "",
                account.getUserName() + "",
                account.getPassword() + "",
                account.getPassword() + "",
                account.getClass().getSimpleName(),
                account.getPersonalInfo().getList(),
                account instanceof Seller ? ((Seller) account).getCompanyInfo().getList() : null,
                account.getWallet()
        );
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniRequest getMiniRequest(@NotNull Request reqPrime) {
        return new MiniRequest(
                reqPrime.getId() + "",
                reqPrime.getTypeOfRequest() + "",
                reqPrime.getForPend().getClass().getSimpleName() + "",
                reqPrime.getForPend() instanceof Product ? ((Product) reqPrime.getForPend()).getName() : ((Auction) reqPrime.getForPend()).getName(),
                reqPrime.getForPend().toString());
    }

    private static void EditCate(@NotNull List<String> inputs, @NotNull RequestHandler requestHandler) {
        String categoryName = inputs.get(0);
        List<String> ids = yaGson.fromJson(inputs.get(1), List.class);
        Category category = null;
        ManagerController managerController = ManagerController.getInstance();
        try {
            managerController.editCategory(category.getId() + "", "name", categoryName);
            category.setSubCategories(ids.stream().map(Long::parseLong).collect(Collectors.toList()));
            DataBase.save(category);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (FieldDoesNotExistException | CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL + "::" + e.getMessage()));
        }
    }

    private static void DeleteProductById(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        Account account = ControllerUnit.getInstance().getAccount();
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            new Request(account.getId(), "remove product", "remove", product);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void GetAllCommentOfProduct(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            List<Long> commetsIds = product.getCommentList();
            List<Comment> comments = commetsIds.stream().map(commentId ->
                    {
                        try {
                            return Comment.getCommentById(commentId);
                        } catch (CommentDoesNotExistException e) {
                            e.printStackTrace();
                            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
                            return null;

                        }
                    }
            ).filter(Objects::nonNull).collect(Collectors.toList());
            requestHandler.sendMessage(yaGson.toJson(comments));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void GetAllProductOfAuction(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        Auction auction = null;
        try {
            auction = Auction.getAuctionById(Long.parseLong(id));
            List<Long> productsIds = auction.getProductList();
            List<Product> products = productsIds.stream().map(productsId ->
            {
                try {
                    return Product.getProductById(productsId);
                } catch (ProductDoesNotExistException e) {
                    e.printStackTrace();
                    requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            List<MiniProduct> miniProducts = createMiniProducts(products);
            requestHandler.sendMessage(yaGson.toJson(miniProducts));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void GetCodeById(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            DiscountCode discountCode = DiscountCode.getDiscountCodeById(Long.parseLong(id));
            requestHandler.sendMessage(yaGson.toJson(discountCode));
        } catch (DiscountCodeExpiredException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void rate(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String accountId = inputs.get(1);
        String rate = inputs.get(2);
        BuyerController buyerController = BuyerController.getInstance();
        try {
            buyerController.rate(productId, rate);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (CannotRateException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addCommentToProduct(List<String> inputs, RequestHandler requestHandler) {
        String accountId = inputs.get(0);
        String productId = inputs.get(1);
        String title = inputs.get(2);
        String content = inputs.get(3);
        Customer customer = null;
        try {
            customer = (Customer) Account.getAccountById(Long.parseLong(accountId));
            try {
                Product product = Product.getProductById(Long.parseLong(productId));
                List<Field> fields = Arrays.asList(new Field("Title", title), new Field("Content", content));
                FieldList fieldList = new FieldList(fields);
                Comment comment = new Comment("تایید شده", customer.getId(), Long.parseLong(productId), fieldList);
                product.addComment(comment.getId());
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
            } catch (ProductDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
            }
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        }
    }

    private static void getProductInfoById(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            List<Field> product_info = (List<Field>) product.getProduct_Info();
            requestHandler.sendMessage(yaGson.toJson(product_info));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addProductToCart(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        String accountId = inputs.get(2);
        try {
            Customer account = (Customer) Account.getAccountById(Long.parseLong(accountId));
            Cart cart = account.getCart();
            cart.addProductToCart(Long.parseLong(sellerId), Long.parseLong(productId));
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void sendPaymanetInfo(List<String> inputs, RequestHandler requestHandler) {
        String address = inputs.get(0);
        String postCode = inputs.get(1);
        String discountCode = inputs.get(2);
        BuyerController instance = BuyerController.getInstance();
        try {
            instance.receiveInformation(postCode, address);
            if (!discountCode.isEmpty()) {
                try {
                    instance.discountCodeUse(discountCode);
                    requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
                } catch (InvalidDiscountCodeException e) {
                    e.printStackTrace();
                    requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
                } catch (DiscountCodeExpiredException e) {
                    e.printStackTrace();
                    requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
                } catch (AccountDoesNotExistException e) {
                    e.printStackTrace();
                    requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
                }
            }
        } catch (PostCodeInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (AddresInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void checkAllDiscountCodes() {
        DiscountCode.getList().forEach(discountCode -> {
            try {
                discountCode.checkExpiredDiscountCode(false);
            } catch (DiscountCodeExpiredException | AccountDoesNotExistException ignored) {
            }
        });
    }

    private static void decreaseProduct(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        try {
            BuyerController.getInstance().decrease(productId, sellerId);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void increaseProduct(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        try {
            BuyerController.getInstance().increase(productId, sellerId);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (ProductIsOutOfStockException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (SellerDoesNotSellOfThisProduct e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewSellerOfPro(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        String newPrice = inputs.get(2);
        String numberOfProducts = inputs.get(3);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            product.addSeller(Long.parseLong(sellerId), Long.parseLong(newPrice), Long.parseLong(numberOfProducts));
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void sort(List<String> inputs, RequestHandler requestHandler) {
        String sortElement = inputs.get(0);
        try {
            ProductsController.getInstance().sort(sortElement);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (NotAvailableSortException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void declineRequest(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().denyRequest(id);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (RequestDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void acceptRequest(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().acceptRequest(id);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (RequestDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addToCodesList(List<String> inputs, RequestHandler requestHandler) {
        String accountId = inputs.get(0);
        String discountId = inputs.get(1);
        Customer account = null;
        try {
            account = (Customer) Account.getAccountById(Long.parseLong(accountId));
            account.addToDiscountCodeList(Long.parseLong(discountId));
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void editAuction(List<String> inputs, RequestHandler requestHandler) {
        Seller seller = null;
        String auctionName = inputs.get(0);
        String startTime = inputs.get(1);
        String endTime = inputs.get(2);
        String auctionLimit = inputs.get(3);
        String auctionPercent = inputs.get(4);
        try {
            sellerController.editAuction(seller.getId() + "", "auctionName", auctionName, "edit Auction");
            sellerController.editAuction(seller.getId() + "", "start", startTime, "edit Auction");
            sellerController.editAuction(seller.getId() + "", "end", endTime, "edit Auction");
            sellerController.editAuction(seller.getId() + "", "discountMaxAmount", auctionLimit, "edit Auction");
            sellerController.editAuction(seller.getId() + "", "discountPercent", auctionPercent, "edit Auction");
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (InvalidInputByUserException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void editAccount(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        String type = inputs.get(1);
        String password = inputs.get(2);
        String balance = inputs.get(3);
        String firstName = inputs.get(4);
        String lastName = inputs.get(5);
        String email = inputs.get(6);
        String phoneNumber = inputs.get(7);
        editAccountManager(requestHandler, type, password, balance, firstName, lastName, email, phoneNumber);
        editAccountSeller(inputs, requestHandler, id, type, password, balance, firstName, lastName, email, phoneNumber);
        editAccountCustomer(requestHandler, type, password, balance, firstName, lastName, email, phoneNumber);
    }

    private static void editAccountSeller(List<String> inputs, RequestHandler requestHandler, String id, String type, String password, String balance, String firstName, String lastName, String email, String phoneNumber) {
        if (type.equals("Seller")) {
            String companyName = inputs.get(8);
            String companyPhoneNumber = inputs.get(9);
            String companyEmail = inputs.get(10);
            Seller seller = null;
            try {
                seller = (Seller) Account.getAccountById(Long.parseLong(id));
                seller.editField("password", password);
                seller.editField("balance", balance);
                seller.editField("FirstName", firstName);
                seller.editField("LastName", lastName);
                seller.editField("Email", email);
                seller.editField("PhoneNumber", phoneNumber);
                seller.editField("CompanyName", companyName);
                seller.editField("CompanyPhoneNumber", companyPhoneNumber);
                seller.editField("CompanyEmail", companyEmail);
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
            }


        }
    }

    private static void editAccountCustomer(RequestHandler requestHandler, String type, String password, String balance, String firstName, String lastName, String email, String phoneNumber) {
        if (type.equals("Customer")) {
            Customer customer = null;
            try {
                customer.editField("password", password);
                customer.editField("balance", balance);
                customer.editField("FirstName", firstName);
                customer.editField("LastName", lastName);
                customer.editField("Email", email);
                customer.editField("PhoneNumber", phoneNumber);
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
            }
        }
    }

    private static void editAccountManager(RequestHandler requestHandler, String type, String password, String balance, String firstName, String lastName, String email, String phoneNumber) {
        if (type.equals("Manager")) {

            Manager manager = null;
            try {
                manager.editField("password", password);
                manager.editField("Balance", balance);
                manager.editField("FirstName", firstName);
                manager.editField("LastName", lastName);
                manager.editField("Email", email);
                manager.editField("PhoneNumber", phoneNumber);
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
            }


        }
    }

    private static void deleteAccountById(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().deleteAccount(id);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
        return;
    }

    private static void editDiscountCode(List<String> inputs, RequestHandler requestHandler) {
        DiscountCode discountCode = null;
        String start = inputs.get(0);
        String end = inputs.get(1);
        String percent = inputs.get(2);
        String limit = inputs.get(3);
        String num = inputs.get(4);
        ManagerController managerController = ManagerController.getInstance();
        try {
            managerController.editDiscountCode(discountCode.getId() + "", "start", start);
            managerController.editDiscountCode(discountCode.getId() + "", "end", end);
            managerController.editDiscountCode(discountCode.getId() + "", "frequentUse", num);
            managerController.editDiscountCode(discountCode.getId() + "", "maxDiscountAmount", limit);
            managerController.editDiscountCode(discountCode.getId() + "", "discountPercent", percent);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (DiscountCodeExpiredException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        }
    }

    private static void editProduct(List<String> inputs, RequestHandler requestHandler) {
        String productName = inputs.get(0);
        String productAction = inputs.get(1);
        String productCategory = inputs.get(2);
        Product product = null;
        try {
            sellerController.editProduct(product.getId() + "", "productName", productName, "edit product name");
            if (!productCategory.equals("0"))
                sellerController.editProduct(product.getId() + "", "category", productCategory, "edit product categpry");
            if (!productAction.equals("0"))
                sellerController.editProduct(product.getId() + "", "Auction", productAction, "edit product auction");
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewFilter(List<String> inputs, RequestHandler requestHandler) {
        String filterName = inputs.get(0);
        String filterValue = inputs.get(1);
        try {
            FilterController.getInstance().filter(filterName, filterValue);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (InvalidFilterException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewProduct(List<String> inputs, RequestHandler requestHandler) {
        String productName = inputs.get(0);
        String categoryId = inputs.get(1);
        String auctionId = inputs.get(2);
        String numberOfThis = inputs.get(3);
        String price = inputs.get(4);
        List<String> fieldNames = yaGson.fromJson(inputs.get(5), List.class);
        List<String> values = yaGson.fromJson(inputs.get(6), List.class);
        try {
            Product product = SellerController.getInstance().createTheBaseOfProduct(productName, categoryId, auctionId, numberOfThis, price);
            SellerController.getInstance().saveProductInfo(product, fieldNames, values);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewDiscountCode(List<String> inputs, RequestHandler requestHandler) {
        String start = inputs.get(0);
        String end = inputs.get(1);
        String percentage = inputs.get(2);
        String max = inputs.get(3);
        String frequentUse = inputs.get(4);
        try {
            ManagerController.getInstance().creatDiscountCode(start, end, percentage, max, frequentUse);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewCategory(List<String> inputs, RequestHandler requestHandler) {
        String categoryName = inputs.get(0);
        List<String> features = yaGson.fromJson(inputs.get(1), List.class);
        List<String> subCategories = yaGson.fromJson(inputs.get(2), List.class);
        try {
            ManagerController.getInstance().createEmptyCategory(categoryName, features, subCategories);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewAuction(List<String> inputs, RequestHandler requestHandler) {
        String auctionName = inputs.get(0);
        String start = inputs.get(1);
        String end = inputs.get(2);
        String percentage = inputs.get(3);
        String maxAmount = inputs.get(4);
        try {
            SellerController.getInstance().addOff(auctionName, start, end, percentage, maxAmount);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));
        } catch (InvalidInputByUserException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));
        }
    }

    private static void addNewCustomerOrManager(List<String> inputs, RequestHandler requestHandler) {
        String type = inputs.get(0);
        String username = inputs.get(1);
        String password = inputs.get(2);
        String firstName = inputs.get(3);
        String lastName = inputs.get(4);
        String phoneNumber = inputs.get(5);
        String email = inputs.get(6);
        String accountType = null;

        if (type.equals("Manager")) {
            accountType = "Manager";
        }
        if (type.equals("Customer")) {
            accountType = "Customer";
        }
        SignUpController signUpController = SignUpController.getInstance();
        Account account = null;
        try {
            account = signUpController.creatTheBaseOfAccount(accountType, username);
            signUpController.creatPasswordForAccount(account, password);
            signUpController.savePersonalInfo(account, firstName, lastName, phoneNumber, email);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));


        } catch (UserNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (UserNameTooShortException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (TypeInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (CanNotCreatMoreThanOneMangerBySignUp canNotCreatMoreThanOneMangerBySignUp) {
            canNotCreatMoreThanOneMangerBySignUp.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (ThisUserNameAlreadyExistsException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (PasswordInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (FirstNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (LastNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (EmailInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (PhoneNumberInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        }
    }

    private static void addNewSeller(List<String> inputs, RequestHandler requestHandler) {
        String type = inputs.get(0);
        String username = inputs.get(1);
        String password = inputs.get(2);
        String firstName = inputs.get(3);
        String lastName = inputs.get(4);
        String phoneNumber = inputs.get(5);
        String email = inputs.get(6);
        String brand = inputs.get(7);
        String companyPhoneNumber = inputs.get(8);
        String companyEmail = inputs.get(9);
        SignUpController signUpController = SignUpController.getInstance();
        Account account = null;
        try {
            account = signUpController.creatTheBaseOfAccount("Seller", username);
            signUpController.creatPasswordForAccount(account, password);
            signUpController.savePersonalInfo(account, firstName, lastName, phoneNumber, email);
            signUpController.saveCompanyInfo(account, brand, companyPhoneNumber, companyEmail);
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.SUCCESS));


        } catch (UserNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (UserNameTooShortException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (TypeInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (CanNotCreatMoreThanOneMangerBySignUp canNotCreatMoreThanOneMangerBySignUp) {
            canNotCreatMoreThanOneMangerBySignUp.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (ThisUserNameAlreadyExistsException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (PasswordInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (FirstNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (LastNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (EmailInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (PhoneNumberInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        } catch (CompanyNameInvalidException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(SuccessOrFail.FAIL));

        }
    }

    @NotNull
    private static MiniDiscountCode getMiniDiscountCode(DiscountCode discountCode) {
        return new MiniDiscountCode(
                discountCode.getId() + "",
                Integer.parseInt(String.valueOf(discountCode.getFrequentUse())),
                discountCode.getDiscountCode(),
                discountCode.getDiscount().getPercent(),
                discountCode.getDiscount().getAmount(),
                discountCode.getStart(),
                discountCode.getEnd());
    }

    @NotNull
    private static List<MiniProduct> createMiniProducts(List<Product> products) {
        return products.stream().map(product ->
                getMiniProduct(product)
        ).collect(Collectors.toList());
    }

    enum SuccessOrFail {
        SUCCESS,
        FAIL
    }
}
