package mblog.wechat.service;

import mblog.wechat.utill.Constants;
import mblog.wechat.utill.HttpClientUtil;
import mblog.wechat.utill.JsonUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.text.MessageFormat;

import java.io.IOException;


/**
 * Created by Netuser on 2016-5-9.
 * 微信消息推送服务
 */
public class MsgPushService {
    private String TokenUrl = Constants.GET_ACCESSTOKEN_URL;
    private String UserInfoUrl = Constants.GET_USERINFO_URL;
    private String MsgPushUrl = Constants.MSG_PUSH_URL;
    private String UserListUrl = Constants.GET_USERLIST_URL;
    private String UploadingUrl = Constants.UP_LOADING_URL;
    private static Logger logger = LoggerFactory.getLogger(MsgPushService.class);


    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-10 16:14
     * @Desc 获取授权token
     */

    public String getAccessToken(String appid,String secret) throws IOException {
        String accessToken = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(TokenUrl,appid,secret);
            httpClient = HttpClientUtil.getHttpClient();
            String response = HttpClientUtil.executeHttpGet(url,httpClient);
            accessToken = JsonUtils.read(response,"access_token");
        }catch (Exception e){
            logger.error("获取授权token出错,请检查参数",e);
        }finally {
            httpClient.close();//使用完后关闭httpclient
        }
        return accessToken;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 15:38
     * @Desc 获取关注用户列表数据
     */

    public String getUserListJson(String accessToken) throws IOException {
        String userListJson = null;
        CloseableHttpClient httpClient = null;
        try {
            String url = MessageFormat.format(UserListUrl,accessToken);
            httpClient = HttpClientUtil.getHttpClient();
            String response = HttpClientUtil.executeHttpGet(url,httpClient);
            userListJson = JsonUtils.read(response,"data");
            userListJson = JsonUtils.read(userListJson,"openid");
        }catch (Exception e){
            logger.error("获取关注用户列表数据出错,请检查参数",e);
        }finally {
            httpClient.close();
        }
        return userListJson;
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2016-5-11 18:02
     * @Desc 获取图片上传后的mediaID
     */
    public String getMediaId(String accessToken){
        String meidaId = null;



        return meidaId;
    }

}