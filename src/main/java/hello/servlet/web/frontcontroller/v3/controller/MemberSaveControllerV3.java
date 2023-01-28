package hello.servlet.web.frontcontroller.v3.controller;
import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;
public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

   // 파람맵에다가 get파라미터해서 꺼내는 것이 아니라 map에다가 프론트 컨트롤러가 다 써서 보내주면 여기서 꺼내서 쓰면 된다

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username"); //파라미터에 값넣기
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);//member객체에 값 저장
        ModelView mv = new ModelView("save-result");// 논리 이름 저장
        mv.getModel().put("member", member);//모델에 객체 넣기
        return mv;
    }
}