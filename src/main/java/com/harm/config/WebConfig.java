package com.harm.config;

import com.harm.intercepter.FristInterceptor;
import com.harm.intercepter.SecondInterceptor;
import com.harm.intercepter.VisitTimeIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitTimeIntercepter());
        registry.addInterceptor(new FristInterceptor()).order(0);
        registry.addInterceptor(new SecondInterceptor()).order(-1).addPathPatterns("/hello2/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .resourceChain(true)

        ;
//            .resourceChain(true) //캐시를 사용할지 말지?
//            .addTransformer() //응답으로 보낼 리소스를 수정
//            .addResolver() //요청에 해당하는 리소스를 찾는 전략

    }

//    포메터는 이렇게 추가한다. 그럼 기본 포메터들은 사용하지 못할까?
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //여기에서 추가하면 기본적으로 등록되는 컨버터들을 사용하지 못함
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //추가하려면 여기서하는데 거이안쓸것임.
//    }

//    이건뭘까? spring boot 에는 기본으로 들어가 있다. spring mvc 에서 사용.
//    @Bean
//    public Jaxb2Marshaller jaxb2Marshaller() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setPackagesToScan(Animal.class.getPackage().getName());
//        return jaxb2Marshaller;
//    }
}
