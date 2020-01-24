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
import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		BikeDao dao = new BikeDao();
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		System.out.println(dao.delete());
		if (command.equals("first")) {
			response.sendRedirect("bike01.jsp");

		} else if (command.equals("firstdb")) {
			String[] bike = request.getParameterValues("bike");

			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for (int i = 0; i < bike.length; i++) {
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
			if (res > 0) {
				jsResponse("등록 성공", "index.html", response);
			} else {
				jsResponse("등록 실패", "index.html", response);
			}
		} else if (command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		} else if (command.equals("second_db")) {
			String txt = request.getParameter("obj");

			// System.out.println(txt);
			JsonElement element = JsonParser.parseString(txt);
			System.out.println(element.getAsJsonObject().get("DESCRIPTION"));

			List<BikeDto> list = new ArrayList<>();

			for (int i = 0; i < element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();

				//System.out.println(tmp.get("addr_gu").getAsString());
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");

				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();

				BikeDto dto = new BikeDto(addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				list.add(dto);
			}

			int res = dao.insert(list);
			if (res == list.size()) {
				System.out.println("insert 성공");
			} else {
				System.out.println("insert 실패");
			}
			response.getWriter().append(res + "");

		}
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "');");
		out.println("location.href='" + url + "';");
		out.println("</script>");
	}

}
