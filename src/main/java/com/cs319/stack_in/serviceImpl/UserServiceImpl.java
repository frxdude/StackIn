package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.UserService;
import com.cs319.stack_in.util.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * UserServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserServiceImpl implements UserService {

    /**
     * @param id  Long
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse resetPassword(Long id, HttpServletRequest req) throws BusinessException {
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
