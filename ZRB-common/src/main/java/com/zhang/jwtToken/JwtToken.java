package com.zhang.jwtToken;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.util.Assert;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: diexi
 * @Date: 2022/3/26 22:39
 * @ClassName: JwtToken
 */
public class JwtToken {

    //唯一标识,签名
    private static final String SIGNATURE = "QIYU";

    //失效时间   一天
    private static final long EXPIRE = 1000 * 60 * 60 * 24 ;

    /**
     * 生成token
     * Header
     * Payload
     * Signature
     * @param userName
     */
//    public void createJToken(String userName){
//        JSONObject header = new JSONObject();
//        //Header
//        header.put("alg","HS256");
//        header.put("typ","Jwt");
//        //Payload
//        JSONObject payload = new JSONObject();
//        payload.put("userName",userName);
//        //这里还要有一个角色权限或者用户标识,暂时先不做，需要查询数据库
//
//        //Payload
//        //对payload和签名一起加密作为token的第三组成部分Signature
//        String signature = DigestUtils.md5Hex(payload.toJSONString()+SIGNATURE);
//        String jwt =  Base64.Encoder.encode(header);
//    }

    public static String createJToken(String userName){
        String token  = null;
        Map<String,Object> header = new HashMap<>();
        header.put("alg","HS256");
        header.put("typ","Jwt");
        Map<String,Object> user = new HashMap<>();
        user.put("userName",userName);
        //签名算法
        Algorithm algorithm = Algorithm.HMAC256(SIGNATURE);

        token= JWT.create()
                //设置头部信息
                .withHeader(header)
                //设置 载荷信息
                .withClaim("user",user)
                //设置载荷 签名是谁生成的
                .withIssuer("Server")
                //设置载荷 签名的观众  也可以理解为谁接受
                .withAudience("Client")
                //设置载荷 该jwt在某一时刻之前都不可以使用
                //.withNotBefore(new Date())
                //设置载荷 jwt生成时间
                .withIssuedAt(new Date())
                //设置载荷 jwt过期时间
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE))
                //主题
                .withSubject("this is my token")
                .withAudience(userName)
                .sign(algorithm);
        Assert.isTrue(token.length() > 0,"Failed to generate token ");
        return token;
    }


    public static void main(String[] args) {
        final String admin = createJToken("admin");
        System.out.println(admin);
    }
}
