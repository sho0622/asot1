//package com.gips.ourapp;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.gips.ourapp.login.LoginController;
//import com.gips.ourapp.login.LoginForm;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class LoginControllerTest {
//
//	// モック
//	private MockMvc mockMvc;
//
//	@Autowired
//	private LoginController target;
//
//	// テストメソッド(@Testをつけたメソッド)の実行前に実施
//	@Before
//	public void initMocks() {
//		// 初期化
//		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
//	}
//
//	// テストメソッド
//	@Test
//	public void initTest() throws Exception {
//
//		// getでControllerを呼出
//		MvcResult result = mockMvc.perform(get("/")).andDo(print()) // コンソールに取得結果を出力
//				.andExpect(status().isOk()) // HTTPステータスのテスト
//				.andExpect(view().name("login")) // 呼出すViewのテスト
//				.andReturn();
//
//		// Controllerが設定したFormの取り出し
//		LoginForm form = (LoginForm) result.getModelAndView().getModel().get("form");
//
//		// Formの値のテスト (Formの値がNULLであれば成功)
//		assertNull(form.getUserId());
//		assertNull(form.getPassword());
//	}
//
//	// テストメソッド(正常系)
//	@Test
//	public void loginTest1() throws Exception {
//
//		// Controllerに渡すFormの作成
//		LoginForm form = new LoginForm();
//		form.setUserId("id");
//		form.setPassword("pass");
//
//		// postでControllerを呼出
//		MvcResult result = mockMvc.perform((post("/")).flashAttr("loginForm", form)) // Formを渡す
//				.andDo(print()) // コンソールに取得結果を出力
//				.andExpect(status().isOk()) // HTTPステータスのテスト
//				.andExpect(view().name("list")) // 呼出すViewのテスト
//				.andReturn();
//	}
//
//	// テストメソッド(異常系:id未入力)
//	@Test
//	public void loginTest2() throws Exception {
//
//		// Controllerに渡すFormの作成
//		LoginForm form = new LoginForm();
//		form.setUserId("");
//		form.setPassword("pass2");
//
//		// postでControllerを呼出
//		MvcResult result = mockMvc.perform((post("/")).flashAttr("loginForm", form)) // Formを渡す
//				.andDo(print()) // コンソールに取得結果を出力
//				.andExpect(status().isOk()) // HTTPステータスのテスト
//				.andExpect(view().name("login")) // 呼出すViewのテスト
//				.andReturn();
//	}
//
//	// テストメソッド(異常系:パスワード未入力)
//	@Test
//	public void loginTest3() throws Exception {
//
//		// Controllerに渡すFormの作成
//		LoginForm form = new LoginForm();
//		form.setUserId("");
//		form.setPassword("");
//
//		// postでControllerを呼出
//		MvcResult result = mockMvc.perform((post("/")).flashAttr("loginForm", form)) // Formを渡す
//				.andDo(print()) // コンソールに取得結果を出力
//				.andExpect(status().isOk()) // HTTPステータスのテスト
//				.andExpect(view().name("login")) // 呼出すViewのテスト
//				.andReturn();
//	}
//}
