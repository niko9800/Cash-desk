package com.example.cashdesk.service;

import com.example.cashdesk.exception.FileOperationsException;
import com.example.cashdesk.model.Balance;
import com.example.cashdesk.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileOperationService {

    private static final String SEMI_COLON = "; ";
    private static final String COMMA = ", ";
    private static final String EMPTY_SPACE = " ";
    private static final String BALANCES_FILE = "balances.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public List<Balance> readBalances() {
        File file = new File(BALANCES_FILE);
        List<Balance> balances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(SEMI_COLON);
                Balance balance = new Balance();
                balance.setAmount(Integer.valueOf(lineArray[0]));
                balance.setCurrency(lineArray[1]);
                balance.setDenominations(toDenominationsMap(lineArray[2]));
                balances.add(balance);
            }
        } catch (IOException e) {
            throw new FileOperationsException(e.getMessage());

        }

        return balances;
    }

    public void updateBalances(List<Balance> balances) {
        File file = new File(BALANCES_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))) {
            for (Balance b : balances) {
                writer.write(b.toString());
            }
        } catch (IOException e) {
            throw new FileOperationsException(e.getMessage());
        }
    }

    public void storeTransactionInFile(Transaction transaction) {
        File file = new File(TRANSACTIONS_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true))) {
            writer.write(transaction.toString());
        } catch (IOException e) {
            throw new FileOperationsException(e.getMessage());
        }
    }

    private Map<Integer, Integer> toDenominationsMap(String denominationsStr) {
        Map<Integer, Integer> denominations = new LinkedHashMap<>();
        String[] denominationsArr = denominationsStr.split(COMMA);
        Arrays.stream(denominationsArr).forEach(d -> {
            String[] pair = d.split(EMPTY_SPACE);
            denominations.put(Integer.valueOf(pair[1]), Integer.valueOf(pair[0]));
        });
        return denominations;
    }
}
