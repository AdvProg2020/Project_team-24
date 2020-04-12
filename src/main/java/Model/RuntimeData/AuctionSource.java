package Model.RuntimeData;

import Model.Models.Auction;

import java.io.File;
import java.util.List;

public class AuctionSource {

    private static File auctionList_File = new File("src/main/resources/allAuctions");

    private List<Auction> auctionList;

    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public AuctionSource(List<Auction> auctionList) {
        this.auctionList = auctionList;
    }
}
