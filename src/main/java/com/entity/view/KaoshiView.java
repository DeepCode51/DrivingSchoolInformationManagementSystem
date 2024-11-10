package com.entity.view;

import com.entity.KaoshiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 考试
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("kaoshi")
public class KaoshiView extends KaoshiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 考试类型的值
		*/
		private String kaoshiValue;
		/**
		* 考试结果的值
		*/
		private String tongguoValue;



		//级联表 jiaolian
			/**
			* 教练姓名
			*/
			private String jiaolianName;
			/**
			* 教练手机号
			*/
			private String jiaolianPhone;
			/**
			* 教练身份证号
			*/
			private String jiaolianIdNumber;
			/**
			* 教练头像
			*/
			private String jiaolianPhoto;
			/**
			* 电子邮箱
			*/
			private String jiaolianEmail;

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

	public KaoshiView() {

	}

	public KaoshiView(KaoshiEntity kaoshiEntity) {
		try {
			BeanUtils.copyProperties(this, kaoshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 考试类型的值
			*/
			public String getKaoshiValue() {
				return kaoshiValue;
			}
			/**
			* 设置： 考试类型的值
			*/
			public void setKaoshiValue(String kaoshiValue) {
				this.kaoshiValue = kaoshiValue;
			}
			/**
			* 获取： 考试结果的值
			*/
			public String getTongguoValue() {
				return tongguoValue;
			}
			/**
			* 设置： 考试结果的值
			*/
			public void setTongguoValue(String tongguoValue) {
				this.tongguoValue = tongguoValue;
			}











				//级联表的get和set jiaolian
					/**
					* 获取： 教练姓名
					*/
					public String getJiaolianName() {
						return jiaolianName;
					}
					/**
					* 设置： 教练姓名
					*/
					public void setJiaolianName(String jiaolianName) {
						this.jiaolianName = jiaolianName;
					}
					/**
					* 获取： 教练手机号
					*/
					public String getJiaolianPhone() {
						return jiaolianPhone;
					}
					/**
					* 设置： 教练手机号
					*/
					public void setJiaolianPhone(String jiaolianPhone) {
						this.jiaolianPhone = jiaolianPhone;
					}
					/**
					* 获取： 教练身份证号
					*/
					public String getJiaolianIdNumber() {
						return jiaolianIdNumber;
					}
					/**
					* 设置： 教练身份证号
					*/
					public void setJiaolianIdNumber(String jiaolianIdNumber) {
						this.jiaolianIdNumber = jiaolianIdNumber;
					}
					/**
					* 获取： 教练头像
					*/
					public String getJiaolianPhoto() {
						return jiaolianPhoto;
					}
					/**
					* 设置： 教练头像
					*/
					public void setJiaolianPhoto(String jiaolianPhoto) {
						this.jiaolianPhoto = jiaolianPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getJiaolianEmail() {
						return jiaolianEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setJiaolianEmail(String jiaolianEmail) {
						this.jiaolianEmail = jiaolianEmail;
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
