package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter  implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return(handler instanceof ControllerV3);// 컨트롤러 vs를 구현한 인터페이스가 넘어오면 ture 반환
    //프론트 컨트롤러가 supports를 호출해서 그것을 걸러서 그다음에 handle을 호출한다
    }


    // MemberFormControllerV3()
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //핸들러가 오브젝트이다
        ControllerV3 controller = (ControllerV3) handler;//캐스팅

        Map<String,String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);// 꺼낸 값을 보낸다 controller에게

        //어댑터역할이 핸들러를 호출을 해주고  결과가 오면 반환타입을 모델 뷰로 맞춰서 반환하기

        return mv;//모델 뷰를 반환한다.
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();//파람 맵을 하나 만들고
        request.getParameterNames().asIterator()//모든 파라미터의 이름을 다 가져온다.
                .forEachRemaining(paramName -> paramMap.put(paramName,//값 이름
                        request.getParameter(paramName)));//값을 다 꺼내 줘야 한다.
        return paramMap;
    }
}
