package org.mklinkj.taojwp.common;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;
import org.mklinkj.taojwp.sec01.ex01.FileUpload;

@WebListener
public class FileUploadListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    Dynamic fileUpload = context.addServlet("fileUpload", FileUpload.class);
    fileUpload.addMapping("/upload.do");
    fileUpload.setMultipartConfig(getMultiPartConfig());
  }

  private MultipartConfigElement getMultiPartConfig() {
    String location = ProjectDataUtils.getProperty("fileUpload.uploadDir");
    long maxFileSize = ProjectDataUtils.getLongProperty("fileUpload.maxFileSize");
    long maxRequestSize = ProjectDataUtils.getLongProperty("fileUpload.maxRequestSize");
    int fileSizeThreshold = ProjectDataUtils.getIntProperty("fileUpload.threshold");
    return new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
  }
}
