
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import io.grpc.StatusRuntimeException;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rbento.api.v1.CommandRequest;
import rbento.api.v1.CommandResponse;
import rbento.api.v1.TerminalGrpc;

public class TerminalEndpointTest extends GrpcServiceTest {
    @BeforeEach
    public void beforeEach() throws IOException {
        grpcCleanupRule.register(serverBuilder
                .directExecutor()
                .addService(new TerminalEndpoint())
                .build()
                .start());
    }

    @Test
    public void testExecuteCommandLsOk() throws IOException {
        CommandRequest request = CommandRequest.newBuilder().setStatement("ls").build();
        TerminalGrpc.TerminalBlockingStub stub = TerminalGrpc.newBlockingStub(channel);

        CommandResponse response = stub.executeCommand(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getResult()).isEqualTo("README.md");
    }

    @Test
    public void testExecuteCommandLsError() throws IOException {
        CommandRequest request =
                CommandRequest.newBuilder().setStatement("wrong").build();
        TerminalGrpc.TerminalBlockingStub stub = TerminalGrpc.newBlockingStub(channel);

        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> stub.executeCommand(request))
                .withMessageContaining("Bad command or file name");
    }
}
