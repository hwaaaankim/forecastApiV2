package com.dev.ForecastApiTestJarV2.config;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.StaticGasProvider;

import com.dev.ForecastApiTestJarV2.contract.ContractWrapper;

import org.web3j.crypto.Credentials;

@Configuration
public class Web3jConfig {

	@Value("${infura.API_URL}")
    private String INFURA_API_URL;

    @Value("${metamask.PRIVATE_KEY}")
    private String PRIVATE_KEY;

    @Value("${metamask.CONTRACT_ADDRESS}")
    private String CONTRACT_ADDRESS;

    @Bean
    public Web3j web3j() {
    	System.out.println("web3j");
        return Web3j.build(new HttpService(INFURA_API_URL));
    }

    @Bean
    public Credentials credentials() {
    	System.out.println("credentials");
        BigInteger privateKeyInBT = new BigInteger(PRIVATE_KEY, 16);
        return Credentials.create(ECKeyPair.create(privateKeyInBT));
    }

    @Bean
    public ContractWrapper contractWrapper() {
        BigInteger gasPrice = Contract.GAS_PRICE;
        BigInteger gasLimit = Contract.GAS_LIMIT;
        StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);

        return ContractWrapper.load(CONTRACT_ADDRESS, web3j(), credentials(), gasProvider);
    }
}
