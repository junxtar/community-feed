package kr.amc.amis.common.principal;

import kr.amc.amis.auth.domain.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider provider;

    public AuthPrincipalArgumentResolver(TokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        try {
            String authToken = webRequest.getHeader("Authorization");

            if (authToken == null || authToken.split(" ").length != 2) {
                throw new IllegalArgumentException("");
            }
            String token = authToken.split(" ")[1];
            Long userId = provider.getUserId(token);
            String userRole = provider.getUserRole(token);

            return new UserPrincipal(userId, userRole);
        } catch (Exception e) {
            throw new IllegalArgumentException("올바르지 않은 token 입니다.");
        }
    }
}
