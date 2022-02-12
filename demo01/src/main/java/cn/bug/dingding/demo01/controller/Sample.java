// This file is auto-generated, don't edit it. Thanks.
package cn.bug.dingding.demo01.controller;

import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;

public class Sample {

    /**
     * 使用 Token 初始化账号Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dingtalkoauth2_1_0.Client client = Sample.createClient();
        GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()
                .setClientId("dingxxx")
                .setClientSecret("1234")
                .setCode("abcd")
                .setRefreshToken("abcd")
                .setGrantType("authorization_code");
        try {
            client.getUserToken(getUserTokenRequest);
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
    }



}