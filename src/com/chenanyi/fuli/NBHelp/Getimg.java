package com.chenanyi.fuli.NBHelp;

import java.util.ArrayList;
import java.util.List;

import com.chenanyi.fuli.Helper.Download;
import com.chenanyi.fuli.Helper.GetHHH;
import com.chenanyi.fuli.Helper.RegContent;

public class Getimg {
	/**
	 * @author Jervis！
	 * @param cate 类别，6是ZpTp
	 * @param pagecount 爬取的页数
	 * @return List<String> 图片链接
	 */
	public static void Getimg(int cate, int pagecount, String path) {
		int count = 0;
		for (int m = 5000; m <= pagecount; m++) {
			// article-list-id-16-page- 16是小说- RQ小说。
			// 6是图片---ZPTP
			String url = "https://www.t66y.com/htm_data/16/1704/235" + m + ".html";
//			String url = "https://www.t66y.com/htm_data/8/1704/2363379.html";
			String info = GetHHH.getContentFormUrl(url);
			String reg = "(?:((<input src='http).+?(jpg)))";
//            List<String> result = RegContent.GetallURL(RegContent.GetCon(reg, info));
            List<String> result = RegContent.GetCon(reg, info);
//			for (int i = 0; i < result.size(); i++) {
//				String cont = GetHHH.getContentFormUrl(result.get(i));
//				List<String> img_urls = RegContent.GetCon("<img src=\"(.*?)/>",
//						cont);
				for (int j = 0; j < result.size(); j++) {
					count++;
//					String temp = result.get(j).substring(10);
//					int index = temp.indexOf("\"");
//					temp = temp.substring(0, index);
					Download.down(result.get(j), path, count);
					System.out.println(count + "\tOK\t" + result.get(j));
				}
			}
		}
//	}

	/**
	 * @author Jervis！
	 * @param cate 类别，6是ZpTp
	 * @param pagecount 爬取的页数
	 * @return List<String> 图片链接
	 */
	public static List<String> GetOnePageimg(int cate, int page) {
		List<String> img_url = new ArrayList<String>();
		// article-list-id-16-page- 16是小说- RQ小说。
		// 6是图片---ZPTP
		String url = "http://www.laossee.com/article-list-id-" + cate
				+ "-page-" + page + ".html";
		String info = GetHHH.getContentFormUrl(url);
		String reg = "article-show-id-\\d{6}";
		List<String> result = RegContent.GetallURL(RegContent.GetCon(reg,
				info));
		for (int i = 0; i < result.size(); i++) {
			String cont = GetHHH.getContentFormUrl(result.get(i));
			List<String> img_urls = RegContent.GetCon("<img src=\"(.*?)/>",
					cont);
			for (int j = 0; j < img_urls.size(); j++) {
				String temp = img_urls.get(j).substring(10);
				int index = temp.indexOf("\"");
				temp = temp.substring(0, index);
				System.out.println(temp);
				img_url.add(temp);
			}
		}
		return img_url;
	}
}