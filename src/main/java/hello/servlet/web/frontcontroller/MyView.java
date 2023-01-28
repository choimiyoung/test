package hello.servlet.web.frontcontroller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    public void render(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);//jsp가 setAttribute를 사용해서 값을 넣는다.
    }
    //스프링은 requesr geteattribute를 사용하기에
    //모델에 있는것은 attribute로 바꾼다. 모델에 있는 값을 다 꺼내서 requset에다가 다 넣고
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));//key,value로 해서 여기에 값을 다 담아놓는다.
    }
}