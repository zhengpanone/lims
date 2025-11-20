package com.zp.lims.sys.filter;

import com.zp.lims.common.constant.SecurityConst;
import com.zp.lims.sys.config.JwtTokenProvider;
import com.zp.lims.sys.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 14:49
 * Version : v1.0.0
 * Description: JWT过滤器
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
            String tokenType = jwtTokenProvider.getTokenType(jwt);
            if ("accessToken".equals(tokenType)) {
                String username = jwtTokenProvider.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConst.HEADER_STRING);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConst.TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;

    }
}
