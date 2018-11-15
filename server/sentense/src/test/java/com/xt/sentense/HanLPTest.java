package com.xt.sentense;

import org.nutz.lang.random.R;

import com.hankcs.hanlp.HanLP;

import junit.framework.TestCase;

public class HanLPTest extends TestCase{
	public void extractKeyword(){
		System.out.println(HanLP.extractKeyword("就算生活给我们再多失望，我们也要坚持梦想", R.random(5, 10)));
	}
}
