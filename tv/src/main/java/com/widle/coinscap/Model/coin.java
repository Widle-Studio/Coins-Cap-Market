package com.widle.coinscap.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by imperial-android on 19/12/17.
 */
public class coin implements Serializable {

    public int Id ;
    public String FROMSYMBOL = "";
    public String TOSYMBOL = "";
    public String MARKET = "";
    public String PRICE = "";
    public String LASTUPDATE = "";
    public String LASTVOLUME = "";
    public String LASTVOLUMETO = "";
    public String LASTTRADEID = "";
    public String VOLUMEDAY = "";
    public String VOLUMEDAYTO = "";
    public String VOLUME24HOUR = "";
    public String VOLUME24HOURTO = "";
    public String OPENDAY = "";
    public String HIGHDAY = "";
    public String LOWDAY = "";
    public String OPEN24HOUR = "";
    public String HIGH24HOUR = "";
    public String LOW24HOUR = "";
    public String LASTMARKET = "";
    public String CHANGE24HOUR = "";
    public String CHANGEPCT24HOUR = "";
    public String CHANGEDAY = "";
    public String CHANGEPCTDAY = "";
    public String SUPPLY = "";
    public String MKTCAP = "";
    public String TOTALVOLUME24H = "";
    public String TOTALVOLUME24HTO = "";
    public String COINSNAME="";
    public String COINSSTRINGNAME;
    public String FULLNAME = "";

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCOINSNAME() {
        return COINSNAME;
    }

    public void setCOINSNAME(String COINSNAME) {
        this.COINSNAME = COINSNAME;
    }

    public String getCOINSSTRINGNAME() {
        return COINSSTRINGNAME;
    }

    public void setCOINSSTRINGNAME(String COINSSTRINGNAME) {
        this.COINSSTRINGNAME = COINSSTRINGNAME;
    }

    public String getFROMSYMBOL() {
        return FROMSYMBOL;
    }

    public void setFROMSYMBOL(String FROMSYMBOL) {
        this.FROMSYMBOL = FROMSYMBOL;
    }

    public String getTOSYMBOL() {
        return TOSYMBOL;
    }

    public void setTOSYMBOL(String TOSYMBOL) {
        this.TOSYMBOL = TOSYMBOL;
    }

    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getLASTUPDATE() {
        return LASTUPDATE;
    }

    public void setLASTUPDATE(String LASTUPDATE) {
        this.LASTUPDATE = LASTUPDATE;
    }

    public String getLASTVOLUME() {
        return LASTVOLUME;
    }

    public void setLASTVOLUME(String LASTVOLUME) {
        this.LASTVOLUME = LASTVOLUME;
    }

    public String getLASTVOLUMETO() {
        return LASTVOLUMETO;
    }

    public void setLASTVOLUMETO(String LASTVOLUMETO) {
        this.LASTVOLUMETO = LASTVOLUMETO;
    }

    public String getLASTTRADEID() {
        return LASTTRADEID;
    }

    public void setLASTTRADEID(String LASTTRADEID) {
        this.LASTTRADEID = LASTTRADEID;
    }

    public String getVOLUMEDAY() {
        return VOLUMEDAY;
    }

    public void setVOLUMEDAY(String VOLUMEDAY) {
        this.VOLUMEDAY = VOLUMEDAY;
    }

    public String getVOLUMEDAYTO() {
        return VOLUMEDAYTO;
    }

    public void setVOLUMEDAYTO(String VOLUMEDAYTO) {
        this.VOLUMEDAYTO = VOLUMEDAYTO;
    }

    public String getVOLUME24HOUR() {
        return VOLUME24HOUR;
    }

    public void setVOLUME24HOUR(String VOLUME24HOUR) {
        this.VOLUME24HOUR = VOLUME24HOUR;
    }

    public String getVOLUME24HOURTO() {
        return VOLUME24HOURTO;
    }

    public void setVOLUME24HOURTO(String VOLUME24HOURTO) {
        this.VOLUME24HOURTO = VOLUME24HOURTO;
    }

    public String getOPENDAY() {
        return OPENDAY;
    }

    public void setOPENDAY(String OPENDAY) {
        this.OPENDAY = OPENDAY;
    }

    public String getHIGHDAY() {
        return HIGHDAY;
    }

    public void setHIGHDAY(String HIGHDAY) {
        this.HIGHDAY = HIGHDAY;
    }

    public String getLOWDAY() {
        return LOWDAY;
    }

    public void setLOWDAY(String LOWDAY) {
        this.LOWDAY = LOWDAY;
    }

    public String getOPEN24HOUR() {
        return OPEN24HOUR;
    }

    public void setOPEN24HOUR(String OPEN24HOUR) {
        this.OPEN24HOUR = OPEN24HOUR;
    }

    public String getHIGH24HOUR() {
        return HIGH24HOUR;
    }

    public void setHIGH24HOUR(String HIGH24HOUR) {
        this.HIGH24HOUR = HIGH24HOUR;
    }

    public String getLOW24HOUR() {
        return LOW24HOUR;
    }

    public void setLOW24HOUR(String LOW24HOUR) {
        this.LOW24HOUR = LOW24HOUR;
    }

    public String getLASTMARKET() {
        return LASTMARKET;
    }

    public void setLASTMARKET(String LASTMARKET) {
        this.LASTMARKET = LASTMARKET;
    }

    public String getCHANGE24HOUR() {
        return CHANGE24HOUR;
    }

    public void setCHANGE24HOUR(String CHANGE24HOUR) {
        this.CHANGE24HOUR = CHANGE24HOUR;
    }

    public String getCHANGEPCT24HOUR() {
        return CHANGEPCT24HOUR;
    }

    public void setCHANGEPCT24HOUR(String CHANGEPCT24HOUR) {
        this.CHANGEPCT24HOUR = CHANGEPCT24HOUR;
    }

    public String getCHANGEDAY() {
        return CHANGEDAY;
    }

    public void setCHANGEDAY(String CHANGEDAY) {
        this.CHANGEDAY = CHANGEDAY;
    }

    public String getCHANGEPCTDAY() {
        return CHANGEPCTDAY;
    }

    public void setCHANGEPCTDAY(String CHANGEPCTDAY) {
        this.CHANGEPCTDAY = CHANGEPCTDAY;
    }

    public String getSUPPLY() {
        return SUPPLY;
    }

    public void setSUPPLY(String SUPPLY) {
        this.SUPPLY = SUPPLY;
    }

    public String getMKTCAP() {
        return MKTCAP;
    }

    public void setMKTCAP(String MKTCAP) {
        this.MKTCAP = MKTCAP;
    }

    public String getTOTALVOLUME24H() {
        return TOTALVOLUME24H;
    }

    public void setTOTALVOLUME24H(String TOTALVOLUME24H) {
        this.TOTALVOLUME24H = TOTALVOLUME24H;
    }

    public String getTOTALVOLUME24HTO() {
        return TOTALVOLUME24HTO;
    }

    public void setTOTALVOLUME24HTO(String TOTALVOLUME24HTO) {
        this.TOTALVOLUME24HTO = TOTALVOLUME24HTO;
    }

    public String getFULLNAME(){
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME){
        this.FULLNAME = FULLNAME;
    }




}
