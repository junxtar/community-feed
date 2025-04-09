package kr.amc.amis.common.config;

import java.util.List;
import kr.amc.amis.auth.domain.TokenProvider;
import kr.amc.amis.common.principal.AuthPrincipalArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }
}
