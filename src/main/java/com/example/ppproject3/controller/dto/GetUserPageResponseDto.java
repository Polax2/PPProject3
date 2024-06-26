package com.example.ppproject3.controller.dto;

import java.util.List;

public class GetUserPageResponseDto {
    private List<GetUserDto> users;
    private int currentPage;
    private long totalItem;
    private int totalPages;
    private boolean hashMore;

    public GetUserPageResponseDto(List<GetUserDto> users, int currentPage, long totalItem, int totalPages, boolean hashMore) {
        this.users = users;
        this.currentPage = currentPage;
        this.totalItem = totalItem;
        this.totalPages = totalPages;
        this.hashMore = hashMore;
    }

    public List<GetUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserDto> loans) {
        this.users = loans;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHashMore() {
        return hashMore;
    }

    public void setHashMore(boolean hashMore) {
        this.hashMore = hashMore;
    }
}
