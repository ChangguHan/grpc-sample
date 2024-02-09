package org.example.client;

import org.chb.examples.lib.HelloReply;
import org.chb.examples.lib.HelloRequest;
import org.chb.examples.lib.SimpleGrpc.SimpleBlockingStub;
import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GrpcClientService {
    @GrpcClient("test")
    private SimpleBlockingStub simpleStub;

    @PostConstruct
    public void init() {
        log.info(sendMessage());
    }

    public String sendMessage() {
        try {
            HelloReply response = simpleStub.sayHello(HelloRequest.newBuilder().setName("TEST from client").build());
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}

