netty的模块：
1、bootstrap
2、buffer
3、channel
4、handler
5、container

TCP/IP的网络模型
应用层，传输层，网络层，链路层


netty server的初始化流程：
1、服务端的初始化需要两个线程池bossGroup和workerGroup，
在bind的时候会从bossGroup中取一个EventLoop作为该端口的监听和获取客户端链接的线程
而workerGroup会作为处理IO读写的线程池。
2、注册channel，netty中通过工厂类来创建channel，server端是ServerBootstrapChannelFactory，client端是BootstrapChannelFactory。
3、配置TCP参数
server端主要配置参数：
backlog：该端口未链接队列和已链接队列长度总和
4、设置handler和childHandler
server端使用的是ServerBootstrap的childHandler()方法，其中的handler是用于NioServerSocketChannel，
而client端使用的是AbstractBootstrap的handler()方法，其中的handler是用于NioSocketChannel。
5、当配置好了之后，在bind方法中，首先进行channel的create、init、register
create：
bossGroup中顺序获取一个EventLoop作为该端口的监听和获取客户端链接的线程
而workerGroup会作为处理IO读写的线程池。
将者两个参数作为入参，通过反射实例化一个channel
init：
首先设置option，tcp的参数
然后设置attrs，channel中我们自定义的参数，key-value，可以用来传递信息
然后将AbstractBootstrap的handler注册到pipeline中，
然后将childOptions、childAttrs、childHandler作为入参，初始化ServerBootstrapAcceptor，并且将ServerBootstrapAcceptor放到pipeline中
6、将NioServerSocketChannel注册到bossGroup中顺序获取的EventLoop的多路复用器上，并且设置监听状态为0-只注册，不监听，
因为到这，整个server还没有全部的启动完成，而netty是采用事件机制，所以此处注册0，后面再改成16.

