package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class CheckCodeServlet
 */
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = request.getParameter("name");
		String sessionCode = (String) request.getSession().getAttribute("checkcode_session");
		System.out.println(code);
		System.out.println(sessionCode);
		String statu = "0";
		if(code.equals(sessionCode)) {
			statu = "1";
		}
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("statuCode", statu);
		jsonarray.add(jsonobj);
		PrintWriter out = response.getWriter();
		out.println(jsonarray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
