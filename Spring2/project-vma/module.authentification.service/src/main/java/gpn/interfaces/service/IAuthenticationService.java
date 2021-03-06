package gpn.interfaces.service;

import gpn.exception.ApplicationException;
import gpn.exception.UserNotFoundException;
import gpn.util.JwtRequest;

import javax.naming.NamingException;

/**Сервис аутентификации пользователей*/
public interface IAuthenticationService {

    /**Получить аутентификационный токен для пользователя*/
    String getAuthToken(String userName) throws UserNotFoundException, NamingException, ApplicationException;
    String getAuthToken(JwtRequest request) throws UserNotFoundException, NamingException, ApplicationException;
}
