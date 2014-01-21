package com.linangran.openstone.framework;

import com.linangran.openstone.card.ICard;
import com.linangran.openstone.context.GeneralContext;
import com.linangran.openstone.context.GeneralGameConfig;
import com.linangran.openstone.context.IContext;
import com.linangran.openstone.context.IGameConfig;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/17/14.
 */
public class GameLoader {
	private static Logger logger = GameLogger.getLogger(GameLoader.class);

	public String gamePath;

	public GameLoader(String gamePath)
	{
		this.gamePath = gamePath;
	}

	public IGameConfig loadGameConfig()
	{
		IGameConfig config = new GeneralGameConfig();
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source source = new StreamSource(this.gamePath);

		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(this.getClass().getClassLoader().getResource("game.xsd"));
		} catch (SAXException e) {
			logger.fatal("Failed to Load Validation schema: game.xsd", e);
			return null;
		}
		Validator validator = schema.newValidator();
		try {
			validator.validate(source);
		} catch (SAXException e) {
			logger.fatal("Failed to validate game.xml, check the syntax of your xml", e);
			return null;
		} catch (IOException e) {
			logger.fatal("Failed to load game.xml file.", e);
			return null;
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.fatal(e);
			return null;
		}
		Document document= null;
		try {
			document = documentBuilder.parse(new File(this.gamePath));
		} catch (SAXException e) {
			logger.fatal(e);
			return null;
		} catch (IOException e) {
			logger.fatal(e);
			return null;
		}
		for (int i = 0; i < document.getChildNodes().getLength(); i++)
		{
			Node node = document.getChildNodes().item(i);
			if (node.getNodeName().equals("gameCards"))
			{
				config.cardList = new ArrayList<ICard>();
				loadGameCards(node.getChildNodes(), config.cardList);
			}
			else if (node.getNodeName().equals("heros"))
			{

			}
			else if (node.getNodeName().equals("groovyClasspath"))
			{
				config.groovyClasspath = node.getNodeValue();
			}
			else if (node.getNodeName().equals("resourcePath"))
			{
				config.resourcePath = node.getNodeValue();
			}
		}

	}

	private void loadGameCards(NodeList childNodes, List<ICard> cardList) {

		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node node = childNodes.item(i);
			cardList.add(loadSingleGameCard(node));
		}
	}

	private ICard loadSingleGameCard(Node node) {
		if (node.getNodeName().equals("minionCard"))
		{

		}
		else if (node.getNodeName().equals("choiceCard"))
		{

		}
		else if (node.getNodeName().equals("comboCard"))
		{

		}
		else if (node.getNodeName().equals("secretCard"))
		{

		}
		else if (node.getNodeName().equals("spellCard"))
		{

		}
		else if (node.getNodeName().equals("weaponCard"))
		{

		}
	}
}
