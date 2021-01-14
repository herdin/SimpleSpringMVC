package com.harm.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//    HandlerIntercepter:
//            핸들러매핑에 설정할 수 있는 인터셉터.
//            핸들러 실행 전/후/완료에서 부가작업을 하고 싶을 때.
//            여러핸들러에서 반복적으로 사용하는 코드를 줄이고 싶을 때.
//            preHandle(request, response, handler):
//            핸들러 실행 전 호출.
//            핸들러에 대한 정보가 넘어옴.
//            요청/응답을 다음 핸들러로 전달할지 여부를 리턴값으로 사용.
//            요청처리
//            postHandle(request, response, modelAndView):
//            핸들러 실행 후 호출.
//            뷰에 전달할 추가적 모델작업.
//            비동기 요청 시 호출되지 않음
//            렌더링(레스트컨트롤러는 렌더링이 없음)
//            afterCompletion
//            요청처리가 완전히 끝난 뒤 호출.
//            preHandler 에서 true 리턴한 경우 호출
//            비동기 요청 시 호출되지 않음
public class FristInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(FristInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("pre 1");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("post 1");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("after 1");
    }
}
