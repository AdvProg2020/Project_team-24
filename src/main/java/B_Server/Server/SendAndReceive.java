package B_Server.Server;

import B_Server.Controller.Controllers.SellerController;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Structs.Medias;
import B_Server.Server.RequestHandler.RequestHandler;
import B_Server.Structs.*;
import Exceptions.*;
import com.gilecode.yagson.YaGson;

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


        }
    }

    private static void getAllDiscountCodes(RequestHandler requestHandler) {
        List<DiscountCode> discountCodes = DiscountCode.getList();
        List<MiniDiscountCode> miniDiscountCodes = discountCodes.stream().map(discountCode -> new MiniDiscountCode(
                discountCode.getId() ,
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
        Account account = null;
        try {
            account = Account.getAccountById(Long.parseLong(inputs.get(0)));
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
        MiniAccount miniAccount = new MiniAccount(
                account.getMediaId() + "",
                account.getUserName() + "",
                account.getPassword() + "",
                account.getPersonalInfo().getList(),
                account instanceof Seller ? ((Seller) account).getCompanyInfo().getList() : null,
                account.getWallet()
        );
        requestHandler.sendMessage(yaGson.toJson(miniAccount));
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
