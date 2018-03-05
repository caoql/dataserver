package com.gaode.xtd.admin.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.gaode.xtd.admin.domain.po.DatasourceconfigPO;
import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.po.QueryparameterPO;
import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * 数据服务配置编辑的数据传输
 * @author andyc
 * 2018-3-2
 */
public class OperationDTO extends OperationPO implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3742111454122472424L;
	
	private DatasourceconfigPO datasourceconfig;
	
	private List<QueryparameterPO> paramList;

	@Override
	public String toString() {
		return ObjReflect.toString(this);
	}
	
	public List<QueryparameterPO> getParamList() {
		return paramList;
	}

	public void setParamList(List<QueryparameterPO> paramList) {
		this.paramList = paramList;
	}

	public DatasourceconfigPO getDatasourceconfig() {
		return datasourceconfig;
	}

	public void setDatasourceconfig(DatasourceconfigPO datasourceconfig) {
		this.datasourceconfig = datasourceconfig;
	} 
}
