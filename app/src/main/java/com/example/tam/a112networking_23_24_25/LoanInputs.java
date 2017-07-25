package com.example.tam.a112networking_23_24_25;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tam on 7/25/2017.
 */

public class LoanInputs {
    private Map<String, String> mInputMap;
    public LoanInputs(String loanMount, String rate, String month) {
        mInputMap = new HashMap<String,String>();
        mInputMap.put("loanMount", loanMount);
        mInputMap.put("rate", rate);
        mInputMap.put("month", month);
    }
    public Map<String, String> getInputMap() {
        return mInputMap;
    }
}
