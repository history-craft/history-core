package com.historycraft.config;

import java.util.ArrayList;
import java.util.List;

public class RecipeCopyConfig {

    public static class Crusher {

        private List<String> removedByProduct = new ArrayList<>();

        public List<String> getRemovedByProduct() {
            return removedByProduct;
        }

        public void setRemovedByProduct(List<String> removedByProduct) {
            this.removedByProduct = removedByProduct;
        }

        public Crusher add(String ore) {
            removedByProduct.add(ore);
            return this;
        }
    }

    public Crusher crusher;

    public Crusher getCrusher() {
        return crusher;
    }

    public void setCrusher(Crusher crusher) {
        this.crusher = crusher;
    }
}
