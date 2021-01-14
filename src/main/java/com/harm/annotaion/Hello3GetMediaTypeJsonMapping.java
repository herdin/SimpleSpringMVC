package com.harm.annotaion;

        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
@Inherited
//@Repeatable()
@RequestMapping(method = RequestMethod.GET, value = "/hello3", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseBody
public @interface Hello3GetMediaTypeJsonMapping {
}
