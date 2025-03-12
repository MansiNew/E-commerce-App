package com.ecommerce.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.utility.JwtConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidator extends OncePerRequestFilter {

	private static final String BEARER_PREFIX ="BEARER";

	/*@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt=request.getHeader("Authorization");
		if(jwt!=null) {
			//jwt token ,whenever we jwt token we get in this formate Bearer(extract total 7) char jwt,here jwt means actual jwt token
		jwt=jwt.substring(7);
		try {
		SecretKey key=Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
		Claims claims=Jwts.parserBuilder().setSigningKey(key).build()
				.parseClaimsJws(jwt).getBody();
		//String email=String.valueOf(claims.get(claims.get("email")));
		//String authorities=String.valueOf(claims.get(claims.get("authorities")));
		String email=(String)claims.get("email");
		String authorities=(String)claims.get("authorities");
		List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
		Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auths);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch(Exception e) {
			throw new BadCredentialsException("Invalid JWT token.....");
		}
		
		}
		// TODO Auto-generated method stub
		
	}
*/
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String jwt = request.getHeader("Authorization");
        
        if (jwt != null && jwt.startsWith(BEARER_PREFIX)) {
            jwt = jwt.substring(BEARER_PREFIX.length());
            
            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                        .parseClaimsJws(jwt).getBody();

                String email = (String) claims.get("email");
                String authorities = (String) claims.get("authorities");

                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                logger.error("JWT token validation failed: " + e.getMessage());
                throw new BadCredentialsException("Invalid JWT token");
            }
        }

        filterChain.doFilter(request, response);
    }
	

}
