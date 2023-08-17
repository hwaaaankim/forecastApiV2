package com.dev.ForecastApiTestJarV2.controller;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.StaticGasProvider;

import com.dev.ForecastApiTestJarV2.contract.ContractWrapperV2;
import com.dev.ForecastApiTestJarV2.service.S3UploadService;
import com.dev.ForecastApiTestJarV2.service.Web3jService;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Autowired
	private S3UploadService s3UploadService;
	
	@Autowired
	private Web3j web3j;
	
	@Value("${infura.API_URL}")
    private String INFURA_API_URL;

    @Value("${metamask.PRIVATE_KEY}")
    private String PRIVATE_KEY;

    @Value("${metamask.CONTRACT_ADDRESS}")
    private String CONTRACT_ADDRESS;
    
    @Value("${metamask.WALLET_ADDRESS}")
    private String WALLET_ADDRESS;

//	@Autowired
//	BasicService basicService;

	@Autowired
	private Web3jService web3jService;

	@RequestMapping(value = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updload(MultipartFile file)
			throws IOException, IllegalArgumentException, FileNotFoundException, java.io.IOException {

		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String now = format.format(today);
//		s3UploadService.upload(file, "imageUpload/" + now);

		return "success";
	}
	
	@RequestMapping(value = "/getEthClientVersionSync")
	public void getEthClientVersionSync() throws java.io.IOException, CipherException, Exception {
		Web3j web3j = Web3j.build(new HttpService("https://polygon-mumbai.infura.io/v3/103a4ca5ca12491ca272983858267a10"));
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		System.out.println(web3ClientVersion.getWeb3ClientVersion());
		BigInteger privateKeyInBT = new BigInteger("02b8db1cc785a22eed9e04563591b8b8f31314139381612130d01c8168276a12", 16);
        Credentials c = Credentials.create(ECKeyPair.create(privateKeyInBT));
        System.out.println(c.getAddress());
        BigInteger gasPrice = Contract.GAS_PRICE;
        BigInteger gasLimit = Contract.GAS_LIMIT;
        StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
        ContractWrapperV2 cw = ContractWrapperV2.load("0x05544cF95Aab590052695A899392D2d72689740B", web3j, c, gasProvider);
        System.out.println(web3jService.getContractName(cw));
        // get the next available nonce
        
//		EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
//				CONTRACT_ADDRESS, DefaultBlockParameterName.LATEST).send();
//		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//		System.out.println(nonce.toString());
	}

	@RequestMapping(value = "/getEthClientVersionASync")
	public void getEthClientVersionASync() throws Exception {
		Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
		System.out.println(web3ClientVersion.getWeb3ClientVersion());
	}

	@RequestMapping(value = "/getEthClientVersionRx")
	public void getEthClientVersionRx() throws Exception {
		Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
		web3.web3ClientVersion().flowable().subscribe(x -> {
			System.out.println(x.getWeb3ClientVersion());
		});

		Thread.sleep(5000);
	}
//
//	@RequestMapping(value = "/getPot")
//	public void getPot() throws InterruptedException, ExecutionException, IOException, java.io.IOException {
//		basicService.getPot();
//	}
//
//	@RequestMapping(value = "/setPot")
//	public void setPot() throws InterruptedException, ExecutionException, IOException, java.io.IOException {
//		basicService.setPot(100);
//	}

//	@RequestMapping("/getBlockNumber")
//    public void getBlockNumber() throws ExecutionException, InterruptedException {
//        EthBlockNumber ethBlockNumber = web3jService.getBlockNumber();
//        long id = ethBlockNumber.getId();
//        BigInteger blockNumber = ethBlockNumber.getBlockNumber();
//
//        log.info("id : {}", id);
//        log.info("blockNumber : {}", blockNumber);
//    }
//
//	@RequestMapping("/getEthAccounts")
//    public void getEthAccounts() throws ExecutionException, InterruptedException {
//        EthAccounts ethAccounts = web3jService.getEthAccounts();
//        List<String> accounts = ethAccounts.getAccounts();
//
//        log.info("accounts : {}", accounts);
//    }
//
//	@RequestMapping("/getTransactionCount")
//    public void getTransactionCount() throws ExecutionException, InterruptedException {
//        EthGetTransactionCount ethGetTransactionCount = web3jService.getTransactionCount();
//        BigInteger transactionCount = ethGetTransactionCount.getTransactionCount();
//
//        log.info("transactionCount : {}", transactionCount);
//    }
//
//	@RequestMapping("/getEthBalance")
//    public void getEthBalance() throws ExecutionException, InterruptedException {
//        EthGetBalance ethGetBalance = web3jService.getEthBalance();
//        BigInteger balance = ethGetBalance.getBalance();
//
//        log.info("balance : {}", balance);
//    }
//
//	@RequestMapping("/getContractName")
//    public void getContractName() throws Exception {
//        String contractName = web3jService.getContractName();
//
//        log.info("contractName : {}", contractName);
//    }
//
//	@RequestMapping("/currentCount")
//    public void currentCount() throws Exception {
//        BigInteger currentCount = web3jService.currentCount();
//
//        log.info("currentCount : {}", currentCount);
//    }
//
//	@RequestMapping("/nftCreate")
//    public void nftCreate() throws Exception {
//        log.info("start time : {}", LocalDateTime.now());
//
//        TransactionReceipt transactionReceipt = web3jService.nftCreate();
//        log.info("transactionReceipt : {}", transactionReceipt);
//
//        log.info("end time : {}", LocalDateTime.now());
//    }
//
//	@RequestMapping("/transferEventFlowable")
//    public void transferEventFlowable() throws Exception {
//        web3jService.transferEventFlowable();
//    }
}
