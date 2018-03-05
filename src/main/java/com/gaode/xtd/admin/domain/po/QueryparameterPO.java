package com.gaode.xtd.admin.domain.po;

import java.io.Serializable;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * queryparameter 表对应的PO
 * @author andyc
 * 2018-3-2
 */
public class QueryparameterPO implements Serializable {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 6946839933118615271L;

	private Integer pid;

    private String pname;

    private String ptype;

    private String pdirection;

    private Integer mid;

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