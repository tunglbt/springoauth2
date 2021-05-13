package com.tunglbt.springoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final String clientId = "tunglbt";
    private final String clientSecret = "tunglbt-secret-key";
    private final String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAyZQj58Enz34Sj7CpASWX4z0sI9eZOPsTUHBi3qjdb2rYV8Vp\n" +
            "SKii4PWzTOHZEXDR4kT/brkE+h0q7KFzz3dj02XeoTYh2LZzyahCj5tDgg7PiaV+\n" +
            "ifo70IVMVd0MGicL7uKAuuitsB8QD/idtl8bra9YcwStJ/uaaLO94w+uSt/ugO7S\n" +
            "qjgTQUK6OdoI5xqDWYJdoAYf0eVqhgqV5jrpjyUeBmdtBX92r1RvF6/7jj6aknXu\n" +
            "UFFDgHjlSXKOCrNhKpMXFUrYJWvIo89mscUXPtCCli+08Qjv4ufm7o+M/9DPen6a\n" +
            "9uNmzBmWyteMsDTi33oSg2BZUEI3xhbfPxiv+wIDAQABAoIBADLmwo5SGyBtIHjC\n" +
            "TlroyErsdonydlGKLR1h08WP9KKe41R+mBBt7PtLb+RZH3wk9griC3JFLDmQRfeM\n" +
            "6XaZGk8WaTpiNffIUsK4R16GAW0FGmkSrjpfQt1r25PPBrBP7dZ7wa/w83MG1QKD\n" +
            "cCfOKmoYXghlROhUw2/u+2Mr4YIsSHhlPQWtyFMmdl0k4SFQ2UvGHekDisP0vLbe\n" +
            "bHkyXuq/Iec4xewneFsO1VII65wwHaETrR3BiBVC6J7Fi9+U/CFOW1YwyEH/iJ+P\n" +
            "SmxA9JjZ0evzRFZcsZA29U6cqJw5Pyz4xv2roRbrABTKDi0+Iw20IdzFPFodQikH\n" +
            "nVyafqkCgYEA+1YuqEsvcQeK5AH/I8au6GrPsGuxEn0Y9gr5M6gFROZLIbZ2hFmP\n" +
            "lcWfZf8nhn1OhusnheDbh8DHiZ8TsdKA62TDgeYbgwFAVlAaYbuvqbWIG/2Vf8GD\n" +
            "iXTscph04qxbVCJlAeeB6vIHB8rYmwRUk/9ZfrGg3QscTjXdEgStpu0CgYEAzVGd\n" +
            "IwJYYJ8fQYjBWeKfA/F53PC9gOzKlpymQRFCJ/HpbeWwIxWh0CLY9vPWGk5+eUSP\n" +
            "wyvNkfBQCweJ/m94hkoQqtE5GikbjNMFInvLMYTKUJJNsU0FOS7/vy7b+wvSvVEj\n" +
            "uxXeRxPxAYGgMhSg/PAC4hxK6FUmN62uwiImLYcCgYBvLH706FTy3VA9bPirnY3u\n" +
            "tphpxCkOMZGh+9ahV9gE0w02u1wPTQJrUIAigMu9/J2IpmnUGVNh6A8bO+ddR+zI\n" +
            "ssicI+nVd+86frI8uuWH3wl2wCOp4DJL0hFo2zEQtwaq34/oWLwp7abYdox7fV3g\n" +
            "HRo8JVwvimOZ4M+J0dHyPQKBgAT8ATuTV//ofQLDRiZfeQrIdvPlKEST+wsS/HpB\n" +
            "6AA+QDN5yv/fC5GY9vlvy/fRhgJ9ppvPWWs8kUTjRvdVT3C7JZJwB9nZu6B8kvhW\n" +
            "VwV5usuf8aDnRMQxnuXORmrey5vq91oYWtAQ7N3jfrcYhxv826UPmMXxIz9gyQhP\n" +
            "eevLAoGBAJF1kPJdQNRvCa5UiwUznv/goPZoDfqxg8zVzuNlChfs1K2rvEiZARIH\n" +
            "sD0jSwXmPbFn01fdomHtEGchRS413yFVyWdl/bGDofihKnLScNOouOd/jOxJr2/e\n" +
            "xAVDv1pxjKAvfh0OZ1R/TQu1B5rt38S6o6eKz4CRP2dCWxKjwJz8\n" +
            "-----END RSA PRIVATE KEY-----";
    private final String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyZQj58Enz34Sj7CpASWX\n" +
            "4z0sI9eZOPsTUHBi3qjdb2rYV8VpSKii4PWzTOHZEXDR4kT/brkE+h0q7KFzz3dj\n" +
            "02XeoTYh2LZzyahCj5tDgg7PiaV+ifo70IVMVd0MGicL7uKAuuitsB8QD/idtl8b\n" +
            "ra9YcwStJ/uaaLO94w+uSt/ugO7SqjgTQUK6OdoI5xqDWYJdoAYf0eVqhgqV5jrp\n" +
            "jyUeBmdtBX92r1RvF6/7jj6aknXuUFFDgHjlSXKOCrNhKpMXFUrYJWvIo89mscUX\n" +
            "PtCCli+08Qjv4ufm7o+M/9DPen6a9uNmzBmWyteMsDTi33oSg2BZUEI3xhbfPxiv\n" +
            "+wIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }

}
