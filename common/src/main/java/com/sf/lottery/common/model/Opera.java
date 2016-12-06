package com.sf.lottery.common.model;

import java.io.Serializable;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/6
 */
public class Opera implements Serializable {
    private Integer id;

    private String opName;

    private String opActor;

    private String opDepartment;

    private Integer opSort;

    private String opImg;

    private Integer opSupport;

    private Integer opDanmukuCount;

    private Integer opFlower;

    private Integer opCar;

    private Integer opRocket;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpActor() {
        return opActor;
    }

    public void setOpActor(String opActor) {
        this.opActor = opActor == null ? null : opActor.trim();
    }

    public String getOpDepartment() {
        return opDepartment;
    }

    public void setOpDepartment(String opDepartment) {
        this.opDepartment = opDepartment == null ? null : opDepartment.trim();
    }

    public Integer getOpSort() {
        return opSort;
    }

    public void setOpSort(Integer opSort) {
        this.opSort = opSort;
    }

    public String getOpImg() {
        return opImg;
    }

    public void setOpImg(String opImg) {
        this.opImg = opImg == null ? null : opImg.trim();
    }

    public Integer getOpSupport() {
        return opSupport;
    }

    public void setOpSupport(Integer opSupport) {
        this.opSupport = opSupport;
    }

    public Integer getOpDanmukuCount() {
        return opDanmukuCount;
    }

    public void setOpDanmukuCount(Integer opDanmukuCount) {
        this.opDanmukuCount = opDanmukuCount;
    }

    public Integer getOpFlower() {
        return opFlower;
    }

    public void setOpFlower(Integer opFlower) {
        this.opFlower = opFlower;
    }

    public Integer getOpCar() {
        return opCar;
    }

    public void setOpCar(Integer opCar) {
        this.opCar = opCar;
    }

    public Integer getOpRocket() {
        return opRocket;
    }

    public void setOpRocket(Integer opRocket) {
        this.opRocket = opRocket;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", opName=").append(opName);
        sb.append(", opActor=").append(opActor);
        sb.append(", opDepartment=").append(opDepartment);
        sb.append(", opSort=").append(opSort);
        sb.append(", opImg=").append(opImg);
        sb.append(", opSupport=").append(opSupport);
        sb.append(", opDanmukuCount=").append(opDanmukuCount);
        sb.append(", opFlower=").append(opFlower);
        sb.append(", opCar=").append(opCar);
        sb.append(", opRocket=").append(opRocket);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}