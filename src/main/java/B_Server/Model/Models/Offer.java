package B_Server.Model.Models;

import B_Server.Model.Models.Accounts.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class Offer {

    /*****************************************************fields*******************************************************/

    private static List<Offer> list;
    private Product productOnOffer;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate start;
    private LocalDate end;
    private HashMap<String,Double> auctioneersPrices = new HashMap<>();

    public Offer(Product productOnOffer, LocalDate start, LocalDate end, HashMap <String, Double> auctioneersPrices) {
        this.productOnOffer = productOnOffer;
        this.start = start;
        this.end = end;
        this.auctioneersPrices = auctioneersPrices;
    }
}
