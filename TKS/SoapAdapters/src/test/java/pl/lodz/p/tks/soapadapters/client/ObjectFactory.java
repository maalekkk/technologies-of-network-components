
package pl.lodz.p.tks.soapadapters.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.lodz.p.tks.soapadapters.client package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddUser_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "addUserResponse");
    private final static QName _GetName_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getName");
    private final static QName _GetNameResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getNameResponse");
    private final static QName _GetUserById_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUserById");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUserByIdResponse");
    private final static QName _GetUserByUsername_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUserByUsername");
    private final static QName _GetUserByUsernameResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUserByUsernameResponse");
    private final static QName _GetUsers_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUsers");
    private final static QName _GetUsersResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "getUsersResponse");
    private final static QName _UpdateUserById_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "updateUserById");
    private final static QName _UpdateUserByIdResponse_QNAME = new QName("http://adapters.soapadapters.tks.p.lodz.pl/", "updateUserByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.lodz.p.tks.soapadapters.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link GetName }
     * 
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     * 
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link GetUserById }
     * 
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     * 
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link GetUserByUsername }
     * 
     */
    public GetUserByUsername createGetUserByUsername() {
        return new GetUserByUsername();
    }

    /**
     * Create an instance of {@link GetUserByUsernameResponse }
     * 
     */
    public GetUserByUsernameResponse createGetUserByUsernameResponse() {
        return new GetUserByUsernameResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link UpdateUserById }
     * 
     */
    public UpdateUserById createUpdateUserById() {
        return new UpdateUserById();
    }

    /**
     * Create an instance of {@link UpdateUserByIdResponse }
     * 
     */
    public UpdateUserByIdResponse createUpdateUserByIdResponse() {
        return new UpdateUserByIdResponse();
    }

    /**
     * Create an instance of {@link UserSoap }
     * 
     */
    public UserSoap createUserSoap() {
        return new UserSoap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByUsername }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserByUsername }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUserByUsername")
    public JAXBElement<GetUserByUsername> createGetUserByUsername(GetUserByUsername value) {
        return new JAXBElement<GetUserByUsername>(_GetUserByUsername_QNAME, GetUserByUsername.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByUsernameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserByUsernameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUserByUsernameResponse")
    public JAXBElement<GetUserByUsernameResponse> createGetUserByUsernameResponse(GetUserByUsernameResponse value) {
        return new JAXBElement<GetUserByUsernameResponse>(_GetUserByUsernameResponse_QNAME, GetUserByUsernameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateUserById }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "updateUserById")
    public JAXBElement<UpdateUserById> createUpdateUserById(UpdateUserById value) {
        return new JAXBElement<UpdateUserById>(_UpdateUserById_QNAME, UpdateUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateUserByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://adapters.soapadapters.tks.p.lodz.pl/", name = "updateUserByIdResponse")
    public JAXBElement<UpdateUserByIdResponse> createUpdateUserByIdResponse(UpdateUserByIdResponse value) {
        return new JAXBElement<UpdateUserByIdResponse>(_UpdateUserByIdResponse_QNAME, UpdateUserByIdResponse.class, null, value);
    }

}
