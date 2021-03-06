package gpn.service;

import gpn.contract.Claim;
import gpn.contract.SystemUser;
import gpn.exception.ApplicationException;
import gpn.exception.UserNotFoundException;
import gpn.interfaces.service.IAuthenticationService;
import gpn.interfaces.service.IClaimsService;
import gpn.util.JwtRequest;
import gpn.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
//    @Autowired
//    private ILdapService ldapService;
    @Autowired
    private UserService userService;
    @Autowired
    private IClaimsService claimsService;

    @Override
    public String getAuthToken(String userName) throws UserNotFoundException, ApplicationException {
//        SearchResult searchResult;
//        try {
//            searchResult = ldapService.findAccountByAccountName(userName);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }
//        if (searchResult == null) {
//            throw new UserNotFoundException();
//        }

        SystemUser sUser = new SystemUser();
        sUser.setUserName(userName);
        sUser.setDomainName("-");
        sUser.setDisplayName("-");
        sUser.setEmail("-");
        sUser.setGuid("-");
//        try {
//            fillAttributes(sUser, searchResult);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }

        SystemUser cUser = userService.getUser(sUser.getUserName());
        boolean isNewUser = userService.update(sUser);
        if (isNewUser) {
            List<Claim> defaultClaims = claimsService.getDefaultUserClaims();
            claimsService.updateClaims(sUser.getId(), defaultClaims);
        }

        if (cUser != null) {
            sUser.setId(cUser.getId());
            sUser.setClaims(userService.getClaims(sUser.getId()));
        }

//        SystemUser sUser = new SystemUser();

//        sUser.setId(1L);
//        sUser.setDomainName("user1");
//        sUser.setUserName("user2");
//        sUser.setDisplayName("user3");
//        sUser.setEmail("mail");
//        sUser.setGuid("uuid");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenUtil.generateToken(userDetails, sUser);
    }

    @Override
    public String getAuthToken(JwtRequest request) throws UserNotFoundException, NamingException, ApplicationException {
        SystemUser sUser = new SystemUser();
        sUser.setId(1L);
        sUser.setUserName(request.getUsername());
        sUser.setDomainName("-");
        sUser.setDisplayName("-");
        sUser.setEmail("-");
        sUser.setGuid("-");
        List<Claim> claimList = new ArrayList<>();
        claimList.add(new Claim("lastName", request.getLastName()));
        claimList.add(new Claim("phone", request.getPhone()));
        sUser.setClaims(claimList);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtTokenUtil.generateToken(userDetails, sUser);
    }


    /**
     * ?????????????????? ?????????????????? ???? ldap
     * @param ldapUser ????????????????
     * @param searchResult ?????????????????? ???????????? ???? Ldap
     */
    private void fillAttributes(SystemUser ldapUser, SearchResult searchResult) throws NamingException {
        Attributes attributes = searchResult.getAttributes();
        ldapUser.setUserName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDomainName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDisplayName(attributes.get("displayname").get(0).toString());
        ldapUser.setEmail(attributes.get("mail").get(0).toString());
        ldapUser.setGuid(attributes.get("objectguid").get(0).toString());
    }
}
