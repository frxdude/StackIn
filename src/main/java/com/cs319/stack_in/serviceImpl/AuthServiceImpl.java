package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.AuthService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    /**
     * @param req          servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse login(AuthRequest authRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + authRequest.toString() + "]");
            Logger.info(this.getClass().getName(), "[login][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req          servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse register(HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + "" + "]");

            Logger.info(this.getClass().getName(), "[login][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req          servlet request
     * @return {@link AuthResponse}
     * @throws TokenException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse exchangeToken(String refreshToken, HttpServletRequest req) throws TokenException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + "" + "]");
            Logger.info(this.getClass().getName(), "[login][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
