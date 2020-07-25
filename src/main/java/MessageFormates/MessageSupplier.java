package MessageFormates;

import com.gilecode.yagson.YaGson;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface MessageSupplier {

    YaGson yaGson = new YaGson();

    Function<List<String>, String> messageSupplier = list -> String.format("%s::%s::%s", list.get(0), list.get(1), list.get(2));

    default String generateMessage(@NotNull RequestType type, List<String> list) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(list == null ? "@" : list.get(0));
        outputs.add(type.toString());

        ArrayList<Object> src = list == null || list.size() < 1 ?
                new ArrayList<>() : new ArrayList<>(list.subList(1,list.size()));

        outputs.add(yaGson.toJson(src));
        return messageSupplier.apply(outputs);
    }

    enum RequestType {
        GetToken,
        Login,
        Logout,
        GetProductById,
        GetAccountById,
        GetCateById,
        GetCartByUserId,
        GetAuctionById,
        GetImageById,
        GetMovieById,
        GetCodeById,
        GetOfferById,
        SetImageById,
        SetMediasOfProduct,
        GetAllProducts,
        GetAllProductsPrime,
        GetAllAccounts,
        GetAllAuctions,
        GetAllRequest,
        GetAllDiscountCodes,
        GetCodesOfUserById,
        GetLogsOfUserById,
        GetAllCate,
        GetAllProductOfCate,
        GetAllProductOfAuction,
        GetAllCommentOfProduct,
        GetAllProductOfCart,
        GetAllMyProducts,
        GetAllPopularProducts,
        addNewCustomerOrManager,
        addNewSeller,
        addNewAuction,
        addNewCate,
        addNewProduct,
        addNewDiscountCode,
        EditAccount,
        EditAuction,
        EditCate,
        EditProduct,
        EditDiscountCode,
        DeleteAccountById,
        DeleteProductById,
        addNewFilter,
        CheckDiscountCodes,
        addNewSellerOfPro,
        saveInfoOfProduct,
        addToCodesList,
        acceptRequest,
        declineRequest,
        SetCurrentCate,
        SetCurrentCode,
        SetCurrentProduct,
        Sort,
        increaseProduct,
        decreaseProduct,
        sendPaymentInfo,
        Purchase,
        addProductToCart,
        getCateInfoOfProduct,
        getProductInfoById,
        addCommentToProduct,
        rate,
        addChatMode,
        addNewBuyerToOfferById,
        SetCurrentOffer,
        GetAllOffers

    }
}
