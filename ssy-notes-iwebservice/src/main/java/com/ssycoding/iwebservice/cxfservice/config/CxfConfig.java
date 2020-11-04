package com.ssycoding.iwebservice.cxfservice.config;

import com.ssycoding.iwebservice.cxfservice.service.MyCXFWebService;
import com.ssycoding.iwebservice.cxfservice.service.MyCXFWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * CXF配置
 */
@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public MyCXFWebService myCXFWebService() {
        return new MyCXFWebServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), myCXFWebService());
        endpoint.publish("/api");
        return endpoint;
    }

}