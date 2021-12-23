package cn.mml;

import io.netty.util.NetUtil;



public class snowFlakemm {

    //永远不要去变动
    private final static long START_STMP = 1572574984045L;//起始时间戳

    /*
    每一部分最大值
     */
    private final static long SEQUENCE_BIT = 12L;//序列号占用的位数
    private final static long MACHINE_BIT = 5L;//机器标识占用的位数
    private final static long DATACENTER_BIT = 5L;//数据中心占用的位数
    /*
    位移量
     */
    private final static long TIMESTMP_LEFT = SEQUENCE_BIT+MACHINE_BIT+DATACENTER_BIT;//时间戳位移量
    private final static long MACHINE_LEFT = SEQUENCE_BIT;//机器标识位移量
    private final static long DATACENTER_LEFT = SEQUENCE_BIT+MACHINE_BIT;//数据中心位移量
    /*
        每一部分最大值
         */
    private final static long MAX_SEQUENCE = -1L^(-1L << SEQUENCE_BIT);//序列号占用的位数
    private final static long MAX_MACHINE = -1L^(-1L << MACHINE_BIT);//机器标识占用的位数
    private final static long MAX_DATACENTER = -1L^(-1L << DATACENTER_BIT);//数据中心占用的位数



    long lateStmp =-1L;
    long squence =0L;
    long datacenterID;
    long machineID;
    /*
    生产下一个ID
     */
    public synchronized long nextId(){
        long currStmp = getNewStmp();
        if(currStmp < lateStmp){

        }

        //都在同一毫秒里
        if(currStmp == lateStmp){
            //相同毫秒内序列号自增
            squence = (squence+1)&MAX_SEQUENCE;
            //同一毫秒，序列数已经达到最大
            if(squence ==0L){
                currStmp = getNextMill();
            }
        }else{
            //不同毫秒序列号置为0
            squence =0L;
        }
        lateStmp = currStmp;

        //返回拼装
        //1位  符号位 0 固定不变 变成1 则为负数
        //41位 时间戳
        //10位工作进程  数据中心+ 机器标识
        //12位 序列号
        return (currStmp-START_STMP) << TIMESTMP_LEFT//时间戳部分
                |(datacenterID << DATACENTER_LEFT)//数据中心部分
                |(machineID << MACHINE_LEFT)//机器标识部分
                |squence;//序列号部分
    }

    private long getNextMill() {
        long mill = getNewStmp();
        while(mill<lateStmp){
            mill = getNewStmp();
        }
        return mill;
    }

    private long getNewStmp() {
        return System.currentTimeMillis();
    }



    public snowFlakemm(long machineID,long datacenterID){
        if(machineID>MAX_MACHINE||machineID<0){
            throw new IllegalArgumentException(
                    String.format(
                            "machineID can`t be greater than %d or less than 0"
                            ,MAX_MACHINE));
        }
        if(datacenterID>MAX_DATACENTER||datacenterID<0){
            throw new IllegalArgumentException(
                    String.format(
                            "datacenterID can`t be greater than %d or less than 0"
                            ,MAX_DATACENTER));
        }
        this.machineID =machineID;
        this.datacenterID =datacenterID;
    }



    //获取电脑的workid
    //public int workid = NetUtil.ipv4AddressToInt(NetUtil.LOCALHOST4);
    //int workid = NetUtil.ipv4
    public static void main(String[] args) {
        int workid2 = NetUtil.ipv4AddressToInt(NetUtil.LOCALHOST4);
        System.out.println(workid2);
    }
}
