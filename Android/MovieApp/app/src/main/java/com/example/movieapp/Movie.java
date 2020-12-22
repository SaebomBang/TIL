package com.example.movieapp;

public class Movie {
    int rank;
    String name;
    String openDate;

    public Movie(int rank, String name, String openDate) {
        this.rank = rank;
        this.name = name;
        this.openDate = openDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", openDate='" + openDate + '\'' +
                '}';
    }
}
