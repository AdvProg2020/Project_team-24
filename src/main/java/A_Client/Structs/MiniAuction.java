package A_Client.Structs;

public class MiniAuction {

    private final String auctionName;
    private final double auctionPercent;

    public String getAuctionName() {
        return auctionName;
    }

    public double getAuctionPercent() {
        return auctionPercent;
    }

    public MiniAuction(String auctionName, double auctionPercent) {
        this.auctionName = auctionName;
        this.auctionPercent = auctionPercent;
    }
}
