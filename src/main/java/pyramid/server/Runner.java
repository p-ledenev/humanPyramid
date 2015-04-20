package pyramid.server;

/**
 * Created by DiKey on 19.04.2015.
 */
public class Runner {

    public static void main(String[] args) throws Exception {
        new NettyServer(8080).run();
    }
}
