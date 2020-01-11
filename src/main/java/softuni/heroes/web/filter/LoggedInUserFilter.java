package softuni.heroes.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.heroes.service.services.AuthenticationUserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoggedInUserFilter implements Filter {

    private final AuthenticationUserService authenticationUserService;

    @Autowired
    public LoggedInUserFilter(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = this.authenticationUserService.getUsername();
        if (username.equals("anonymousUser")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        session.setAttribute("username", username);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
