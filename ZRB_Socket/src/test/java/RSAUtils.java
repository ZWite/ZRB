import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述: rsa utils 支持openssl
 *
 * @author xiawen
 * @create 2023/1/11 9:46
 * @copyright
 */
public class RSAUtils {
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwibeP4PJAR30nVrE3zRVjLK3Wfxa+PByGzAVbjNqx+gmcBULsTJxfGoJzemgvTzW0EbhQ7fODNTcrFW4HU2JfXE0LvHtoQNTVFSic6GHkqhBV+VuK1yALDdJnmgjt72IFCwEyXNGgCO36GQYdJ4lp0XVwOP5kKK1sfDg9Waf/SQIDAQAB";
    public static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALCJt4/g8kBHfSdWsTfNFWMsrdZ/Fr48HIbMBVuM2rH6CZwFQuxMnF8agnN6aC9PNbQRuFDt84M1NysVbgdTYl9cTQu8e2hA1NUVKJzoYeSqEFX5W4rXIAsN0meaCO3vYgULATJc0aAI7foZBh0niWnRdXA4/mQorWx8OD1Zp/9JAgMBAAECgYBo1M7ZNIYGX61VH+jKrxEFPHHaTsutmbqC3UJNNB5l04nDSWiNM40j+HUAp+6YMVw9mswt2q9g+cfR4L7ewMhHw1LBim3qjCrtPeIKn5vVBgQ+KJhbZpF30XZXFRneywLVxEOOaKA0NM0YWt8K/ujiA4YEHFcayKinKzRVi9P6lQJBAPU9xb2pnnH2ILk7uSrIQ/U80JGCU4wjfblCo3iRkwd91g9Z3D9+QHui30mHWVUZkgoj+QK1X+Wvjn65d1Ev/hMCQQC4SFvs8ZtToEaQsxj/zkL2ewSnTdbVBbTMpM/+pg3CNTUc+DkO0SJvS5V6rKykHwtoTc3X41aOKCHxZQ+4TEizAkA2+Gu0gaq0XLklDBALWOxysfkGRLI5fVMXDarawba1PS2YL0pBKSmHDREDyETr4Flt0HZmdwVR1LIaEbR/DETlAkAQ0KlyqWke7FUEBxOU4+FAVGFMUtHzhh3FPpSsC8LdobVC+3NZYsPdm+v70Z2/LAYCcncB6ACR4B+Ywzv3+R35AkEA61/c+IoLQt1A4Sb8YXd8jVrE73HNALKfMTe6jAsleX0O1ae+uvylh7zsALZoT05qWwBQ30EflVKBktpzxkG5hA==";


    // RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int KEYSIZE = 1024;
    private static final int MAX_DECRYPT_BLOCK = KEYSIZE / 8;

