package com.sf.lottery.web.couple;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.vo.CpGiftVo;
import com.sf.lottery.service.CoupleService;
import com.sf.lottery.web.weixin.GetWeixinAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/14
 */
@Controller
public class CoupleController {
    @Autowired
    private CoupleService coupleService;
    @Autowired
    private GetWeixinAccessToken getWeixinAccessToken;

    @ResponseBody
    @RequestMapping(value = "/couple/printCouples", method = RequestMethod.GET)
    public JsonResult<Boolean> printCouples(){
        List<CpGiftVo> coupleList = coupleService.getAllCouple();
        JsonResult<Boolean> result = new JsonResult<>();
        for(CpGiftVo cv:coupleList){
            try {
                String imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + getWeixinAccessToken.getWXAccessToken() + "&media_id=" + cv.getCpImg();
                String imgName = ""+cv.getUser1SfNum()+cv.getUser1SfName() + cv.getUser2SfNum()+cv.getUser2SfName();
                //String imgName = "D:\\test.jpg";
                //String imgSrc = "http://wx.qlogo.cn/mmopen/PiajxSqBRaEKmcUfYbQkqebFgX3TKWjwWvW9yhg2mocmJ7GDapW0Hbd4MSIibTVY6keQnq8cEZLX49j0ZFWeicwpw/0";
                this.printImg(imgName,imgSrc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.setData(true);
        return result;
    }

    public void printImg(String imgName, String imgSrc) {
        URL url = null;
        try {
            url = new URL(imgSrc);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = imgName + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));

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
