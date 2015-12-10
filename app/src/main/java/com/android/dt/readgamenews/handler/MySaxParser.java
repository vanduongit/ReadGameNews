package com.android.dt.readgamenews.handler;

import com.android.dt.readgamenews.models.Item;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by toan on 12/8/2015.
 */
public class MySaxParser {

    public ArrayList<Item> xmlParser(String url)
    {
        ArrayList<Item> ds=null;
        try
        {
            XMLReader xmlreader= SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            MySaxHandler saxHandler=new MySaxHandler();
            xmlreader.setContentHandler(saxHandler);
            URL url1=new URL(url);
            HttpURLConnection httpurl=(HttpURLConnection)url1.openConnection();
            InputSource is=new InputSource(url1.openStream());
            xmlreader.parse(is);
            ds=saxHandler.getitems();

        }catch(Exception e)
        {
            try {
                DefaultHttpClient httpclient=new DefaultHttpClient();
                HttpGet httpget=new HttpGet(url);
                HttpResponse httpresponse=httpclient.execute(httpget);
                HttpEntity httpentity=httpresponse.getEntity();
                XMLReader xmlreader=SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                MySaxHandler handler=new MySaxHandler();
                xmlreader.setContentHandler(handler);
                InputSource is=new InputSource();
                is.setCharacterStream(new StringReader(EntityUtils.toString(httpentity)));
                xmlreader.parse(is);
                ds=handler.getitems();
            }catch(Exception e1)
            {
                e.printStackTrace();
            }

        }
        return ds;
    }
}
