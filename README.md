# Cross-Site Request Forgery (CSRF) implementation Spring boot 3.2.4
This application is build on Spring Boot 3.2.4
1. Create a child class of OncePerRequestFilter

         @Override
         protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
            CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (null!= csrfToken.getHeaderName()){
               response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
            }
            filterChain.doFilter(request,response);
            }
   2. Add the object of the child class bean to the filter chain 

          @Bean
            SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
            CsrfTokenRequestAttributeHandler requestHandler =
            new CsrfTokenRequestAttributeHandler();
            requestHandler.setCsrfRequestAttributeName("_csrf");

           httpSecurity
                   .securityContext((context) -> context
                           .requireExplicitSave(false))
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                   .cors(cors ->cors
                           .configurationSource(corsConfigurationSource()))
                   .csrf(csrf -> csrf
                           .csrfTokenRequestHandler(requestHandler)
                           .ignoringRequestMatchers("/api/v1/contact", "/api/v1/register")
                           .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                   .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                   .authorizeHttpRequests(auth -> auth
                           .requestMatchers("/api/v1/notice",
                                   "/api/v1/contact",
                                   "/api/v1/register")
                           .permitAll()
                           .requestMatchers("/api/v1/myAccount",
                                   "/api/v1/myCards",
                                   "/api/v1/myBalance",
                                   "/api/v1/myLoans",
                                   "/api/v1/user")
                           .authenticated()
                   )
                   .formLogin(Customizer.withDefaults())
                   .httpBasic(Customizer.withDefaults());
           return httpSecurity.build();
      }

https://www.linkedin.com/in/tatekahmed/   
    
