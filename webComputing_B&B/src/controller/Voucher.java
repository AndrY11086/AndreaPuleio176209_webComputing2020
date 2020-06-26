package controller;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import model.Cliente;
import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class Voucher
 */
@WebServlet("/Voucher")
public class Voucher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*Cookie[] ospite = request.getCookies();
		for (int i = 0; i < ospite.length; i++) {
			if (ospite[i].getName().equals("email")) {
				emailCliente = ospite[i].getValue();
			}
		}*/
		HttpSession session = request.getSession();
		String emailCliente = (String) session.getAttribute("email");
		
		boolean lavatrice = (boolean) session.getAttribute("lavatrice");
		boolean air = (boolean) session.getAttribute("air");
		boolean allIn = (boolean) session.getAttribute("allIn");
		
		boolean loco = (boolean) session.getAttribute("loco");
		boolean bar = (boolean) session.getAttribute("bar");
		boolean barCamera = (boolean) session.getAttribute("barCamera");
		
		
		//ClienteDao clienteDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getClienteDAO();
		Cliente cliente = (Cliente) session.getAttribute("cliente");//clienteDao.findByPrimaryKey(emailCliente);
		/*PrenotazioneDao prnzDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();
		LinkedList<Prenotazione> prenotazioni = (LinkedList<Prenotazione>) prnzDao.findAll();
		int id = 0;
		for (Prenotazione prenotazione : prenotazioni) {
			if (prenotazione.getIdPrenotazione() > id) {
				id = prenotazione.getIdPrenotazione();
			}
		}*/

		Prenotazione prn = (Prenotazione) session.getAttribute("prenotazione");//prnzDao.findByPrimaryKey(id);

		PdfWriter pdf = new PdfWriter(response.getOutputStream());
		PdfDocument doc = new PdfDocument(pdf);
		Document document = new Document(doc);// servirà per creare e aggiungere tutti gli elementi da inserire nel pdf

		String imageFile = "/images/logo.jpg";
		String absoluteDiskPath = getServletContext().getRealPath(imageFile);
		ImageData data = ImageDataFactory.create(absoluteDiskPath);
		Image img = new Image(data);

		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

		document.add(img);
		
		document.add(new Paragraph("Riepilogo dati: ").setFontColor(ColorConstants.GREEN).setFont(font));
		document.add(new Paragraph("Prenotazione numero: " + prn.getIdPrenotazione()).setFont(font));
		document.add(new Paragraph("Ospite: " + cliente.getCognome() + " " + cliente.getNome()).setFont(font));
		document.add(new Paragraph("Data di arrivo: " + prn.getArrivo().toString()).setFont(font));
		document.add(new Paragraph("Data di partenza: " + prn.getPartenza().toString()).setFont(font));
		document.add(new Paragraph("Durata del soggiorno: " + prn.getnNotti()).setFont(font));
		document.add(new Paragraph("Numero Ospiti: " + prn.getNumeroPersone()).setFont(font));
		document.add(new Paragraph("Prezzo Finale: " + prn.getPrezzo()).setFont(font));
		if (prn.getPagamento().equals("Presso la Struttura")) {
			document.add(new Paragraph(
					"Pagamento presso la struttura. \n vi comunichiamo che il pagamento può essere effettuato in loco sia in contanti che con carta di credito/debito")
							.setFontColor(ColorConstants.RED).setFont(font));
			
			

		} else {
			document.add(new Paragraph(
					"Pagamento effettuato con carta di credito\n NON CI SONO COSTI AGGIUNTIVI PRESSO LA STRUTTURA (salvo nuovo richieste successive alla prenotazione)")
					.setFontColor(ColorConstants.RED).setFont(font));
		}
		
		if(lavatrice || air || allIn || loco || bar || barCamera) {
			document.add(new Paragraph("Servizi Extra inclusi nel prezzo finale:"));
			if(lavatrice) {
				document.add(new Paragraph("		- Lavatrice"));
			}
			if(air) {
				document.add(new Paragraph("		- Aria condizionata"));
			}
			if(allIn) {
				document.add(new Paragraph("		- MiniBar All Inclusive"));
			}
			if(loco) {
				document.add(new Paragraph("		- Colazione standard presso la struttura"));
			}
			else if(bar) {
				document.add(new Paragraph("		- Colazione presso il bar vicino alla struttura"));
			}else {
				document.add(new Paragraph("		- Colazione servita in camera dal bar vicino alla struttura"));
			}
		}

		document.close();
		
		session.setAttribute("prenotazione", null);
		session.setAttribute("prenotazioni", null);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=Voucher B&B Chiara.pdf");

		//System.out.println("VOUCHER è stata invocata");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
