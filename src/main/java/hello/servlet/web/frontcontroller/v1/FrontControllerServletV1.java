package hello.servlet.web.frontcontroller.v1;


import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//v1에하위에 어떤게 들어와도 , 다 여기가 호출이 된다.
@WebServlet(name ="FrontControllerServletV1" ,urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String ,ControllerV1> controllerMap = new HashMap<>();
//어떤 url 요청이 오면 controller 꺼내서 호출 해

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");


        //클라이언트가 uri 요청이 그 주소를 담고
        String requestURI = request.getRequestURI();
        // cotrollerMap에서 그 해당 주소를 꺼내면 ,해당 컨트롤러가 찾아진다.

        //다형성
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);

    }
}
