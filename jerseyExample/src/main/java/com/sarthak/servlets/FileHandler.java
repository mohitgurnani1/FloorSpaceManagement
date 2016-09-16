package com.sarthak.servlets;

import com.sarthak.HibernateMapping.Desk;
import com.sarthak.HibernateMapping.LayoutExtremes;
import com.sarthak.HibernateMapping.OfficeDetails;
import com.sarthak.HibernateMapping.TableData;
import com.sarthak.Parser.DataLoader;
import com.sarthak.Parser.ExcelParser;
import com.sarthak.Parser.LayoutData;
import com.sarthak.Parser.TableGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sarthak on 13-09-2016.
 */
public class FileHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file ;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = getServletContext();
        String filePath = context.getInitParameter("file-upload");

        // Verify the content type
        String contentType = request.getContentType();
        if ((contentType.indexOf("multipart/form-data") >= 0)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("C:\\Users\\Sarthak\\IdeaProjects\\JerseyExample\\target\\temp"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax( maxFileSize );
            try{
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while ( i.hasNext () )
                {
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () )
                    {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( filePath +
                                    fileName.substring( fileName.lastIndexOf("\\"))) ;
                        }else{
                            file = new File( filePath +
                                    fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        String country=request.getParameter("country");
                        String city=request.getParameter("city");
                        String branch=request.getParameter("branch");
                        String floor=request.getParameter("floor");
                        if(country==null)
                            country="India";
                        if(city==null)
                            city="Pune";
                        if(branch==null)
                            branch="Kharadi";
                        if(floor==null)
                            floor="3";

                        DataLoader dataLoader=new DataLoader();
                        String locationId="";
                        OfficeDetails officeDetails=dataLoader.getOfficeDetails(country,city,branch,floor);
                        locationId=officeDetails.getOfficeUid()+"";
                        if(officeDetails!=null)
                            dataLoader.deleteData(locationId);
                        officeDetails=new OfficeDetails(country,city,branch,floor);
                        dataLoader.saveOfficeDetails(officeDetails);
                        officeDetails=dataLoader.getOfficeDetails(country,city,branch,floor);
                        String path=filePath+fileName;
                        locationId=officeDetails.getOfficeUid()+"";
                        ExcelParser excelParser=new ExcelParser();
                        LayoutData layoutData=excelParser.getDesk(path,locationId);
                        List<Desk> obtaineddesk= layoutData.getDesks();
                        LayoutExtremes layoutExtremes = layoutData.getLayoutExtremes();
                        TableGenerator tableGenerator=new TableGenerator();
                        List<TableData> tableList=tableGenerator.generateTables(layoutData,locationId);
                        dataLoader.saveDesk(obtaineddesk);
                        dataLoader.saveExtremes(layoutExtremes);
                        dataLoader.saveTableData(tableList);
                    }
                }
                response.sendRedirect(request.getContextPath()+"/index2.html");
            }catch(Exception ex) {
                System.out.println(ex);
            }
        }else{
            PrintWriter out=response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
