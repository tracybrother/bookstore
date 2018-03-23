package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.TokenUtil;

public class AdminPrivilegeFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 1 强制转换
		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;

		// 2判断是否具有权限
//		User user = (User) request.getSession().getAttribute("user");

//		if (user != null && "超级用户".equals(user.getRole())) {
//			// 3.放行
//			chain.doFilter(request, response);
//			return;
//		}
		final HttpSession session = request.getSession();

		synchronized (session) {
			String paramSessionToken = request.getParameter(TokenUtil.TOKENPARAM);
			String sessionSessionToken = (String) session.getAttribute(TokenUtil.TOKENPARAM);
			if (sessionSessionToken == null || paramSessionToken == null
					|| !paramSessionToken.equals(sessionSessionToken)) {
				// response.sendRedirect(request.getContextPath() + "/error/privilege.jsp");
				return;
			}
			session.removeAttribute(TokenUtil.TOKENPARAM);
		}
		return;

	}

	public void destroy() {

	}

}
