
package pl.lodz.p.tks.user.soapadapters.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IUserAdapter", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IUserAdapter {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getName", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetName")
    @ResponseWrapper(localName = "getNameResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetNameResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getNameRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getNameResponse")
    public String getName();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addUser", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.AddUserResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/addUserRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/addUserResponse")
    public void addUser(
        @WebParam(name = "arg0", targetNamespace = "")
        UserSoap arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns pl.lodz.p.tks.user.soapadapters.client.UserSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUserById", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUserById")
    @ResponseWrapper(localName = "getUserByIdResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUserByIdResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUserByIdRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUserByIdResponse")
    public UserSoap getUserById(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List&lt;pl.lodz.p.tks.user.soapadapters.client.UserSoap&gt;
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUsers")
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUsersResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUsersRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUsersResponse")
    public List<UserSoap> getUsers();

    /**
     * 
     * @param arg0
     * @return
     *     returns pl.lodz.p.tks.user.soapadapters.client.UserSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUserByUsername", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUserByUsername")
    @ResponseWrapper(localName = "getUserByUsernameResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.GetUserByUsernameResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUserByUsernameRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/getUserByUsernameResponse")
    public UserSoap getUserByUsername(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateUserById", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.UpdateUserById")
    @ResponseWrapper(localName = "updateUserByIdResponse", targetNamespace = "http://adapters.soapadapters.user.tks.p.lodz.pl/", className = "pl.lodz.p.tks.user.soapadapters.client.UpdateUserByIdResponse")
    @Action(input = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/updateUserByIdRequest", output = "http://adapters.soapadapters.user.tks.p.lodz.pl/IUserAdapter/updateUserByIdResponse")
    public void updateUserById(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        UserSoap arg1);

}
