package com.gaode.xtd.admin.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * operation表对应的PO
 * @author andyc 
 * 2018-2-16
 */
public class OperationPO implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7054111805881554185L;

	private Integer id;
	// 文本名字
	private String operName;
	// 存储文本类型,A:sql,B:存储过程
	private String operType;
	// 存储过程是否有返回值，既out参数(1：是，0：否）
	private String isReturn;
	// 储存脚本内容
	private String text;

	private String remark;

	private Date createTime;

	private String creator;

	private Date updateTime;

	private String updator;

	@Override
	public String toString() {
		return ObjReflect.toString(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName == null ? null : operName.trim();
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType == null ? null : operType.trim();
	}

	
	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn == null ? null : isReturn.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator == null ? null : updator.trim();
	}
}