package earthquakeGetter;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlConnect {
	private String url;
	private Connection conn;
	private Element content;
	Elements paragraphs;
	public HtmlConnect(String url) {
		this.url = url;
		conn = Jsoup.connect(url);
	}
	public Boolean getHtml(String divName,String paraName) {
		Document doc;
		try {
			doc = conn.get();
			content = doc.getElementById(divName);
			paragraphs = content.select(paraName);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Element getDiv() {
		return content;
	}
	public void setContent(Element content) {
		this.content = content;
	}
	public Elements getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(Elements paragraphs) {
		this.paragraphs = paragraphs;
	}
	
}
