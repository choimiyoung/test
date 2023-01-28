package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
public class ModelView {
    private String viewName;//뷰 내용
    private Map<String, Object> model = new HashMap<>();//모델 내용(원하는 데이터를 넣으면 jsp에서 쓸수있게 한다)


    //생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}