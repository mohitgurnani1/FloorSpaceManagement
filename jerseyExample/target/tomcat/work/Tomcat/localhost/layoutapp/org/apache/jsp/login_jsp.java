/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-09-16 08:53:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    function showPassword() {\r\n");
      out.write("\r\n");
      out.write("        var key_attr = $('#key').attr('type');\r\n");
      out.write("\r\n");
      out.write("        if(key_attr != 'text') {\r\n");
      out.write("\r\n");
      out.write("            $('.checkbox').addClass('show');\r\n");
      out.write("            $('#key').attr('type', 'text');\r\n");
      out.write("\r\n");
      out.write("        } else {\r\n");
      out.write("\r\n");
      out.write("            $('.checkbox').removeClass('show');\r\n");
      out.write("            $('#key').attr('type', 'password');\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("    </script>\r\n");
      out.write("<style>\r\n");
      out.write("    #login {\r\n");
      out.write("        padding-top: 50px\r\n");
      out.write("    }\r\n");
      out.write("    #login .form-wrap {\r\n");
      out.write("        width: 30%;\r\n");
      out.write("        margin: 0 auto;\r\n");
      out.write("    }\r\n");
      out.write("    #login h1 {\r\n");
      out.write("        color: #333;\r\n");
      out.write("        font-size: 18px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("        padding-bottom: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    #login .form-group {\r\n");
      out.write("        margin-bottom: 25px;\r\n");
      out.write("    }\r\n");
      out.write("    #login .checkbox {\r\n");
      out.write("        margin-bottom: 20px;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        -webkit-user-select: none;\r\n");
      out.write("        -moz-user-select: none;\r\n");
      out.write("        -ms-user-select: none;\r\n");
      out.write("        -o-user-select: none;\r\n");
      out.write("        user-select: none;\r\n");
      out.write("    }\r\n");
      out.write("    #login .checkbox.show:before {\r\n");
      out.write("        content: '\\e013';\r\n");
      out.write("        color: #1fa67b;\r\n");
      out.write("        font-size: 17px;\r\n");
      out.write("        margin: 1px 0 0 3px;\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        pointer-events: none;\r\n");
      out.write("        font-family: 'Glyphicons Halflings';\r\n");
      out.write("    }\r\n");
      out.write("    #login .checkbox .character-checkbox {\r\n");
      out.write("        width: 25px;\r\n");
      out.write("        height: 25px;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("        border-radius: 3px;\r\n");
      out.write("        border: 1px solid #ccc;\r\n");
      out.write("        vertical-align: middle;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("    }\r\n");
      out.write("    #login .checkbox .label {\r\n");
      out.write("        color: #6d6d6d;\r\n");
      out.write("        font-size: 13px;\r\n");
      out.write("        font-weight: normal;\r\n");
      out.write("    }\r\n");
      out.write("    #login .btn.btn-custom {\r\n");
      out.write("        font-size: 14px;\r\n");
      out.write("        margin-bottom: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    #login .forget {\r\n");
      out.write("        font-size: 13px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        display: block;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /*    --------------------------------------------------\r\n");
      out.write("        :: Inputs & Buttons\r\n");
      out.write("        -------------------------------------------------- */\r\n");
      out.write("    .form-control {\r\n");
      out.write("        color: #212121;\r\n");
      out.write("    }\r\n");
      out.write("    .btn-custom {\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        background-color: #555;\r\n");
      out.write("    }\r\n");
      out.write("    .btn-custom:hover,\r\n");
      out.write("    .btn-custom:focus {\r\n");
      out.write("        color: #fff;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /*    --------------------------------------------------\r\n");
      out.write("        :: Footer\r\n");
      out.write("        -------------------------------------------------- */\r\n");
      out.write("    #footer {\r\n");
      out.write("        color: #6d6d6d;\r\n");
      out.write("        font-size: 12px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("    }\r\n");
      out.write("    #footer p {\r\n");
      out.write("        margin-bottom: 0;\r\n");
      out.write("    }\r\n");
      out.write("    #footer a {\r\n");
      out.write("        color: inherit;\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!--<form action=\"");
      out.print( request.getContextPath() );
      out.write("/login\" method=\"POST\">\r\n");
      out.write("    id : <input type=\"text\" name=\"user\" id=\"user\"><br>\r\n");
      out.write("    password : <input type=\"text\" name=\"password\" id=\"password\"><br>\r\n");
      out.write("    <input type=\"submit\">\r\n");
      out.write("</form>\r\n");
      out.write("-->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"./resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<section id=\"login\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-xs-12\">\r\n");
      out.write("                <div class=\"form-wrap\">\r\n");
      out.write("                    <h1>Log in</h1>\r\n");
      out.write("                    <form role=\"form\" action=\"");
      out.print( request.getContextPath() );
      out.write("/login\" method=\"POST\" id=\"login-form\" autocomplete=\"off\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"user\" class=\"sr-only\">Email</label>\r\n");
      out.write("                            <input type=\"email\"  name=\"user\" id=\"user\"  class=\"form-control\" placeholder=\"username\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"password\" class=\"sr-only\">Password</label>\r\n");
      out.write("                            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\" placeholder=\"Password\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"checkbox\">\r\n");
      out.write("                            <span class=\"character-checkbox\" onclick=\"showPassword()\"></span>\r\n");
      out.write("                            <span class=\"label\">Show password</span>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <input type=\"submit\" id=\"btn-login\" class=\"btn btn-custom btn-lg btn-block\" value=\"Log in\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <a href=\"javascript:;\" class=\"forget\" data-toggle=\"modal\" data-target=\".forget-modal\">Forgot your password?</a>\r\n");
      out.write("                    <hr>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div> <!-- /.col-xs-12 -->\r\n");
      out.write("        </div> <!-- /.row -->\r\n");
      out.write("    </div> <!-- /.container -->\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("<div class=\"modal fade forget-modal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myForgetModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("    <div class=\"modal-dialog modal-sm\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <div class=\"modal-header\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n");
      out.write("                    <span aria-hidden=\"true\">Ã</span>\r\n");
      out.write("                    <span class=\"sr-only\">Close</span>\r\n");
      out.write("                </button>\r\n");
      out.write("                <h4 class=\"modal-title\">Recovery password</h4>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-body\">\r\n");
      out.write("                <p>Type your email account</p>\r\n");
      out.write("                <input type=\"email\" name=\"recovery-email\" id=\"recovery-email\" class=\"form-control\" autocomplete=\"off\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-footer\">\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-custom\">Recovery</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div> <!-- /.modal-content -->\r\n");
      out.write("    </div> <!-- /.modal-dialog -->\r\n");
      out.write("</div> <!-- /.modal -->\r\n");
      out.write("\r\n");
      out.write("<footer id=\"footer\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-xs-12\">\r\n");
      out.write("                <p>Powered by <strong>Barclays</strong></p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
