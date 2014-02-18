package com.linangran.openstone.framework;

import com.linangran.openstone.card.*;
import com.linangran.openstone.context.GeneralContext;
import com.linangran.openstone.context.GeneralGameConfig;
import com.linangran.openstone.context.IContext;
import com.linangran.openstone.context.IGameConfig;
import com.linangran.openstone.defaultcard.DefaultMinionCard;
import com.linangran.openstone.defaultcard.DefaultWeaponCard;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Admin on 1/17/14.
 */
public class GameLoader {
	private static Logger logger = GameLogger.getLogger(GameLoader.class);

	public String gamePath;
	public String workPath;
	public String groovyClassDir;
	public String resourceDir;
	public GroovyClassLoader groovyClassLoader = null;
	public HashMap<String, Class> loadedClass;

	public GameLoader(String gamePath)
	{
		this.gamePath = gamePath;
		this.groovyClassLoader = new GroovyClassLoader();
		this.loadedClass = new HashMap<String, Class>();
	}

	public IGameConfig loadGameConfig()
	{
		IGameConfig config = new GeneralGameConfig();
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source source = new StreamSource(this.gamePath);
		File file = new File(this.gamePath);
		this.workPath = file.getParent();

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
				config.heroList = new ArrayList<IHeroCard>();
				loadHeroCards(node.getChildNodes(), config.heroList);
			}
			else if (node.getNodeName().equals("groovyClasspath"))
			{
				config.groovyClasspath = node.getNodeValue();
				config.groovyClasspath = config.groovyClasspath.replace('.', File.separatorChar);
				this.groovyClassDir = config.groovyClasspath;
			}
			else if (node.getNodeName().equals("resourcePath"))
			{
				config.resourcePath = node.getNodeValue();
				config.resourcePath = config.resourcePath.replace('.', File.separatorChar);
				this.resourceDir = config.resourcePath;
			}
		}
		return config;
	}

	private void loadHeroCards(NodeList childNodes, List<IHeroCard> heroList) {
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node node = childNodes.item(i);
			heroList.add(loadSingleHeroCard(node));
		}
	}

	private IHeroCard loadSingleHeroCard(Node node) {
		IHeroCard heroCard = new IHeroCard();
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node anode = childNodes.item(i);
			if (anode.getNodeName().equals("heroCard"))
			{
				heroCard.heroCard = loadMinionCard(anode);
			}
			else if (anode.getNodeName().equals("heroSpell"))
			{
				heroCard.heroSpell = loadSpellCard(anode);
			}
		}
		return heroCard;
	}

	private void loadGameCards(NodeList childNodes, List<ICard> cardList) {

		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node node = childNodes.item(i);
			cardList.add(loadSingleGameCard(node));
		}
	}

	private ICard loadSingleGameCard(Node node) {
		ICard card = null;
		if (node.getNodeName().equals("minionCard"))
		{
			card = loadMinionCard(node);
		}
		else if (node.getNodeName().equals("choiceCard"))
		{
			card = loadChoiceCard(node);
		}
		else if (node.getNodeName().equals("comboCard"))
		{
			card = loadComboCard(node);
		}
		else if (node.getNodeName().equals("secretCard"))
		{
			card = loadSecretCard(node);
		}
		else if (node.getNodeName().equals("spellCard"))
		{
			card = loadSpellCard(node);
		}
		else if (node.getNodeName().equals("weaponCard"))
		{
			card = loadWeaponCard(node);
		}
		return card;
	}

	private IWeaponCard loadWeaponCard(Node node) {
		IWeaponCard card = null;
		if (hasGroovyClass(node))
		{
			card = (IWeaponCard) loadCardFromGroovyClass(node);
		}
		else
		{
			card = new DefaultWeaponCard();
		}
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node anode = childNodes.item(i);
			if (anode.getNodeName().equals("durability"))
			{
				card.durability = Integer.parseInt(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("attack"))
			{
				card.attack = Integer.parseInt(anode.getNodeValue());
			}
		}
		return card;
	}

	private ISpellCard loadSpellCard(Node node) {
		ISpellCard card = null;
		if (hasGroovyClass(node))
		{
			card = (ISpellCard) loadCardFromGroovyClass(node);
		}
		else
		{
			logger.fatal("Spell cards can only be constructed from groovy class.", new ClassNotFoundException());
		}
		return card;
	}

	private ISecretCard loadSecretCard(Node node) {
		ISecretCard card = null;
		if (hasGroovyClass(node))
		{
			card = (ISecretCard) loadCardFromGroovyClass(node);
		}
		else
		{
			logger.fatal("Secret cards can only be constructed from groovy class.", new ClassNotFoundException());
		}
		return card;
	}

	private IComboCard loadComboCard(Node node) {
		IComboCard card = null;
		if (hasGroovyClass(node))
		{
			card = (IComboCard)loadCardFromGroovyClass(node);
		}
		else
		{
			logger.fatal("Combo cards can only be constructed from groovy class.", new ClassNotFoundException());
		}
		return card;
	}

	private IChoiceCard loadChoiceCard(Node node) {
		//TO-do: XML cross reference
		return null;
	}

	private boolean hasGroovyClass(Node node)
	{
		Element element = (Element) node;
		return element.getElementsByTagName("groovyClass").getLength() == 1;
	}

	private IMinionCard loadMinionCard(Node node) {
		IMinionCard card = null;
		if (hasGroovyClass(node))
		{
			card = (IMinionCard) loadCardFromGroovyClass(node);
		}
		else
		{
			card = new DefaultMinionCard();
		}
		loadBasicCardInfo(node, card);
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node anode = childNodes.item(i);
			if (anode.getNodeName().equals("health"))
			{
				card.health = Integer.parseInt(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("attack"))
			{
				card.attack = Integer.parseInt(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("immune"))
			{
				card.immune = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("stealth"))
			{
				card.stealth = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("taunt"))
			{
				card.taunt = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("windfury"))
			{
				card.windfury = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("hasShield"))
			{
				card.hasShield = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("hasCharge"))
			{
				card.hasCharge = Boolean.parseBoolean(anode.getNodeValue());
			}
		}
		return card;
	}

	private void loadBasicCardInfo(Node node, ICard card)
	{
		NodeList childNodes = node.getChildNodes();
		//For consistence, do not use reflection
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			Node anode = childNodes.item(i);
			if (anode.getNodeName().equals("cardName"))
			{
				card.cardName = anode.getNodeValue();
			}
			else if (anode.getNodeName().equals("cardDescription"))
			{
				card.cardDescription = anode.getNodeValue();
			}
			else if (anode.getNodeName().equals("cardImage"))
			{
				card.cardImage = anode.getNodeValue();
				card.cardImage = this.gamePath + File.separator + resourceDir + File.separator + card.cardImage;
			}
			else if (anode.getNodeName().equals("deckUsable"))
			{
				card.deckUsable = Boolean.parseBoolean(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("cost"))
			{
				card.cost = Integer.parseInt(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("overload"))
			{
				card.overLoad = Integer.parseInt(anode.getNodeValue());
			}
			else if (anode.getNodeName().equals("quality"))
			{
				card.quality = Integer.parseInt(anode.getNodeValue());
			}
		}
		return;
	}

	private ICard loadCardFromGroovyClass(Node node)
	{
		Element element = (Element) node;
		ICard card = null;
		String groovyClassPath = element.getElementsByTagName("groovyClass").item(0).getNodeValue();
		Class clazz = loadGroovyClass(groovyClassPath);
		Object cardObject = null;
		try {
			cardObject = clazz.newInstance();
		} catch (Exception e) {
			logger.fatal("Cannot initialize groovy object of class: " + groovyClassPath + ", check the constructor.", e);
		}
		return card;
	}



	private Class loadGroovyClass(String className) {
		if (loadedClass.containsKey(className))
		{
			return loadedClass.get(className);
		}
		String classPath = className.replace('.', File.separatorChar);
		String fullPath = workPath + File.separator + this.groovyClassDir + File.separator + classPath + ".groovy";
		Class clazz = null;
		try {
			clazz = groovyClassLoader.parseClass(new GroovyCodeSource(new File(fullPath)), true);
			loadedClass.put(className, clazz);
		} catch (IOException e) {
			logger.fatal("Groovy class cannot be found:" + className, e);
		}
		return clazz;
	}
}
