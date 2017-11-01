package com.nomura.sandeep.chronicle.mina;


import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import static java.nio.charset.Charset.forName;

public class MinaTimeServer {
    public static void main(String[] args) {
        int PORT = 9213;
        try {
            IoAcceptor acceptor = new NioSocketAcceptor();
            IoFilterChainBuilder builder = chain -> {
                chain.addLast("logging", new LoggingFilter());
                chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(forName("UTF-8"))));
            };
            acceptor.setFilterChainBuilder(builder);


        } catch (Exception exp) {

        }
    }
}
