package ejerServicioWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.lang.invoke.ClassSpecializer.Factory;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import teclado.*;

public class Main {
	public static void main(String[] args) {
		
		int numMonumentos = Teclado.solicitarEnteroPositivo("Introduce numero de monumentos a visualizar. ");
	
		try {
			URL url = new URL("https://www.zaragoza.es/sede/servicio/monumento.xml?srsname=wgs84&rows=" +  numMonumentos + "&fl=id,title,description");
			//URLConnection conexion = url.openConnection();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
				Document documento = builder.parse(url.openStream());
				
				documento.getDocumentElement().normalize();
				//System.out.println(documento.getDocumentElement().getNodeName());
				NodeList monumentos = documento.getElementsByTagName("monumento");
				
				for (int i = 0; i < monumentos.getLength(); i++) {
					//cogemos el primer nodo
					Node monumento = monumentos.item(i);
					//lo transformamos a tipo Element
					Element elemento = (Element) monumento;
					//obtenemos la lista de nodos con nombre "title" del elemento.
					NodeList titulos = elemento.getElementsByTagName("title").item(0).getChildNodes();
					Node titulo = titulos.item(0);
					System.out.println("Título: " + titulo.getNodeValue());
				}
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//			//BufferedWriter bw = new BufferedWriter(new FileWriter("resultado.xml"));
//			String cadena;
//			System.out.println("Obteniendo resultados ---------------------------------------\n");
//			while ( (cadena = br.readLine()) != null) {
//				System.out.println(cadena);
//				//bw.write(cadena);
//				//bw.newLine();
//				//bw.flush();
//			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
