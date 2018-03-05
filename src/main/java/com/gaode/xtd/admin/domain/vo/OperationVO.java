package com.gaode.xtd.admin.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.po.QueryparameterPO;
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
	// 模板
	private String sqltemplate;
	private String remark;
	// 是否有返回值
	private String isReturn;
	// 存储脚本
	private String text;
	// 参数列表
	private List<QueryparameterVO> queryParamList;
	
	// 数据服务ID
	private Integer datasourceId;
	// 冗余的数据服务名字
	private String datasourceName;
	/**
	 * 转换成PO
	 * @return
	 */
	public OperationPO toPO() {
		OperationPO po = new OperationPO();
		po.setId(id);
		po.setOperName(operName);
		po.setOperType(operType);
		po.setSqltemplate(sqltemplate);
		po.setRemark(remark);
		po.setText(text);
		po.setIsReturn(isReturn);
		po.setDatasourceId(datasourceId);
		po.setDatasourceName(datasourceName);
		return po;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<QueryparameterPO> toQueryparameterPO() {
		List<QueryparameterPO> list = new ArrayList<QueryparameterPO>();
		if (queryParamList != null) {
			for (QueryparameterVO queryParam: queryParamList) {
				QueryparameterPO po = queryParam.toPO();
				list.add(po);
			}
		}
		return list;
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

	public String getSqltemplate() {
		return sqltemplate;
	}

	public void setSqltemplate(String sqltemplate) {
		this.sqltemplate = sqltemplate == null ? null : sqltemplate.trim();
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

	public List<QueryparameterVO> getQueryParamList() {
		return queryParamList;
	}

	public void setQueryParamList(List<QueryparameterVO> queryParamList) {
		this.queryParamList = queryParamList;
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