package com.automataproj.automataproject.Popups;

import java.util.List;

public class PopupAutomataReturn {
    public boolean isOk = false;
    public String automataName;
    public List<Character> alphabets;
    public boolean isAFND = false;

    @Override
    public String toString() {
        return "PopupAutomataReturn{" +
                "automataName='" + automataName + '\'' +
                ", alphabets=" + alphabets +
                ", isAFND=" + isAFND +
                '}';
    }
}
