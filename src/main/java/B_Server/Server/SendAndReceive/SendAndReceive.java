package B_Server.Server.SendAndReceive;

import B_Server.Bank.BankAPI;
import B_Server.Controller.Controllers.AccountControllers.BuyerController;
import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import B_Server.Controller.Controllers.AccountControllers.SellerController;
import B_Server.Controller.Controllers.FilterController;
import B_Server.Controller.Controllers.LoginaAndRegister.LoginController;
import B_Server.Controller.Controllers.LoginaAndRegister.SignUpController;
import B_Server.Controller.Controllers.ProductController;
import B_Server.Controller.Controllers.ProductsController;
import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SendAndReceive {

    private final static BuyerController buyerController = BuyerController.getInstance();
    private final static SellerController sellerController = SellerController.getInstance();
    private final static ManagerController managerController = ManagerController.getInstance();
    private final static YaGson yaGson = new YaGson();
    private final static Object lockInDecreeProductNums = new Object();
    private final static Object lockAcceptDeclineReq = new Object();
    private final static Object lockAddNewAccount = new Object();
    private final static Object loginUserLock = new Object();
    private final static Object lockEditCategory = new Object();
    private final static Object lockAuctionCreating = new Object();
    private final static Object lockProductCreating = new Object();
    private final static Object lockAddDiscountCode = new Object();
    private final static Object lockAddCommentToPro = new Object();

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
                logout(requestHandler, newToken);
                break;
            case "GetCodesOfUserById":
                getCodesOfUsersById(newToken, inputs, requestHandler);
                break;
            case "GetLogsOfUserById":
                getLogsOfUserById(newToken, inputs, requestHandler);
                break;
            case "addNewCustomerOrManager":
                addNewCustomerOrManager(newToken, inputs, requestHandler);
                break;
            case "addNewSeller":
                addNewSeller(newToken, inputs, requestHandler);
                break;
            case "addNewAuction":
                addNewAuction(newToken, inputs, requestHandler);
                break;
            case "addNewCate":
                addNewCategory(newToken, inputs, requestHandler);
                break;
            case "EditCate":
                EditCate(newToken, inputs, requestHandler);
                break;
            case "addNewDiscountCode":
                addNewDiscountCode(newToken, inputs, requestHandler);
                break;
            case "addNewProduct":
                addNewProduct(newToken, inputs, requestHandler);
                break;
            case "EditAccount":
                editAccount(newToken, inputs, requestHandler);
                break;
            case "EditAuction":
                editAuction(newToken, inputs, requestHandler);
                break;
            case "EditProduct":
                editProduct(newToken, inputs, requestHandler);
                break;
            case "EditDiscountCode":
                editDiscountCode(newToken, inputs, requestHandler);
                break;
            case "DeleteAccountById":
                deleteAccountById(newToken, inputs, requestHandler);
                break;
            case "addNewFilter":
                addNewFilter(newToken, inputs, requestHandler);
                break;
            case "CheckDiscountCodes":
                checkAllDiscountCodes(newToken, requestHandler);
                break;
            case "addNewSellerOfPro":
                addNewSellerOfPro(newToken, inputs, requestHandler);
                break;
            case "saveInfoOfProduct":
                saveInfoOfProduct(newToken, inputs, requestHandler);
                break;
            case "addToCodesList":
                addToCodesList(newToken, inputs, requestHandler);
                break;
            case "acceptRequest":
                acceptRequest(newToken, inputs, requestHandler);
                break;
            case "declineRequest":
                declineRequest(newToken, inputs, requestHandler);
                break;
            case "SetCurrentCate":
                setCurrentCate(newToken, inputs, requestHandler);
                break;
            case "SetCurrentCode":
                setCurrentCode(newToken, inputs, requestHandler);
                break;
            case "SetCurrentProduct":
                setCurrentProduct(newToken, inputs, requestHandler);
                break;
            case "Sort":
                sort(newToken, inputs, requestHandler);
                break;
            case "increaseProduct":
                increaseProduct(newToken, inputs, requestHandler);
                break;
            case "decreaseProduct":
                decreaseProduct(newToken, inputs, requestHandler);
                break;
            case "sendPaymentInfo":
                sendPaymentInfo(newToken, inputs, requestHandler);
                break;
            case "Purchase":
                purchase(newToken, requestHandler);
                break;
            case "addProductToCart":
                addProductToCart(newToken, inputs, requestHandler);
                break;
            case "getCateInfoOfProduct":
                getCateInfoById(newToken, inputs, requestHandler);
                break;
            case "getProductInfoById":
                getProductInfoById(newToken, inputs, requestHandler);
                break;
            case "addCommentToProduct":
                addCommentToProduct(newToken, inputs, requestHandler);
                break;
            case "rate":
                rate(newToken, inputs, requestHandler);
                break;
            case "GetCodeById":
                getCodeById(newToken, inputs, requestHandler);
                break;
            case "SetMediasOfProduct":
                setMediasOfProduct(newToken, inputs, requestHandler);
                break;
            case "GetAllProductsPrime":
                getAllProducts(newToken, request, requestHandler,
                        ProductsController.getInstance().showProducts());
                break;
            case "GetAllRequest":
                GetAllRequest(newToken, requestHandler);
                break;
            case "GetAllProductOfAuction":
                getAllProductOfAuction(newToken, inputs, requestHandler);
                break;
            case "GetAllCommentOfProduct":
                getAllCommentOfProduct(newToken, inputs, requestHandler);
                break;
            case "DeleteProductById":
                deleteProductById(newToken, inputs, requestHandler);
                break;
            case "Kill":
                Offline(requestHandler, info, newToken);
        }
    }

    private static void Offline(RequestHandler requestHandler, InstantInfo info, String newToken) {
        logout(requestHandler, newToken);
        Server.removeClient(info);
        sender(newToken, MessageSupplier.RequestType.Kill, SuccessOrFail.SUCCESS.toString(), requestHandler);
    }

    private static void purchase(String token, RequestHandler requestHandler) {
        try {
            LogHistory logHistory = buyerController.buyProductsOfCart();
            MiniLogHistory miniLogHistory = getMiniLogHistory(logHistory);
            sender(token, MessageSupplier.RequestType.Purchase, yaGson.toJson(miniLogHistory), requestHandler);
        } catch (NotEnoughCreditException | AccountDoesNotExistException | ProductDoesNotExistException | SellerDoesNotSellOfThisProduct e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.Purchase, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void OnlineNewClient(RequestHandler requestHandler) {
        String newToken = Server.createToken();
        Server.addClient(new InstantInfo(newToken));
        sendToken(newToken, requestHandler);
    }

    private static void logout(RequestHandler requestHandler, String newToken) {
        managerController.getClientInfo().get().setAccount(null);
        sender(newToken, MessageSupplier.RequestType.Logout, SuccessOrFail.SUCCESS.toString(), requestHandler);
    }

    private static void setCurrentProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Product product = Product.getProductById(Long.parseLong(inputs.get(0)));
            managerController.getClientInfo().get().setProduct(product);
            sender(token, MessageSupplier.RequestType.SetCurrentProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.SetCurrentProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void setCurrentCode(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            DiscountCode discountCode = DiscountCode.getDiscountCodeById(Long.parseLong(inputs.get(0)));
            managerController.getClientInfo().get().setCode(discountCode);
            sender(token, MessageSupplier.RequestType.SetCurrentCode, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (DiscountCodeExpiredException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.SetCurrentCode, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void setCurrentCate(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Category category = Category.getCategoryById(Long.parseLong(inputs.get(0)));
            managerController.getClientInfo().get().setCategory(category);
            sender(token, MessageSupplier.RequestType.SetCurrentCate, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.SetCurrentCate, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void setMediasOfProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String Str_Image = inputs.get(0);
        String Str_Movie = inputs.get(1);

        Medias medias = new Medias();
        Medias.addMedia(medias);

        JsonHandler<byte[]> jsonHandler = new JsonHandler<>();

        try {
            image_write(Str_Image, medias, jsonHandler, "src/main/resources/DataBase/MediasContent-src/Images/", ".jpg");
            image_write(Str_Movie, medias, jsonHandler, "src/main/resources/DataBase/MediasContent-src/Movies/", ".mp4");
            sellerController.saveProductMedias(medias);
            sender(token, MessageSupplier.RequestType.SetMediasOfProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);

        } catch (IOException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.SetMediasOfProduct, SuccessOrFail.FAIL.toString(), requestHandler);
        }
    }

    private static void image_write(String str_Image, @NotNull Medias medias, @NotNull JsonHandler<byte[]> jsonHandler, String s, String s2) throws IOException {
        byte[] image_bytes = jsonHandler.JsonToObject(str_Image, byte[].class);
        File image = new File(s + medias.getId() + s2);
        OutputStream osi = new FileOutputStream(image);
        osi.write(image_bytes);
        osi.close();
    }

    private static void sender(String token, MessageSupplier.RequestType requestType, String message, @NotNull RequestHandler requestHandler) {
        requestHandler.sendMessage(requestHandler.generateMessage(requestType, Arrays.asList(token, message)));
    }

    private static void sendToken(String token, @NotNull RequestHandler requestHandler) {
        sender(token, MessageSupplier.RequestType.GetToken, "Slm Slm!", requestHandler);
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
            Account account;
            synchronized (loginUserLock) {
                account = LoginController.getInstance().login(username, password);
            }
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

    private static void addNewCustomerOrManager(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String accountType = inputs.get(0);
        String username = inputs.get(1);
        String password = inputs.get(2);
        String firstName = inputs.get(4);
        String lastName = inputs.get(5);
        String email = inputs.get(6);
        String phoneNumber = inputs.get(7);

        SignUpController signUpController = SignUpController.getInstance();
        try {
            synchronized (lockAddNewAccount) {
                Account account = signUpController.creatTheBaseOfAccount(accountType, username);
                signUpController.creatPasswordForAccount(account, password);
                signUpController.savePersonalInfo(account, firstName, lastName, phoneNumber, email);
                List<String> list = Arrays.asList(firstName,lastName,username,password,password);
                BankAPI.sendAndReceive("create_account",list);
            }
            sender(token, MessageSupplier.RequestType.addNewCustomerOrManager, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (UserNameInvalidException | UserNameTooShortException | TypeInvalidException | CanNotCreatMoreThanOneMangerBySignUp | ThisUserNameAlreadyExistsException | PasswordInvalidException | FirstNameInvalidException | LastNameInvalidException | EmailInvalidException | PhoneNumberInvalidException | IOException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewCustomerOrManager,
                    SuccessOrFail.FAIL + "/" + e.getClass().getSimpleName() + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewSeller(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String username = inputs.get(1);
        String password = inputs.get(2);
        String com_name = inputs.get(3);
        String companyEmail = inputs.get(4);
        String companyPhoneNumber = inputs.get(5);
        String firstName = inputs.get(6);
        String lastName = inputs.get(7);
        String email = inputs.get(8);
        String phoneNumber = inputs.get(9);

        SignUpController signUpController = SignUpController.getInstance();
        try {
            synchronized (lockAddNewAccount) {
                Account account = signUpController.creatTheBaseOfAccount("Seller", username);
                signUpController.creatPasswordForAccount(account, password);
                signUpController.savePersonalInfo(account, firstName, lastName, phoneNumber, email);
                signUpController.saveCompanyInfo(account, com_name, companyPhoneNumber, companyEmail);
                List<String> list = Arrays.asList(firstName,lastName,username,password,password);
                try {
                    BankAPI.sendAndReceive("create_account",list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sender(token, MessageSupplier.RequestType.addNewSeller, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (UserNameInvalidException | UserNameTooShortException | TypeInvalidException | CanNotCreatMoreThanOneMangerBySignUp | ThisUserNameAlreadyExistsException | PasswordInvalidException | FirstNameInvalidException | LastNameInvalidException | EmailInvalidException | PhoneNumberInvalidException | CompanyNameInvalidException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewSeller,
                    SuccessOrFail.FAIL + "/" + e.getClass().getSimpleName() + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewAuction(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String auctionName = inputs.get(0);
        String start = inputs.get(1);
        String end = inputs.get(2);
        String percentage = inputs.get(3);
        String maxAmount = inputs.get(4);
        List<String> ids = new JsonHandler<List>().JsonToObject(inputs.get(5), List.class);
        try {
            synchronized (lockAuctionCreating) {
                Auction auction = SellerController.getInstance().addOff(auctionName, start, end, percentage, maxAmount);
                sellerController.addProductsToAuction(auction, ids);
                sellerController.sendRequest(auction, "new Auction", "new");
            }
            sender(token, MessageSupplier.RequestType.addNewAuction, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (InvalidInputByUserException | ProductCantBeInMoreThanOneAuction | ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewAuction, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productName = inputs.get(0);
        String categoryId = inputs.get(1);
        String auctionId = inputs.get(2);
        String numberOfThis = inputs.get(3);
        String priceOfThis = inputs.get(4);
        try {
            synchronized (lockProductCreating) {
                SellerController.getInstance().createTheBaseOfProduct(productName, categoryId, auctionId, numberOfThis, priceOfThis);
            }
            sender(token, MessageSupplier.RequestType.addNewProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (AuctionDoesNotExistException | CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void saveInfoOfProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        List<String> fp = yaGson.fromJson(inputs.get(0), List.class);
        List<String> vp = yaGson.fromJson(inputs.get(1), List.class);
        List<String> fc = yaGson.fromJson(inputs.get(2), List.class);
        List<String> vc = yaGson.fromJson(inputs.get(3), List.class);
        try {
            sellerController.saveProductInfo(fp, vp);
            sellerController.saveCategoryInfo(fc, vc);
            sellerController.sendRequest(sellerController.getInRegisteringProduct(), "new Product", "new");
            sender(token, MessageSupplier.RequestType.saveInfoOfProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (NullPointerException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.saveInfoOfProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewDiscountCode(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String start = inputs.get(0);
        String end = inputs.get(1);
        String percentage = inputs.get(2);
        String max = inputs.get(3);
        String frequentUse = inputs.get(4);
        try {
            ManagerController.getInstance().creatDiscountCode(start, end, percentage, max, frequentUse);
            sender(token, MessageSupplier.RequestType.addNewDiscountCode, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewDiscountCode, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewCategory(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String categoryName = inputs.get(0);
        List<String> features = yaGson.fromJson(inputs.get(1), List.class);
        List<String> subCategories = yaGson.fromJson(inputs.get(2), List.class);
        try {
            ManagerController.getInstance().createEmptyCategory(categoryName, features, subCategories);
            sender(token, MessageSupplier.RequestType.addNewCate, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewCate, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void EditCate(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String categoryName = inputs.get(0);
        List<String> ids = yaGson.fromJson(inputs.get(1), List.class);
        Category category = managerController.getClientInfo().get().getCategory();
        try {
            synchronized (lockEditCategory) {
                managerController.editCategory(category.getId() + "", "name", categoryName);
                category.setSubCategories(ids.stream().map(Long::parseLong).collect(Collectors.toList()));
                DataBase.save(category);
            }
            sender(token, MessageSupplier.RequestType.EditCate, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (FieldDoesNotExistException | CategoryDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.EditCate, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void editAccount(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String fieldName = inputs.get(0);
        String fieldValue = inputs.get(1);
        try {
            Account account = ManagerController.getInstance().getClientInfo().get().getAccount();
            account.editField(fieldName, fieldValue);
            sender(token, MessageSupplier.RequestType.EditAccount, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.EditAccount, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void editAuction(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String sellerId = inputs.get(0);
        String fieldName = inputs.get(1);
        String fieldValue = inputs.get(2);
        String modeOfRequest = inputs.get(3);
        try {
            sellerController.editAuction(sellerId, fieldName, fieldValue, modeOfRequest);
            sender(token, MessageSupplier.RequestType.EditAuction, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (AuctionDoesNotExistException | FieldDoesNotExistException | InvalidInputByUserException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.EditAuction, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void editProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String fieldName = inputs.get(1);
        String fieldValue = inputs.get(2);
        String information = inputs.get(3);
        try {
            if (!((fieldName.equals("category") || fieldName.equals("Auction")) && fieldValue.equals("0"))) {
                sellerController.editProduct(productId, fieldName, fieldValue, information);
                sender(token, MessageSupplier.RequestType.EditProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
            } else
                sender(token, MessageSupplier.RequestType.EditProduct, SuccessOrFail.FAIL + "/Invalid...", requestHandler);
        } catch (AuctionDoesNotExistException | FieldDoesNotExistException | CategoryDoesNotExistException | ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.EditProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void editDiscountCode(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String discountCodeId = inputs.get(0);
        String fieldName = inputs.get(1);
        String fieldValue = inputs.get(2);
        try {
            managerController.editDiscountCode(discountCodeId, fieldName, fieldValue);
            sender(token, MessageSupplier.RequestType.EditDiscountCode, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (DiscountCodeExpiredException | FieldDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.EditDiscountCode, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void deleteAccountById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            synchronized (lockAddNewAccount) {
                managerController.deleteAccount(inputs.get(0));
            }
            sender(token, MessageSupplier.RequestType.DeleteAccountById, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.DeleteAccountById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addNewFilter(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String filterName = inputs.get(0);
        String filterValue = inputs.get(1);
        try {
            FilterController.getInstance().filter(filterName, filterValue);
            sender(token, MessageSupplier.RequestType.addNewFilter, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (InvalidFilterException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewFilter, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void checkAllDiscountCodes(String token, RequestHandler requestHandler) {
        DiscountCode.getList().forEach(discountCode -> {
            try {
                discountCode.checkExpiredDiscountCode(false);
            } catch (DiscountCodeExpiredException | AccountDoesNotExistException ignored) {
            }
        });
        sender(token, MessageSupplier.RequestType.CheckDiscountCodes, SuccessOrFail.SUCCESS.toString(), requestHandler);
    }

    private static void addNewSellerOfPro(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        String newPrice = inputs.get(2);
        String numberOfProducts = inputs.get(3);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            product.addSeller(Long.parseLong(sellerId), Long.parseLong(newPrice), Long.parseLong(numberOfProducts));
            sender(token, MessageSupplier.RequestType.addNewSellerOfPro, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addNewSellerOfPro, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addToCodesList(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String accountId = inputs.get(0);
        String discountId = inputs.get(1);
        try {
            synchronized (lockAddDiscountCode) {
                Customer account = (Customer) Account.getAccountById(Long.parseLong(accountId));
                account.addToDiscountCodeList(Long.parseLong(discountId));
                DiscountCode discountCode = DiscountCode.getDiscountCodeById(Long.parseLong(discountId));
                discountCode.addAccount(Long.parseLong(accountId));
            }
            sender(token, MessageSupplier.RequestType.addToCodesList, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (AccountDoesNotExistException | DiscountCodeExpiredException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addToCodesList, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void acceptRequest(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            synchronized (lockAcceptDeclineReq) {
                managerController.acceptRequest(inputs.get(0));
            }
            sender(token, MessageSupplier.RequestType.acceptRequest, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (RequestDoesNotExistException | AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.acceptRequest, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void declineRequest(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            synchronized (lockAcceptDeclineReq) {
                managerController.denyRequest(inputs.get(0));
            }
            sender(token, MessageSupplier.RequestType.declineRequest, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (RequestDoesNotExistException | AccountDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.declineRequest, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void sort(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            ProductsController.getInstance().sort(inputs.get(0));
            sender(token, MessageSupplier.RequestType.Sort, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (NotAvailableSortException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.Sort, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void decreaseProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        synchronized (lockInDecreeProductNums) {
            try {
                buyerController.decrease(productId, sellerId);
                sender(token, MessageSupplier.RequestType.decreaseProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
            } catch (ProductDoesNotExistException e) {
                e.printStackTrace();
                sender(token, MessageSupplier.RequestType.decreaseProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
            }
        }
    }

    private static void increaseProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        synchronized (lockInDecreeProductNums) {
            try {
                buyerController.increase(productId, sellerId);
                sender(token, MessageSupplier.RequestType.increaseProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
            } catch (ProductDoesNotExistException | ProductIsOutOfStockException | SellerDoesNotSellOfThisProduct e) {
                e.printStackTrace();
                sender(token, MessageSupplier.RequestType.increaseProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
            }
        }
    }

    private static void sendPaymentInfo(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String postCode = inputs.get(0);
        String address = inputs.get(1);
        String discountCode = inputs.get(2);
        try {
            buyerController.receiveInformation(postCode, address);
            if (!discountCode.isEmpty()) buyerController.discountCodeUse(discountCode);
            sender(token, MessageSupplier.RequestType.sendPaymentInfo, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (PostCodeInvalidException | AddresInvalidException | FieldDoesNotExistException | AccountDoesNotExistException | InvalidDiscountCodeException | DiscountCodeExpiredException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.sendPaymentInfo, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addProductToCart(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            ProductController.getInstance().addToCart(inputs.get(1));
            sender(token, MessageSupplier.RequestType.addProductToCart, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (AccountHasNotLogin | ProductIsOutOfStockException | ProductDoesNotExistException | SellerDoesNotSellOfThisProduct e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addProductToCart, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getProductInfoById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Product product = Product.getProductById(Long.parseLong(inputs.get(0)));
            List<Field> product_info = product.getProduct_Info().getList().getFieldList();
            sender(token, MessageSupplier.RequestType.getProductInfoById, yaGson.toJson(product_info), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.getProductInfoById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getCateInfoById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Product product = Product.getProductById(Long.parseLong(inputs.get(0)));
            List<Field> cate_info = product.getCategoryInfo().getList().getFieldList();
            sender(token, MessageSupplier.RequestType.getCateInfoOfProduct, yaGson.toJson(cate_info), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.getCateInfoOfProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void addCommentToProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String accountId = inputs.get(0);
        String productId = inputs.get(1);
        String title = inputs.get(2);
        String content = inputs.get(3);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            FieldList fieldList = new FieldList(Arrays.asList(new Field("Title", title), new Field("Content", content)));
            Comment comment = new Comment("تایید شده", Long.parseLong(accountId), Long.parseLong(productId), fieldList);
            synchronized (lockAddCommentToPro) {
                Comment.addComment(comment);
                product.addComment(comment.getId());
            }
            sender(token, MessageSupplier.RequestType.addCommentToProduct, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.addCommentToProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void rate(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String rate = inputs.get(2);
        try {
            buyerController.rate(productId, rate);
            sender(token, MessageSupplier.RequestType.rate, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (ProductDoesNotExistException | CannotRateException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.rate,
                    SuccessOrFail.FAIL + "/" + e.getClass().getSimpleName() + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getCodeById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            DiscountCode discountCode = DiscountCode.getDiscountCodeById(Long.parseLong(inputs.get(0)));
            sender(token, MessageSupplier.RequestType.GetCodeById, yaGson.toJson(discountCode), requestHandler);
        } catch (DiscountCodeExpiredException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetCodeById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getAllProductOfAuction(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Auction auction = Auction.getAuctionById(Long.parseLong(inputs.get(0)));
            List<Long> productsIds = auction.getProductList();
            List<Product> products = productsIds.stream().map(productsId -> {
                try {
                    return Product.getProductById(productsId);
                } catch (ProductDoesNotExistException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            List<MiniProduct> miniProducts = createMiniProducts(products);
            sender(token, MessageSupplier.RequestType.GetAllProductOfAuction, yaGson.toJson(miniProducts), requestHandler);
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAllProductOfAuction, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void getAllCommentOfProduct(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        try {
            Product product = Product.getProductById(Long.parseLong(inputs.get(0)));
            List<Comment> comments = product.getCommentList().stream().map(commentId -> {
                        try {
                            return Comment.getCommentById(commentId);
                        } catch (CommentDoesNotExistException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
            ).filter(Objects::nonNull).collect(Collectors.toList());
            sender(token, MessageSupplier.RequestType.GetAllCommentOfProduct, yaGson.toJson(comments), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.GetAllCommentOfProduct, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    private static void deleteProductById(String token, @NotNull List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            sellerController.sendRequest(product, "remove product", "remove");
            sender(token, MessageSupplier.RequestType.DeleteProductById, SuccessOrFail.SUCCESS.toString(), requestHandler);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            sender(token, MessageSupplier.RequestType.DeleteProductById, SuccessOrFail.FAIL + "/" + e.getMessage(), requestHandler);
        }
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniDiscountCode getMiniDiscountCode(@NotNull DiscountCode discountCode) {
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
    private static List<MiniProduct> createMiniProducts(@NotNull List<Product> products) {
        return products.stream().map(SendAndReceive::getMiniProduct).collect(Collectors.toList());
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
                product.getOffer() == null ? "0" : product.getOffer().getId() + "",
                product.getCategory().getId() + "",
                product.getMediaId() + "",
                product.getAverageScore() + "",
                product.getSellersOfProduct()
        );
    }

    @NotNull
    private static List<MiniAccount> createMiniAccounts(@NotNull List<Account> accountList) {
        return accountList.stream().map(SendAndReceive::getMiniAccount).collect(Collectors.toList());
    }

    @Contract("_ -> new")
    @NotNull
    private static MiniAccount getMiniAccount(@NotNull Account account) {
        return new MiniAccount(
                account.getId() + "",
                account.getMediaId() + "",
                account.getUserName() + "",
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

    enum SuccessOrFail {
        SUCCESS,
        FAIL
    }
}
