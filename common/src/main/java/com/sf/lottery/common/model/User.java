package com.sf.lottery.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/11/30
 */
public class User implements Serializable {
    private Integer id;

    private Integer sfNum;

    private String sfName;

    private Integer awardCount;

    private Boolean isSign;

    private Date timeSign;

    private String wxOpenid;

    private String wxHeadimgurl;

    private String wxNickname;

    private Integer wxSex;

    private String wxProvince;

    private String wxCity;

    private String wxCountry;

    private String wxPrivilege;

    private String wxUnionid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    //public void setId(Integer id) {
    //    this.id = id;
    //}

    public Integer getSfNum() {
        return sfNum;
    }

    public void setSfNum(Integer sfNum) {
        this.sfNum = sfNum;
    }

    public String getSfName() {
        return sfName;
    }

    public void setSfName(String sfName) {
        this.sfName = sfName == null ? null : sfName.trim();
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean isSign) {
        this.isSign = isSign;
    }

    public Date getTimeSign() {
        return timeSign;
    }

    public void setTimeSign(Date timeSign) {
        this.timeSign = timeSign;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public String getWxHeadimgurl() {
        return wxHeadimgurl;
    }

    public void setWxHeadimgurl(String wxHeadimgurl) {
        this.wxHeadimgurl = wxHeadimgurl == null ? null : wxHeadimgurl.trim();
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    public String getWxPrivilege() {
        return wxPrivilege;
    }

    public void setWxPrivilege(String wxPrivilege) {
        this.wxPrivilege = wxPrivilege == null ? null : wxPrivilege.trim();
    }

    public String getWxUnionid() {
        return wxUnionid;
    }

    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid == null ? null : wxUnionid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sfNum=").append(sfNum);
        sb.append(", sfName=").append(sfName);
        sb.append(", awardCount=").append(awardCount);
        sb.append(", isSign=").append(isSign);
        sb.append(", timeSign=").append(timeSign);
        sb.append(", wxOpenid=").append(wxOpenid);
        sb.append(", wxHeadimgurl=").append(wxHeadimgurl);
        sb.append(", wxNickname=").append(wxNickname);
        sb.append(", wxSex=").append(wxSex);
        sb.append(", wxProvince=").append(wxProvince);
        sb.append(", wxCity=").append(wxCity);
        sb.append(", wxCountry=").append(wxCountry);
        sb.append(", wxPrivilege=").append(wxPrivilege);
        sb.append(", wxUnionid=").append(wxUnionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}