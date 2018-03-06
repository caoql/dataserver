package com.gaode.xtd.admin.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull(message="操作名称不能为空")
	@Size(max=40,message="操作名称最多40个字符")
	private String operName;
	
	// 存储文本类型,A:sql,B:存储过程
	@NotNull(message="语句类型不能为空")
	@Size(max=10,message="语句类型最多10个字符")
	private String operType;
	
	// 模板
	@NotNull(message="模板不能为空")
	@Size(max=2500,message="模板最多2500个字符")
	private String sqltemplate;
	
	@Size(max=255,message="备注最多255个字符")
	private String remark;
	
	// 是否有返回值
	@Size(max=1,message="是否有返回值最多1个字符")
	private String isReturn;
	
	// 存储脚本
	@Size(max=2500,message="存储脚本最多2500个字符")
	private String text;
	
	// 参数列表
	private List<QueryparameterVO> queryParamList;
	
	// 数据服务ID
	@NotNull(message="数据源Id(datasourceId)在接口发送的时候不能为空")
	private Integer datasourceId;
	
	// 冗余的数据服务名字
	@NotNull(message="数据源不能为空")
	@Size(max=2500,message="数据源最多40个字符")
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
	 * 参数转化
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