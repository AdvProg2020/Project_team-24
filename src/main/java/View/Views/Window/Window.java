package View.Views.Window;

import View.AuctionsArea.AuctionArea;
import View.GoodsArea.GoodsArea;
import View.UserArea.UserArea;

public class Window {

    protected UserArea userArea;
    protected GoodsArea goodsArea;
    protected AuctionArea auctionArea;

    //

    public Window(UserArea userArea, GoodsArea goodsArea, AuctionArea auctionArea) {
        this.userArea = userArea;
        this.goodsArea = goodsArea;
        this.auctionArea = auctionArea;
    }
}
