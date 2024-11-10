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
 * 学员
 *
 * @author 
 * @email
 */
@TableName("xueyuan")
public class XueyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueyuanEntity() {

	}

	public XueyuanEntity(T t) {
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
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 学员姓名
     */
    @TableField(value = "xueyuan_name")

    private String xueyuanName;


    /**
     * 学员手机号
     */
    @TableField(value = "xueyuan_phone")

    private String xueyuanPhone;


    /**
     * 学员身份证号
     */
    @TableField(value = "xueyuan_id_number")

    private String xueyuanIdNumber;


    /**
     * 学员头像
     */
    @TableField(value = "xueyuan_photo")

    private String xueyuanPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "xueyuan_email")

    private String xueyuanEmail;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：学员姓名
	 */
    public String getXueyuanName() {
        return xueyuanName;
    }


    /**
	 * 获取：学员姓名
	 */

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }
    /**
	 * 设置：学员手机号
	 */
    public String getXueyuanPhone() {
        return xueyuanPhone;
    }


    /**
	 * 获取：学员手机号
	 */

    public void setXueyuanPhone(String xueyuanPhone) {
        this.xueyuanPhone = xueyuanPhone;
    }
    /**
	 * 设置：学员身份证号
	 */
    public String getXueyuanIdNumber() {
        return xueyuanIdNumber;
    }


    /**
	 * 获取：学员身份证号
	 */

    public void setXueyuanIdNumber(String xueyuanIdNumber) {
        this.xueyuanIdNumber = xueyuanIdNumber;
    }
    /**
	 * 设置：学员头像
	 */
    public String getXueyuanPhoto() {
        return xueyuanPhoto;
    }


    /**
	 * 获取：学员头像
	 */

    public void setXueyuanPhoto(String xueyuanPhoto) {
        this.xueyuanPhoto = xueyuanPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getXueyuanEmail() {
        return xueyuanEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setXueyuanEmail(String xueyuanEmail) {
        this.xueyuanEmail = xueyuanEmail;
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
        return "Xueyuan{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xueyuanName=" + xueyuanName +
            ", xueyuanPhone=" + xueyuanPhone +
            ", xueyuanIdNumber=" + xueyuanIdNumber +
            ", xueyuanPhoto=" + xueyuanPhoto +
            ", sexTypes=" + sexTypes +
            ", xueyuanEmail=" + xueyuanEmail +
            ", createTime=" + createTime +
        "}";
    }
}
