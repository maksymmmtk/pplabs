package com.pplab4_8.moblileCommunication.tariffs;

public class TariffPrepaid {
    private String name;
    private float monthlyFee;
    private int callMinutesCount;
    private float internetGigabytesCount;
    private int subscribersCount = 0;

    public TariffPrepaid(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.callMinutesCount = callMinutesCount;
        this.internetGigabytesCount = internetGigabytesCount;
    }

    public String getName() {
        return name;
    }
    public float getMonthlyFee() {
        return monthlyFee;
    }
    public int getCallMinutesCount() {
        return callMinutesCount;
    }
    public float getInternetGigabytesCount() {
        return internetGigabytesCount;
    }
    public int getSubscribersCount() {
        return subscribersCount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    public void setCallMinutesCount(int callMinutesCount) {
        this.callMinutesCount = callMinutesCount;
    }
    public void setInternetGigabytesCount(float internetGigabytesCount) {
        this.internetGigabytesCount = internetGigabytesCount;
    }

    public void addSubscriber() {
        this.subscribersCount++;
    }
}
