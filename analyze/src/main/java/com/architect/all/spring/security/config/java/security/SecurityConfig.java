package com.architect.all.spring.security.config.java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider provider;  //注入我们自己的AuthenticationProvider
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;  //注入陈宫后跳转
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;  //注入失败后跳转配置
    @Autowired
    @Qualifier("myUserDetailService")
    private UserDetailsService userDetailService;
    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource;

    /**
     * 设置登录页面  表单提交路径   失败路径
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
////        super.configure(http);
//        http
//                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login/main").failureUrl("/auth/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }

    /**
     * 只定义  登录成功和   登录失败  处理结果
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
//        //super.configure(http);
//        http
//                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login/main")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailHander)
//                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }

    /**
     * 对个别页面进行授权
     * UserInfo userInfo = new UserInfo("lw", "123456", "ROLE_LW", true, true, true, true);
     * .antMatchers("/auth/lw").hasAnyRole("LW")
     * 主页角色前面不能有 ROLE_
     *
     * @param http 如果需要同时满足多个要求的，不能连写如 ，我们有个URL需要管理员权限也同时要限定IP的话，不能：
     *             .hasRole("ADMIN").hasIPAddress("192.168.1.1"); 
     *             而是需要用access方法    
     *             .access("hasRole('ADMIN') and hasIpAddress('192.168.1.1')");这种。
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
//        //super.configure(http);
//        http
//                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login/main")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailHander)
//                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/index").permitAll()  //这就表示 /index这个页面不需要权限认证，所有人都可以访问
////                .antMatchers("/lw").hasAnyRole("ROLE_LW")
//                .antMatchers("/auth/lw").hasAnyRole("LW")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }

    /**
     * RbacService  自定义权限过滤配置
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
//        //super.configure(http);
//        http
//                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login/main")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailHander)
//                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests()
////                      .antMatchers("/index").permitAll()
////                .antMatchers("/whoim").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST,"/user/*").hasRole("ADMIN")
////                .antMatchers(HttpMethod.GET,"/user/*").hasRole("USER")
//                .anyRequest().access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
//                .and()
//                .csrf().disable();
//    }

    /**
     * 记录用户登录后的权限信息
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);
        http
                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login/main")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .rememberMe()
                .useSecureCookie(false)//此方法禁用cookie
                //        AbstractAuthenticationProcessingFilter  控制记住权限的操作
                .rememberMeParameter("remember-me").userDetailsService(userDetailService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .and()
                .authorizeRequests()
//                      .antMatchers("/index").permitAll()
//                .antMatchers("/whoim").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/user/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/user/*").hasRole("USER")
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
//                .anyRequest().access("@rbacService.hasParcam(request,authentication)")    //只能设置一次，否则会被覆盖掉
                .and()
                .csrf().disable();
//        System.out.println(this.request);
//        System.out.println(this.authentication);

    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new MyJdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);//创建token表

        return tokenRepository;
    }
















//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("123456").roles("ADMIN");
//
//    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        super.configure(auth);
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("USER")
//                .and()
//                .withUser("test").password("test123").roles("ADMIN")
//                .and().
//                passwordEncoder(new MyPasswordEncoder()).
//                withUser("lw").
//                password("lw").roles("ADMIN");
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置静态资源不要拦截
        web.ignoring().antMatchers("/js/**", "/cs/**", "/images/**");
    }
}