package com.EasyListServer.user.pojo;

/**
 * 修改完数据返回的bean
 * @author Administrator
 *
 */
public class ChangeDataReturnBean extends BaseBean {
    private boolean isAmendSuccess;

	public boolean isAmendSuccess() {
		return isAmendSuccess;
	}

	public void setAmendSuccess(boolean isAmendSuccess) {
		this.isAmendSuccess = isAmendSuccess;
	}
}
