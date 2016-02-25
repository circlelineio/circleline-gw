package io.circleline.filter.auth;

import io.circleline.filter.error.UnauthorizedException;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1002515 on 2016. 2. 25..
 */
public class HmacAuth implements Authentication {

    private static final String HEADER_NAME = "Authentication";

    @Override
    public void challenge(HttpServletRequest request) throws UnauthorizedException {

        String credentials = request.getHeader(HEADER_NAME);
        if(credentials == null)
            throw new UnauthorizedException("'Authentication' header must not be null");

        Signature signature = Signature.from(credentials);

        if(!isValidKeyId(signature.keyId())){
            throw new UnauthorizedException("invalid keyId");
        }

        String signatureFromDateHeader = generateSignatureBasedOnDateHeader(request);
        if (!signature.signature().equals(signatureFromDateHeader)) {
            throw new UnauthorizedException("invalid signature");
        }
    }

    private boolean isValidKeyId(String keyId){
        //TODO keyId가 유효한지 확인한다.
        return false;
    }

    private String generateSignatureBasedOnDateHeader(HttpServletRequest request){
        String date = request.getHeader("date");
        String signedStr = HmacUtils.hmacSha1Hex("secret".getBytes(), ("date:" + date).getBytes());
        return Base64.getEncoder().encodeToString(signedStr.getBytes());
    }

    static class Signature {

        private Map<String, String> fields = new HashMap();
        public String keyId(){
            return get("KeyId");
        }
        public String algorithm(){
            return get("algorithm");
        }
        public String signature(){
            return get("signature");
        }
        private String get(String field){
            return StringUtils.substringBetween(fields.get(field), "\"", "\"");
        }
        public static Signature from(final String credentials){
            String params = credentials.replaceFirst("Signature","");
            Signature hmac = new Signature();
            for(String field: fieldsIn(params)){
                hmac.addField(field);
            }
            return hmac;
        }
        public void addField(String field){
            String[] pair = field.split("=",2);
            fields.put(pair[0].trim(), pair[1].trim());
        }
        static String[] fieldsIn(String credentials){
            return credentials.split(",");
        }
    }
}
