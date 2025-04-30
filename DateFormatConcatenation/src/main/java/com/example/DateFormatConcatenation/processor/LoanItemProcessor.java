package com.example.DateFormatConcatenation.processor;




import com.example.DateFormatConcatenation.model.LoanDetails;
import com.example.DateFormatConcatenation.model.LoanEmiSchedule;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class LoanItemProcessor implements ItemProcessor<LoanDetails, List<LoanEmiSchedule>> {

    @Override
    public List<LoanEmiSchedule> process(LoanDetails loan) {
        double r = loan.getInterestRate() / 12 / 100;
        int n = loan.getDurationMonths();
        double emi = (loan.getPrincipal() * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        List<LoanEmiSchedule> scheduleList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            LoanEmiSchedule emiEntry = new LoanEmiSchedule();
            emiEntry.setLoanId(loan.getLoanId());
            emiEntry.setMonthNumber(i);
            emiEntry.setEmi(Math.round(emi * 100.0) / 100.0);
            scheduleList.add(emiEntry);
        }
        return scheduleList;
    }
}
