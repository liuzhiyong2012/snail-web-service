package com.snail.web.common;

/*@EnableWebMvc
@Configuration*/
public class WebMvcConfig {// extends WebMvcConfigurerAdapter

    /*@Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();

        List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        MediaType textMedia = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        supportedMediaTypes.add(textMedia);
        MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
        supportedMediaTypes.add(jsonMedia);jsonConverter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(jsonConverter);
        adapter.setMessageConverters(converters);

        return adapter;
    }*/
}
