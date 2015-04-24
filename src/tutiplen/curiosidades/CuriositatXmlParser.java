package tutiplen.curiosidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class CuriositatXmlParser {

	private static final String ns = null;
	
	public ArrayList<Curiositat> parse(InputStream in) throws XmlPullParserException,IOException {
		try{
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
			parser.setInput(in, null);
			parser.nextTag();
			return llegirNoticies(parser);
		}
		finally{
			in.close();
		}
	}
	
	private ArrayList<Curiositat> llegirNoticies(XmlPullParser parser) throws XmlPullParserException, IOException{
		ArrayList<Curiositat> noticies = new ArrayList<Curiositat>();
		
		parser.require(XmlPullParser.START_TAG, ns, "curiositats"); //Document Arrel
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String nomTag = parser.getName();
			if (nomTag.equals("curiositat"))
			{
				noticies.add(llegirNoticia(parser));
			}
			else {
				skip(parser);
			}
		}
		return noticies;
	}
	
	private Curiositat llegirNoticia(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "curiositat"); //Fill document arrel

		String titol = null;
		String id = "0"; 
		String text = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) 
				continue;

			String nomTag = parser.getName();

			if (nomTag.equals("titol")) {
				titol = llegirTagSimple(parser,"titol");
			} else if (nomTag.equals("curiositat")) {
				id = llegirAtribut(parser, "curiositat", "id");
			} else if (nomTag.equals("text")) {
				text = llegirTagSimple(parser,"text");
			} else {
				skip(parser);
			}
		}
		return new Curiositat(Integer.parseInt(id), titol, text);
	}
	
	private String llegirTagSimple(XmlPullParser parser,String tag) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String title = llegirText(parser);
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return title;
	}
	
	private String llegirAtribut(XmlPullParser parser,String tag, String atribute) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String title = parser.getAttributeValue(ns, atribute);
		parser.next();
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return title;
	}

	private String llegirText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;

	}

	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}
	
}
