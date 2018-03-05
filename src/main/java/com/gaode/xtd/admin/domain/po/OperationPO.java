package com.gaode.xtd.admin.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * operation表对应的PO
 * @author andyc 
 * 2018-2-26
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
	// 模板
	private String sqltemplate;

	private String remark;

	private Date createTime;

	private String creator;

	private Date updateTime;

	private String updator;
	
	// 数据服务ID
	private Integer datasourceId;
	// 冗余的数据服务名字
	private String datasourceName;
		
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

	public String getSqltemplate() {
		return sqltemplate;
	}

	public void setSqltemplate(String sqltemplate) {
		this.sqltemplate = sqltemplate == null ? null : sqltemplate.trim();
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

	public Integer getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(Integer datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName == null ? null : datasourceName.trim();
	}
}