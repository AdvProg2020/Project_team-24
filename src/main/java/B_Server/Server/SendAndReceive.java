package B_Server.Server;

import B_Server.Controller.Controllers.*;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Field.Field;
import B_Server.Model.Models.Structs.Medias;
import B_Server.Server.RequestHandler.RequestHandler;
import Exceptions.*;
import Structs.*;
import com.gilecode.yagson.YaGson;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SendAndReceive {

    private final static SellerController sellerController = SellerController.getInstance();
    private final static YaGson yaGson = new YaGson();

    public static void messageAnalyser(String request, List<String> inputs, RequestHandler requestHandler) {

        switch (request) {
            case "GetToken":
                requestHandler.sendMessage(Server.createToken());
                break;
            case "GetAllMyProducts":
                getAllMyProducts(requestHandler);
                break;
            case "GetProductById":
                getProductById(inputs, requestHandler);
                break;
            case "GetAccountById":
                getAccountById(inputs, requestHandler);
                break;
            case "GetCateById":
                getCategoryById(inputs, requestHandler);
                break;
            case "GetAuctionById":
                getAuctionById(inputs, requestHandler);
                break;
            case "GetImageById":
                getImageById(inputs, requestHandler);
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
                getAllProducts(requestHandler, ProductsController.getInstance().showProducts());
                break;
            case "GetAllAccounts":
                getAllAcounts(requestHandler);
                break;
            case "GetAllAuctions":
                getAllAuctions(requestHandler);
                break;
            case "GetAllDiscountCodes":
                getAllDiscountCodes(requestHandler);
                break;
            case "GetAllCate":
                getAllCategories(requestHandler);
                break;
            case "GetAllProductOfCate":
                getAllProductOfCate(inputs, requestHandler);
                break;
            case "GetAllPopularProducts":
                getAllPopularProducts(requestHandler);
                break;
            case "Login":
                login(inputs, requestHandler);
                break;
            case "Logout":
                //...
                break;
            case "GetCodesOfUserById":
                getCodesOfUsersById(inputs, requestHandler);
                break;
            case "GetLogsOfUserById":
                getLogsOfUserById(inputs);
                break;
            case "addNewCustomerOrManager":
                addNewCustomerOrManager(inputs);
                break;
            case "addNewSeller":
                addNewSeller(inputs);
                break;
            case "addNewAuction":
                addNewAuction(inputs, requestHandler);
                break;
            case "addNewCate":
                addNewCategory(inputs, requestHandler);
                break;
            case "EditCate":
                ///????
                String categoryName = inputs.get(0);
                List<String> ids = yaGson.fromJson(inputs.get(1), List.class);
                Category category = null;
                ManagerController managerController = ManagerController.getInstance();
                managerController.editCategory(category.getId() + "", "name", categoryName);
                category.setSubCategories(ids.stream().map(Long::parseLong).collect(Collectors.toList()));
                category.setCategoryFields(new FieldList(str_feature.stream().map(Field::new).collect(Collectors.toList())));
                DataBase.save(category);
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
                DiscountCode.getList().forEach(discountCode -> {
                    try {
                        discountCode.checkExpiredDiscountCode(false);
                    } catch (DiscountCodeExpiredException | AccountDoesNotExistException ignored) {}
                });
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
        }
    }

    private static void addNewSellerOfPro(List<String> inputs, RequestHandler requestHandler) {
        String productId = inputs.get(0);
        String sellerId = inputs.get(1);
        String newPrice = inputs.get(2);
        String numberOfProducts = inputs.get(3);
        try {
            Product product = Product.getProductById(Long.parseLong(productId));
            product.addSeller(Long.parseLong(sellerId),Long.parseLong(newPrice),Long.parseLong(numberOfProducts));
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL);
        }
    }

    private static void sort(List<String> inputs, RequestHandler requestHandler) {
        String sortElement = inputs.get(0);
        try {
            ProductsController.getInstance().sort(sortElement);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (NotAvailableSortException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void declineRequest(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().denyRequest(id);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (RequestDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void acceptRequest(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().acceptRequest(id);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (RequestDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void addToCodesList(List<String> inputs, RequestHandler requestHandler) {
        String accountId = inputs.get(0);
        String discountId = inputs.get(1);
        Customer account = null;
        try {
            account = (Customer) Account.getAccountById(Long.parseLong(accountId));
            account.addToDiscountCodeList(Long.parseLong(discountId));
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (InvalidInputByUserException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
                requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
            }


        }
    }

    private static void deleteAccountById(List<String> inputs, RequestHandler requestHandler) {
        String id = inputs.get(0);
        try {
            ManagerController.getInstance().deleteAccount(id);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (DiscountCodeExpiredException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));

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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void addNewFilter(List<String> inputs, RequestHandler requestHandler) {
        String filterName = inputs.get(0);
        String filterValue = inputs.get(1);
        try {
            FilterController.getInstance().filter(filterName, filterValue);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (InvalidFilterException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void addNewCategory(List<String> inputs, RequestHandler requestHandler) {
        String categoryName = inputs.get(0);
        List<String> features = yaGson.fromJson(inputs.get(1), List.class);
        List<String> subCategories = yaGson.fromJson(inputs.get(2), List.class);
        try {
            Category category = ManagerController.getInstance().createEmptyCategory(categoryName, features, subCategories);
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
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
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.SUCCESS));
        } catch (InvalidInputByUserException e) {
            e.printStackTrace();
            requestHandler.sendMessage(String.valueOf(successOrFailMessage.FAIL));
        }
    }

    private static void addNewCustomerOrManager(List<String> inputs) {
        String type = inputs.get(0);
        String username = inputs.get(1);
        String password = inputs.get(2);
        String firstName = inputs.get(3);
        String lastName = inputs.get(4);
        String phoneNumeber = inputs.get(5);
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
            signUpController.savePersonalInfo(account, firstName, lastName, phoneNumeber, email);

        } catch (UserNameInvalidException e) {
            e.printStackTrace();
        } catch (UserNameTooShortException e) {
            e.printStackTrace();
        } catch (TypeInvalidException e) {
            e.printStackTrace();
        } catch (CanNotCreatMoreThanOneMangerBySignUp canNotCreatMoreThanOneMangerBySignUp) {
            canNotCreatMoreThanOneMangerBySignUp.printStackTrace();
        } catch (ThisUserNameAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PasswordInvalidException e) {
            e.printStackTrace();
        } catch (FirstNameInvalidException e) {
            e.printStackTrace();
        } catch (LastNameInvalidException e) {
            e.printStackTrace();
        } catch (EmailInvalidException e) {
            e.printStackTrace();
        } catch (PhoneNumberInvalidException e) {
            e.printStackTrace();
        }
    }

    private static void addNewSeller(List<String> inputs) {
        String type = inputs.get(0);
        String username = inputs.get(1);
        String password = inputs.get(2);
        String firstName = inputs.get(3);
        String lastName = inputs.get(4);
        String phoneNumeber = inputs.get(5);
        String email = inputs.get(6);
        String brand = inputs.get(7);
        String companyPhoneNumber = inputs.get(8);
        String companyEmail = inputs.get(9);
        SignUpController signUpController = SignUpController.getInstance();
        Account account = null;
        try {
            account = signUpController.creatTheBaseOfAccount("Seller", username);
            signUpController.creatPasswordForAccount(account, password);
            signUpController.savePersonalInfo(account, firstName, lastName, phoneNumeber, email);
            signUpController.saveCompanyInfo(account, brand, companyPhoneNumber, companyEmail);


        } catch (UserNameInvalidException e) {
            e.printStackTrace();
        } catch (UserNameTooShortException e) {
            e.printStackTrace();
        } catch (TypeInvalidException e) {
            e.printStackTrace();
        } catch (CanNotCreatMoreThanOneMangerBySignUp canNotCreatMoreThanOneMangerBySignUp) {
            canNotCreatMoreThanOneMangerBySignUp.printStackTrace();
        } catch (ThisUserNameAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PasswordInvalidException e) {
            e.printStackTrace();
        } catch (FirstNameInvalidException e) {
            e.printStackTrace();
        } catch (LastNameInvalidException e) {
            e.printStackTrace();
        } catch (EmailInvalidException e) {
            e.printStackTrace();
        } catch (PhoneNumberInvalidException e) {
            e.printStackTrace();
        } catch (CompanyNameInvalidException e) {
            e.printStackTrace();
        } catch (PhoneNumberInvalidException e) {
            e.printStackTrace();
        } catch (EmailInvalidException e) {
            e.printStackTrace();
        }
    }

    private static void getLogsOfUserById(List<String> inputs) {
        Account account = null;
        try {
            account = Account.getAccountById(Long.parseLong(inputs.get(0)));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
        List<Long> logHistoriesIds = null;
        if (account instanceof Customer) {
            logHistoriesIds = ((Customer) account).getLogHistoryList();
        }
        if (account instanceof Seller) {
            logHistoriesIds = ((Seller) account).getLogHistoryList();
        }
        List<MiniLogHistory> logHistoryList = logHistoriesIds.stream().map(id -> {

            try {
                LogHistory logHistory = LogHistory.getLogHistoryById(id);
                return new MiniLogHistory(
                        logHistory.getId() + "",
                        logHistory.getFinalAmount() + "",
                        logHistory.getDiscountAmount() + "",
                        logHistory.getAuctionDiscount() + "",
                        logHistory.getFieldList(),
                        logHistory.getProductLogList());
                ////ishala farda

                );
            } catch (LogHistoryDoesNotExistException e) {
                e.printStackTrace();
                return null;
            }

        }).collect(Collectors.toList());
    }

    private static void getCodesOfUsersById(List<String> inputs, RequestHandler requestHandler) {
        Customer account = null;
        try {
            account = (Customer) Account.getAccountById(Long.parseLong(inputs.get(0)));
            List<Long> discountCodeIds = account.getDiscountCodeList();
            List<MiniDiscountCode> miniDiscountCodes = discountCodeIds.stream().map(id -> {

                DiscountCode discountCode = null;
                try {
                    discountCode = DiscountCode.getDiscountCodeById(id);
                    return new MiniDiscountCode(
                            discountCode.getId(),
                            discountCode.getDiscountCode(),
                            discountCode.getDiscount().getPercent(),
                            discountCode.getDiscount().getAmount(),
                            discountCode.getStart(),
                            discountCode.getEnd());
                } catch (DiscountCodeExpiredException e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
            requestHandler.sendMessage(yaGson.toJson(miniDiscountCodes));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private static void login(List<String> inputs, RequestHandler requestHandler) {
        try {
            String username = inputs.get(0);
            String password = inputs.get(1);
            Account account = LoginController.getInstance().login(username, password);
            MiniAccount miniAccount = getMiniAccount(account);
            requestHandler.sendMessage(yaGson.toJson(miniAccount));
        } catch (PassIncorrectException e) {
            e.printStackTrace();
        } catch (UserNameInvalidException e) {
            e.printStackTrace();
        } catch (UserNameTooShortException e) {
            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static MiniAccount getMiniAccount(Account account) {
        return new MiniAccount(
                account.getMediaId() + "",
                account.getUserName() + "",
                account.getPassword() + "",
                account.getPersonalInfo().getList(),
                account instanceof Seller ? ((Seller) account).getCompanyInfo().getList() : null,
                account.getWallet()
        );
    }

    private static void getAllDiscountCodes(RequestHandler requestHandler) {
        List<DiscountCode> discountCodes = DiscountCode.getList();
        List<MiniDiscountCode> miniDiscountCodes = discountCodes.stream().map(discountCode -> new MiniDiscountCode(
                discountCode.getId(),
                discountCode.getDiscountCode(),
                discountCode.getDiscount().getPercent(),
                discountCode.getDiscount().getAmount(),
                discountCode.getStart(),
                discountCode.getEnd())).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniDiscountCodes));
    }

    private static void getAllCategories(RequestHandler requestHandler) {
        List<Category> categories = Category.getList();
        List<MiniCate> miniCates = categories.stream().map(category -> new MiniCate(
                category.getId() + "",
                category.getName(),
                category.getCategoryFields())).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniCates));
    }

    private static void getAllProductOfCate(List<String> inputs, RequestHandler requestHandler) {
        int categoryId = Integer.parseInt(inputs.get(0));
        try {
            Category category = Category.getCategoryById(categoryId);
            List<Long> productIds = category.getProductList();
            List<MiniProduct> products = productIds.stream().map(id -> {

                try {
                    Product product = Product.getProductById(id);
                    return new MiniProduct(
                            product.getId() + "",
                            product.getName(),
                            product.getAuction().getId() + "",
                            product.getCategory().getId() + "",
                            product.getMediaId() + "",
                            product.getSellersOfProduct());

                } catch (ProductDoesNotExistException e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());

            requestHandler.sendMessage(yaGson.toJson(products));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private static void getAllPopularProducts(RequestHandler requestHandler) {
        List<Product> productList = Product.getList();
        List<MiniProduct> miniProducts = productList.stream().map(product ->
                new MiniProduct(
                        product.getId() + "",
                        product.getName(),
                        product.getAuction().getId() + "",
                        product.getCategory().getId() + "",
                        product.getMediaId() + "",
                        product.getSellersOfProduct()
                )
        ).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniProducts));
    }

    private static void getAllAuctions(RequestHandler requestHandler) {
        List<Auction> auctionList = Auction.getList();
        List<MiniAuction> miniAuctions = auctionList.stream().map(auction -> new MiniAuction(
                auction.getId() + "",
                auction.getName(),
                auction.getDiscount().getPercent(),
                auction.getDiscount().getAmount(),
                auction.getStart(),
                auction.getEnd())).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniAuctions));
    }

    private static void getAllAcounts(RequestHandler requestHandler) {
        List<Account> accountList = Account.getList();
        List<MiniAccount> miniAccounts = accountList.stream().map(account -> new MiniAccount(
                account.getMediaId() + "",
                account.getUserName() + "",
                account.getPassword() + "",
                account.getPersonalInfo().getList(),
                account instanceof Seller ? ((Seller) account).getCompanyInfo().getList() : null,
                account.getWallet()
        )).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniAccounts));
    }

    private static void getAllProducts(RequestHandler requestHandler, List<Product> list) {
        List<Product> products = list;
        List<MiniProduct> miniProducts = products.stream().map(product ->
                new MiniProduct(
                        product.getId() + "",
                        product.getName(),
                        product.getAuction().getId() + "",
                        product.getCategory().getId() + "",
                        product.getMediaId() + "",
                        product.getSellersOfProduct()
                )
        ).collect(Collectors.toList());
        requestHandler.sendMessage(yaGson.toJson(miniProducts));
    }

    private static void getImageById(List<String> inputs, RequestHandler requestHandler) {
        Medias medias = null;
        try {
            medias = Medias.getMediasById(Long.parseLong(inputs.get(0)));
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(medias.getImageSrc()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageString = new YaGson().toJson(byteArrayOutputStream.toByteArray());
        requestHandler.sendMessage(yaGson.toJson(imageString));
    }

    private static void getAuctionById(List<String> inputs, RequestHandler requestHandler) {
        Auction auction = null;
        try {
            auction = Auction.getAuctionById(Long.parseLong(inputs.get(0)));
        } catch (AuctionDoesNotExistException e) {
            e.printStackTrace();
        }
        MiniAuction miniAuction = new MiniAuction(
                auction.getId() + "",
                auction.getName(),
                auction.getDiscount().getPercent(),
                auction.getDiscount().getAmount(),
                auction.getStart(),
                auction.getEnd());
        requestHandler.sendMessage(yaGson.toJson(miniAuction));
    }

    private static void getCategoryById(List<String> inputs, RequestHandler requestHandler) {
        Category category = null;
        try {
            category = Category.getCategoryById(Long.parseLong(inputs.get(0)));
        } catch (CategoryDoesNotExistException e) {
            e.printStackTrace();
        }
        MiniCate miniCate = new MiniCate(
                category.getId() + "",
                category.getName(),
                category.getCategoryFields());
        requestHandler.sendMessage(yaGson.toJson(miniCate));
    }

    private static void getAccountById(List<String> inputs, RequestHandler requestHandler) {
        try {
            Account account = Account.getAccountById(Long.parseLong(inputs.get(0)));
            MiniAccount miniAccount = getMiniAccount(account);
            requestHandler.sendMessage(yaGson.toJson(miniAccount));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }

    }

    private static void getProductById(List<String> inputs, RequestHandler requestHandler) {
        Product product = null;
        try {
            product = Product.getProductById(Long.parseLong(inputs.get(0)));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
        MiniProduct miniProduct = new MiniProduct(
                product.getId() + "",
                product.getName(),
                product.getAuction().getId() + "",
                product.getCategory().getId() + "",
                product.getMediaId() + "",
                product.getSellersOfProduct());
        requestHandler.sendMessage(yaGson.toJson(miniProduct));
    }

    private static void getAllMyProducts(RequestHandler requestHandler) {
        try {
            List<Product> products = sellerController.showProducts();
            List<MiniProduct> miniProducts = products.stream().map(product ->
                    new MiniProduct(
                            product.getId() + "",
                            product.getName(),
                            product.getAuction().getId() + "",
                            product.getCategory().getId() + "",
                            product.getMediaId() + "",
                            product.getSellersOfProduct()
                    )
            ).collect(Collectors.toList());
            requestHandler.sendMessage(yaGson.toJson(miniProducts));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    enum successOrFailMessage {
        SUCCESS,
        FAIL

    }
}
