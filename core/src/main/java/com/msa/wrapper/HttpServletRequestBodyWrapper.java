package com.msa.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * request의 body데이터를 처리하는 wrapper 클래스
 *  - request.getInputStream()은 1번만 사용이 가능하므로 InputSteam을 읽어서 관련 기능을 오버라이드함.
 *      - body 항목을 통해서 본문내용을 가지고 있음.
 *  - https://taetaetae.github.io/2019/06/30/controller-common-logging/ 참고해서 재작업 진행함.
 *      - getParameter 관련 4개 method Override 진행함.
 *      - 파일업로드에 대한 본문데이터 대응 처리는 아직 안되어 있음.
 */
public class HttpServletRequestBodyWrapper extends HttpServletRequestWrapper {

    private byte[] body;
    private Map<String, String[]> params = new HashMap<>();
    private final Charset encoding;

    /**
     * 생성자에서 request의 InputSteam을 읽어서 내부에서 가지고 있음.
     * @param request
     * @throws IOException
     */
    public HttpServletRequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);

        // 파라미터 정보 저장
        this.params.putAll(request.getParameterMap());

        // 인코딩 정보 저장
        this.encoding = StringUtils.isBlank(request.getCharacterEncoding()) ? StandardCharsets.UTF_8 : Charset.forName(request.getCharacterEncoding());

        // 본문 데이터 획득
        InputStream is = request.getInputStream();
        this.body = StreamUtils.copyToByteArray(is);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new BodyServletInputStream(this.body);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = null;
        String[] values = params.get(name);

        if(values != null) {
            result = new String[values.length];
            System.arraycopy(values, 0, result, 0, values.length);
        }

        return result;
    }

    @Override
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        return values != null && values.length != 0 ? values[0] : null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(params);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    /**
     * 파라미터 정보를 json 문자열로 반환함.
     * @return
     */
    public String getParamJson() {
        try{
            ObjectMapper om = new ObjectMapper();
            String str = om.writeValueAsString(this.params);
            return str;
        }catch(Exception e){
            throw new RuntimeException("convert model to json error!", e);
        }
    }

    /**
     * 본문내용을 문자열로 반환함.
     * @return
     */
    public String getBodyString(){
        return new String(this.body);
    }

    /**
     * ServletInputStream 클래스
     *  request의 InputSteam을 대체함.
     */
    private static class BodyServletInputStream extends ServletInputStream {
        private final InputStream inputStream;

        public BodyServletInputStream(byte[] is) {
            this.inputStream = new ByteArrayInputStream(is);
        }

        @Override
        public boolean isFinished() {
            try{
                return this.inputStream.available() == 0;
            }catch(IOException e){
                throw new RuntimeException("[BodyServletInputStream] InputStream available fail", e);
            }
        }

        @Override
        public boolean isReady() {
            return this.inputStream != null;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int read() throws IOException {
            return this.inputStream.read();
        }
    }
}