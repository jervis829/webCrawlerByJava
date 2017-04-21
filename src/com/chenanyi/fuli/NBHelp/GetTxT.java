package com.chenanyi.fuli.NBHelp;

import java.util.List;

import com.chenanyi.fuli.Helper.GetHHH;
import com.chenanyi.fuli.Helper.RegContent;
import com.chenanyi.fuli.Helper.SaveTxt;

public class GetTxT {
	/**
	 * @author Jervis
	 * @param cate 分类，16是RQ
	 * @param pagecount 爬取得总页数
	 */
	public static void Gettxt(int cate, int pagecount) {
		for (int m = 1; m < pagecount; m++) {
			int count = 0;
			// article-list-id-16-page- 16是小说- RQ小说。
			// 6是图片---ZPTP
			String url = "http://www.laossee.com/article-list-id-" + cate + "-page-" + m
					+ ".html";
			String info = GetHHH.getContentFormUrl(url);
			String reg = "article-show-id-\\d{6}";
			List<String> result = RegContent.GetallURL(RegContent.GetCon(reg,
					info));
			for (int i = 0; i < result.size(); i++) {
				String cont = GetHHH.getContentFormUrl(result.get(i));
				List<String> titles = RegContent.GetCon("<title>.*?</title>",
						cont);
				String reggg = "<br />.*?<br />";
				List<String> Content = RegContent.GetCon(reggg, cont);
				String conts = "";
				for (int f = 0; f < Content.size(); f++) {
					conts += Content.get(f);
				}
				conts = conts.replace("<br />", "");
				for (int j = 0; j < titles.size(); j++) {
					count++;
					String title = RegContent.GetOneCon(">.*?<", titles.get(j));
					title = title.replace("/", "").replace(" ", "");
					title = "txt/"
							+ title.substring(1, title.length() - 1)
									.replace('(', ' ').trim().replace(')', ' ')
									.trim().replace('（', ' ').trim()
									.replace('）', ' ').trim() + ".txt";
					SaveTxt.Sava(title, conts);
					System.out.println("第" + m + "页第" + count + "个" + title);
				}
			}
		}
	}
}