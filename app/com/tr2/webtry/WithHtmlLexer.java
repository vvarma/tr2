package com.tr2.webtry;
/*package com.tr1.webtry;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.util.ParserException;

public class WithHtmlLexer {

	*//**
	 * @param args
	 * @throws IOException 
	 * @throws ParserException 
	 *//*
	public void downloadCsv(String urlName) throws IOException, ParserException{
		URL url = new URL(urlName);
		URLConnection conn=url.openConnection();
		Lexer lex=new Lexer(conn);
		Node node;
		while((node=lex.nextNode())!=null){
			if(node.getText().equals("Bhavcopy")){
				System.err.println("-------------------------------------------------------------------------------------");
			}
				System.out.println("new");
				System.out.println(node.getText());
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WithHtmlLexer lex=new WithHtmlLexer();
		try {
			lex.downloadCsv("http://www.nseindia.com/products/content/all_daily_reports.htm");
		} catch (ParserException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
*/