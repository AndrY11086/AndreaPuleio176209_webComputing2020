package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import model.Escursione;

/**
 * Servlet implementation class VoucherEscursioni
 */
@WebServlet("/VoucherEscursioni")
public class VoucherEscursioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoucherEscursioni() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LinkedList<Escursione> escursioni = (LinkedList<Escursione>) session.getAttribute("carrello");
		
		PdfWriter pdf = new PdfWriter(response.getOutputStream());
		PdfDocument doc = new PdfDocument(pdf);
		Document document = new Document(doc);
		
		int index = 0;
		
		for (Escursione escursione : escursioni) {
			
			String img = getImg(escursione.getNome());
			ImageData data = ImageDataFactory.create(img);
			Image imgData = new Image(data);
			PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
			document.add(imgData);
			
			document.add(new Paragraph("Riepilodo dati:"));
			document.add(new Paragraph("Id Escursione prenotata: " + escursione.getId() + " " + escursione.getNome()));
			document.add(new Paragraph("Nome Cliente: " + escursione.getCliente()));
			document.add(new Paragraph("Persone prenotate: " + escursione.getnPersone()));
			
			if(escursione.getNome().equals("Lido con 1 ombrellone e 2 lettini")) {
				String periodo = (String) session.getAttribute("periodo");
				document.add(new Paragraph("Periodo: " + periodo));
			}
			
			document.add(new Paragraph("prezzo finale: " + escursione.getPrezzo()));
			document.add(new Paragraph("Totale già saldato tramite pagamento online"));
			
			if(escursioni.size() > 1 && index < escursioni.size()-2) {
				AreaBreak newPage = new AreaBreak();
				document.add(newPage);
			}
			index++;
		}
		
		document.close();
		
		session.setAttribute("carrello", null);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=Escursioni B&B Chiara.pdf");
		
		System.out.println("VoucherEscursioni invocata");
	}

	private String getImg(String nome) {
		switch(nome) {
		case "Giornata alle isole Eolie":
			String imageFile = "/images/Escursioni/tourEolie.jpg";
			String absoluteDiskPath = getServletContext().getRealPath(imageFile);
			return absoluteDiskPath;
		case "Tracciolino di Palmi":
			 imageFile = "/images/Escursioni/tracciolino.jpg";
			 absoluteDiskPath = getServletContext().getRealPath(imageFile);
			return absoluteDiskPath;
		case "Immersioni subacquee":
			 imageFile = "/images/Escursioni/sub.jpg";
			 absoluteDiskPath = getServletContext().getRealPath(imageFile);
			return absoluteDiskPath;
		case "Lido con 1 ombrellone e 2 lettini":
			 imageFile = "/images/Escursioni/Lido.jpg";
			 absoluteDiskPath = getServletContext().getRealPath(imageFile);
			return absoluteDiskPath;
		default:
			return null;
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
