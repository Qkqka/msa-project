package com.msa.wrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * response의 body데이터를 처리하는 wrapper 클래스
 */
public class HttpServletResponseBodyWrapper extends HttpServletResponseWrapper {

    private ServletResponse response;

    private BodyPrintWriter bodyPrintWriter;

    private BodyServletOutputStream bodyServletOutputStream;

    public HttpServletResponseBodyWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    /**
     * OutputStream을 재정의한 클래스로 대체함.
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        if (this.bodyServletOutputStream == null) {
            ServletOutputStream outputStream = this.response.getOutputStream();
            this.bodyServletOutputStream = new BodyServletOutputStream(outputStream);
        }

        return this.bodyServletOutputStream;
    }

    /**
     * PrintWriter를 재정의한 클래스로 대체함.
     */
    @Override
    public PrintWriter getWriter() throws IOException {

        if (this.bodyPrintWriter == null) {
            this.bodyPrintWriter = new BodyPrintWriter(this.response.getWriter());
        }

        return this.bodyPrintWriter;
    }

    /**
     * 본문내용을 반환함.
     * 
     * @return
     */
    public String getBodyString() {

        if (this.bodyServletOutputStream != null) {
            return this.bodyServletOutputStream.getBodyString();
        }

        if (this.bodyPrintWriter != null) {
            return this.bodyPrintWriter.getBodyString();
        }

        return "";
    }

    /**
     * PrintWriter 클래스 - response의 PrintWriter를 대체함. - writer를 오버라이드해서 내부에 가지고 있는
     * StringBuilder에 같이 저장하는 부분이 포인트임. - 이부분은 검증이 되지 않았음.
     */
    private static class BodyPrintWriter extends PrintWriter {

        private StringBuilder body = new StringBuilder();

        public BodyPrintWriter(Writer out) {
            super(out);
        }

        @Override
        public void write(int c) {
            super.write(c);
            this.body.append((char) c);
        }

        @Override
        public void write(char[] chars, int offset, int length) {
            super.write(chars, offset, length);
            this.body.append(chars, offset, length);
        }

        @Override
        public void write(String string, int offset, int length) {
            super.write(string, offset, length);
            this.body.append(string, offset, length);
        }

        public String getBodyString() {
            return this.body.toString();
        }
    }

    /**
     * ServletOutputStream 클래스 response의 OutputSteam을 대체함.
     */
    private static class BodyServletOutputStream extends ServletOutputStream {

        private byte[] body = new byte[] {};

        private ServletOutputStream target;

        /**
         * 생성자에서 대상 outputstream을 설정함.
         * 
         * @param target
         */
        public BodyServletOutputStream(ServletOutputStream target) {
            this.target = target;
        }

        @Override
        public boolean isReady() {
            return this.target.isReady();
        }

        @Override
        public void setWriteListener(WriteListener listener) {
            this.target.setWriteListener(listener);

        }

        /**
         * 여기가 포인트임. target에도 write를 하고, 내부적으로 가지고 있는 body에도 append해서 값을 별도로 가지고 있도록
         * 구성함.
         */
        @Override
        public void write(int b) throws IOException {
            // 기존 항목에 write처리
            this.target.write(b);

            // 내부값에 값추가 처리
            byte[] arr = new byte[this.body.length + 1];
            System.arraycopy(this.body, 0, arr, 0, this.body.length);
            arr[this.body.length] = (byte) b;
            this.body = arr;
        }

        /**
         * 본문 내용을 반환함.
         * 
         * @return
         */
        public String getBodyString() {
            return new String(this.body);
        }
    }
}