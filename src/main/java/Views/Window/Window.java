package Views.Window;

import Views.AuctionsArea.AuctionArea;
import Views.GoodsArea.GoodsArea;
import Views.UserArea.UserArea;

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
