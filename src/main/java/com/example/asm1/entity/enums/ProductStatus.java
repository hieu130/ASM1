package com.example.asm1.entity.enums;

public enum ProductStatus {
    ON_SALE(1), STOP_SALE(0);
    private int value;

    ProductStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static ProductStatus of(int value){
        for (ProductStatus productEnums: ProductStatus.values()
        ) {
            if (productEnums.getValue() == value) {
                return productEnums;
            }
        }
        return ON_SALE;
    }
}
