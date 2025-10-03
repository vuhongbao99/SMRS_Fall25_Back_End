//package com.example.smrsservice.service;
//
//import com.example.smrsservice.entity.User;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.Date;
//@Service
//public class JwtService {
//
//    private String secretkey = "Jd06Q6owr0oJgIDDImHFbY2lPLXSW8s2+pOvbBthby+fPSzz4htWYGBgA1UnYAcVYdjJYtzuTcMzHM+uZGnIZA==";
//    public String generateAccessToken(User user) throws JOSEException {
//        //Header
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//        //Payload (noi dung token)
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(String.valueOf(user.getUserId()))
//                .issueTime(new Date())
//                .expirationTime(new Date(Instant.now().plus(java.time.Duration.ofMinutes(30)).toEpochMilli()))
//                .build();
//
//        Payload payload = new Payload(claimsSet.toJSONObject());
//
//
//        //Signature
//        JWSObject jwsObject = new JWSObject(header,payload);
//        jwsObject.sign(new MACSigner(secretkey));
//
//        return jwsObject.serialize();
//    }
//
//    public String generateRefreshToken(User user) throws JOSEException {
//        //Header
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//        //Payload (noi dung token)
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(String.valueOf(user.getUserId()))
//                .issueTime(new Date())
//                .expirationTime(new Date(Instant.now().plus(java.time.Duration.ofDays(14)).toEpochMilli()))
//                .build();
//
//        Payload payload = new Payload(claimsSet.toJSONObject());
//
//
//        //Signature
//        JWSObject jwsObject = new JWSObject(header,payload);
//        jwsObject.sign(new MACSigner(secretkey));
//
//        return jwsObject.serialize();
//    }
//}
