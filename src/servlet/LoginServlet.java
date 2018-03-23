package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import po.User;
import service.UserService;

/**
 * 登录的Servlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		UserService userService = new UserService();
		try {
			User user = userService.login(username, password);
			JSONArray jsonarray = new JSONArray();
			JSONObject json = new JSONObject();
			if (user == null) {
				json.put("statuCode", "0");
			} else {
				json = (JSONObject) JSONObject.toJSON(user);
				json.put("statuCode", "1");
			}
			jsonarray.add(json);
			PrintWriter out = response.getWriter();
			out.println(jsonarray);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
