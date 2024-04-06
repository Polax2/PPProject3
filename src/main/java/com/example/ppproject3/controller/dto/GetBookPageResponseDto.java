package com.example.ppproject3.controller.dto;

import java.util.List;

public class GetBookPageResponseDto {

    private List<GetBookDto> books;
    private int currentPage;
    private long totalItem;
    private int totalPages;
    private boolean hashMore;

    public GetBookPageResponseDto(List<GetBookDto> books, int currentPage, long totalItem, int totalPages, boolean hashMore) {
        this.books = books;
        this.currentPage = currentPage;
        this.totalItem = totalItem;
        this.totalPages = totalPages;
        this.hashMore = hashMore;
    }

    public List<GetBookDto> getBooks() {
        return books;
    }

    public void setBooks(List<GetBookDto> books) {
        this.books = books;
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
