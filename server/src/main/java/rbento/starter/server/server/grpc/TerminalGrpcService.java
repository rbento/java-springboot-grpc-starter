
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter.server.server.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import rbento.api.v1.CommandRequest;
import rbento.api.v1.CommandResponse;
import rbento.api.v1.TerminalGrpc;

@Slf4j
@GRpcService
public class TerminalGrpcService extends TerminalGrpc.TerminalImplBase {

    @Override
    public void executeCommand(CommandRequest request, StreamObserver<CommandResponse> responseObserver) {
        log.info("executeCommand[request={}]", request);
        if ("ls".equalsIgnoreCase(request.getStatement())) {
            CommandResponse response = CommandResponse.newBuilder()
                    .setCode(200)
                    .setResult("README.md")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Bad command or file name")
                    .asRuntimeException());
        }
    }
}
