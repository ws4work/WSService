package personal.ws.myservice.sniper.baidu.config;


import personal.ws.util.http.CharTransUtil;

import java.io.File;

public class Configuration {

	//贴吧url
	public static final String				CONNECT_URL				= "http://tieba.baidu.com";
	//贴吧名
	public static final String				CONNECT_BARNAME			= CharTransUtil.charTransMethod("dnf");
	//贴吧url后缀
	public static final String				CONNECT_PACKAGE			= CharTransUtil.charTransMethod("/f?kw=" + CONNECT_BARNAME);
	//标题搜索词
	public static String					TITLE_SEARCH_KEY		= "";
	public static int						titlePageNum			= 10;
	//贴吧搜寻页数
	//帖子内搜索词
	public static String					TOPIC_SEARCH_KEY		= "";
	//贴子内搜寻页数
	public static int						topicPageNum			= 1;
	//程序模式: prd 正式       test 测试
	public static final String				MODE					= "test";
	//输出txt模式:  open 开启      close 关闭
	public static final String				PRINT_MODE				= "open";
	//MongoIP
	public static final String				MONGO_IP				= "192.168.1.224";
	//MongoPort
	public static final int					MONGO_PORT				= 27017;
	//MongoSave
	public static final String				DBSAVE					= "open";
	//DownLoad Resourse
	public static final String				RESOURSE_IMG_DOWNLOAD	= "open";
	//DownLoad Path
	public static final String				DOWNLOAD_PATH			= "E:" + File.separator + "imgtest" + File.separator;
}
