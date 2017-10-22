package com.tc.tcbase.constants;

/**
 * author：   tc
 * date：     2015/9/11 11:50
 * version    1.0
 * description 系统常量类,用于存放一些路径
 * modify by
 */
public interface BaseConstants {
    /*------------------------------------------ app文件存储模块  ------------------------------------------*/

    String PARAM_APP_CATALOG = "tomtop";      //应用存储根目录
    String APP_IMAGE = "image";               //应用图片存储目录名
    String APP_THUMBNAIL = "thumbnail";       //应用图片缩略图存储目录名
    String APP_TMP = "temp";                  //应用临时文件目录名
    String APP_DOWNLOAD = "download";         //应用下载目录名

    /*------------------------------------------ preference存储模块  -------------------------------------*/

    String APP_SHARE = "app_share";                         // 默认preference名字
    String APP_HAND_SHAKE_SHARE = "app_hand_shake_share";   //握手参数表
    String APP_USER_SHARE = "app_user_share";               //用户表
    String APP_CONFIG_SHARE = "app_config_share";           //配置表

    String DOWNLOAD = "Download";
}
