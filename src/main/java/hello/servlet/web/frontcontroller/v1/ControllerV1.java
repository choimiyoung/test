package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

public interface ControllerV1 {


    //서블릿이랑 똑같이 만든다.
    //이것을 가지고 구현을 여러개 할것이다( 인터페이스를 다 구현)
    void process(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException, ServletException;

}
