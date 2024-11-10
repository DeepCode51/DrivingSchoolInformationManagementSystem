package com.entity.view;

import com.entity.LiancheyuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 练车预约
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("liancheyuyue")
public class LiancheyuyueView extends LiancheyuyueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 预约科目的值
		*/
		private String kaoshiValue;
		/**
		* 申请状态的值
		*/
		private String liancheyuyueYesnoValue;



		//级联表 cheliang
			/**
			* 车辆 的 教练
			*/
			private Integer cheliangJiaolianId;
			/**
			* 车辆编号
			*/
			private String cheliangUuidNumber;
			/**
			* 车辆名称
			*/
			private String cheliangName;
			/**
			* 车辆类型
			*/
			private Integer cheliangTypes;
				/**
				* 车辆类型的值
				*/
				private String cheliangValue;
			/**
			* 车辆详情
			*/
			private String cheliangContent;

		//级联表 xueyuan
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
			* 电子邮箱
			*/
			private String xueyuanEmail;

	public LiancheyuyueView() {

	}

	public LiancheyuyueView(LiancheyuyueEntity liancheyuyueEntity) {
		try {
			BeanUtils.copyProperties(this, liancheyuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 预约科目的值
			*/
			public String getKaoshiValue() {
				return kaoshiValue;
			}
			/**
			* 设置： 预约科目的值
			*/
			public void setKaoshiValue(String kaoshiValue) {
				this.kaoshiValue = kaoshiValue;
			}
			/**
			* 获取： 申请状态的值
			*/
			public String getLiancheyuyueYesnoValue() {
				return liancheyuyueYesnoValue;
			}
			/**
			* 设置： 申请状态的值
			*/
			public void setLiancheyuyueYesnoValue(String liancheyuyueYesnoValue) {
				this.liancheyuyueYesnoValue = liancheyuyueYesnoValue;
			}





				//级联表的get和set cheliang
					/**
					* 获取：车辆 的 教练
					*/
					public Integer getCheliangJiaolianId() {
						return cheliangJiaolianId;
					}
					/**
					* 设置：车辆 的 教练
					*/
					public void setCheliangJiaolianId(Integer cheliangJiaolianId) {
						this.cheliangJiaolianId = cheliangJiaolianId;
					}

					/**
					* 获取： 车辆编号
					*/
					public String getCheliangUuidNumber() {
						return cheliangUuidNumber;
					}
					/**
					* 设置： 车辆编号
					*/
					public void setCheliangUuidNumber(String cheliangUuidNumber) {
						this.cheliangUuidNumber = cheliangUuidNumber;
					}
					/**
					* 获取： 车辆名称
					*/
					public String getCheliangName() {
						return cheliangName;
					}
					/**
					* 设置： 车辆名称
					*/
					public void setCheliangName(String cheliangName) {
						this.cheliangName = cheliangName;
					}
					/**
					* 获取： 车辆类型
					*/
					public Integer getCheliangTypes() {
						return cheliangTypes;
					}
					/**
					* 设置： 车辆类型
					*/
					public void setCheliangTypes(Integer cheliangTypes) {
						this.cheliangTypes = cheliangTypes;
					}


						/**
						* 获取： 车辆类型的值
						*/
						public String getCheliangValue() {
							return cheliangValue;
						}
						/**
						* 设置： 车辆类型的值
						*/
						public void setCheliangValue(String cheliangValue) {
							this.cheliangValue = cheliangValue;
						}
					/**
					* 获取： 车辆详情
					*/
					public String getCheliangContent() {
						return cheliangContent;
					}
					/**
					* 设置： 车辆详情
					*/
					public void setCheliangContent(String cheliangContent) {
						this.cheliangContent = cheliangContent;
					}














				//级联表的get和set xueyuan
					/**
					* 获取： 学员姓名
					*/
					public String getXueyuanName() {
						return xueyuanName;
					}
					/**
					* 设置： 学员姓名
					*/
					public void setXueyuanName(String xueyuanName) {
						this.xueyuanName = xueyuanName;
					}
					/**
					* 获取： 学员手机号
					*/
					public String getXueyuanPhone() {
						return xueyuanPhone;
					}
					/**
					* 设置： 学员手机号
					*/
					public void setXueyuanPhone(String xueyuanPhone) {
						this.xueyuanPhone = xueyuanPhone;
					}
					/**
					* 获取： 学员身份证号
					*/
					public String getXueyuanIdNumber() {
						return xueyuanIdNumber;
					}
					/**
					* 设置： 学员身份证号
					*/
					public void setXueyuanIdNumber(String xueyuanIdNumber) {
						this.xueyuanIdNumber = xueyuanIdNumber;
					}
					/**
					* 获取： 学员头像
					*/
					public String getXueyuanPhoto() {
						return xueyuanPhoto;
					}
					/**
					* 设置： 学员头像
					*/
					public void setXueyuanPhoto(String xueyuanPhoto) {
						this.xueyuanPhoto = xueyuanPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getXueyuanEmail() {
						return xueyuanEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setXueyuanEmail(String xueyuanEmail) {
						this.xueyuanEmail = xueyuanEmail;
					}




}
