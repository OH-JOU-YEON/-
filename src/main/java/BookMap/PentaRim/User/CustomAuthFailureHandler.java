package BookMap.PentaRim.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {


    private final String DEFAULT_FAILURE_URL = "/security-login/login?error";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
    throws IOException, ServletException {
        String errorMessage = null;

        if(exception instanceof BadCredentialsException || exception
        instanceof InternalAuthenticationServiceException) {
            errorMessage = "아이디나 비밀번호가 일치하지 않습니다. 다시 확인해주십시오.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요";
        } else {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요";
        }

        request.setAttribute("errorMessage", errorMessage);

        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }

}
