package hello.servlet.web.frontcontroller.v3.controller;
import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.List;
import java.util.Map;
public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {//파라미터 값 넣기
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");//논리 주소 전달
        mv.getModel().put("members", members);//모델에 값넣기
        return mv;
    }
}