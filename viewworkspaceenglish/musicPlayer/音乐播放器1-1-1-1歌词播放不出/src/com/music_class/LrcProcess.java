package com.music_class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LrcProcess {

	private List<lrcContent> lrcList; //List���ϴ�Ÿ�����ݶ���  
    private lrcContent mLrcContent;     //����һ��������ݶ���  
    /** 
     * �޲ι��캯������ʵ�������� 
     */  
    public LrcProcess() {  
        mLrcContent = new lrcContent();  
        lrcList = new ArrayList<lrcContent>();  
    }  
      
    /** 
     * ��ȡ��� 
     * @param path 
     * @return 
     */  
	public String readLRC(String path) {  
        //����һ��StringBuilder����������Ÿ������  
        StringBuilder stringBuilder = new StringBuilder();  
        File f = new File(path.replace(".mp3", ".lrc"));  
          
        try {  
            //����һ���ļ�����������  
            FileInputStream fis = new FileInputStream(f);  
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");  
            BufferedReader br = new BufferedReader(isr);  
            String s = "";  
            while((s = br.readLine()) != null) {  
                //�滻�ַ�  
                s = s.replace("[", "");  
                s = s.replace("]", "@");  
                  
                //���롰@���ַ�  
                String splitLrcData[] = s.split("@");  
                if(splitLrcData.length > 1) {  
                    mLrcContent.setLrcStr(splitLrcData[1]);  
                      
                    //������ȡ�ø�����ʱ��  
                    int lrcTime = time2Str(splitLrcData[0]);  
                      
                    mLrcContent.setLrcTime(lrcTime);  
                      
                    //��ӽ��б�����  
                    lrcList.add(mLrcContent);  
                      
                    //�´���������ݶ���  
                    mLrcContent = new lrcContent();  
                }  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            stringBuilder.append("ľ�и���ļ�...");  
        } catch (IOException e) {  
            e.printStackTrace();  
            stringBuilder.append("ľ�ж�ȡ�����Ŷ��");  
        }  
        return stringBuilder.toString();  
    }  
    /** 
     * �������ʱ�� 
     * ������ݸ�ʽ���£� 
     * [00:02.32]����Ѹ 
     * [00:03.43]�þò��� 
     * [00:05.22]�������  ���� 
     * @param timeStr 
     * @return 
     */  
    public int time2Str(String timeStr) {  
        timeStr = timeStr.replace(":", ".");  
        timeStr = timeStr.replace(".", "@");  
          
        String timeData[] = timeStr.split("@"); //��ʱ��ָ����ַ�������  
          
        //������֡��벢ת��Ϊ����  
        int minute = Integer.parseInt(timeData[0]);  
        int second = Integer.parseInt(timeData[1]);  
        int millisecond = Integer.parseInt(timeData[2]);  
          
        //������һ������һ�е�ʱ��ת��Ϊ������  
        int currentTime = (minute * 60 + second) * 1000 + millisecond * 10;  
        return currentTime;  
    }  
    public List<lrcContent> getLrcList() {  
        return lrcList;  
    }  
	
}
