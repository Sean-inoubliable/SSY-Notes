package com.ssycoding.iwebservice.axis2client.webservice;

/**
 * @Comments: SoapXML - 征信2.0 - HTTP请求体
 * @Author: Sean
 * @Date: 2020-03-19 0:03
 */
public class SoapXML {

    String name = "Sean";
    Integer age = 26;
    String motto = "黎明就在眼前！";

    /**
     * @Author: Sean
     * @Date: 2020/3/18 23:55
     * @Description: SoapXML拼接
     * @Param:
     * @Return:
     */
    public StringBuffer credit2SoapXml() {

        StringBuffer xmlContent = new StringBuffer();
        xmlContent.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.axis2client.iws.com/\">");
        xmlContent.append("   <soapenv:Header/>");
        xmlContent.append("   <soapenv:Body>");
        xmlContent.append("      <ser:userMethod>");
        xmlContent.append("         <name>"+ name +"</name>");
        xmlContent.append("         <age>"+ age +"</age>");
        xmlContent.append("         <motto>"+ motto +"</motto>");
        xmlContent.append("      </ser:userMethod>");
        xmlContent.append("   </soapenv:Body>");
        xmlContent.append("</soapenv:Envelope>");

        return xmlContent;
    }
}
