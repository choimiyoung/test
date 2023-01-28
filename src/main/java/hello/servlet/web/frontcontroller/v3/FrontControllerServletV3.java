package hello.servlet.web.frontcontroller.v3;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/frontcontroller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request);//파라미터에서 온 값 뽑기

        ModelView mv = controller.process(paramMap);//파라매터 맵에 값을 다 집어 놓는다
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);//모델 담았던 것을 같이 보내줘야 한다.
    }

    //파라미터 값 뽑아오기
    //파람 맵을 넘겨줘야 한다.requser에서 파람 다 꺼내야 한다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();//파람 맵을 하나 만들고
        request.getParameterNames().asIterator()//모든 파라미터의 이름을 다 가져온다.
                .forEachRemaining(paramName -> paramMap.put(paramName,//값 이름
                        request.getParameter(paramName)));//값을 다 꺼내 줘야 한다.
        return paramMap;
    }

    //view 이름 가져오기
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}