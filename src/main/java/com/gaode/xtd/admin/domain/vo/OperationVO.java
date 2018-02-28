package com.gaode.xtd.admin.domain.vo;

import java.io.Serializable;

import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * operation 对应的VO
 * @author andyc 
 * 2018-2-28
 */
public class OperationVO implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8321410386041533202L;
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

	/**
	 * 转换成PO
	 * @return
	 */
	public OperationPO toPO() {
		OperationPO po = new OperationPO();
		po.setId(id);
		po.setOperName(operName);
		po.setOperType(operType);
		po.setIsReturn(isReturn);
		po.setText(text);
		po.setRemark(remark);
		return po;
	}
	
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
}