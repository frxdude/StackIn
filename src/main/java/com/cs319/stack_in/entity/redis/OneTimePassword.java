package com.cs319.stack_in.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

/**
 * OTPRedis
 *
 * @author Sainjargal Ishdorj
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("OTP")
public class OneTimePassword {

    @Id
    private String id;

    private String otp;

    private int tries;

    //Default is seconds
    @TimeToLive
    private Long timeout;

}
