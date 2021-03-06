package com.github.taoroot.cloud.auth.service;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	private final RedisConnectionFactory connectionFactory;

	@Setter
	private String prefix = SecurityConstants.OAUTH_CODE_PREFIX;

	@Setter
	private RedisTokenStoreSerializationStrategy serializationStrategy = new JdkSerializationStrategy();

	@Override
	protected void store(String code, OAuth2Authentication authentication) {
		@Cleanup
		RedisConnection connection = connectionFactory.getConnection();
		connection.set(serializationStrategy.serialize(prefix + code), serializationStrategy.serialize(authentication));
	}

	@Override
	protected OAuth2Authentication remove(String code) {
		@Cleanup
		RedisConnection connection = connectionFactory.getConnection();

		byte[] key = serializationStrategy.serialize(prefix + code);
		byte[] value = connection.get(key);

		if (value == null) {
			return null;
		}

		OAuth2Authentication oAuth2Authentication = serializationStrategy.deserialize(value,
				OAuth2Authentication.class);

		connection.del(key);
		return oAuth2Authentication;
	}

}
