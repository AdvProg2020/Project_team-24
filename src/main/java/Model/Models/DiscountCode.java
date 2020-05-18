package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DiscountCode implements Packable<DiscountCode>, Cloneable {

    /*****************************************************fields*******************************************************/

    private static List<DiscountCode> list;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private long id;
    private String discountCode;
    private LocalDate start;
    private LocalDate end;
    private Discount discount;
    private long frequentUse;
    private List<Long> accountList = new ArrayList<>();

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    public long getFrequentUse() {
        return frequentUse;
    }

    public List<Long> getAccountList() {
        return Collections.unmodifiableList(accountList);
    }

    public static List<DiscountCode> getList() {
        return Collections.unmodifiableList(list);
    }

    /*****************************************************setters*******************************************************/

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setFrequentUse(long frequentUse) {
        this.frequentUse = frequentUse;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static void setList(List<DiscountCode> list) {
        DiscountCode.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public void addAccount(long accountId) {
        accountList.add(accountId);
        DataBase.save(this);
    }

    public void removeAccount(long accountId) {
        accountList.remove(accountId);
        DataBase.save(this);
    }

    public static void addDiscountCode(@NotNull DiscountCode discountCode) {
        discountCode.setId(AddingNew.getRegisteringId().apply(DiscountCode.getList()));
        list.add(discountCode);
        DataBase.save(discountCode, true);
    }

    public static void removeFromDiscountCode(DiscountCode discountCode) {
        list.removeIf(dis -> discountCode.getId() == dis.getId());
        DataBase.remove(discountCode);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<DiscountCode> pack() {
        return new Data<DiscountCode>()
                .addField(id)
                .addField(discountCode)
                .addField(start)
                .addField(end)
                .addField(discount)
                .addField(frequentUse)
                .addField(accountList)
                .setInstance(new DiscountCode());
    }

    @Override
    public DiscountCode dpkg(@NotNull Data<DiscountCode> data) {
        this.id = (long) data.getFields().get(0);
        this.discountCode = (String) data.getFields().get(1);
        this.start = (LocalDate) data.getFields().get(2);
        this.end = (LocalDate) data.getFields().get(3);
        this.discount = (Discount) data.getFields().get(4);
        this.frequentUse = (long) data.getFields().get(5);
        this.accountList = (List<Long>) data.getFields().get(5);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static DiscountCode getDiscountCodeById(long id) throws DiscountCodeExpiredException {
        return list.stream()
                .filter(code -> id == code.getId())
                .findFirst()
                .orElseThrow(() -> new DiscountCodeExpiredException(
                        "DiscountCode with the id:" + id + " does not exist in list of all discountCodes."
                ));
    }

    public static String createDiscountCode() {
        return UUID.randomUUID().toString();
    }

    public double getDiscountCodeDiscount(double price) {
        return Math.min(discount.getAmount(), discount.getPercent() * price / 100);
    }

    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException, NumberFormatException, DateTimeParseException {

        switch (fieldName) {
            case "start":
                setStart(LocalDate.parse(value, formatter));
                break;
            case "end":
                setEnd(LocalDate.parse(value, formatter));
                break;
            case "frequentUse":
                setFrequentUse(Long.parseLong(value));
                break;
            case "maxDiscountAmount":
                discount.setAmount(Double.parseDouble(value));
                break;
            case "discountPercent":
                discount.setPercent(Double.parseDouble(value));
                break;
            default:
                throw new FieldDoesNotExistException("Not such a field in DiscountCode");
        }
    }

    /**************************************************constructors*****************************************************/

    public DiscountCode(String discountCode, LocalDate start, LocalDate end, Discount discount, long frequentUse) {
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.frequentUse = frequentUse;
    }

    private DiscountCode() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "DiscountCode{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", discount=" + discount +
                ", frequentUse=" + frequentUse +
                '}';
    }
}

