package apiTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ljt.openapi.demo.Client;
import com.ljt.openapi.demo.Request;
import com.ljt.openapi.demo.Response;
import com.ljt.openapi.demo.constant.Constants;
import com.ljt.openapi.demo.constant.ContentType;
import com.ljt.openapi.demo.constant.HttpHeader;
import com.ljt.openapi.demo.constant.HttpSchema;
import com.ljt.openapi.demo.constant.SystemHeader;
import com.ljt.openapi.demo.enums.ApiHost;
import com.ljt.openapi.demo.enums.Method;
import com.ljt.openapi.demo.util.AESUtil;
import com.ljt.openapi.demo.util.MessageDigestUtil;
import com.ljt.openapi.demo.vo.LoanCol;
import com.ljt.openapi.demo.vo.LoanContact;
import com.ljt.openapi.demo.vo.LoanEmplymt;
import com.ljt.openapi.demo.vo.LoanFac;
import com.ljt.openapi.demo.vo.LoanIndv;
import com.ljt.openapi.demo.vo.LoanParams;

/**
 * 
 * @Project : dcms-openapi-demo
 * @Program Name : com.ljt.openapi.demo.demos.CreditAppDemo.java
 * @Description : 推贷申请创建demo
 * @Author : bingo刑天
 * @Creation Date : 2016年12月28日 下午4:47:42
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 *                      bingo刑天 2016年12月28日 create
 */
public class CreatedAppTest {
  private static String aesKey = "";
  private static String appKey = "";
  private static String appSecret = "";

