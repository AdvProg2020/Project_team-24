package Model.RuntimeData;

import Model.Models.LogHistories.SaleLog;

import java.io.File;
import java.util.List;

public class SaleLogSource {

    private static File saleLogList_File = new File("src/main/resources/allSaleLogs");

    private List<SaleLog> saleLogList;

    public List<SaleLog> getSaleLogList() {
        return saleLogList;
    }

    public SaleLogSource(List<SaleLog> saleLogList) {
        this.saleLogList = saleLogList;
    }
}
