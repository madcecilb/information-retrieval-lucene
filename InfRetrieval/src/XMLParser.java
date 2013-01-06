import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	@SuppressWarnings("deprecation")
	public static ArrayList<Article> getArticles(String fileName) {
		ArrayList<Article> articles = new ArrayList<Article>();
			
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(fileName);
			if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();

				// Print root element of the document
				System.out.println("Root element of the document: "
						+ docEle.getNodeName());

				NodeList articleList = docEle.getElementsByTagName("REUTERS");

				// Print total articles elements in document
				System.out
						.println("Total articles: " + articleList.getLength());

				if (articleList != null && articleList.getLength() > 0) {
					for (int i = 0; i < articleList.getLength(); i++) {

						Node node = articleList.item(i);

						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Article article = new Article();
							
							Element e = (Element) node;
							
							Node nodeAttribute = e.getAttributeNode("NEWID"); 							
							article.setId(Integer.parseInt(nodeAttribute.getNodeValue()));
							
							NodeList nodeList = e.getElementsByTagName("DATE");
							if(nodeList.getLength() != 0){
								Date date = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH).parse(nodeList.item(0).getChildNodes().item(0)
										.getNodeValue());
								
								article.setDate(XMLParser.GetStringFromDate(date));
							}
							
							nodeList = e.getElementsByTagName("TITLE");
							if(nodeList.getLength() != 0){
								article.setTitle(nodeList.item(0).getChildNodes().item(0)
												.getNodeValue());
							}
							
							nodeList = e.getElementsByTagName("BODY");
							if(nodeList.getLength() != 0){
								article.setText(nodeList.item(0).getChildNodes().item(0)
										.getNodeValue());
							}
							
							articles.add(article);
						}
					}
				} else {
					System.exit(1);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return articles;
	}
	
	public static String GetStringFromDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.format("%04d%02d%02d", 
				calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH) + 1, 
				calendar.get(Calendar.DAY_OF_MONTH));
	}
	
}