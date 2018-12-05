package com.xt.sentense.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.Encoding;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankcs.hanlp.HanLP;
import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.CategoryRepository;
import com.xt.sentense.entity.Scene;
import com.xt.sentense.entity.SceneRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;

/**
 * 句子大全网采集
 * @author XiangTao
 *
 */
@Service
public class JuziDaquanCaijiService {
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	@Autowired
	private SentenseService sentenseService;
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private SceneRepository sceneRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	private static List<NutMap> linkMapStatus;
	private static List<NutMap> linkMap;
	
	static{
		linkMapStatus = new ArrayList<>();
		
		linkMap = new ArrayList<>();
//		linkMap.add(NutMap.NEW().addv("id", 9L).addv("name", "影视经典").addv("url", ""));
//		linkMap.add(NutMap.NEW().addv("id", 10L).addv("name", "小说短句").addv("url", ""));
//		linkMap.add(NutMap.NEW().addv("id", 11L).addv("name", "散文美句").addv("url", "http://www.1juzi.com/juzidaquan/"));
//		linkMap.add(NutMap.NEW().addv("id", 23L).addv("name", "名人名言").addv("url", "http://www.1juzi.com/mingrenmingyan/"));
		linkMap.add(NutMap.NEW().addv("id", 33L).addv("name", "经典语录").addv("url", "http://www.1juzi.com/yuludaquan/"));
		linkMap.add(NutMap.NEW().addv("id", 41L).addv("name", "个性签名").addv("url", "http://www.1juzi.com/qianmingdaquan/"));
		linkMap.add(NutMap.NEW().addv("id", 101L).addv("name", "说说大全").addv("url", "http://www.1juzi.com/shuoshuodaquan/"));
		linkMap.add(NutMap.NEW().addv("id", 102L).addv("name", "诗词鉴赏").addv("url", "http://www.1juzi.com/shijudaquan/"));
		linkMap.add(NutMap.NEW().addv("id", 103L).addv("name", "短信大全").addv("url", "http://www.1juzi.com/duanxindaquan/"));
		
	}
	
	public NutMap getInfo(){
		ThreadPoolExecutor p = (ThreadPoolExecutor) executor;
		String info = "采集进度: " + progressInfo;
		info += "<br>激活的进程数: " + p.getActiveCount();
		return NutMap.NEW().addv("status", linkMapStatus)
				.addv("info", info);
	}
	
	private String progressInfo = "";
	public void start(){
		ThreadPoolExecutor p = (ThreadPoolExecutor) executor;
		progressInfo = "准备开始...";
		if(p.getActiveCount() <= 0){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					progressInfo = "正在采集....";
					linkMapStatus.clear();
					caiji();
				}
			});
		}else{
			progressInfo = "已经启动了,请勿在点击";
		}
	}
	
	int databaseNum = 0; //插入到数据库的数量
	/**
	 * 采集
	 */
	private void caiji(){
		for(int k = 0; k < linkMap.size(); k ++){
			//取出分类信息及采集的Url
			List<NutMap> links = getLinks(linkMap.get(k).getString("url"));
			//添加到状态中去
			NutMap nmLinkStatus = NutMap.NEW().addv("label", linkMap.get(k).getString("name"));
			List<NutMap> nmSceneStatus = new ArrayList<>();
			nmLinkStatus.addv("children", nmSceneStatus);
			linkMapStatus.add(nmLinkStatus);
			
			Long categoryId = linkMap.get(k).getLong("id");
			for(int m = 0; m < links.size(); m ++){
				String scene = links.get(m).getString("scene");
				NutMap nmScene = NutMap.NEW().addv("label", scene);
				List<NutMap> nmUrlStatus = new ArrayList<>();
				nmScene.addv("children", nmUrlStatus);
				nmSceneStatus.add(nmScene);
				
				if("经典语录".equals(scene)){
					continue;
				}
				
				String[] urls = links.get(m).getArray("url", String.class);
				int len = urls.length > 10 ? 10 : urls.length;
				for(int i = 0; i < len; i ++){
					NutMap nmUrl = NutMap.NEW().addv("label", urls[i]);
					nmUrlStatus.add(nmUrl);
					
					Long[] rs = {75L, 104L, 105L};
					try{
						caijiInfo(urls[i], categoryId, scene, rs[R.random(0, 2)]);
					}catch(Exception e){
						
					}
				}
			}
		}
		progressInfo = "采集完成";
		databaseNum = 0;
	}
	
	/**
	 * 获取url列表
	 * @param url
	 * @return scene和url对应的列表
	 */
	private List<NutMap> getLinks(String url){
		System.out.println(url);
		List<NutMap> linkMap = new ArrayList<NutMap>();
		Response resp = Http.get(url);
		Element ele = Jsoup.parse(resp.getContent(Encoding.GB2312));
		Elements as = ele.getElementsByClass("childs").get(0).getElementsByTag("a");
		for(int i = 0; i < as.size(); i ++){
			String scene = as.get(i).attr("title");
			Response r1 = Http.get("http://www.1juzi.com" + as.get(i).attr("href"));
			Elements links = Jsoup.parse(r1.getContent(Encoding.GB2312)).getElementsByClass("alist")
					.get(0).getElementsByTag("li");
			String[] urls = new String[links.size()];
			for(int j = 0; j < links.size(); j ++){
				Element link = links.get(j).getElementsByTag("h3").get(0)
						.getElementsByTag("a").get(0);
				urls[j] = "http://www.1juzi.com" + link.attr("href");
			}
			linkMap.add(NutMap.NEW().addv("scene", scene).addv("url", urls));
		}
		return linkMap;
	}
	
	/**
	 * 采集句子大全网站
	 * url: 路径
	 * categoryId: 分类ID
	 * scene：场景
	 * robotId： 采集机器人ID
	 */
	private void caijiInfo(String url,Long categoryId, String scene, Long robotId){
		Header h = Header.create();
		h.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		h.set("Accept-Encoding", "gzip, deflate");
		h.set("Accept-Language", "zh-CN,zh;q=0.9");
		Response resp = Http.get(url, h, 10000);
		Element ele = Jsoup.parse(resp.getContent(Encoding.GB2312));
		Elements eles = ele.getElementsByClass("content").get(0).getElementsByTag("p");
		for(int i = 1; i < eles.size(); i ++){
			Element e = eles.get(i);
			String str = e.html().split("、")[1].replaceAll("\\&[a-zA-Z]{1,10};", "") //去除类似< >  的字串 
			        .replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "") //去除开始标签及没有结束标签的标签 
			        .replaceAll("</[a-zA-Z]+[1-9]?>", "") //去除结束标签
			        .replaceAll("http://Www.1juzI.coM", "") 
			        .replaceAll("http://", "") 
			        .replaceAll("Www.1juzI.coM", "") 
			        .replaceAll("http", "") 
			        .replaceAll("/", "") 
			        .replace("句子大全", ""); //替换句子大全
			Sentense sentense = new Sentense();
			sentense.setCategoryId(categoryId);
			sentense.setCommentNum(0);
			sentense.setContent(str);
			sentense.setFormSource("<<句子大全网站>>");
			sentense.setScenes(scene);
			sentense.setUserId(robotId);
			List<String> labels = HanLP.extractKeyword(str, R.random(5, 10));
			String label = "";
			for(String la : labels){
				label += ", " + la;
			}
			sentense.setLabels(label);
			sentense.setCreateTime(new Date());
			sentense.setUpdateTime(new Date());
			sentenseService.add(sentense);
			progressInfo = "采集第" + (databaseNum++) + "条";
		}
	}
}
