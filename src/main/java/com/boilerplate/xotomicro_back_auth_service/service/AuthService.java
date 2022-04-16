package com.boilerplate.xotomicro_back_auth_service.service;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.boilerplate.xotomicro_back_auth_service.dto.AuthDto;
import com.boilerplate.xotomicro_back_auth_service.dto.TokenDto;
import com.boilerplate.xotomicro_back_auth_service.dto.UserDto;
import com.boilerplate.xotomicro_back_auth_service.exception.ServiceException;
import com.boilerplate.xotomicro_back_auth_service.service.downstream.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Value("${jwt.token.expire-in-minutes}")
    private Long tokenExpireTime;

    @Value("${jwt.token.secret-key:eyJhbGc}")
    private String secretKey;

    @Value("${jwt.token.issuer}")
    private String issuer;

    private final UserService userService;

    // private final HttpServletRequest request;
    // private final RedisTemplate template = new RedisTemplate();
    // private final String JWT_BLACK_LIST = "JWT_BACK_LIST";
    // private final JwtUtil jwtUtil;

    public TokenDto authLogin(AuthDto auth) {
        if (StringUtils.isBlank(auth.getUsername()) || StringUtils.isBlank(auth.getPassword())) {
            throw new ServiceException("Username and password must be provided.");
        }
        UserDto user = userService.searchUser(auth.getUsername());
        var errorMessage = "Username or password is not match.";
        if (user == null) {
            logger.warn("Username not found for [{}]", auth.getUsername());
            throw new ServiceException(errorMessage);
        }
        if (!user.getPassword().equals(auth.getPassword())) {
            logger.warn("Password does not match");
            throw new ServiceException(errorMessage);
        }
        return generateJwtToken(user);
    }

    // public void authLogout() {
    //   final String token = request.getHeader("Authorization");
    //     List<String> redisTokenBlackList = getRedisTokenBlackList();
    //         redisTokenBlackList.add(token);
    //         template.opsForList().rightPushAll(JWT_BLACK_LIST, redisTokenBlackList);
    // }

    // the size of list:  we will create scheduled job every 5min to remove expired token in the list -> size of list will be reduce
    // we need to crate a common package so every service can use that package
    // create a folder name Util which has these method

    // private List<String> getRedisTokenBlackList(){
    //     List<String> redisTokenBlackList = new ArrayList<>();
    //     try{
    //         redisTokenBlackList = template.opsForList().range(JWT_BLACK_LIST, 0, -1);
    //         return redisTokenBlackList;
    //     }catch (Exception e){
    //         return new ArrayList<>();
    //     }
    // }

    // @Scheduled(fixedDelay = 300000) // every 5 min
    // private void cleanTokenBlackList() {
    //     List<String> redisTokenBlackList = getRedisTokenBlackList();
    //     for (int i = 0; i < redisTokenBlackList.size(); i++){
    //         if (jwtUtil.isTokenExpired(redisTokenBlackList.get(i))){
    //             redisTokenBlackList.remove(redisTokenBlackList.get(i));
    //         }
    //     }
    //     template.opsForList().rightPushAll(JWT_BLACK_LIST, redisTokenBlackList);
    // }

    private TokenDto generateJwtToken(UserDto user) {
        var signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        var now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        var signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        var builder = Jwts.builder().setId(String.valueOf(user.getId())).setIssuedAt(now).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + (tokenExpireTime * 1000 * 60);
        var exp = new Date(expMillis);
        builder.setExpiration(exp);

        var token = builder.compact();
        return new TokenDto(token, expMillis, user.getScope());
    }
}
