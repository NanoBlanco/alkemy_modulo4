package controlador;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.service.ProductoService;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name = "productos", urlPatterns = { "/productos" })
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductoService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.service = new ProductoService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String accion = req.getParameter("accion");
		if(accion == null) accion = "listar";
		
		switch(accion) {
			case "listar" -> listar(req, res);
			//case "nuevo" -> mostrarFormulario(req, res, null);
			//case "editar" -> cargarEdicion(req, res);
			//case "eliminar" -> eliminar(req, res);
			default -> res.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("accion");
		
		switch(accion) {
			case "guardar" -> guardar(req, res);
			//case "actualizar" -> actualizar(req, res);
			default -> res.sendError(400);
		}
	}
	
	private void listar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("productos", service.obtenerTodos());
		req.getRequestDispatcher("lista.jsp");
	}
	
	private void guardar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Producto p = extraerProducto(req);
		service.guardarProducto(p);
		res.sendRedirect("/productos");
	}

	private Producto extraerProducto(HttpServletRequest req) {
		Producto p = new Producto();
		
		p.setNombre(req.getParameter("nombre"));
		p.setDescripcion(req.getParameter("descripcion"));
		p.setPrecio(Integer.parseInt(req.getParameter("precio")));
		p.setStock(Integer.parseInt(req.getParameter("stock")));
		p.setStockMin(Integer.parseInt(req.getParameter("stockMin")));
		p.setIdCategoria(Integer.parseInt(req.getParameter("idCategoria")));
		
		return p;
	}
}
