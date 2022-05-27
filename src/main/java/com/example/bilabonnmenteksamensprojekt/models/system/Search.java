package com.example.bilabonnmenteksamensprojekt.models.system;

public class Search {

    private String resultType;
    private String resultInfo;
    private String resultLink;
    private String resultGeneralLink;


    public Search(String resultType, String resultInfo, String resultLink, String resultGeneralLink){
        this.resultType = resultType;
        this.resultInfo = resultInfo;
        this.resultLink = resultLink;
        this.resultGeneralLink = resultGeneralLink;
    }
    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink;
    }

    public String getResultGeneralLink() {
        return resultGeneralLink;
    }

    public void setResultGeneralLink(String resultGeneralLink) {
        this.resultGeneralLink = resultGeneralLink;
    }
}
