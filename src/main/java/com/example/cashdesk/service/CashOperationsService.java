package com.example.cashdesk.service;

import com.example.cashdesk.exception.CashBalanceException;
import com.example.cashdesk.model.Balance;
import com.example.cashdesk.dto.CashOperationsRequestDTO;
import com.example.cashdesk.dto.CashOperationsResponseDTO;
import com.example.cashdesk.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CashOperationsService {

    private static final String DEPOSIT = "DEPOSIT";
    private static final String WITHDRAWAL = "WITHDRAWAL";

    private static final String INSUFFICIENT_AMOUNT = "Insufficient amount!";
    private static final String INSUFFICIENT_BANKNOTES = "Insufficient banknotes!";

    private final FileOperationService fileOperationService;

    public CashOperationsResponseDTO performOperation(CashOperationsRequestDTO cashOperationsRequest) {
        Transaction transaction = createTransaction(cashOperationsRequest);
        List<Balance> balances = fileOperationService.readBalances();
        Balance balance = balances.stream().filter(b -> cashOperationsRequest.getCurrency()
                .equalsIgnoreCase(b.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new CashBalanceException("Could not find balance for the given currency"));
        String operationType = cashOperationsRequest.getOperationType();
        Integer totalAmount = balance.getAmount();
        Map<Integer, Integer> requestDenominations = cashOperationsRequest.getDenominations();
        Map<Integer, Integer> balanceDenominations = balance.getDenominations();

        totalAmount = handleCalculations(
                totalAmount,
                operationType,
                cashOperationsRequest.getAmount(),
                INSUFFICIENT_AMOUNT);

        for (Map.Entry<Integer, Integer> denomination : requestDenominations.entrySet()) {
            Integer banknoteValue = denomination.getKey();
            Integer numberOfBanknotes = balanceDenominations.get(banknoteValue);
            if (balanceDenominations.containsKey(banknoteValue)) {
                numberOfBanknotes = handleCalculations(
                        numberOfBanknotes,
                        operationType,
                        denomination.getValue(),
                        INSUFFICIENT_BANKNOTES);

            } else {
                if (WITHDRAWAL.equalsIgnoreCase(operationType)) {
                    throw new CashBalanceException(INSUFFICIENT_BANKNOTES);
                }
                numberOfBanknotes = denomination.getValue();
            }

            if (numberOfBanknotes != null && numberOfBanknotes != 0) {
                balanceDenominations.put(banknoteValue, numberOfBanknotes);
            } else {
                balanceDenominations.remove(banknoteValue);
            }
            balance.setAmount(totalAmount);
        }

        fileOperationService.storeTransactionInFile(transaction);
        fileOperationService.updateBalances(balances);
        return createResponse(transaction);
    }

    private Integer handleCalculations(
            Integer value,
            String operationType,
            Integer valuesOffset,
            String errorMessage) {
        if (DEPOSIT.equalsIgnoreCase(operationType)) {
            value += valuesOffset;
        } else {
            value -= valuesOffset;
            if (value < 0) {
                throw new CashBalanceException(errorMessage);
            }
        }
        return value;
    }

    private Transaction createTransaction(CashOperationsRequestDTO cashOperationsRequest) {
        return Transaction
                .builder()
                .amount(cashOperationsRequest.getAmount())
                .currency(cashOperationsRequest.getCurrency())
                .operationType(cashOperationsRequest.getOperationType())
                .transactionTime(LocalDateTime.now())
                .build();
    }

    private CashOperationsResponseDTO createResponse(Transaction transaction) {
        CashOperationsResponseDTO response = new CashOperationsResponseDTO();
        response.setAmount(transaction.getAmount());
        response.setCurrency(transaction.getCurrency());
        response.setOperationType(transaction.getOperationType());
        response.setTransactionTime(transaction.getTransactionTime());
        return response;
    }
}
