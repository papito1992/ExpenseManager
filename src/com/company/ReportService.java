package com.company;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ReportService {
   private Repository repo= Repository.getRepository();
    public Map<String,Float> calculateMonthlyTotal(){
        Map<String,Float> m = new TreeMap();
        for (Expense exp :
                repo.expList) {

            Date expDate=exp.getDate();
            String yearMonth=DataUtil.getYearAndMonth(expDate);
            if(m.containsKey(yearMonth)){
                //for when expense is already present for a month
                Float total=m.get(yearMonth);
                total=total+exp.getAmount();
                m.put(yearMonth,total); // new total, replace old total

            }else {
                // this month is not yet added
                m.put(yearMonth, exp.getAmount());

            }
        }return m; //return final result as map
    }
}
