package clone.tricount.controller;



import clone.tricount.TricountApiConst;
import clone.tricount.model.LoginRequest;
import clone.tricount.model.Member;
import clone.tricount.model.SignupRequest;
import clone.tricount.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("signup")
    public ResponseEntity<Member> signup(@RequestBody SignupRequest signupRequest) {
        Member member = Member.builder()
                .loginId(signupRequest.getUserId())
                .password(signupRequest.getPassword())
                .name(signupRequest.getName())
                .build();

        return new ResponseEntity<>(memberService.signup(member), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Member> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        Member member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        Cookie cookie = new Cookie(TricountApiConst.LOGIN_MEMBER_COOKIE, String.valueOf(member.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(TricountApiConst.LOGIN_MEMBER_COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
