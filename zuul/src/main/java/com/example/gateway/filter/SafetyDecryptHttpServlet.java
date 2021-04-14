//package com.example.gateway.filter;
//
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
//public class SafetyDecryptHttpServlet extends HttpServletRequestWrapper {
//
//  // 保存request body的数据
//  private String body;
//
//  // 解析request的inputStream(即body)数据，转成字符串
//  public SafetyDecryptHttpServlet(HttpServletRequest request, String newBody) throws IOException {
//    super(request);
//    body = newBody;
//  }
//
//  public static String getBodyString(HttpServletRequest request) throws IOException {
//    StringBuilder sb = new StringBuilder();
//    InputStream inputStream = null;
//    BufferedReader reader = null;
//    try {
//      inputStream = request.getInputStream();
//      reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//      String line = "";
//      while ((line = reader.readLine()) != null) {
//        sb.append(line);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      // 关闭流
//      IOUtils.closeQuietly(reader);
//      IOUtils.closeQuietly(inputStream);
//    }
//    return sb.toString();
//  }
//
//  @Override
//  public long getContentLengthLong() {
//    return body.getBytes(StandardCharsets.UTF_8).length;
//  }
//
//  @Override
//  public int getContentLength() {
//    return body.getBytes(StandardCharsets.UTF_8).length;
//  }
//
//  @Override
//  public BufferedReader getReader() throws IOException {
//    return new BufferedReader(new InputStreamReader(getInputStream()));
//  }
//
//  @Override
//  public ServletInputStream getInputStream() throws IOException {
//
//    final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
//
//    return new ServletInputStream() {
//
//      @Override
//      public int read() throws IOException {
//        return bais.read();
//      }
//
//      @Override
//      public boolean isFinished() {
//        return false;
//      }
//
//      @Override
//      public boolean isReady() {
//        return false;
//      }
//
//      @Override
//      public void setReadListener(ReadListener readListener) {
//
//      }
//    };
//  }
//
//  public String getBody() {
//    return this.body;
//  }
//
//  public void setBody(String body) {
//    this.body = body;
//  }
//
//}