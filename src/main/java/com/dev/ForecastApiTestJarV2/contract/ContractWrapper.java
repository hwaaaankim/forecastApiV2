//package com.dev.ForecastApiTestJarV2.contract;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//import org.web3j.abi.EventEncoder;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Bool;
//import org.web3j.abi.datatypes.Event;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.Utf8String;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.RemoteCall;
//import org.web3j.protocol.core.RemoteFunctionCall;
//import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.BaseEventResponse;
//import org.web3j.protocol.core.methods.response.Log;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.tx.Contract;
//import org.web3j.tx.TransactionManager;
//import org.web3j.tx.gas.ContractGasProvider;
//
//import io.reactivex.Flowable;
//import io.reactivex.functions.Function;
//
//@SuppressWarnings("rawtypes")
//public class ContractWrapper extends Contract{
//	
//	public static final String BINARY = "0x608060405234801561001057600080fd5b506004361061010b5760003560e01c806370a08231116100a257806395d89b411161007157806395d89b411461020f578063a457c2d714610217578063a9059cbb1461022a578063dd62ed3e1461023d578063f2fde38b1461027657600080fd5b806370a08231146101ad578063715018a6146101d65780638456cb59146101de5780638da5cb5b146101e657600080fd5b8063313ce567116100de578063313ce5671461017657806339509351146101855780633f4ba83a146101985780635c975abb146101a257600080fd5b806306fdde0314610110578063095ea7b31461012e57806318160ddd1461015157806323b872dd14610163575b600080fd5b610118610289565b6040516101259190610adf565b60405180910390f35b61014161013c366004610b49565b61031b565b6040519015158152602001610125565b6002545b604051908152602001610125565b610141610171366004610b73565b610335565b60405160128152602001610125565b610141610193366004610b49565b610359565b6101a0610398565b005b60055460ff16610141565b6101556101bb366004610baf565b6001600160a01b031660009081526020819052604090205490565b6101a06103db565b6101a0610415565b60055461010090046001600160a01b03166040516001600160a01b039091168152602001610125565b61011861044d565b610141610225366004610b49565b61045c565b610141610238366004610b49565b6104ee565b61015561024b366004610bd1565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b6101a0610284366004610baf565b6104fc565b60606003805461029890610c04565b80601f01602080910402602001604051908101604052809291908181526020018280546102c490610c04565b80156103115780601f106102e657610100808354040283529160200191610311565b820191906000526020600020905b8154815290600101906020018083116102f457829003601f168201915b5050505050905090565b6000336103298185856105a2565b60019150505b92915050565b6000336103438582856106c6565b61034e858585610758565b506001949350505050565b3360008181526001602090815260408083206001600160a01b03871684529091528120549091906103299082908690610393908790610c3e565b6105a2565b6005546001600160a01b036101009091041633146103d15760405162461bcd60e51b81526004016103c890610c5f565b60405180910390fd5b6103d9610931565b565b6005546001600160a01b0361010090910416331461040b5760405162461bcd60e51b81526004016103c890610c5f565b6103d960006109c4565b6005546001600160a01b036101009091041633146104455760405162461bcd60e51b81526004016103c890610c5f565b6103d9610a1e565b60606004805461029890610c04565b3360008181526001602090815260408083206001600160a01b0387168452909152812054909190838110156104e15760405162461bcd60e51b815260206004820152602560248201527f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f77604482015264207a65726f60d81b60648201526084016103c8565b61034e82868684036105a2565b600033610329818585610758565b6005546001600160a01b0361010090910416331461052c5760405162461bcd60e51b81526004016103c890610c5f565b6001600160a01b0381166105915760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016103c8565b61059a816109c4565b50565b505050565b6001600160a01b0383166106045760405162461bcd60e51b8152602060048201526024808201527f45524332303a20617070726f76652066726f6d20746865207a65726f206164646044820152637265737360e01b60648201526084016103c8565b6001600160a01b0382166106655760405162461bcd60e51b815260206004820152602260248201527f45524332303a20617070726f766520746f20746865207a65726f206164647265604482015261737360f01b60648201526084016103c8565b6001600160a01b0383811660008181526001602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b6001600160a01b03838116600090815260016020908152604080832093861683529290522054600019811461075257818110156107455760405162461bcd60e51b815260206004820152601d60248201527f45524332303a20696e73756666696369656e7420616c6c6f77616e636500000060448201526064016103c8565b61075284848484036105a2565b50505050565b6001600160a01b0383166107bc5760405162461bcd60e51b815260206004820152602560248201527f45524332303a207472616e736665722066726f6d20746865207a65726f206164604482015264647265737360d81b60648201526084016103c8565b6001600160a01b03821661081e5760405162461bcd60e51b815260206004820152602360248201527f45524332303a207472616e7366657220746f20746865207a65726f206164647260448201526265737360e81b60648201526084016103c8565b610829838383610a99565b6001600160a01b038316600090815260208190526040902054818110156108a15760405162461bcd60e51b815260206004820152602660248201527f45524332303a207472616e7366657220616d6f756e7420657863656564732062604482015265616c616e636560d01b60648201526084016103c8565b6001600160a01b038085166000908152602081905260408082208585039055918516815290812080548492906108d8908490610c3e565b92505081905550826001600160a01b0316846001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8460405161092491815260200190565b60405180910390a3610752565b60055460ff1661097a5760405162461bcd60e51b815260206004820152601460248201527314185d5cd8589b194e881b9bdd081c185d5cd95960621b60448201526064016103c8565b6005805460ff191690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa335b6040516001600160a01b03909116815260200160405180910390a1565b600580546001600160a01b03838116610100818102610100600160a81b031985161790945560405193909204169182907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b60055460ff1615610a645760405162461bcd60e51b815260206004820152601060248201526f14185d5cd8589b194e881c185d5cd95960821b60448201526064016103c8565b6005805460ff191660011790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a2586109a73390565b60055460ff161561059d5760405162461bcd60e51b815260206004820152601060248201526f14185d5cd8589b194e881c185d5cd95960821b60448201526064016103c8565b600060208083528351808285015260005b81811015610b0c57858101830151858201604001528201610af0565b506000604082860101526040601f19601f8301168501019250505092915050565b80356001600160a01b0381168114610b4457600080fd5b919050565b60008060408385031215610b5c57600080fd5b610b6583610b2d565b946020939093013593505050565b600080600060608486031215610b8857600080fd5b610b9184610b2d565b9250610b9f60208501610b2d565b9150604084013590509250925092565b600060208284031215610bc157600080fd5b610bca82610b2d565b9392505050565b60008060408385031215610be457600080fd5b610bed83610b2d565b9150610bfb60208401610b2d565b90509250929050565b600181811c90821680610c1857607f821691505b602082108103610c3857634e487b7160e01b600052602260045260246000fd5b50919050565b8082018082111561032f57634e487b7160e01b600052601160045260246000fd5b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260408201526060019056fea26469706673582212206e45ed92ea824c6bd62ad48658da750e073c24239e461df337eb4c88d09d03dc64736f6c63430008120033";
//
//    public static final String FUNC_APPROVE = "approve";
//
//    public static final String FUNC_BALANCEOF = "balanceOf";
//
//    public static final String FUNC_GETAPPROVED = "getApproved";
//
//    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";
//
//    public static final String FUNC_NAME = "name";
//
//    public static final String FUNC_OWNEROF = "ownerOf";
//
//    public static final String FUNC_safeTransferFrom = "safeTransferFrom";
//
//    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";
//
//    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";
//
//    public static final String FUNC_SYMBOL = "symbol";
//
//    public static final String FUNC_TRANSFERFROM = "transferFrom";
//
//    public static final String FUNC_TOKENURI = "tokenURI";
//
//    public static final String FUNC_CREATE = "create";
//
//    public static final Event APPROVAL_EVENT = new Event("Approval", 
//            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
//    ;
//
//    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
//            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
//    ;
//
//    public static final Event TRANSFER_EVENT = new Event("Transfer", 
//            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
//    ;
//
//    protected static final HashMap<String, String> _addresses;
//
//    static {
//        _addresses = new HashMap<String, String>();
//    }
//
//    @Deprecated
//    protected ContractWrapper(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    protected ContractWrapper(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
//    }
//
//    @Deprecated
//    protected ContractWrapper(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    protected ContractWrapper(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
//    }
//
//    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
//        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
//        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
//            @Override
//            public ApprovalEventResponse apply(Log log) {
//                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
//                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//                typedResponse.log = log;
//                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
//                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
//                return typedResponse;
//            }
//        });
//    }
//
//    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
//        return approvalEventFlowable(filter);
//    }
//
//    public static List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
//        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
//        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
//            @Override
//            public ApprovalForAllEventResponse apply(Log log) {
//                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
//                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
//                typedResponse.log = log;
//                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
//                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
//                return typedResponse;
//            }
//        });
//    }
//
//    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
//        return approvalForAllEventFlowable(filter);
//    }
//
//    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
//        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            TransferEventResponse typedResponse = new TransferEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
//        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
//            @Override
//            public TransferEventResponse apply(Log log) {
//                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
//                TransferEventResponse typedResponse = new TransferEventResponse();
//                typedResponse.log = log;
//                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
//                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
//                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
//                return typedResponse;
//            }
//        });
//    }
//
//    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
//        return transferEventFlowable(filter);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_APPROVE, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
//                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//    }
//
//    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
//        return executeRemoteCallSingleValueReturn(function, String.class);
//    }
//
//    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner), 
//                new org.web3j.abi.datatypes.Address(operator)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
//        return executeRemoteCallSingleValueReturn(function, Boolean.class);
//    }
//
//    public RemoteFunctionCall<String> name() {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
//                Arrays.<Type>asList(), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//        return executeRemoteCallSingleValueReturn(function, String.class);
//    }
//
//    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
//        return executeRemoteCallSingleValueReturn(function, String.class);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_safeTransferFrom, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
//                new org.web3j.abi.datatypes.Address(to), 
//                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_safeTransferFrom, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
//                new org.web3j.abi.datatypes.Address(to), 
//                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
//                new org.web3j.abi.datatypes.DynamicBytes(data)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_SETAPPROVALFORALL, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(operator), 
//                new org.web3j.abi.datatypes.Bool(approved)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
//        return executeRemoteCallSingleValueReturn(function, Boolean.class);
//    }
//
//    public RemoteFunctionCall<String> symbol() {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
//                Arrays.<Type>asList(), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//        return executeRemoteCallSingleValueReturn(function, String.class);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_TRANSFERFROM, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
//                new org.web3j.abi.datatypes.Address(to), 
//                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//        return executeRemoteCallSingleValueReturn(function, String.class);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> create(String to, String tokenURI) {
//        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
//                FUNC_CREATE, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
//                new org.web3j.abi.datatypes.Utf8String(tokenURI)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    @Deprecated
//    public static ContractWrapper load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return new ContractWrapper(contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    @Deprecated
//    public static ContractWrapper load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return new ContractWrapper(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    public static ContractWrapper load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new ContractWrapper(contractAddress, web3j, credentials, contractGasProvider);
//    }
//
//    public static ContractWrapper load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return new ContractWrapper(contractAddress, web3j, transactionManager, contractGasProvider);
//    }
//
//    public static RemoteCall<ContractWrapper> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(ContractWrapper.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }
//
//    public static RemoteCall<ContractWrapper> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(ContractWrapper.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }
//
//    @Deprecated
//    public static RemoteCall<ContractWrapper> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(ContractWrapper.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
//    }
//
//    @Deprecated
//    public static RemoteCall<ContractWrapper> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(ContractWrapper.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
//    }
//
//    protected String getStaticDeployedAddress(String networkId) {
//        return _addresses.get(networkId);
//    }
//
//    public static String getPreviouslyDeployedAddress(String networkId) {
//        return _addresses.get(networkId);
//    }
//
//    public static class ApprovalEventResponse extends BaseEventResponse {
//        public String owner;
//
//        public String approved;
//
//        public BigInteger tokenId;
//    }
//
//    public static class ApprovalForAllEventResponse extends BaseEventResponse {
//        public String owner;
//
//        public String operator;
//
//        public Boolean approved;
//    }
//
//    public static class TransferEventResponse extends BaseEventResponse {
//        public String from;
//
//        public String to;
//
//        public BigInteger tokenId;
//    }
//}
