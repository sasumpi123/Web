package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.biz.BikeBiz;
import com.bike.dto.BikeDto;

/**
 * Servlet implementation class BikeServlet
 */
@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BikeBiz biz = new BikeBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		
		} else if(command.equals("firstdb")) {
			String[] bike = request.getParameterValues("bike");
			
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for(int i =0; i < bike.length; i++) {
				String[] tmp = bike[i].split("/");
				BikeDto dto = new BikeDto();
				dto.setAddr_gu(tmp[0]);
				dto.setContent_id(Integer.parseInt(tmp[1]));
				dto.setContent_num(tmp[2]);
				dto.setNew_addr(tmp[3]);
				dto.setCradle_count(Integer.parseInt(tmp[4]));
				dto.setLongitude(Double.parseDouble(tmp[5]));
				dto.setLatitude(Double.parseDouble(tmp[6]));
				
				bikes.add(dto);
			}
			int res = biz.insert(bikes);
			if(res>0) {
				jsResponse("등록 성공", "index.html", response);
			} else {
				jsResponse("등록 실패", "index.html", response);
			}
		}
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"';");
		out.println("</script>");
	}

}
