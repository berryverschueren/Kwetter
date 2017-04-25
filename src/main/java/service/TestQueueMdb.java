package service;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

/**
 * Message-Driven Bean implementation class for: TestMdb
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "testqueue")},
        mappedName = "testqueue")
public class TestQueueMdb implements MessageListener {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "testtopic")
    private Topic topic;

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {

        try {
            message.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextMessage txtMessage = (TextMessage) message;

        try {
            System.out.println(txtMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            MessageProducer producer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Topic message broadcasted from the MDB inside the Kwetter Server.");
            connection.start();
            producer.send(textMessage);
            System.out.println("Topic message broadcasted.");
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}