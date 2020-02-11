package net.vehiclemanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vehiclemanagement.dao.VehicleDAO;
import net.vehiclemanagement.model.Vehicle;

@WebServlet("/")
public class VehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleDAO vehicleDAO;

    public void init() {
        vehicleDAO = new VehicleDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertVehicle(request, response);
                    break;
                case "/delete":
                    deleteVehicle(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateVehicle(request, response);
                    break;
                case "/search":
                    showSearchForm(request, response);
                    break;
                case "/search-nav":
                    chooseSearch(request, response);
                    break;

                default:
                    listVehicle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    //show vehicle list
    private void listVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vehicle> listVehicle = vehicleDAO.selectAllVehicles();
        request.setAttribute("listVehicle", listVehicle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-list.jsp");
        dispatcher.forward(request, response);
    }
    //nav to new vehicle form page
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-form.jsp");
        dispatcher.forward(request, response);
    }
    //nav to edit vehicle form page
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle existingVehicle = vehicleDAO.selectVehicle(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-form.jsp");
        request.setAttribute("vehicle", existingVehicle);
        dispatcher.forward(request, response);

    }
    //insert a new vehicle
    private void insertVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int year = Integer.parseInt(request.getParameter("year"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        Vehicle newVehicle = new Vehicle(year, make, model);
        vehicleDAO.insertVehicle(newVehicle);
        response.sendRedirect("list");
    }
    //update the vehicle value
    private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int year = Integer.parseInt(request.getParameter("year"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");

        Vehicle book = new Vehicle(id, year, make, model);
        vehicleDAO.updateYear(book);
        response.sendRedirect("list");
    }
    //delete the vehicle value
    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        vehicleDAO.deleteVehicle(id);
        response.sendRedirect("list");

    }
    //show the searching page
    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-form.jsp");
        dispatcher.forward(request, response);
    }
    //find to the correct searching item
    private void chooseSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String item = request.getParameter("item");
        if(item.equals("year")){
            showSearchYear(request, response);
        } else if(item.equals("make")){
            showSearchMake(request, response);
        }else {
            showSearchModel(request, response);
        }
    }
    //search data under year column and show result
    private void showSearchYear(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int year = Integer.parseInt(request.getParameter("val"));
        List<Vehicle> yearList = vehicleDAO.searchVehicleYear(year);
        request.setAttribute("searchVehicle", yearList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-list.jsp");
        dispatcher.forward(request, response);
    }
    //search data under make column and show result
    private void showSearchMake(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String make = request.getParameter("val");
        List<Vehicle> makeList = vehicleDAO.searchVehicleMake(make);
        request.setAttribute("searchVehicle", makeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-list.jsp");
        dispatcher.forward(request, response);
    }
    //search data under model column and show result
    private void showSearchModel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String model = request.getParameter("val");
        List<Vehicle> modelList = vehicleDAO.searchVehicleModel(model);
        request.setAttribute("searchVehicle", modelList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-list.jsp");
        dispatcher.forward(request, response);
    }
}
