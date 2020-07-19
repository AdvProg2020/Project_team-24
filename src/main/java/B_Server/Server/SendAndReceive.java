package B_Server.Server;

import B_Server.Controller.Controllers.*;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
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
                getAllProducts(requestHandler, Product.getList());

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
                String type = inputs.get(0);
                String username = inputs.get(1);
                String password = inputs.get(2);
                String firstName = inputs.get(3);
                String lastName = inputs.get(4);
                String phoneNumeber = inputs.get(5);
                String email = inputs.get(6);
                if(type.equals("Manager")){
                   Account account =  SignUpController.getInstance().creatTheBaseOfAccount("Manager",username);

                }
                if(type.equals("Customer")){

                }

                break;
            case "addNewSeller":
                String brand = inputs.get(7);
                String companyPhoneNumber = inputs.get(8);
                String companyEmail = inputs.get(9);


                break;
            case "addNewAuction":
                break;
            case "addNewCate":
                break;
            case "EditCate":
                break;
            case "addNewDiscountCode":
                break;
            case "addNewProduct":
                break;
            case "EditAccount":
                break;
            case "EditAuction":
                break;
            case "EditProduct":
                break;
            case "EditDiscountCode":
                break;
            case "DeleteAccountById":
                break;
            case "addNewFilter":
                break;
            case "CheckDiscountCodes":
                BuyerController.getInstance().
                break;


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
        if(account instanceof Customer){
           logHistoriesIds = ((Customer) account).getLogHistoryList();
        }
        if(account instanceof Seller){
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
}
