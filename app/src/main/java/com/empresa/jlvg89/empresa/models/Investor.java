package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;

/**
 * Created by jlvg89 on 02/03/18.
 */

public class Investor implements Serializable {
    private int id;
    private String investorName;
    private String email;
    private String city;
    private String country;
    private int balance;
    private String photo;
    private Portfolio portfolio;
    private int portfolioValue;
    private boolean firstAccess;
    private boolean superAngel;

    public Investor(int id, String investorName, String email, String city, String country, int balance, String photo, Portfolio portfolio, int portfolioValue, boolean firstAccess, boolean superAngel) {
        this.id = id;
        this.investorName = investorName;
        this.email = email;
        this.city = city;
        this.country = country;
        this.balance = balance;
        this.photo = photo;
        this.portfolio = portfolio;
        this.portfolioValue = portfolioValue;
        this.firstAccess = firstAccess;
        this.superAngel = superAngel;
    }

    public Investor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public int getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(int portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public boolean isFirstAccess() {
        return firstAccess;
    }

    public void setFirstAccess(boolean firstAccess) {
        this.firstAccess = firstAccess;
    }

    public boolean isSuperAngel() {
        return superAngel;
    }

    public void setSuperAngel(boolean superAngel) {
        this.superAngel = superAngel;
    }
}
