package com.boot.social.api;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public abstract class ApiBinding
{
	protected RestTemplate restTemplate;

	public ApiBinding(String accessToken)
	{
		this.restTemplate = new RestTemplate();
		if (accessToken != null)
		{
			this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
		}
		else
		{
			this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
		}
	}

	private ClientHttpRequestInterceptor getBearerTokenInterceptor(final String accessToken)
	{
		return new ClientHttpRequestInterceptor()
		{
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException
			{
				request.getHeaders().add("Authorization", "Bearer " + accessToken);
				return execution.execute(request, body);
			}
		};
	}

	private ClientHttpRequestInterceptor getNoTokenInterceptor()
	{
		return new ClientHttpRequestInterceptor()
		{
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException
			{
				throw new IllegalStateException("Can't access the Facebook API without an access token");
			}
		};
	}

}