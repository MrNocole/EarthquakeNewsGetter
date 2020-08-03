package earthquakeGetter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;

public class JavaRun {
	public static void main(String mailAddress) throws ClassNotFoundException {
		String lv;String time;String wd;String jd;String depth;String location;
		String url = "http://news.ceic.ac.cn/index.html?time=1596004673";
		HtmlConnect htmlGetter = new HtmlConnect(url);
		SQLControl service = new SQLControl("cdb-0qc9c4mc.cd.tencentcdb.com", 10059, yourDBId, yourDBPW, "earthquakeDataBase");
		htmlGetter.getHtml("news","td");
		ArrayList<String> msg = new ArrayList<String>();
		Node node = htmlGetter.getParagraphs().get(1);
		msg.add(Jsoup.parse(node.toString()).text());
		String mailMsg = "Set Done";
	    try {
		    MailClientSend client=new MailClientSend();
		    client.init();
			client.sendMessage(mailMsg,mailAddress);
		    client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			if(htmlGetter.getHtml("news", "td")) {
				node =htmlGetter.getParagraphs().get(1);
				if(!Jsoup.parse(node.toString()).text().equals(msg.get(0))) {
					for(int i=0;i<6;i++) {
						msg.add(i, Jsoup.parse(htmlGetter.paragraphs.get(i).toString()).text());
					}
					lv = msg.get(0);
					time = msg.get(1);
					wd = msg.get(2);
					jd = msg.get(3);
					depth = msg.get(4);
					location = msg.get(5);
					service.SQLDataInsert(lv, time, wd, jd, depth, location); 
					mailMsg = "Location:"+location + "\nLv:" + lv + "\nTime:" + time + "\nDepth:" + depth;
				    try {
					    MailClientSend client=new MailClientSend();
					    client.init();
						client.sendMessage(mailMsg,mailAddress);
					    client.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    System.out.println("changed!");

				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		//add all msg into mysql
//		for(Node node1:htmlGetter.getParagraphs()) {
//			msg.add(Jsoup.parse(node1.toString()).text());
//		}
//		
//
//		for(int i=0;i<msg.size();i+=6) {
//			lv = msg.get(i);
//			time = msg.get(i+1);
//			wd = msg.get(i+2);
//			jd = msg.get(i+3);
//			depth = msg.get(i+4);
//			location = msg.get(i+5);
//			service.SQLDataInsert(lv, time, wd, jd, depth, location);
//		}
//		
	}
}
