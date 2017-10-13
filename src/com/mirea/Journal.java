package com.mirea;

public interface Journal {
    /**
     * Saves actions of user in file
     * @param UserId user id
     * @param Act user action
     * @throws SaveUserException Error of saving user actions
     * @author Vorobew A.S. <and.vor.93@mail.ru>
     * @version 1.0
     */
    void addUserAction(int UserId, ActionUser Act) throws SaveUserException;
    /**
     * Saves actions made by user inside of document in file
     * @param UserId user id
     * @param DocId document id
     * @param Act action preformed on document
     * @throws SaveDocException Error of saving document actions
     * @author Vorobew A.S. <and.vor.93@mail.ru>
     * @version 1.0
     */
    void addDocumentAction(int UserId,String DocId, ActionDoc Act) throws SaveDocException;
    /**
     *Sends array of user actions to interface
     * @param UserId user id
     * @return Act of user
     * @throws WrongUserIdException Wrong user id
     * @author Vorobew A.S. <and.vor.93@mail.ru>
     * @version 1.0
     */
    ActionUser[] getUserAction(int UserId) throws WrongUserIdException;
    /**
     * Sends array of actions made by user inside of document to interface
     * @param DocId Id of document
     * @author Vorobew A.S. <and.vor.93@mail.ru>
     * @return Information about document actions and users id by array
     * @throws WrongDocIdException Wrong document id
     * @version 1.0
     */

    Information[] getDocumentAction(String DocId) throws WrongDocIdException;
}









//String FieldName