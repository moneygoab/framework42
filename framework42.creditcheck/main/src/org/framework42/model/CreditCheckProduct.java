package org.framework42.model;

public enum CreditCheckProduct {
    UC_INDIVIDUAL_FULL(3, "3"),UC_INDIVIDUAL_UNKNOWN(90, "3"),
    UC_JURIDICAL_RISK(4, "4"),UC_JURIDICAL_MINI(41, "41"),UC_JURIDICAL_STANDARD(410, "410"),
    UC_SOLE_TRADER_RISK(5, "4"), UC_SOLE_TRADER_STANDARD(411,"410")
    ;

    private int id;

    private String value;

    CreditCheckProduct(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public static CreditCheckProduct getById(int id) {

        for(CreditCheckProduct product: CreditCheckProduct.values()) {

            if(id == product.getId()) {

                return product;
            }
        }

        throw new IllegalArgumentException("No credit check product with id "+id+" exists!");
    }

}
