package com.sf.lottery.web.method;


import com.alibaba.fastjson.JSON;
import com.sf.lottery.common.utils.StrUtils;
import com.sf.lottery.web.weixin.domain.AccessTokenReturn;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/21
 */
public class saveCoupleImg {
    public static void main(String[] args) throws IOException {
        String appId="wx0c7b8ab55037d5ca";
        String appSecret = "2be53e2a455ec83353eb5a8865844e57";
        getWeiXinToken getToken = new getWeiXinToken();

        try {
            //1、加载驱动类
            Class.forName("com.mysql.jdbc.Driver");

            //2、建立连接 建立连接很耗时间 因为连接内部其实包含了Socket对象，是一个远程的连接。比较耗时。
            //后期使用连接池连接
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://118.89.21.105:3306/lottery?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true",
                    "root", "kingzhx9");

            PreparedStatement query=
                    conn.prepareStatement("select * from couple");

            ResultSet rs = query.executeQuery();

            String s = getToken.sendGet("https://api.weixin.qq.com/cgi-bin/token", StrUtils
                    .makeString("grant_type=client_credential&appid=", appId, "&secret=", appSecret));
            AccessTokenReturn accessTokenReturn = JSON.parseObject(s, AccessTokenReturn.class);

            while(rs.next()){
                int user1_sf_num = rs.getInt("user1_sf_num");
                int user2_sf_num = rs.getInt("user2_sf_num");
                String imgName = "D:\\cp\\"+user1_sf_num+""+user2_sf_num + ".jpg";
                System.out.println(user1_sf_num);
                //String cp_img = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessTokenReturn + "&media_id=" + rs.getString("cp_img");
                String cp_img = rs.getString("cp_img");
                printImg(imgName,cp_img);
            }

        }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printImg(String imgName, String imgSrc) {
        URL url = null;
        try {
            url = new URL(imgSrc);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imgName));

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
