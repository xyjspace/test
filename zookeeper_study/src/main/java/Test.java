import org.apache.zookeeper.*;

import java.util.List;

/**
 * Created by hasee on 2017/5/17.
 */
public class Test {

    //会话超时时间
    private static final int SESSION_TIMEOUT = 30000;

    ZooKeeper zooKeeper;
    Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.toString());
        }
    };

    //初始化zookeeper实例
    private void createZKinstance() throws Exception{
        zooKeeper = new ZooKeeper("192.168.184.9:2181", SESSION_TIMEOUT, watcher);
    }

    private void zkOperations()throws Exception{
        System.out.println("创建节点");
        zooKeeper.create("/xyj","11000".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("huo获取节点数据");
        System.out.println(new String(zooKeeper.getData("/xyj",false,null)));
        System.out.println("修改节点数据");
        zooKeeper.setData("/xyj","21000".getBytes(),-1);
        System.out.println("查看是否修改成功");
        System.out.println(new String(zooKeeper.getData("/xyj",false,null)));

        System.out.println("添加子节点");
        for(int i=0;i<3;i++){
            zooKeeper.create("/xyj/"+"child"+ i+1,("child"+i+1).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        System.out.println("查看所有子节点");
        List<String> list = zooKeeper.getChildren("/xyj",false);
        for (String i: list) {
            System.out.println("子节点"+i);
        }
        System.out.println("删除所有子节点");
        for (String child:list) {
            zooKeeper.delete("/xyj/"+child,-1);
        }
        System.out.println("删除节点");
        zooKeeper.delete("/xyj",-1);
        System.out.println("查看节点是否被删除");
        System.out.println(zooKeeper.exists("/xyj",false));

    }

    private void zkClose() throws Exception{
        zooKeeper.close();
    }

    public static void main(String[] args) throws Exception{
        Test test = new Test();
        test.createZKinstance();
        test.zkOperations();
        test.zkClose();
    }
}
