package provaBalanaBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.wso2.balana.utils.policy.BasicPolicyHelper;
import org.wso2.balana.utils.policy.dto.BasicPolicyDTO;

public class BalanaBuilder {

	public BalanaBuilder() throws Exception{
		
		BasicPolicyDTO basciPolicyDTO = new BasicPolicyDTO();
		Document document = new Document() {

			@Override
			public String getNodeName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getNodeValue() throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setNodeValue(String nodeValue) throws DOMException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public short getNodeType() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Node getParentNode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public NodeList getChildNodes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node getFirstChild() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node getLastChild() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node getPreviousSibling() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node getNextSibling() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public NamedNodeMap getAttributes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Document getOwnerDocument() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node insertBefore(Node newChild, Node refChild) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node removeChild(Node oldChild) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node appendChild(Node newChild) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasChildNodes() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Node cloneNode(boolean deep) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void normalize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isSupported(String feature, String version) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getNamespaceURI() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPrefix() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setPrefix(String prefix) throws DOMException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasAttributes() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getBaseURI() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public short compareDocumentPosition(Node other) throws DOMException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getTextContent() throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setTextContent(String textContent) throws DOMException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isSameNode(Node other) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String lookupPrefix(String namespaceURI) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isDefaultNamespace(String namespaceURI) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String lookupNamespaceURI(String prefix) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEqualNode(Node arg) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object getFeature(String feature, String version) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object setUserData(String key, Object data, UserDataHandler handler) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getUserData(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DocumentType getDoctype() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DOMImplementation getImplementation() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Element getDocumentElement() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Element createElement(String tagName) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DocumentFragment createDocumentFragment() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Text createTextNode(String data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Comment createComment(String data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public CDATASection createCDATASection(String data) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Attr createAttribute(String name) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EntityReference createEntityReference(String name) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public NodeList getElementsByTagName(String tagname) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Node importNode(Node importedNode, boolean deep) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Element getElementById(String elementId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getInputEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getXmlEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean getXmlStandalone() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getXmlVersion() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setXmlVersion(String xmlVersion) throws DOMException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean getStrictErrorChecking() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setStrictErrorChecking(boolean strictErrorChecking) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getDocumentURI() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setDocumentURI(String documentURI) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Node adoptNode(Node source) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DOMConfiguration getDomConfig() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void normalizeDocument() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
				// TODO Auto-generated method stub
				return null;
			}};
		
		document.setDocumentURI("src\\main\\resources\\PolicyCittadini.xml");
		BasicPolicyHelper policyBuilder = new BasicPolicyHelper();
		policyBuilder.createPolicyElement(basciPolicyDTO, document);
	}
	
	public static void main(String[] args) {

		try {
			new BalanaBuilder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
