package com.pplab4_8.moblileCommunication.tariffs;

public class TariffContract extends TariffPrepaid {
    private String additionalServices;
    public TariffContract(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount, String additionalServices){
        super(name, monthlyFee, callMinutesCount, internetGigabytesCount);
        this.additionalServices = additionalServices;
    }
    public String getAdditionalServices(){
        return additionalServices;
    }
    public void setAdditionalServices(String additionalServices){
        this.additionalServices = additionalServices;
    }
}
