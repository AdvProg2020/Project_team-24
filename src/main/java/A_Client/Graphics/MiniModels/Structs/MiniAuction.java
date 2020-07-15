package A_Client.Graphics.MiniModels.Structs;

public class MiniAuction {

    private final String auctionId;
    private final String auctionName;
    private final double auctionPercent;

    public String getAuctionName() {
        return auctionName;
    }

    public double getAuctionPercent() {
        return auctionPercent;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public MiniAuction(String auctionId, String auctionName, double auctionPercent) {
        this.auctionId = auctionId;
        this.auctionName = auctionName;
        this.auctionPercent = auctionPercent;
    }
}
