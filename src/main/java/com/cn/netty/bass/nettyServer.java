package com.cn.netty.bass;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class nettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组，bossGroup 和workerGroup ,含有的NioEventLoop的个数默认为cpu核数的两倍
        //bossGroup只是处理连接请求，真正的客户端处理会交给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try{


            //创建服务器启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程配置参数
            bootstrap.group(bossGroup,workerGroup)//设置两个线程组
                    //使用NioServerSocketChannel作为服务器通道实现
                    .channel(NioServerSocketChannel.class)
                    //初始化服务器连接队列大小，服务器端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端链接
                    //多个客户端同时来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //对workerGroup的SocketChannel设置处理器
                            ch.pipeline().addLast( new NettyServerHandler());
                        }
                    });
            System.out.println("netty server start .....");
            //绑定一个端口并同步，生成一个channlFuture 异步对象，通过isDone()等方法判断异步事件的执行情况
            //启动服务器并绑定端口，bind是异步操作sync方法是等待异步操作执行完毕
                ChannelFuture cf = bootstrap.bind(9000).sync();

                //给cf注册监听器，监听我们关心的事件
//                cf.addListener(
//                        new ChannelFutureListener() {
//                            @Override
//                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                                if(cf.isSuccess()){
//                                    System.out.println("监听端口成功");
//                                }else{
//                                    System.out.println("监听端口失败");
//                                }
//                            }
//                        }
//                );

                //等待服务端监听端口关闭，closeFuture 是异步操作
                //通过sync方法同步等待通道关闭处理完毕这里会阻塞等待通道关闭完成，内部调用的是Object 的wait() 方法
                cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
