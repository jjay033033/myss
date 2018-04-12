/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.lmoon;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.lmoon.mail.MailUtil;


@Controller
@SpringBootApplication
@ServletComponentScan
public class Main {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Main.class, args);
//			ShadowsUpdate.start();
		} catch (Exception e) {
			e.printStackTrace();			
			MailUtil.asyncSendErrorEmail(e);
		}
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/test")
	@ResponseBody
	String test(Map<String, Object> map) {
		System.err.println("Hello, test!");
		// map.put("list", "");
		return "Hello, test!";
		// return "<a
		// href=\"ss://cmM0LW1kNTo3MTk3MzU1NkAxMzguNjguNjEuNDI6MjM0NTYK\">hello
		// world!</a>";
	}

	@RequestMapping("/test2")
	@ResponseBody
	String test2(String cookie) {
		System.err.println("Hello, test2!!!");
		System.err.println("Hello, test2!!!!!");
		try {
//			String a = URLDecoder.decode(new String(Base64Coder.decodeBase64(cookie)), "utf-8");
			// String b = URLDecoder.decode(new
			// String(Base64Coder.decodeBase64(cookie),"utf-8"), "utf-8");
			// String c = new String(Base64Coder.decodeBase64(cookie),"utf-8");
			return "Hello, test2!!!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MailUtil.asyncSendErrorEmail(e);
			return e.getMessage();
		}
	}


}
