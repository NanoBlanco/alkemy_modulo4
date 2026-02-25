package config;


import dao.UserDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import servicios.UsuarioServicio;

/**
 * Servlet implementation class AppInitializer
 */
//@WebListener
public class AppInitializer implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		UserDAO dao = new UserDAO();
		UsuarioServicio us = new UsuarioServicio(dao);
		System.out.println("Servicio inicializado...");
		sce.getServletContext().setAttribute("servicio", us);
	}
}
