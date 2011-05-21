/*
 *   (C) Copyright 2010-2011 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */
package hms.ctap.simulator.ui.sms;

import com.vaadin.ui.Table;
import hms.ctap.simulator.ui.services.NcsService;
import hms.ctap.simulator.ui.services.NcsUIService;
import hms.ctap.simulator.sms.SmsAoRequestMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
public class SmsNcsUIService implements NcsUIService {

    private Table sentMsgTable;
    private int sentRowCount = 1;
    private int receivedRowCount = 1;
    private Table receivedMsgTable;

    @Override
    public void init() {
        sentMsgTable = new Table("Sent Messages");
        receivedMsgTable = new Table("Received Messages");
    }


    @Override
    public Table createSentMessageService() {

        String[] headings = {"Time", "Phone #", "Message", "Status"};
        for (String heading : headings) {
            sentMsgTable.addContainerProperty(heading, String.class, null);
        }
        sentMsgTable.setColumnWidth(headings[0], 30);
        sentMsgTable.setColumnWidth(headings[1], 75);
        sentMsgTable.setColumnWidth(headings[2], 100);
        sentMsgTable.setColumnWidth(headings[3], 35);
        sentMsgTable.setHeight("100%");
        sentMsgTable.setWidth("100%");
        return sentMsgTable;
    }


    @Override
    public Table createReceivedMessageService() {

        String[] headings = {"Time", "Phone #", "Message"};
        for (String heading : headings) {
            receivedMsgTable.addContainerProperty(heading, String.class, null);
        }
        receivedMsgTable.setColumnWidth(headings[0], 30);
        receivedMsgTable.setColumnWidth(headings[1], 100);
        receivedMsgTable.setColumnWidth(headings[2], 180);
        receivedMsgTable.setHeight("100%");
        receivedMsgTable.setWidth("100%");
        return receivedMsgTable;
    }

    @Override
    public void addElementToReceiveTable(int objectId, Object object) {

        if (receivedMsgTable.getItem(objectId) == null) {
            SmsAoRequestMessage smsAoRequestMessage = (SmsAoRequestMessage) object;
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            receivedMsgTable.addItem(new Object[]{dateFormat.format(new Date()), smsAoRequestMessage.getAddress(),
                    smsAoRequestMessage.getMessage()}, objectId);
        }

    }

    @Override
    public void addElementToSentTable(String date, String address, String message, String status) {

        sentMsgTable.addItem(new Object[]{date, address, message, status}, sentRowCount);
        sentRowCount++;
    }

    @Override
    public NcsService getNcsService() {
        return NcsService.SMS;
    }
}
