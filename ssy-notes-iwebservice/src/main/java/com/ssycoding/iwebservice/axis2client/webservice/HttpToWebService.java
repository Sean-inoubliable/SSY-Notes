package com.ssycoding.iwebservice.axis2client.webservice;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLBuilderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @Comments: Main函数通过 HTTP调用WebService
 * @Author: Sean
 * @Date: 2020-03-18 21:24
 */
public class HttpToWebService {

    // WebService的wsdl地址
    public static final String wsdlURL = "http://127.0.0.1:9527/webservice/test?wsdl";

    // 设置编码。（因为是直接传xml,所以需要设置为text/axis2client;charset=utf-8）
    public static final String contentType = "text/axis2client;charset=utf-8";

    public static void main(String[] args) throws IOException {

        // 获取SoapXML的请求体对象
        SoapXML soapXML = new SoapXML();
        // 获取SoapXML的请求体
        StringBuffer soapXml = soapXML.credit2SoapXml();

        // 调用工具类方法，发送HTTP请求
        String responseXML = HttpSendUtil.doHttpPostByHttpClient(wsdlURL, contentType, soapXml.toString());

        // 利用axis2的omElement，将xml转换为omElement
        OMElement omElement = OMXMLBuilderFactory
                .createOMBuilder(
                        new ByteArrayInputStream(responseXML.getBytes()), "utf-8").getDocumentElement();

        // 根据responseXML的数据样式，定位到对应element，然后获得其childElements，遍历
        Iterator<OMElement> it = omElement.getFirstElement().getFirstElement().getFirstElement().getChildElements();
        while (it.hasNext()) {
            OMElement element = it.next();
            System.out.println(element.getLocalName() + " ----- " + element.getText());
        }
    }
}
