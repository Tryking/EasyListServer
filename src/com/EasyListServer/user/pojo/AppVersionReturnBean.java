package com.EasyListServer.user.pojo;

/**
 * Created by 26011 on 2016/8/8.
 */
public class AppVersionReturnBean extends BaseBean{
    private String appName;
    private String appVersionNum;
    private String appDownloadPath;
    private String appDescribe;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersionNum() {
        return appVersionNum;
    }

    public void setAppVersionNum(String appVersionNum) {
        this.appVersionNum = appVersionNum;
    }

    public String getAppDownloadPath() {
        return appDownloadPath;
    }

    public void setAppDownloadPath(String appDownloadPath) {
        this.appDownloadPath = appDownloadPath;
    }

    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }
}
