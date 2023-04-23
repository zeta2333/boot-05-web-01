package usts.pycro.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;
import usts.pycro.boot.bean.Pet;
import usts.pycro.boot.converter.PycroMessageConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            /**
             * 自定义内容协商策略
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //Map<String, MediaType> mediaTypes
                Map<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("json", MediaType.APPLICATION_JSON);
                mediaTypes.put("xml", MediaType.APPLICATION_XML);
                mediaTypes.put("pycro", MediaType.parseMediaType("application/x-pycro"));
                //指定支持解析哪些参数对应的哪些媒体类型
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                //自定义参数名（默认为format）
                parameterStrategy.setParameterName("fuck");
                //请求头内容协商策略
                HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterStrategy, headerStrategy));
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new PycroMessageConverter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //不移除";"后面的内容，矩阵变量功能就可以生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                //自定义转换器Converter：String --> Pet
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        if (StringUtils.isEmpty(source)) return null;
                        Pet pet = new Pet();
                        String[] split = source.split(",");
                        pet.setName(split[0]);
                        pet.setAge(Integer.parseInt(split[1]));
                        return pet;
                    }
                });
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