    public static void main(String[] args) throws Exception {
		//生成公钥和私钥
//		genKeyPair();
//        String message = "{\"projectId\":\"213123123\",\"projectCode\":\"XXX资建2021257号\",\"projectName\":\"XXX县沙河生态修复工程一期建设项目\",\"projectInfo\":{\"agency\":\"XXX有限公司\",\"bidAmount\":3054606,\"bidDapert\":\"XXX电子科技有限公司\",\"bidMethod\":\"公开招标\",\"budget\":3070000,\"openBidTime\":\"2022-07-01\",\"projectCategory\":\"货物\",\"purchaseDapert\":\"XXX学院\",\"section\":\"第一标段\"},\"fiveInfoVOs\":[{\"agencyCode\":\"914101027694936XXH\",\"agencyContact\":\"王XXX\",\"agencyContactNumber\":\"13213800XXX\",\"agencyName\":\"XXX有限公司\",\"bidAmount\":50117751.02,\"bidDapert\":\"XXX集团有限公司\",\"bidDapertAddress\":\"XX市XX区XX路563号402室\",\"bidDapertCode\":\"913502005684046000\",\"bidDapertContact\":\"翁XX\",\"bidDapertContactNumber\":\"187050005XX\",\"deposit\":100,\"owner\":\"XXX投资有限公司\",\"ownerCode\":\"91410422MA40PGGB6D\",\"ownerContact\":\"申XX\",\"ownerContactNumber\":\"0375-XXXXX\",\"section\":\"第一标段\",\"suppliersNumber\":\"5\"}],\"projectStageInfoVOs\":[{\"approachTime\":\"2022-01-05\",\"bidOpenTime\":\"2022-01-05\",\"depositEndTime\":\"2022-01-05\",\"section\":\"第一标段\",\"submitEndTime\":\"2022-01-05\"}],\"projectBidRecordVOs\":[{\"bidDapert\":\"XXX市政工程有限公司\",\"bidNumber\":\"平公资采XXXX号\",\"section\":\"第一标段\",\"tenderOffer\":50000,\"openBidTime\":\"2022-01-05\"}],\"projectQuestionVOs\":[{\"address\":\"XX市XX北路\",\"contactNumber\":\"136XXXX\",\"contactPerson\":\"王XXX\",\"name\":\"袭XX\",\"openBidTime\":\"YYYY-MM-DD\",\"questionContent\":\"1、招标文件第五章服务要求：备注：1、要求以硬盘形式提交4套。\",\"questionDapert\":\"XXX科技股份有限公司\",\"uniformCode\":\"913101046727346XXY\"}],\"specialisScoreVOs\":[{\"bidDapert\":\"XX市A2建设工程有限公司\",\"isBid\":\"1\",\"section\":\"第一标段\",\"totalScore\":57.96}],\"specialistInfoVOs\":[{\"contact\":\"1369375XXXX\",\"name\":\"张XX\",\"workDapert\":\"XXX建设管理有限公司\"}]}";
        String message = "{\"projectinfo\":{\"agency\":\"党秀浩\",\"bidAmount\":351000,\"bidDapert\":\"河南省安海建筑工程有限公司\",\"bidMethod\":\"竞争性磋商\",\"budget\":392618.37,\"openBidTime\":\"2019-07-02\",\"projectCategory\":\"????\",\"purchaseDapert\":\"4028b2836b0babca016b4afa2a7348a3\",\"section\":\"第一标段\"},\"fiveInfos\":[{\"AGENCYCONTACT\":\"柳国彬\",\"AGENCYCONTACTNUMBER\":\"18135638798\",\"AGENCYNAME\":\"中韵天隆工程集团有限公司\",\"BIDDAPERT\":\"河南省安海建筑工程有限公司\",\"BIDDAPERTADDRESS\":\"河南省平顶山市湛河区锦绣家和31号楼2单元3层308室\",\"BIDDAPERTCONTACT\":\"张方方\",\"BIDDAPERTCONTACTNUMBER\":\"15137598644\",\"DEPOSIT\":5000,\"OWNER\":\"4028b2836b0babca016b4afa2a7348a3\",\"SECTION\":\"第一标段\",\"SUPPLIERSNUMBER\":4},{\"AGENCYCONTACT\":\"柳国彬\",\"AGENCYCONTACTNUMBER\":\"18135638798\",\"AGENCYNAME\":\"中韵天隆工程集团有限公司\",\"BIDDAPERT\":\"河南省安海建筑工程有限公司\",\"BIDDAPERTADDRESS\":\"河南省平顶山市湛河区锦绣家和31号楼2单元3层308室\",\"BIDDAPERTCONTACT\":\"张方方\",\"BIDDAPERTCONTACTNUMBER\":\"15137598644\",\"DEPOSIT\":300,\"OWNER\":\"4028b2836b0babca016b4afa2a7348a3\",\"SECTION\":\"第一标段\",\"SUPPLIERSNUMBER\":4}],\"projectQuestions\":[{}],\"BXH\":\"6b5765f7edbb4323878a3c11f62c608c\",\"PROJECTCODE\":\"平公资采2019566号\",\"PROJECTID\":\"000b6b56609d4512b55d348728b4bc29\",\"projectBidRecords\":[{\"BIDDAPERT\":\"河南暖阳建筑工程有限公司\",\"BIDNUMBER\":\"平公资采2019566号\",\"SECTION\":\"第一标段\"},{\"BIDDAPERT\":\"河南省强基建筑工程有限公司\",\"BIDNUMBER\":\"平公资采2019566号\",\"SECTION\":\"第一标段\"},{\"BIDDAPERT\":\"商丘市国基建筑安装有限公司\",\"BIDNUMBER\":\"平公资采2019566号\",\"SECTION\":\"第一标段\"},{\"BIDDAPERT\":\"河南省安海建筑工程有限公司\",\"BIDNUMBER\":\"平公资采2019566号\",\"SECTION\":\"第一标段\"}],\"specialisScores\":[{\"ZDF\":108.5,\"BIDDAPERT\":\"河南省安海建筑工程有限公司\",\"ISBID\":1,\"SECTION\":\"第一标段\"},{\"ZDF\":107.1,\"BIDDAPERT\":\"河南省强基建筑工程有限公司\",\"ISBID\":0,\"SECTION\":\"第一标段\"},{\"ZDF\":97.8,\"BIDDAPERT\":\"河南暖阳建筑工程有限公司\",\"ISBID\":0,\"SECTION\":\"第一标段\"}],\"projectStageInfos\":[{\"APPROACHTIME\":\"2019-06-17\",\"BIDOPENTIME\":\"2019-07-02\",\"DEPOSITENDTIME\":\"2019-07-01\",\"SECTION\":\"第一标段\",\"SUBMITENDTIME\":\"2019-07-02\"}],\"specialistInfos\":[{\"CONTACT\":\"13837525340\",\"NAME\":\"何丰丽\",\"WORKDAPERT\":\"平煤集团\"},{\"CONTACT\":\"13837513811\",\"NAME\":\"贺晓伟\",\"WORKDAPERT\":\"叶县建设局建设工程质量监督站\"},{\"CONTACT\":\"13903752200\",\"NAME\":\"李巍\",\"WORKDAPERT\":\"平顶山燃气有限责任公司\"}],\"PROJECTNAME\":\"新华区财政局大楼外装修工程\"}";
//        //加密
        String encrypted= encrypt(message,publicKey);
        System.out.println("encrypt:"+encrypted);
//        String encrypted = "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";
        //解密
        String decrypted= decrypt("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\n",privateKey);
        System.out.println("decrypt:"+decrypted);
//        if (decrypted.equals(message)){
//            System.out.println("success....");
//        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        System.out.println("publicKeyString:"+publicKeyString);
        System.out.println("privateKeyString:"+privateKeyString);
    }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //分段加密
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] bytes = str.getBytes();
        int inputLen = bytes.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache = new byte[4];
            if(inputLen - offLen > 117){
                cache = cipher.doFinal(bytes, offLen,117);
            }else{
                cache = cipher.doFinal(bytes, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        bops.close();
        byte[] encryptedData = bops.toByteArray();
        String encodeToString = Base64.encodeBase64String(encryptedData);
        return encodeToString;
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //分段解密
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        byte[] bytes = Base64.decodeBase64(str);
        int inputLen = bytes.length;
        int offLen = 0;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte[] cache = new byte[4];
            if(inputLen - offLen > 128){
                cache = cipher.doFinal(bytes,offLen,128);
            }else{
                cache = cipher.doFinal(bytes,offLen,inputLen - offLen);
            }
            byteArrayOutputStream.write(cache);
            i++;
            offLen = 128 * i;

        }
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return new String(byteArray);
    }
}
