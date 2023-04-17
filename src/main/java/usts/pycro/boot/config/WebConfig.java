package usts.pycro.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-11 10:56 PM
 */
@Configuration(proxyBeanMethods = false)
//方法1：实现WebMvcConfigurer并重写实现其中的configurePathMatch方法
public class WebConfig /*implements WebMvcConfigurer*/ {
    //扩展点：如何把_method改成自定义的名称
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }

    //方法2：在容器中添加一个WebMvcConfigurer
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //不移除";"后面的内容，矩阵变量功能就可以生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }

    //@Override
    //public void configurePathMatch(PathMatchConfigurer configurer) {
    //    UrlPathHelper urlPathHelper = new UrlPathHelper();
    //    //不移除";"后面的内容，矩阵变量功能就可以生效
    //    urlPathHelper.setRemoveSemicolonContent(false);
    //    configurer.setUrlPathHelper(urlPathHelper);
    //}
}