  /**
   * 
   * @Description : 正例测试创建推贷申请
   * @throws Exception
   * @return : void
   * @Creation Date : 2017年1月3日 下午4:09:28
   * @Author : bingo刑天
   */
  @Test
  public void createCsAppTest() throws Exception {
    String requestBody = loanCifIsBizEntity();
    String method = "loan_app:app:create";
    Request request = new Request();
    request.setMethod(Method.POST_STRING);
    request.setHost(HttpSchema.HTTPS + ApiHost.DEV_API_HOST.getHost());
    request.setPath("/v1/gateway/" + method);
    request.setAppKey(appKey);
    request.setAppSecret(appSecret);
    request.setTimeout(Constants.DEFAULT_TIMEOUT);
    Map<String, String> headers = new HashMap<>();
    headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
    // （必填）根据期望的Response内容类型设置
    headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
    // Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
    requestBody = AESUtil.encrypt(aesKey, requestBody);
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(requestBody));
    // （POST/PUT请求必选）请求Body内容格式
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT);
    request.setHeaders(headers);
    request.setStringBody(requestBody);
    Response response = Client.execute(request);
    System.out.println(response.getStatusCode());
  }

  /**
   * 
   * @Description : 反例测试无产品密钥
   * @throws Exception 参数错误异常
   * @return : void
   * @Creation Date : 2017年1月3日 下午2:15:46
   * @Author : bingo刑天
   */
  @Test(expected = IllegalArgumentException.class)
  public void createCsAppTest1() throws Exception {
    String requestBody = loanCifIsNotBizEntity();
    String method = "loan_app:app:create";
    String aesKey = UUID.randomUUID().toString();
    Request request = new Request();
    request.setMethod(Method.POST_STRING);
    request.setHost(HttpSchema.HTTPS + ApiHost.DEV_API_HOST.getHost());
    request.setPath("/v1/gateway/" + method);
    request.setAppKey(UUID.randomUUID().toString());
    request.setAppSecret(null);
    request.setTimeout(Constants.DEFAULT_TIMEOUT);
    Map<String, String> headers = new HashMap<>();
    headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
    // （必填）根据期望的Response内容类型设置
    headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
    // Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
    requestBody = AESUtil.encrypt(aesKey, requestBody);
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(requestBody));
    // （POST/PUT请求必选）请求Body内容格式
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT);
    request.setHeaders(headers);
    request.setStringBody(requestBody);
    Client.execute(request);
  }

  /**
   * 
   * @Description : 反例测试aes密钥为空
   * @throws Exception
   * @return : void
   * @Creation Date : 2017年1月3日 下午2:17:32
   * @Author : bingo刑天
   */
  @Test(expected = NullPointerException.class)
  public void createCsAppTest2() throws Exception {
    String requestBody = loanCifIsBizEntity();
    String method = "loan_app:app:create";
    String aesKey = null;
    Request request = new Request();
    request.setMethod(Method.POST_STRING);
    request.setHost(HttpSchema.HTTP + ApiHost.DEV_API_HOST.getHost());
    request.setPath("/v1/gateway/" + method);
    request.setAppKey(UUID.randomUUID().toString());
    request.setAppSecret(UUID.randomUUID().toString());
    request.setTimeout(Constants.DEFAULT_TIMEOUT);
    Map<String, String> headers = new HashMap<>();
    headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
    // （必填）根据期望的Response内容类型设置
    headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
    // Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
    requestBody = AESUtil.encrypt(aesKey, requestBody);
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(requestBody));
    // （POST/PUT请求必选）请求Body内容格式
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT);
    request.setHeaders(headers);
    request.setStringBody(requestBody);
    Client.execute(request);
  }

  /**
   * 
   * @Description : 反例测试aes密钥格式错误
   * @throws Exception
   * @return : void
   * @Creation Date : 2017年1月3日 下午2:18:17
   * @Author : bingo刑天
   */
  @Test(expected = IllegalArgumentException.class)
  public void createCsAppTest3() throws Exception {
    String requestBody = loanCifIsBizEntity();
    String method = "loan_app:app:create";
    String aesKey = UUID.randomUUID().toString();
    Request request = new Request();
    request.setMethod(Method.POST_STRING);
    request.setHost(HttpSchema.HTTP + ApiHost.DEV_API_HOST.getHost());
    request.setPath("/v1/gateway/" + method);
    request.setAppKey(UUID.randomUUID().toString());
    request.setAppSecret(UUID.randomUUID().toString());
    request.setTimeout(Constants.DEFAULT_TIMEOUT);
    Map<String, String> headers = new HashMap<>();
    headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
    // （必填）根据期望的Response内容类型设置
    headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
    // Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
    requestBody = AESUtil.encrypt(aesKey, requestBody);
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(requestBody));
    // （POST/PUT请求必选）请求Body内容格式
    headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT);
    request.setHeaders(headers);
    request.setStringBody(requestBody);
    Client.execute(request);
  }


  /**
   * 
   * @Description : 组装个人私营业主参数
   * @return : String
   * @Creation Date : 2016年12月28日 下午3:53:48
   * @Author : bingo刑天
   */
  @SuppressWarnings("static-access")
  private String loanCifIsBizEntity() {
    LoanParams loanParams = new LoanParams();
    loanParams.setNm("个人私营");
    loanParams.setIdNo(UUID.randomUUID().toString());
    loanParams.setMtCityCd("010");
    loanParams.setDtIssue(new Date());
    loanParams.setMtMaritalStsCd("2");
    loanParams.setDtExpiry(new Date());
    loanParams.setMtGenderCd("M");
    loanParams.setMtEduLvlCd("001");
    loanParams.setMtResidenceStsCd("001");
    loanParams.setIsFamily("Y");
    loanParams.setMtJobSectorCd("21");
    loanParams.setMonthlyIncAmt(new BigDecimal("100000"));
    loanParams.setEmail("123@qq.com");
    loanParams.setMobileNo("18888888888");
    loanParams.setMtIndvMobileUsageStsCd("32");
    loanParams.setQq(12345);
    loanParams.setWeChat("1234");
    loanParams.setBankCard(new BigDecimal("1234556565"));
    loanParams.setCreditCardLines(new BigDecimal("12345"));
    loanParams.setLoanFixedYear(new BigDecimal("11"));
    loanParams.setIsBizEntity("Y");
    loanParams.setIsComb("Y");
    loanParams.setIndv(loanIndvParam());
    loanParams.setContacts(loanContactParam());
    loanParams.setFac(loanFacParam());
    loanParams.setColls(loanColParam());
    JSONObject jsob = new JSONObject();
    return jsob.toJSONString(loanParams, SerializerFeature.WriteDateUseDateFormat);

  }

  /**
   * 
   * @Description : 组装个人非私营业主参数
   * @return : String
   * @Creation Date : 2016年12月28日 下午3:47:46
   * @Author : bingo刑天
   */
  @SuppressWarnings("static-access")
  private String loanCifIsNotBizEntity() {
    LoanParams loanParams = new LoanParams();
    loanParams.setNm("个人非私营");
    loanParams.setIdNo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18));
    loanParams.setMtCityCd("010");
    loanParams.setDtIssue(new Date());
    loanParams.setMtMaritalStsCd("2");
    loanParams.setDtExpiry(new Date());
    loanParams.setMtGenderCd("M");
    loanParams.setMtEduLvlCd("001");
    loanParams.setMtResidenceStsCd("001");
    loanParams.setIsFamily("Y");
    loanParams.setMtJobSectorCd("21");
    loanParams.setMonthlyIncAmt(new BigDecimal("100000"));
    loanParams.setEmail("123@qq.com");
    loanParams.setMobileNo("18888888888");
    loanParams.setMtIndvMobileUsageStsCd("32");
    loanParams.setQq(12345);
    loanParams.setWeChat("1234");
    loanParams.setBankCard(new BigDecimal("1234556565"));
    loanParams.setCreditCardLines(new BigDecimal("12345"));
    loanParams.setLoanFixedYear(new BigDecimal("11"));
    loanParams.setIsBizEntity("N");
    loanParams.setEmplymt(loanEmpParam());
    loanParams.setContacts(loanContactParam());
    loanParams.setFac(loanFacParam());
    loanParams.setColls(loanColParam());
    JSONObject jsob = new JSONObject();
    return jsob.toJSONString(loanParams, SerializerFeature.WriteDateUseDateFormat);
  }

  /**
   * 
   * @Description : 业务信息参数
   * @return : LoanFac
   * @Creation Date : 2016年12月28日 下午3:16:22
   * @Author : bingo刑天
   */
  private LoanFac loanFacParam() {
    LoanFac loanFac = new LoanFac();
    loanFac.setIsRevolvingAllowed("Y");
    loanFac.setLmtAppr(new BigDecimal("123"));
    loanFac.setIntRate(new BigDecimal("123"));
    loanFac.setMtRepymtTypCd("44");
    loanFac.setTenureAppr(new BigDecimal("133"));
    loanFac.setDtMaturity(new Date());
    loanFac.setMtFacCd("1104");
    List<String> loanFacPurCds = new ArrayList<>();
    loanFacPurCds.add("1132");
    loanFacPurCds.add("2134");
    loanFac.setMtFacPurCds(loanFacPurCds);
    return loanFac;
  }

  /**
   * 
   * @Description : 职位信息参数
   * @return : LoanEmplymt
   * @Creation Date : 2016年12月28日 下午3:16:45
   * @Author : bingo刑天
   */
  private LoanEmplymt loanEmpParam() {
    LoanEmplymt loanEmplymt = new LoanEmplymt();
    loanEmplymt.setPrevServiceMth(1);
    loanEmplymt.setPrevServiceYr(2);
    loanEmplymt.setMtPosHeldCd("123");
    loanEmplymt.setEmployerNm("12345");
    loanEmplymt.setDtWorkInCurrIndustry(new Date());
    return loanEmplymt;
  }

  /**
   * 
   * @Description : 联系人信息参数
   * @return : List<LoanContact>
   * @Creation Date : 2016年12月28日 下午3:16:57
   * @Author : bingo刑天
   */
  private List<LoanContact> loanContactParam() {
    List<LoanContact> contacts = new ArrayList<>();
    LoanContact loanContact = new LoanContact();
    loanContact.setEmail("1233@qq.com");
    loanContact.setMobileNo("13333333333");
    loanContact.setNm("联系人");
    loanContact.setMtCifContactCd("001");
    loanContact.setMtPosHeldCd("123");
    contacts.add(loanContact);
    return contacts;
  }

  /**
   * 
   * @Description : 担保品信息参数
   * @return : List<LoanCol>
   * @Creation Date : 2016年12月28日 下午3:17:14
   * @Author : bingo刑天
   */
  private List<LoanCol> loanColParam() {
    LoanCol loanCol = new LoanCol();
    loanCol.setMtCollTypCd("1102");
    loanCol.setMtCollTypeCd("11022");
    loanCol.setCollOwner("所有人");
    loanCol.setCollValue("111111");
    loanCol.setIsDeposit("N");
    List<LoanCol> loanCols = new ArrayList<>();
    loanCols.add(loanCol);
    return loanCols;
  }

  /**
   * 
   * @Description :私营业主信息
   * @return : LoanIndv
   * @Creation Date : 2016年12月28日 下午3:30:33
   * @Author : bingo刑天
   */
  private LoanIndv loanIndvParam() {
    LoanIndv loanIndv = new LoanIndv();
    loanIndv.setBizAddr("北京");
    loanIndv.setBizArea("电子商务");
    loanIndv.setBizRegNo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18));
    loanIndv.setCurrentTotal(new BigDecimal("123"));
    loanIndv.setElectricityDosage(new BigDecimal("123"));
    loanIndv.setRatal(new BigDecimal("123"));
    loanIndv.setSocialSecurity(new BigDecimal("123"));
    loanIndv.setEquityLine(new BigDecimal("123"));
    loanIndv.setSalaryTotal(new BigDecimal("123"));
    loanIndv.setIsLegalRep("Y");
    loanIndv.setMtJobSectorCd("102");
    loanIndv.setEmployees("22");
    loanIndv.setWaterDosage(new BigDecimal("123"));
    return loanIndv;
  }
}