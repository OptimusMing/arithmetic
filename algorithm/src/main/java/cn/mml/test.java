package cn.mml;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.DecimalFormat;

public class test {
//    public static void main(String[] args) {
//        for(int i=0;i<100;i++){
//            System.out.println(UUID());
//        }
//
//    }
//    public static int UUID()
//    {
//
//        java.util.Random r=new java.util.Random();
////如生成的随机位数不足6位则自动加零补充
//        DecimalFormat g=new DecimalFormat("1000000");
////返回时间增量+随机数的序列
//        String s = String.format("%s%s",System.currentTimeMillis(),g.format(r.nextInt(1000000)));
//        return Integer.parseInt(s.substring(11));
//    }

    public static void main(String[] args) {
        String s ="{\n" +
                "    \"resultCode\": \"1\",\n" +
                "    \"resultInfo\": \"处理成功\",\n" +
                "    \"customers\": [\n" +
                "        {\n" +
                "            \"ecifId\": \"10040333037622\",\n" +
                "            \"realName\": \"袁首创\",\n" +
                "            \"gender\": \"M\",\n" +
                "            \"birthDay\": \"1994-11-26\",\n" +
                "            \"certiType\": \"1\",\n" +
                "            \"certiCode\": \"131182199411266219\",\n" +
                "            \"policyList\": [\n" +
                "                {\n" +
                "              \n" +
                "               \n" +
                "               \n" +
                "               \"isRenewalProductOfferRenewal\": \"N\"\n" +
                "               \"policyCode\": \"1234567890\"\n" +
                "              \n" +
                "              \n" +
                "               }\n" +
                "            ]\n" +
                "             \n" +
                "            \n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        //System.out.println(jsonObject.getJSONObject("customers").getJSONObject("policyList").get("isRenewalProductOfferRenewal"));

        //String[] customersArr
        JSONArray jsonArray = jsonObject.getJSONArray("customers");
        String ss="";
        if(jsonArray.stream().iterator().hasNext()){

            ss =jsonArray.stream().iterator().next().toString();
        }
        System.out.println(ss);
        //jsonObject= jsonObject.getJSONObject("policyList");
        JSONObject jsonObject2 = JSONObject.parseObject(ss);
        JSONArray jsonArray2 = jsonObject2.getJSONArray("policyList");
        if(jsonArray2.stream().iterator().hasNext()){

            ss =jsonArray2.stream().iterator().next().toString();
        }
        System.out.println(ss);
        JSONObject jsonObject3 = JSONObject.parseObject(ss);
        jsonObject3.getString("isRenewalProductOfferRenewal");
        System.out.println(jsonObject3.getString("isRenewalProductOfferRenewal"));
    }
}
