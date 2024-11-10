package com.entity.model;

import com.entity.XueyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XueyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 学员姓名
     */
    private String xueyuanName;


    /**
     * 学员手机号
     */
    private String xueyuanPhone;


    /**
     * 学员身份证号
     */
    private String xueyuanIdNumber;


    /**
     * 学员头像
     */
    private String xueyuanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String xueyuanEmail;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：学员姓名
	 */
    public String getXueyuanName() {
        return xueyuanName;
    }


    /**
	 * 设置：学员姓名
	 */
    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }
    /**
	 * 获取：学员手机号
	 */
    public String getXueyuanPhone() {
        return xueyuanPhone;
    }


    /**
	 * 设置：学员手机号
	 */
    public void setXueyuanPhone(String xueyuanPhone) {
        this.xueyuanPhone = xueyuanPhone;
    }
    /**
	 * 获取：学员身份证号
	 */
    public String getXueyuanIdNumber() {
        return xueyuanIdNumber;
    }


    /**
	 * 设置：学员身份证号
	 */
    public void setXueyuanIdNumber(String xueyuanIdNumber) {
        this.xueyuanIdNumber = xueyuanIdNumber;
    }
    /**
	 * 获取：学员头像
	 */
    public String getXueyuanPhoto() {
        return xueyuanPhoto;
    }


    /**
	 * 设置：学员头像
	 */
    public void setXueyuanPhoto(String xueyuanPhoto) {
        this.xueyuanPhoto = xueyuanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getXueyuanEmail() {
        return xueyuanEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setXueyuanEmail(String xueyuanEmail) {
        this.xueyuanEmail = xueyuanEmail;
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
