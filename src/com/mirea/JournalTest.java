package com.mirea;

import java.util.List;

import static org.junit.Assert.*;

public class JournalTest {
   private Journal j;
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void TestAddUserAction() throws Exception {
        //Add user action
        ActionUser a = ActionUser.USER_CREATED;
        int u=55555;
        j.addUserAction(u,a);
    }

    @org.junit.Test
    public void TestAddDocumentAction() throws Exception {
        //Add document action
        ActionDoc a = ActionDoc.DOCUMENT_CREATED;
        int u=55555;
        String s="00000";
        j.addDocumentAction(u,s,a);
    }

    @org.junit.Test
    public void TestGetUserAction() throws Exception {
        int u = 55555;
        j.addUserAction(u,ActionUser.USER_CREATED);
        ActionUser[] text;
        text=j.getUserAction(u);
        assertEquals(text,ActionUser.USER_CREATED);
    }

    @org.junit.Test
    public void TestGetDocumentAction() throws Exception {
    }

}