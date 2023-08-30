package com.crbcph.attendance.members.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPageDto<T> {
    private List<T> items;
    private int currentPage;
    private int size;
    private int totalPages;
    private long resultItems;
    private long allItems;

    public double getPercentage() {
        return ((float) resultItems / allItems) * 100;
    }


    public List<Integer> getDisplayPages() {
        List<Integer> displayPages = new ArrayList<>();
        int startPage = Math.max(1, currentPage - 3);
        int endPage = Math.min(currentPage + 3, totalPages);

        for (int i = startPage; i <= endPage; i++) {
            displayPages.add(i);
        }

        return displayPages;
    }
}