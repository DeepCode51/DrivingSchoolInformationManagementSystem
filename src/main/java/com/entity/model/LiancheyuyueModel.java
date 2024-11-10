package com.entity.model;

import com.entity.LiancheyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 练车预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LiancheyuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学员
     */
    private Integer xueyuanId;


    /**
     * 车辆
     */
    private Integer cheliangId;


    /**
     * 预约编号
     */
    private String liancheyuyueUuidNumber;


    /**
     * 预约科目
     */
    private Integer kaoshiTypes;


    /**
     * 预约详情
     */
    private String liancheyuyueContent;


    /**
     * 申请日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date shenqingTime;


    /**
     * 申请练车时长(小时)
     */
    private Integer xiaoshi;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 申请状态
     */
    private Integer liancheyuyueYesnoTypes;


    /**
     * 审核理由
     */
    private String liancheyuyueYesnoText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学员
	 */
    public Integer getXueyuanId() {
        return xueyuanId;
    }


    /**
	 * 设置：学员
	 */
    public void setXueyuanId(Integer xueyuanId) {
        this.xueyuanId = xueyuanId;
    }
    /**
	 * 获取：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }


    /**
	 * 设置：车辆
	 */
    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 获取：预约编号
	 */
    public String getLiancheyuyueUuidNumber() {
        return liancheyuyueUuidNumber;
    }


    /**
	 * 设置：预约编号
	 */
    public void setLiancheyuyueUuidNumber(String liancheyuyueUuidNumber) {
        this.liancheyuyueUuidNumber = liancheyuyueUuidNumber;
    }
    /**
	 * 获取：预约科目
	 */
    public Integer getKaoshiTypes() {
        return kaoshiTypes;
    }


    /**
	 * 设置：预约科目
	 */
    public void setKaoshiTypes(Integer kaoshiTypes) {
        this.kaoshiTypes = kaoshiTypes;
    }
    /**
	 * 获取：预约详情
	 */
    public String getLiancheyuyueContent() {
        return liancheyuyueContent;
    }


    /**
	 * 设置：预约详情
	 */
    public void setLiancheyuyueContent(String liancheyuyueContent) {
        this.liancheyuyueContent = liancheyuyueContent;
    }
    /**
	 * 获取：申请日期
	 */
    public Date getShenqingTime() {
        return shenqingTime;
    }


    /**
	 * 设置：申请日期
	 */
    public void setShenqingTime(Date shenqingTime) {
        this.shenqingTime = shenqingTime;
    }
    /**
	 * 获取：申请练车时长(小时)
	 */
    public Integer getXiaoshi() {
        return xiaoshi;
    }


    /**
	 * 设置：申请练车时长(小时)
	 */
    public void setXiaoshi(Integer xiaoshi) {
        this.xiaoshi = xiaoshi;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getLiancheyuyueYesnoTypes() {
        return liancheyuyueYesnoTypes;
    }


    /**
	 * 设置：申请状态
	 */
    public void setLiancheyuyueYesnoTypes(Integer liancheyuyueYesnoTypes) {
        this.liancheyuyueYesnoTypes = liancheyuyueYesnoTypes;
    }
    /**
	 * 获取：审核理由
	 */
    public String getLiancheyuyueYesnoText() {
        return liancheyuyueYesnoText;
    }


    /**
	 * 设置：审核理由
	 */
    public void setLiancheyuyueYesnoText(String liancheyuyueYesnoText) {
        this.liancheyuyueYesnoText = liancheyuyueYesnoText;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
