package controller.global;

import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.marchio.Marchio;
import model.marchio.MarchioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletStart", value = "/ServletStart",loadOnStartup = 0) // Serve per chiamare automaticamente la servlet

@Generated
public class ServletStart extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

        CategoriaDAO categoriaDAO= new CategoriaDAO();
        ArrayList<Categoria>categorie= categoriaDAO.doRetraiveByAllCategorieRoot();
        MarchioDAO marchioDAO= new MarchioDAO();
        ArrayList<Marchio> marchi= marchioDAO.doRetraiveByAllMarchi();
        getServletContext().setAttribute("marchi",marchi); // ServletContext contiene attributi che sono visibili in tutte le pagine
        getServletContext().setAttribute("categorie",categorie);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
