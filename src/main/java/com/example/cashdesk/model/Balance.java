package com.example.cashdesk.model;

import lombok.Data;

import java.util.Iterator;
import java.util.Map;

@Data
public class Balance {

    private Integer amount;
    private String currency;
    private Map<Integer, Integer> denominations;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(amount);
        sb.append("; ");
        sb.append(currency);
        sb.append("; ");
        Iterator<Map.Entry<Integer, Integer>> iterator = denominations.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> currentDenomination = iterator.next();
            sb.append(currentDenomination.getValue());
            sb.append(' ');
            sb.append(currentDenomination.getKey());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('\n');
        return sb.toString();
    }
}
