package cn.bug.dingding.demo01.controller;

import cn.bug.dingding.demo01.utils.AccessTokenUtil;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author coding-bug
 * date 2022/1/20 14:30
 */
@Controller
public class TestController {

    @GetMapping("/")
    public String index() {
        return "test";
    }


    @GetMapping("getToken")
    @ResponseBody
    public Object getToken() throws Exception {
        List<String> args = List.of("111");
        com.aliyun.dingtalkoauth2_1_0.Client client = createClient();
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey("dingpsvxqmnyfpufaffb")
                .setAppSecret("tl7PTolvP1CJCtDX--w6atE6ogyWaIDSqmQCGKFf7dpwmeWSxW163RHgBvlKVL1N");
        try {
            GetAccessTokenResponse response = client.getAccessToken(getAccessTokenRequest);
            return response;
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }

        return null;
    }

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    /**
     * @param requestAuthCode
     * @return ServiceResult
     * 2020-11-3
     */
    @ResponseBody
    @RequestMapping(value = "/dingLogin", method = RequestMethod.POST)
    public Object login(@RequestParam("code") String requestAuthCode) throws Exception {

        // 获取access_token，注意正式代码要有异常流处理
        String access_token = AccessTokenUtil.getToken();
        // 获取用户信息
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, access_token);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("userId", userId);
        return returnMap;
    }

}
