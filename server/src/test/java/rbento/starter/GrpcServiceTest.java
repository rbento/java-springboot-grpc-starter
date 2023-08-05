
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;

public class GrpcServiceTest {
    protected String generatedName = InProcessServerBuilder.generateName();

    protected InProcessServerBuilder serverBuilder =
            InProcessServerBuilder.forName(generatedName).directExecutor();

    protected InProcessChannelBuilder channelBuilder =
            InProcessChannelBuilder.forName(generatedName).directExecutor();

    protected ManagedChannel channel;

    @Rule
    public GrpcCleanupRule grpcCleanupRule = new GrpcCleanupRule();

    @BeforeEach
    public void doBeforeEach() {
        channel = grpcCleanupRule.register(channelBuilder.directExecutor().build());
    }
}
