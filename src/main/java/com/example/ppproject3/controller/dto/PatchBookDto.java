package com.example.ppproject3.controller.dto;

public class PatchBookDto {
    private int newCopies;

    public PatchBookDto(int newCopies) {
        this.newCopies = newCopies;
    }

    public PatchBookDto() {
    }

    public int getNewCopies() {
        return newCopies;
    }

    public void setNewCopies(int newCopies) {
        this.newCopies = newCopies;
    }
}
