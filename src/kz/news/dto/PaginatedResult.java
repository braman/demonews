package kz.news.dto;

import java.util.List;

public class PaginatedResult<T> {
    
    private List<T> resultList;
    private int amount;
    
    public PaginatedResult(List<T> resultList, int amount) {
        this.resultList = resultList;
        this.amount = amount;
    }

    public List<T> getResultList() {
        return resultList;
    }
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
