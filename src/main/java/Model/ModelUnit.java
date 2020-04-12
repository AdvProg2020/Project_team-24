package Model;

import Controller.ControllerUnit;
import Model.RuntimeData.*;

public class ModelUnit {

    private ControllerUnit controllerUnit;

    private AccountSource accountSource;
    private ProductSource productSource;
    private AuctionSource auctionSource;
    private CategorySource categorySource;
    private FilterSource filterSource;
    private SortSource sortSource;
    private RequestSource requestSource;
    private PersonalInformationSource personalInformationSource;
    private DiscountWithCodeSource discountWithCodeSource;
    private BuyAndSellHistorySource buyAndSellHistorySource;
    private SaleLogSource saleLogSource;
    private ShoppingLogSource shoppingLogSource;

    public ControllerUnit getControllerUnit() {
        return controllerUnit;
    }

    public void setControllerUnit(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public AccountSource getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(AccountSource accountSource) {
        this.accountSource = accountSource;
    }

    public ProductSource getProductSource() {
        return productSource;
    }

    public void setProductSource(ProductSource productSource) {
        this.productSource = productSource;
    }

    public AuctionSource getAuctionSource() {
        return auctionSource;
    }

    public void setAuctionSource(AuctionSource auctionSource) {
        this.auctionSource = auctionSource;
    }

    public CategorySource getCategorySource() {
        return categorySource;
    }

    public void setCategorySource(CategorySource categorySource) {
        this.categorySource = categorySource;
    }

    public FilterSource getFilterSource() {
        return filterSource;
    }

    public void setFilterSource(FilterSource filterSource) {
        this.filterSource = filterSource;
    }

    public SortSource getSortSource() {
        return sortSource;
    }

    public void setSortSource(SortSource sortSource) {
        this.sortSource = sortSource;
    }

    public RequestSource getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(RequestSource requestSource) {
        this.requestSource = requestSource;
    }

    public PersonalInformationSource getPersonalInformationSource() {
        return personalInformationSource;
    }

    public void setPersonalInformationSource(PersonalInformationSource personalInformationSource) {
        this.personalInformationSource = personalInformationSource;
    }

    public DiscountWithCodeSource getDiscountWithCodeSource() {
        return discountWithCodeSource;
    }

    public void setDiscountWithCodeSource(DiscountWithCodeSource discountWithCodeSource) {
        this.discountWithCodeSource = discountWithCodeSource;
    }

    public BuyAndSellHistorySource getBuyAndSellHistorySource() {
        return buyAndSellHistorySource;
    }

    public void setBuyAndSellHistorySource(BuyAndSellHistorySource buyAndSellHistorySource) {
        this.buyAndSellHistorySource = buyAndSellHistorySource;
    }

    public SaleLogSource getSaleLogSource() {
        return saleLogSource;
    }

    public void setSaleLogSource(SaleLogSource saleLogSource) {
        this.saleLogSource = saleLogSource;
    }

    public ShoppingLogSource getShoppingLogSource() {
        return shoppingLogSource;
    }

    public void setShoppingLogSource(ShoppingLogSource shoppingLogSource) {
        this.shoppingLogSource = shoppingLogSource;
    }

    public ModelUnit(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }
}
