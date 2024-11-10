package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 练车预约
 *
 * @author 
 * @email
 */
@TableName("liancheyuyue")
public class LiancheyuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LiancheyuyueEntity() {

	}

	public LiancheyuyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 学员
     */
    @TableField(value = "xueyuan_id")

    private Integer xueyuanId;


    /**
     * 车辆
     */
    @TableField(value = "cheliang_id")

    private Integer cheliangId;


    /**
     * 预约编号
     */
    @TableField(value = "liancheyuyue_uuid_number")

    private String liancheyuyueUuidNumber;


    /**
     * 预约科目
     */
    @TableField(value = "kaoshi_types")

    private Integer kaoshiTypes;


    /**
     * 预约详情
     */
    @TableField(value = "liancheyuyue_content")

    private String liancheyuyueContent;


    /**
     * 申请日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "shenqing_time")

    private Date shenqingTime;


    /**
     * 申请练车时长(小时)
     */
    @TableField(value = "xiaoshi")

    private Integer xiaoshi;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 申请状态
     */
    @TableField(value = "liancheyuyue_yesno_types")

    private Integer liancheyuyueYesnoTypes;


    /**
     * 审核理由
     */
    @TableField(value = "liancheyuyue_yesno_text")

    private String liancheyuyueYesnoText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学员
	 */
    public Integer getXueyuanId() {
        return xueyuanId;
    }


    /**
	 * 获取：学员
	 */

    public void setXueyuanId(Integer xueyuanId) {
        this.xueyuanId = xueyuanId;
    }
    /**
	 * 设置：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }


    /**
	 * 获取：车辆
	 */

    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 设置：预约编号
	 */
    public String getLiancheyuyueUuidNumber() {
        return liancheyuyueUuidNumber;
    }


    /**
	 * 获取：预约编号
	 */

    public void setLiancheyuyueUuidNumber(String liancheyuyueUuidNumber) {
        this.liancheyuyueUuidNumber = liancheyuyueUuidNumber;
    }
    /**
	 * 设置：预约科目
	 */
    public Integer getKaoshiTypes() {
        return kaoshiTypes;
    }


    /**
	 * 获取：预约科目
	 */

    public void setKaoshiTypes(Integer kaoshiTypes) {
        this.kaoshiTypes = kaoshiTypes;
    }
    /**
	 * 设置：预约详情
	 */
    public String getLiancheyuyueContent() {
        return liancheyuyueContent;
    }


    /**
	 * 获取：预约详情
	 */

    public void setLiancheyuyueContent(String liancheyuyueContent) {
        this.liancheyuyueContent = liancheyuyueContent;
    }
    /**
	 * 设置：申请日期
	 */
    public Date getShenqingTime() {
        return shenqingTime;
    }


    /**
	 * 获取：申请日期
	 */

    public void setShenqingTime(Date shenqingTime) {
        this.shenqingTime = shenqingTime;
    }
    /**
	 * 设置：申请练车时长(小时)
	 */
    public Integer getXiaoshi() {
        return xiaoshi;
    }


    /**
	 * 获取：申请练车时长(小时)
	 */

    public void setXiaoshi(Integer xiaoshi) {
        this.xiaoshi = xiaoshi;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getLiancheyuyueYesnoTypes() {
        return liancheyuyueYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setLiancheyuyueYesnoTypes(Integer liancheyuyueYesnoTypes) {
        this.liancheyuyueYesnoTypes = liancheyuyueYesnoTypes;
    }
    /**
	 * 设置：审核理由
	 */
    public String getLiancheyuyueYesnoText() {
        return liancheyuyueYesnoText;
    }


    /**
	 * 获取：审核理由
	 */

    public void setLiancheyuyueYesnoText(String liancheyuyueYesnoText) {
        this.liancheyuyueYesnoText = liancheyuyueYesnoText;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Liancheyuyue{" +
            "id=" + id +
            ", xueyuanId=" + xueyuanId +
            ", cheliangId=" + cheliangId +
            ", liancheyuyueUuidNumber=" + liancheyuyueUuidNumber +
            ", kaoshiTypes=" + kaoshiTypes +
            ", liancheyuyueContent=" + liancheyuyueContent +
            ", shenqingTime=" + shenqingTime +
            ", xiaoshi=" + xiaoshi +
            ", insertTime=" + insertTime +
            ", liancheyuyueYesnoTypes=" + liancheyuyueYesnoTypes +
            ", liancheyuyueYesnoText=" + liancheyuyueYesnoText +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
