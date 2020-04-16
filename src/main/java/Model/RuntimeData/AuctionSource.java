package Model.RuntimeData;

import Model.Models.Auction;
import Model.Tools.FileHandler;

import java.io.File;
import java.util.List;

public class AuctionSource implements PackClass, FileHandler {

    private static File auctionList_File = new File("src/main/resources/allAuctions");

    private static List<Auction> auctionList;

    static {

    }

    public static List<Auction> getAuctionList() {
        return auctionList;
    }
}
