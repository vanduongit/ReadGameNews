package com.android.dt.readgamenews.handler;

import com.android.dt.readgamenews.models.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by toan on 12/8/2015.
 */
public class MySaxHandler extends DefaultHandler {
    ArrayList<Item> items;
    Item item_tam;
    boolean vaoitem=false;
    StringBuilder sb=new StringBuilder();

    public MySaxHandler() {
        items=new ArrayList<Item>();
    }

    public ArrayList<Item> getitems()
    {
        return items;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equalsIgnoreCase("item"))
        {
            item_tam=new Item();
            vaoitem=true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(vaoitem==true)
            sb.append(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("item"))
        {
            items.add(item_tam);
            vaoitem=false;
        }else if(vaoitem==true)
        {
            if(qName.equalsIgnoreCase("title"))
                item_tam.setTitle(sb.toString());
            if(qName.equalsIgnoreCase("description"))
                item_tam.setDescription(sb.toString());
            if(qName.equalsIgnoreCase("link"))
                item_tam.setLink(sb.toString());
            if(qName.equalsIgnoreCase("pubdate"))
                item_tam.setPubdate(sb.toString());
            sb=new StringBuilder();
        }
    }
}
