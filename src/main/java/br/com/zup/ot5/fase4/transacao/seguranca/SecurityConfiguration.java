package br.com.zup.ot5.fase4.transacao.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests( authorizeRequest ->
                authorizeRequest
                        .antMatchers(HttpMethod.GET, "/transacoes/**").hasAuthority("SCOPE_transacoes:leitura")
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_cartoes:leitura")
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
