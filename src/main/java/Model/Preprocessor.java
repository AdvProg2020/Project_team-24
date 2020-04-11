package Model;

import Model.Models.Account;
import Model.Tools.FileHandler;
import Model.Tools.PackClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Preprocessor implements PackClass, FileHandler {

    private static File accountList_File;
    private static File productList_File;
    private static File auctionList_File;
    private static File categoryList_File;
    private static File buyAndSellHistoryList_File;
    private static File discountWithCodeList_File;
    private static File saleLogList_File;
    private static File shoppingLogList_File;
    private static File personalInformationList_File;
    private static File discountList_File;

    static {
        accountList_File = new File("src/main/resources/allAccounts");
        productList_File = new File("src/main/resources/allProducts");
        auctionList_File = new File("src/main/resources/allAuctions");
        categoryList_File = new File("src/main/resources/allCategories");
        buyAndSellHistoryList_File = new File("src/main/resources/allBuyAndSellHistories");
        discountWithCodeList_File = new File("src/main/resources/allDiscountWithCodes");
        saleLogList_File = new File("src/main/resources/allSaleLogs");
        shoppingLogList_File = new File("src/main/resources/allShoppingLogs");
        personalInformationList_File = new File("src/main/resources/allPersonalInformation");
        discountList_File = new File("src/main/resources/allDiscounts");
    }

    @Nullable
    private static List<Pack> getPackList(File file) {
        try {
            return FileHandler.fileToStrings(file).stream()
                    .map(PackClass::unpack)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static List<Account> getAccountList() {
//        List<Pack> packList = getPackList(accountList_File);
//        return packList.stream().map(pack -> {
//            ?
//        }).collect(Collectors.toList());
//    }

    public static void addAccountToFile(@NotNull List<Account> accountList) {
        List<String> strings = accountList.stream()
                .map(Account::getParam)
                .map(Pack::new)
                .map(PackClass::pack)
                .collect(Collectors.toList());

        Function<String,String> nameSupplier = s -> {
            Pack pack = PackClass.unpack(s);
            return String.valueOf(pack.getParam().get(0));
        };

        FileHandler.StringsToFile(accountList_File,strings,nameSupplier);
    }

}
