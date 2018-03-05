package com.gaode.xtd.admin.domain.vo;

import java.io.Serializable;

import com.gaode.xtd.admin.domain.po.QueryparameterPO;
import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * 
 * @author andyc
 *
 */
public class QueryparameterVO implements Serializable  {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 7997407951077254454L;

	private Integer pid;

    private String pname;

    private String ptype;

    private String pdirection;

    private Integer mid;
    
    /**
	 * 转换成PO
	 * @return
	 */
	public QueryparameterPO toPO() {
		QueryparameterPO po = new QueryparameterPO();
		po.setPid(pid);
		po.setPname(pname);
		po.setPtype(ptype);
		po.setPdirection(pdirection);
		po.setMid(mid);
		return po;
	}
	
    @Override
	public String toString() {
		return ObjReflect.toString(this);
	}

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }

    public String getPdirection() {
        return pdirection;
    }

    public void setPdirection(String pdirection) {
        this.pdirection = pdirection == null ? null : pdirection.trim();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}